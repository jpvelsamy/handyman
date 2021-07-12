package in.handyman.process.onethread

import com.typesafe.scalalogging.LazyLogging
import in.handyman.server.{ProcessExecutor, RuntimeContext}

import java.util.concurrent.{Callable, CountDownLatch}

class ForkProcessCallable(runtimeContext:RuntimeContext, countDownLatch: CountDownLatch) extends Callable[Void] with LazyLogging {
  
  def call(): Void = {
     try {
        //TODO - change to create(config.get("runmode"))
        val runtime = ProcessExecutor.execute("in.handyman.process.onethread", runtimeContext)

      } catch {
        case ex: Throwable => {
          handleError(ex)
          throw ex
        }

      } finally {
        val onFinally = runtimeContext.process.getFinally
        handleFinally()
        countDownLatch.countDown
      }
    ???
  }
  
   def handleError(ex: Throwable) = {
    logger.info("Error section of call process", ex)
    throw ex
  }

  def handleFinally() = {
    logger.info("Finally section of call process")
  }

}
