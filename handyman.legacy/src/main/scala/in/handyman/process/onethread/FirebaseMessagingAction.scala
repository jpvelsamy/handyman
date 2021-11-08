package in.handyman.process.onethread

import com.typesafe.scalalogging.LazyLogging
import in.handyman.dsl.Action
import in.handyman.command.Context
import in.handyman.util.ResourceAccess
import in.handyman.command.CommandProxy
import in.handyman.util.ParameterisationEngine
import in.handyman.util.ExceptionUtil
import java.sql.SQLException
import in.handyman.dsl.Expression
import java.util.HashMap
import org.slf4j.MarkerFactory
import in.handyman.audit.AuditService

class FirebaseMessagingAction extends in.handyman.command.Action with LazyLogging {
  val detailMap = new java.util.HashMap[String, String]
  val auditMarker = "FIREBASE-MESSAGING";
  val aMarker = MarkerFactory.getMarker(auditMarker);

  def execute(context: Context, action: Action, actionId: Integer): Context = {
    val fbrnAsIs = action.asInstanceOf[in.handyman.dsl.FirebaseReactiveNotification]
    val fbrn: in.handyman.dsl.FirebaseReactiveNotification = CommandProxy.createProxy(fbrnAsIs, classOf[in.handyman.dsl.FirebaseReactiveNotification], context)
    val className = fbrn.getClassFqn
    val jsonPath = fbrn.getFbjson
    val groupPath = fbrn.getGroupPath
    val databaseFQNUrl = fbrn.getUrl
    val clazz = this.getClass.getClassLoader.loadClass(className)
    val dbSrc = fbrn.getDbSrc
    val name = fbrn.getName


    try {
      //Bso here means business specific object
      val fbrnBso = clazz.newInstance()
      logger.info(aMarker, "Starting the messaging custom code execution with param class = {}, authkey = {}, url = {}, group = {}, dbSrc = {}", className, jsonPath, databaseFQNUrl, groupPath, dbSrc)
      val method = clazz.getDeclaredMethod("execute", classOf[Integer], classOf[String], classOf[String], classOf[String], classOf[String], classOf[Context], classOf[HashMap[String, String]])
      method.invoke(fbrnBso, actionId, jsonPath, groupPath, databaseFQNUrl, dbSrc, context, detailMap).asInstanceOf[Context]
      logger.info(aMarker, "Completed the messaging custom code execution with param class = {}, authkey = {}, url = {}, group = {}, dbSrc = {}", className, jsonPath, databaseFQNUrl, groupPath, dbSrc)
      context
    } finally {
      detailMap.put("name", fbrn.getName)
      detailMap.put("className", className)
      detailMap.put("jsonPath", jsonPath)
      detailMap.put("groupPath", groupPath)
      detailMap.put("databaseFQNUrl", databaseFQNUrl)
      detailMap.put("dataSrc", dbSrc)
    }
  }

  def executeIf(context: Context, action: Action): Boolean = {
    val fbrnAsIs = action.asInstanceOf[in.handyman.dsl.FirebaseReactiveNotification]
    val fbrn: in.handyman.dsl.FirebaseReactiveNotification = CommandProxy.createProxy(fbrnAsIs, classOf[in.handyman.dsl.FirebaseReactiveNotification], context)

    val dbSrc = fbrn.getUrl()
    val name = fbrn.getName
    val id = context.getValue("process-id")
    val expression: Expression = fbrn.getCondition
    try {
      val output = ParameterisationEngine.doYieldtoTrue(expression)
      logger.info(aMarker, "Completed evaluation to execute id#{}, name#{}, dbSrc#{}, expression#{}", id, name, dbSrc, expression)
      detailMap.put("name", name)
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
