package in.handyman.process.onethread

import com.typesafe.scalalogging.LazyLogging
import in.handyman.command.{CommandProxy, Context}
import in.handyman.dsl.{Action, Expression}
import in.handyman.util.ParameterisationEngine
import org.slf4j.MarkerFactory

import java.util.HashMap

class FirebaseDbUpdateAction extends in.handyman.command.Action with LazyLogging {
  val detailMap = new java.util.HashMap[String, String]
  val auditMarker = "FIREBASE-UPDATE";
  val aMarker = MarkerFactory.getMarker(auditMarker);

  def execute(context: Context, action: Action, actionId:Integer): Context = {
    val fbDuAsIs = action.asInstanceOf[in.handyman.dsl.FirebaseDatabasePut]
    val fbDu: in.handyman.dsl.FirebaseDatabasePut = CommandProxy.createProxy(fbDuAsIs, classOf[in.handyman.dsl.FirebaseDatabasePut], context)
    val className = fbDu.getClassFqn
    val jsonPath = fbDu.getFbjson
    val groupPath = fbDu.getGroupPath
    val databaseFQNUrl = fbDu.getUrl
    val dataSrc = fbDu.getDbSrc
    val sql = fbDu.getValue
    val name = fbDu.getName
    
    val clazz = this.getClass.getClassLoader.loadClass(className)

    try {
      //Bso here means business specific object
      val fbrnBso = clazz.newInstance()
      
      logger.info(aMarker, "Starting the firebase database update custom code execution with param class = {}, authkey = {}, url = {}, group = {}, dbSrc = {}", className, jsonPath, databaseFQNUrl, groupPath, dataSrc)
      val method = clazz.getDeclaredMethod("execute", classOf[Integer], classOf[String], classOf[String], classOf[String], classOf[String], classOf[String], classOf[Context], classOf[HashMap[String, String]])
      method.invoke(fbrnBso, actionId, jsonPath, groupPath, databaseFQNUrl, dataSrc, sql, context, detailMap).asInstanceOf[Context]
      logger.info(aMarker, "Starting the firebase database update custom code execution with param class = {}, authkey = {}, url = {}, group = {}, dbSrc = {}", className, jsonPath, databaseFQNUrl, groupPath, dataSrc)
      context
    } finally {
      detailMap.put("name", fbDu.getName)
      detailMap.put("className", className)
      detailMap.put("jsonPath", jsonPath)
      detailMap.put("groupPath", groupPath)
      detailMap.put("databaseFQNUrl", databaseFQNUrl)
      detailMap.put("dataSrc", dataSrc)
      detailMap.put("sql", sql)
    }
  }

  def executeIf(context: Context, action: Action): Boolean = {
    val fbDuAsIs = action.asInstanceOf[in.handyman.dsl.FirebaseDatabasePut]
    val fbdu: in.handyman.dsl.FirebaseDatabasePut = CommandProxy.createProxy(fbDuAsIs, classOf[in.handyman.dsl.FirebaseDatabasePut], context)

    val dbSrc = fbdu.getUrl()
    val name = fbdu.getName
    val id = context.getValue("process-id")
    val expression: Expression = fbdu.getCondition
    try {
      val output = ParameterisationEngine.doYieldtoTrue(expression)
      logger.info(aMarker, "Completed evaluation to execute id#{}, name#{}, dbSrc#{}, expression#{},  output = {}", id, name, dbSrc, expression, output.toString())
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
