package in.handyman.process.onethread

import com.typesafe.scalalogging.LazyLogging
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import in.handyman.command.CommandProxy
import in.handyman.util.ParameterisationEngine
import in.handyman.util.ResourceAccess
import in.handyman.HandymanException
import in.handyman.audit.AuditService
import in.handyman.config.ConfigurationService
import java.sql.ResultSet
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.CountDownLatch
import java.util.Collections
import net.sf.jsqlparser.parser.CCJSqlParserUtil
import net.sf.jsqlparser.statement.select.Select
import net.sf.jsqlparser.statement.select.PlainSelect
import net.sf.jsqlparser.statement.insert.Insert
import scala.collection.mutable.HashSet
import java.sql.ResultSetMetaData
import java.util.concurrent.atomic.AtomicInteger

/**
 * TODO - Still need to add more rich ness to audit trail with respect to statement warnings
 */
class CopyDataAction extends in.handyman.command.Action with LazyLogging {

  val rowQueue: BlockingQueue[Row] = new LinkedBlockingDeque[Row];
  val executor: ExecutorService = Executors.newCachedThreadPool;
  val detailMap = new java.util.HashMap[String, String]
  var rowsProcessed: AtomicInteger = new AtomicInteger(0)

  def execute(context: in.handyman.command.Context, action: in.handyman.dsl.Action, actionId: Integer): in.handyman.command.Context = {

    //Setting up the proxy for retrieving configuration for the macro
    val copyDataAsIs: in.handyman.dsl.Copydata = action.asInstanceOf[in.handyman.dsl.Copydata]
    val copyData: in.handyman.dsl.Copydata = CommandProxy.createProxy(copyDataAsIs, classOf[in.handyman.dsl.Copydata], context)

    //Retrieving the global config map for default value
    val configMap = ConfigurationService.getGlobalconfig()

    val instanceId = context.getValue("process-id")

    val name = copyData.getName

    val source = {
      if (!copyData.getSource.trim.isEmpty())
        copyData.getSource.trim
      else
        throw new HandymanException("source data source cannot be empty for copydata for " + name)
    }

    val target = {
      if (!copyData.getTo.trim.isEmpty())
        copyData.getTo.trim
      else
        throw new HandymanException("target data source cannot be empty for copydata for " + name)
    }

    val fetchSize: Int = {
      if (copyData.getFetchBatchSize.isValidInt)
        copyData.getFetchBatchSize
      else {
        configMap.getOrElse(Constants.READSIZE, Constants.DEFAULT_READ_SIZE).toInt
      }
    }

    val writeSize = {
      if (copyData.getWriteBatchSize.isValidInt)
        copyData.getWriteBatchSize
      else {
        configMap.getOrElse(Constants.WRITESIZE, Constants.DEFAULT_WRITE_SIZE).toInt
      }
    }

    val threadCount: Int = {
      if (copyData.getWriteThreadCount.isValidInt)
        copyData.getWriteThreadCount
      else {
        configMap.getOrElse(Constants.WRITERTHREAD, Constants.DEFAULT_WRITER_COUNT).toInt
      }
    }

    //retrieving the insert into sql statement
    val insertStatementAsIs = copyData.getValue
    val insertStatement = {
      if (!insertStatementAsIs.trim.isEmpty())
        insertStatementAsIs.trim
      else
        throw new HandymanException("INSERT INTO SELECT .... cannot be empty for copydata for " + name)
    }
    val insert = CCJSqlParserUtil.parse(insertStatement).asInstanceOf[Insert]
    val select = insert.getSelect

    logger.info(s"Copydata action input variables id:$instanceId,name: $name, source-database:$source, target-database:$target, fetchSize:$fetchSize, writeSize:$writeSize,threadCount:$threadCount ")
    logger.info(s"Copydata Insert Sql input post parameter ingestion \n :$insert")
    logger.info(s"Copydata Select Sql input post parameter ingestion \n :$select")

    //initializing the connection related statement
    val  sourceConnection= ResourceAccess.rdbmsConn(source)
    val stmt = sourceConnection.createStatement
    stmt.setFetchSize(fetchSize)
    val statementId = AuditService.insertStatementAudit(actionId, "copydata->" + name, context.getValue("process-name"))
    //Updating the
    AuditService.updateStatementAudit(statementId, -1, 0, insertStatement, 1)

    //Prepping up the parallelization framework

    val prepOut = prepWokerPool(configMap, insert, copyData, threadCount, instanceId)
    val poisonPillSet = prepOut._1
    val countDownLatch = prepOut._2
    val workerPool = prepOut._3

    //Retreiving the data from the source
    val rs: ResultSet = stmt.executeQuery(select.toString)
    val rsmd = rs.getMetaData
    val nrCols = rsmd.getColumnCount
    val rowBatchSet: java.util.Set[Row] = new java.util.HashSet[Row]

    while (rs.next()) {

      val startTime = System.currentTimeMillis
      val columnSet: HashSet[ColumnInARow] = new HashSet[ColumnInARow]
      val id = rs.getRow
      for (i <- 1 to nrCols) {
        val column: ColumnInARow = createColumn(i, rs, rsmd, nrCols)
        columnSet.add(column)
      }

      val row = new Row(id, columnSet)
      rowBatchSet.add(row)
      if (rowBatchSet.size == fetchSize) {
        rowQueue.addAll(rowBatchSet)
        rowBatchSet.clear
        val endTime = System.currentTimeMillis
        val timeTaken = endTime - startTime
        logger.info(s"Copydata flushing batch with batch size:$rowBatchSet.size, taking time of:$timeTaken")
        //Taken care of batch audit
        AuditService.insertBatchAudit(statementId, name, instanceId.toInt, rowBatchSet.size, timeTaken.toInt)
      }
      rowsProcessed.incrementAndGet
    }

    try {
      if (!rowBatchSet.isEmpty) {
        rowQueue.addAll(rowBatchSet)
      }

      countDownLatch.await();
      workerPool.forEach(worker => {
        logger.info(s"Copydata:$instanceId cleaning up worker:$worker")
        worker.cleanup
      })
    } catch {
      case ex: InterruptedException => {
        logger.error(s"Copydata:$instanceId error during waiting for worker threads to finish their job", ex)
        throw ex
      }
    } finally {
      detailMap.put("name", name)
      detailMap.put("source", source)
      detailMap.put("destination", target)
      detailMap.put("ddlSql", insertStatementAsIs)
      detailMap.put("rows-processed", String.valueOf(rowsProcessed.intValue));
      context.addValue("rows-processed", String.valueOf(rowsProcessed.intValue));
      
      
        try {
          if(rs!=null)
            rs.close
          if(stmt!=null)
            stmt.close
          if(sourceConnection!=null)
          sourceConnection.close
        }catch{
          case ex: Throwable => {
            logger.error(s"Copydata:$instanceId error closing source connection for database:$source", ex)
          }
        }
      
    }
    context
  }

