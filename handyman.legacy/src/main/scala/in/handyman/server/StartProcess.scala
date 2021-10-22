package in.handyman.server

import com.fasterxml.jackson.databind.ObjectMapper
import com.typesafe.scalalogging.LazyLogging
import org.restlet.representation.Representation
import org.restlet.resource.{Post, ServerResource}

class StartProcess extends ServerResource with LazyLogging {
  val runMode = "in.handyman.process.onethread"
  val jsonSerializer = new ObjectMapper

  //
  @Post("json")
  def doPost(entity: Representation): String = {
    val inboundValue = getRequest.getAttributes.get("instance");
    val alias = getRequest().getAttributes().get("alias");
    val instanceName: String = inboundValue.asInstanceOf[String] + "#" + alias.asInstanceOf[String]
    logger.info("Starting the process=" + instanceName)
    val runtimeContext = ProcessAST.loadProcessAST(instanceName, "{}")

    try {

      val processResponse = ProcessExecutor.execute(runMode, runtimeContext)
      jsonSerializer.writeValueAsString(processResponse)
    } catch {
      case ex: Throwable => {
        handleError(ex)
        jsonSerializer.writeValueAsString(ex)
      }
    } finally {
      handleFinally()
    }
  }

  /*@Get("application/json")
  def represent(): String = {
    val inboundValue = getRequest.getAttributes.get("instance");

    val alias = getRequest().getAttributes().get("alias");
    val instanceName: String = inboundValue.asInstanceOf[String] + "#" + alias.asInstanceOf[String]
    logger.info("Starting the process=" + instanceName)
    val runtimeContext = ProcessAST.loadProcessAST(instanceName, "{}")

    try {

      ProcessExecutor.execute(runMode, runtimeContext)

    } catch {
      case ex: Throwable => {
        handleError(ex)
      }
    } finally {

      handleFinally()
    }
    "{\"start_status\": \"SUCCESS\"}"
  }

  @Post("application/json")
  def acceptAndReturnJson(json: Representation): String = {
    val inboundValue = getRequest.getAttributes.get("instance");

    val alias = getRequest().getAttributes().get("alias");
    val instanceName: String = inboundValue.asInstanceOf[String] + "#" + alias.asInstanceOf[String]
    logger.info("Starting the process=" + instanceName + ", with incoming json=" + json)
    val jsonObject = json.getJsonObject.toString
    val runtimeContext = ProcessAST.loadProcessAST(instanceName, jsonObject)

    try {

      ProcessExecutor.execute(runMode, runtimeContext)

    } catch {
      case ex: Throwable => {
        handleError(ex)
      }
    } finally {

      handleFinally()
    }

    "{\"start_status\": \"SUCCESS\"}"
  }*/

  def handleError(ex: Throwable) = {
    ex.printStackTrace()
  }

  def handleFinally() = {
    println("Processing the request completed")
  }
}
