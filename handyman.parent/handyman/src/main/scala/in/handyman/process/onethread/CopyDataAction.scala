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

class CopyDataAction extends in.handyman.command.Action with LazyLogging {

  val rowQueue: BlockingQueue[Row] = new LinkedBlockingDeque[Row];
  
  val executor: ExecutorService = Executors.newCachedThreadPool;
  val detailMap = new java.util.HashMap[String, String]

  def execute(context: in.handyman.command.Context, action: in.handyman.dsl.Action, actionId: Integer): in.handyman.command.Context = {

    val copyDataAsIs: in.handyman.dsl.Copydata = action.asInstanceOf[in.handyman.dsl.Copydata]
    val copyData: in.handyman.dsl.Copydata = CommandProxy.createProxy(copyDataAsIs, classOf[in.handyman.dsl.Copydata], context)

    val configMap = ConfigurationService.getGlobalconfig()
    val id = context.getValue("process-id")
    val name = copyData.getName
    val source = copyData.getSource
    val target = copyData.getTo
    val fetchSize:Int = {
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
    val threadCount:Int = {
      if (copyData.getWriteThreadCount.isValidInt)
        copyData.getWriteThreadCount
      else {
        configMap.getOrElse(Constants.WRITERTHREAD, Constants.DEFAULT_WRITER_COUNT).toInt
      }
    }
    val insertStatementAsIs = copyData.getValue
    val insertStatement = {
      if (!insertStatementAsIs.trim.isEmpty())
        insertStatementAsIs.trim
      else
        throw new HandymanException("Sql string cannot be empty for copydata for " + name)
    }
    val insert=CCJSqlParserUtil.parse(insertStatement).asInstanceOf[Insert]
    val select = insert.getSelect
    
    
    logger.info(s"Copydata action input variables id:$id,name: $name, source-database:$source, target-database:$target, fetchSize:$fetchSize, writeSize:$writeSize,threadCount:$threadCount ")
    logger.info(s"Copydata Insert Sql input post parameter ingestion \n :$insert")
    logger.info(s"Copydata Select Sql input post parameter ingestion \n :$select")
    
    val sourceConnection = ResourceAccess.rdbmsConn(source)
    val stmt = sourceConnection.createStatement
    stmt.setFetchSize(fetchSize)
    val statementId = AuditService.insertStatementAudit(actionId, "copydata->" + name, context.getValue("process-name"))
    val countDownLatch: CountDownLatch = new CountDownLatch(threadCount);
    val poisonPillSet: java.util.Set[Row] = new java.util.HashSet[Row]
    for (i <- 0 to 10) {
      val poisonPill: Row = new Row(i, null)      
      poisonPillSet.add(poisonPill)
      
     
    val jdbcWriter: CopyDataJdbcWriter = new CopyDataJdbcWriter(configMap, insert,poisonPill, copyData, id, rowQueue, countDownLatch)
      this.executor.submit(jdbcWriter)
    }
    
    val rs: ResultSet = stmt.executeQuery(select.toString)
    val rsmd = rs.getMetaData
    val nrCols = rsmd.getColumnCount
    val rowBatchSet:java.util.Set[Row] = new java.util.HashSet[Row]
    while(rs.next())
    {
      val columnSet: HashSet[ColumnInARow] = new HashSet[ColumnInARow]
      val id = rs.getRow
       for (i <- 1 to nrCols)
       {
			  		
			  		//case class ColumnInARow(columnType: String, columnTypeName: String, columnName: String, columnLabel: String, scale: Integer, 
            //value: Object, columnFunction:String, isLastColumn:Boolean)
			  		val columnType = rsmd.getColumnType(i)
			  		val columnTypeName = rsmd.getColumnTypeName(i)
			  		val columnName  = rsmd.getColumnName(i)
			  		val columnLabel = rsmd.getColumnLabel(i)
			  		val scale:Int = rsmd.getScale(i)
			  		val value = rs.getObject(i)
			  		val isLastColumn:Boolean = {
			  		  if(i==nrCols)
			  		    true
			  		  else
			  		    false
			  		}
			  		val column:ColumnInARow = new ColumnInARow(columnType, columnTypeName, columnName, columnLabel, scale, value, null, isLastColumn)
			  		columnSet.add(column)
				}
      val row  = new Row(id, columnSet)
      rowBatchSet.add(row)
      if(rowBatchSet.size==fetchSize){
        rowQueue.addAll(rowBatchSet)
        rowBatchSet.clear
      }
    }

    context
  }

  def executeIf(context: in.handyman.command.Context, action: in.handyman.dsl.Action): Boolean = {
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