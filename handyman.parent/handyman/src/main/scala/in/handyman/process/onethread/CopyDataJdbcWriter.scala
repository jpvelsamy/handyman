package in.handyman.process.onethread

import com.typesafe.scalalogging.LazyLogging
import in.handyman.HandymanException
import in.handyman.util.ResourceAccess
import net.sf.jsqlparser.statement.insert.Insert

import java.sql.{SQLException, Statement}
import java.util.concurrent.{BlockingQueue, Callable, CountDownLatch}
import scala.collection.mutable.HashSet
import scala.util.control.Breaks

class CopyDataJdbcWriter(configMap: Map[String, String], insert: Insert, poisonPill: Row,
                         copyData: in.handyman.dsl.Copydata,
                         id:       String, rowQueue: BlockingQueue[Row],
                         countDownLatch: CountDownLatch,
                         isTempTable:    Boolean) extends Callable[Void] with LazyLogging {

  val writeBuffer: HashSet[String] = new HashSet[String]
  val target = {
    if (!copyData.getTo.trim.isEmpty())
      copyData.getTo.trim
    else
      throw new HandymanException("target data source cannot be empty for copydata for " + copyData.getName)
  }

  val conn = {
    val c = ResourceAccess.rdbmsConn(target)
    c.setAutoCommit(false)
    c
  }

  val writeSize = {
    if (!copyData.getWriteBatchSize.isEmpty && copyData.getWriteBatchSize.toInt > 0)
      copyData.getWriteBatchSize.toInt
    else {
      configMap.getOrElse(Constants.WRITESIZE, Constants.DEFAULT_WRITE_SIZE).toInt
    }
  }

  val columnList = {
    val colListBuilder = new StringBuilder
    val colListSize = insert.getColumns.size
    insert.getColumns.forEach(item => {
      val currentIndex = insert.getColumns.indexOf(item)
      if (currentIndex + 1 == colListSize)
        colListBuilder.append(item.getColumnName)
      else
        colListBuilder.append(item.getColumnName).append(",")
    })
    colListBuilder.toString
  }

  def call(): Void = {

    Breaks.breakable {
      while (true) {
        val row = rowQueue.take();
        if (poisonPill.equals(row)) {
          if (!writeBuffer.isEmpty) {
            logger.info(s"CopydataWriter(After poison pill) flushing to database rows:$writeBuffer.size")
            writeToDb
          }
          countDownLatch.countDown
          Breaks.break

        } else {
          val dataFrame = generateDataFrame(row)
          writeBuffer.add(dataFrame)
          if (writeBuffer.size % writeSize == 0) {
            logger.info(s"CopydataWriter(Before poison pill) flushing to database rows:$writeBuffer.size")
            writeToDb
          }
        }
      }
    }

    ???
  }

  def generateDataFrame(row: Row): String = {
    logger.debug(s"Copydata Writer generating dataframe for row:$row")
    val columnSet = row.columnSet
    val dataFrameBuilder = new StringBuilder

    val targetTable = insert.getTable

    dataFrameBuilder.append("INSERT INTO ").append(targetTable).append(" (").
      append(columnList).append(") VALUES").append(Constants.INSERT_STMT_VALUE_START)

    columnSet.foreach(column => {

      val columnType = column.columnTypeName
      columnType.toLowerCase match {
        case Constants.STRING_DATATYPE | "java.lang.string"=> dataFrameBuilder.append(Constants.STRING_ENCLOSER).
          append(column.value).append(Constants.STRING_ENCLOSER)
        case "datetime" => dataFrameBuilder.append(Constants.STRING_ENCLOSER).
          append(column.value).append(Constants.STRING_ENCLOSER)
        case "timestamp" => dataFrameBuilder.append(Constants.STRING_ENCLOSER).
          append(column.value).append(Constants.STRING_ENCLOSER)
        case _ => dataFrameBuilder.append(column.value)
      }
      if (!column.isLastColumn)
        dataFrameBuilder.append(Constants.FIELD_SEPARATOR)
    })

    dataFrameBuilder.append(Constants.INSERT_STMT_VALUE_END)
    dataFrameBuilder.toString()
  }

  def writeToDb() = {
    logger.info(s"Writing to database using conn:$target")
    val stmt: Statement = conn.createStatement
    try {
      writeBuffer.foreach(sql => {
        stmt.addBatch(sql)
      })
      stmt.executeBatch
      conn.commit
      writeBuffer.clear
    } catch {
      case ex: SQLException => {
        logger.error(s"CopydataWriter:$id error closing source connection for database:$target", ex)
      }
      case ex: Throwable => {
        logger.error(s"CopydataWriter:$id error closing source connection for database:$target", ex)
      }
    } finally {
      try {
        if (stmt != null)
          stmt.close
      } catch {
        case ex: Throwable => {
          ex.printStackTrace
          logger.error(s"Copydata:$id error closing source connection for database:$target", ex)
        }
      }
    }

  }

  def cleanup = {
    /*try {
      if (conn != null) {
        conn.close()
      }
    } catch {
      case ex: Exception => {
        ex.printStackTrace()
        //throw ex
      }
    }*/
  }
}
