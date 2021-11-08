package in.handyman.process.onethread

import in.handyman.server.RuntimeContext
import java.util.concurrent.Callable
import com.typesafe.scalalogging.LazyLogging
import in.handyman.server.ProcessExecutor

class SpawnProcessCallable(runtimeContext: RuntimeContext) extends Callable[Void] with LazyLogging {

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

    }
    ???
  }

  def handleError(ex: Throwable) = {
    logger.info("Error section of call process", ex)
    throw ex
  }
}
