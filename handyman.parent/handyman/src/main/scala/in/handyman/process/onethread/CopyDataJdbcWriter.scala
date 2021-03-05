package in.handyman.process.onethread

import java.util.concurrent.BlockingQueue
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Callable
import com.typesafe.scalalogging.LazyLogging
import net.sf.jsqlparser.statement.insert.Insert
import scala.collection.mutable.HashSet
import in.handyman.util.ResourceAccess

class CopyDataJdbcWriter(configMap: Map[String, String], insert: Insert, poisonPill: Row,
  copyData: in.handyman.dsl.Copydata,
  id: String, rowQueue: BlockingQueue[Row],
  countDownLatch: CountDownLatch) extends Callable[Void] with LazyLogging {

  val writeBuffer: HashSet[String] = new HashSet[String]
  val conn = {
    val c = ResourceAccess.rdbmsConn(copyData.getTo)
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
      countDownLatch.countDown();
    } else {
      val dataFrame = generateDataFrame(row)
      writeBuffer.add(dataFrame)
      if (writeBuffer.size == writeSize) {
        flush()
      }
    }
    ???
  }

  def generateDataFrame(row: Row): String = {
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