  def prepWokerPool(configMap: Map[String, String], insert: Insert, copyData: in.handyman.dsl.Copydata, threadCount: Int, instanceId: String): Tuple3[java.util.Set[Row], CountDownLatch, java.util.Set[CopyDataJdbcWriter]] = {
    val countDownLatch: CountDownLatch = new CountDownLatch(threadCount);
    val poisonPillSet: java.util.Set[Row] = new java.util.HashSet[Row]
    val workerPool: java.util.Set[CopyDataJdbcWriter] = new java.util.HashSet[CopyDataJdbcWriter]
    for (i <- 0 to 10) {

      val poisonPill: Row = new Row(i, null)
      poisonPillSet.add(poisonPill)
      logger.info(s"Copydata action is prepping up writer thread with poison pill:$poisonPill")
      val jdbcWriter: CopyDataJdbcWriter = new CopyDataJdbcWriter(configMap, insert, poisonPill, copyData, instanceId, rowQueue, countDownLatch)
      workerPool.add(jdbcWriter)
      this.executor.submit(jdbcWriter)

    }
    new Tuple3(poisonPillSet, countDownLatch, workerPool)
  }

  def createColumn(i: Int, rs: ResultSet, rsmd: ResultSetMetaData, nrCols: Int): ColumnInARow = {

    //case class ColumnInARow(columnType: String, columnTypeName: String, columnName: String, columnLabel: String, scale: Integer,
    //value: Object, columnFunction:String, isLastColumn:Boolean)
    val columnType = rsmd.getColumnType(i)
    val columnTypeName = rsmd.getColumnTypeName(i)
    val columnName = rsmd.getColumnName(i)
    val columnLabel = rsmd.getColumnLabel(i)
    val scale: Int = rsmd.getScale(i)
    val value = rs.getObject(i)
    val isLastColumn: Boolean = {
      if (i == nrCols)
        true
      else
        false
    }
    val column: ColumnInARow = new ColumnInARow(columnType, columnTypeName, columnName, columnLabel, scale, value, null, isLastColumn)
    column
  }

  def executeIf(context: in.handyman.command.Context, action: in.handyman.dsl.Action): Boolean =
    {
      val copyDataAsIs: in.handyman.dsl.Copydata = action.asInstanceOf[in.handyman.dsl.Copydata]
      val copyData: in.handyman.dsl.Copydata = CommandProxy.createProxy(copyDataAsIs, classOf[in.handyman.dsl.Copydata], context)

      val expression = copyData.getCondition
      try {
        val output = ParameterisationEngine.doYieldtoTrue(expression)
        detailMap.putIfAbsent("condition-output", output.toString())
        output
      } finally {
        if (expression != null)
          detailMap.putIfAbsent("condition", "LHS=" + expression.getLhs + ", Operator=" + expression.getOperator + ", RHS=" + expression.getRhs)
      }

    }

  def generateAudit(): java.util.Map[String, String] = {
    detailMap
  }

}