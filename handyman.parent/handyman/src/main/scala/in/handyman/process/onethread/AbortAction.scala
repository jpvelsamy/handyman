package in.handyman.process.onethread

import com.typesafe.scalalogging.LazyLogging
import in.handyman.AbortException
import in.handyman.command.{CommandProxy, Context}
import in.handyman.util.ParameterisationEngine
import org.slf4j.MarkerFactory

class AbortAction extends in.handyman.command.Action with LazyLogging {
  val detailMap = new java.util.HashMap[String, String]
  val auditMarker = "ABORT";
  val aMarker = MarkerFactory.getMarker(auditMarker);

  def execute(context: Context, action: in.handyman.dsl.Action, actionId:Integer): Context = {
    val abortAsIs: in.handyman.dsl.Abort = action.asInstanceOf[in.handyman.dsl.Abort]
    val abort: in.handyman.dsl.Abort = CommandProxy.createProxy(abortAsIs, classOf[in.handyman.dsl.Abort], context)
    val message = abort.getValue.trim
    val id = context.getValue("process-id")
    val name = abort.getName

    logger.info(aMarker, "Abort action id#{}, name#{}, calledprocess#{}, message#{}", id, name, name, message)
    try {
      throw new AbortException(message, name, context)
    } finally {
      detailMap.putIfAbsent("message", message)
      detailMap.putIfAbsent("name", name)
    }
  }

  def executeIf(context: Context, action: in.handyman.dsl.Action): Boolean = {
    val abortAsIs: in.handyman.dsl.Abort = action.asInstanceOf[in.handyman.dsl.Abort]
    val abort: in.handyman.dsl.Abort = CommandProxy.createProxy(abortAsIs, classOf[in.handyman.dsl.Abort], context)
    
    val name = abort.getName
    val id = context.getValue("process-id")
    
    val expression = abort.getCondition
    try {
      val output = ParameterisationEngine.doYieldtoTrue(expression)
      logger.info(aMarker, "Completed evaluation to execute id#{}, name#{}, dbSrc#{}, expression#{}", id, name,  expression)
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
