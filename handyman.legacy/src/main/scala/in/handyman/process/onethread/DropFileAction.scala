package in.handyman.process.onethread

import com.typesafe.scalalogging.LazyLogging
import in.handyman.command.CommandProxy
import java.io.File
import in.handyman.util.ParameterisationEngine

class DropFileAction extends in.handyman.command.Action with LazyLogging {
  val detailMap = new java.util.HashMap[String, String]

  def execute(context: in.handyman.command.Context, action: in.handyman.dsl.Action, actionId: Integer): in.handyman.command.Context = {
    val dropFileAsIs: in.handyman.dsl.Dropfile = action.asInstanceOf[in.handyman.dsl.Dropfile]
    val dropFile: in.handyman.dsl.Dropfile = CommandProxy.createProxy(dropFileAsIs, classOf[in.handyman.dsl.Dropfile], context)

    val file = dropFile.getTarget
    val name = dropFile.getName
    val id = context.getValue("process-id")

    logger.info("Dropfile id#{}, name#{}, file#{}, db=#{}", id, name, file)

    val filePath = new File(file)
    try {
      filePath.delete
      logger.info("Completed dropfile id#{}, name#{}, file#{}, db=#{}", id, name, file)
    }
    finally {
      detailMap.put("file", file)
    }

    context
  }

  def executeIf(context: in.handyman.command.Context, action: in.handyman.dsl.Action): Boolean = {
    val dropFileAsIs: in.handyman.dsl.Dropfile = action.asInstanceOf[in.handyman.dsl.Dropfile]
    val dropFile: in.handyman.dsl.Dropfile = CommandProxy.createProxy(dropFileAsIs, classOf[in.handyman.dsl.Dropfile], context)

    val expression = dropFile.getCondition
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
