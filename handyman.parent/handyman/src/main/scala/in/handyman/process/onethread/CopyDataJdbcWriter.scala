package in.handyman.process.onethread

import java.util.concurrent.BlockingQueue
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Callable
import com.typesafe.scalalogging.LazyLogging
import net.sf.jsqlparser.statement.insert.Insert
import scala.collection.mutable.HashSet
import in.handyman.util.ResourceAccess
import in.handyman.HandymanException

class CopyDataJdbcWriter(configMap: Map[String, String], insert: Insert, poisonPill: Row,
  copyData: in.handyman.dsl.Copydata,
  id: String, rowQueue: BlockingQueue[Row],
  countDownLatch: CountDownLatch) extends Callable[Void] with LazyLogging {

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
    if (copyData.getWriteBatchSize.isValidInt)
      copyData.getWriteBatchSize
    else {
      configMap.getOrElse(Constants.WRITESIZE, Constants.DEFAULT_WRITE_SIZE).toInt
    }
  }
  
  def call(): Void = {
    val row = rowQueue.take();

    if (row.equals(poisonPill)) {
      if(!writeBuffer.isEmpty){
        logger.info(s"CopydataWriter(After poison pill) flushing to database rows:$writeBuffer.size")
        flush
      }
      countDownLatch.countDown();
    } else {
      val dataFrame = generateDataFrame(row)
      writeBuffer.add(dataFrame)
      if (writeBuffer.size == writeSize) {
        logger.info(s"CopydataWriter(Before poison pill) flushing to database rows:$writeBuffer.size")
        flush
      }
    }
    ???
  }

  def generateDataFrame(row: Row): String = {
    logger.debug(s"Copydata Writer generating dataframe for row:$row")
    val columnSet = row.columnSet
    val dataFrameBuilder = new StringBuilder
    dataFrameBuilder.append(Constants.INSERT_STMT_VALUE_START)
    
    
    columnSet.foreach(column => {
      val columnType = column.columnTypeName
      columnType.toLowerCase match {
        case Constants.STRING_DATATYPE => dataFrameBuilder.append(Constants.STRING_ENCLOSER).
                                                                                            append(column.value).append(Constants.STRING_ENCLOSER)
        case _ => dataFrameBuilder.append(column.value)
      }
      if(!column.isLastColumn)
        dataFrameBuilder.append(Constants.FIELD_SEPARATOR)
    })
    
    dataFrameBuilder.append(Constants.INSERT_STMT_VALUE_END)
    dataFrameBuilder.toString()
  }

  def flush() = {
    
    val stmt = conn.createStatement
    writeBuffer.foreach(sql=>{
      stmt.addBatch(sql)
    })
    stmt.executeLargeBatch
    stmt.closeOnCompletion
    conn.commit
    writeBuffer.clear
    try {
         
          if(stmt!=null)
            stmt.close
        
        }catch{
          case ex: Throwable => {
            logger.error(s"CopydataWriter:$id error closing source connection for database:$target", ex)
          }
        }
    ???
  }

  def cleanup = {
    try {
      if (conn != null) {
        conn.close()
      }
    } catch {
      case ex: Exception => {
        ex.printStackTrace()
        //throw ex
      }
    }

  }
}