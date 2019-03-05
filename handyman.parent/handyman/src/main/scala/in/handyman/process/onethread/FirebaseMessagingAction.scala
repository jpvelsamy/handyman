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

class FirebaseMessagingAction extends in.handyman.command.Action with LazyLogging {
  val detailMap = new java.util.HashMap[String, String]

  def execute(context: Context, action: Action): Context = {
    val fbrnAsIs = action.asInstanceOf[in.handyman.dsl.FirebaseReactiveNotification]
    val fbrn: in.handyman.dsl.FirebaseReactiveNotification = CommandProxy.createProxy(fbrnAsIs, classOf[in.handyman.dsl.FirebaseReactiveNotification], context)
    val className = fbrn.getClassFqn
    val jsonPath = fbrn.getFbjson
    val groupPath = fbrn.getGroupPath
    val databaseFQNUrl = fbrn.getUrl
    val clazz = this.getClass.getClassLoader.loadClass(className)
    //Bso here means business specific object
    val fbrnBso = clazz.newInstance()
    val method = clazz.getDeclaredMethod("", classOf[String],classOf[String], classOf[String],classOf[Context], classOf[HashMap[String, String]])
    method.invoke(fbrnBso, jsonPath, groupPath, databaseFQNUrl, context, detailMap).asInstanceOf[Context]    
  }

  def executeIf(context: Context, action: Action): Boolean = {
    val fbrnAsIs = action.asInstanceOf[in.handyman.dsl.FirebaseReactiveNotification]
    val fbrn: in.handyman.dsl.FirebaseReactiveNotification = CommandProxy.createProxy(fbrnAsIs, classOf[in.handyman.dsl.FirebaseReactiveNotification], context)

    val expression: Expression = fbrn.getCondition
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