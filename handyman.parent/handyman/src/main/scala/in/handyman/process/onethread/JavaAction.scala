package in.handyman.process.onethread

import com.typesafe.scalalogging.LazyLogging
import in.handyman.command.{CommandProxy, Context}
import in.handyman.dsl.{Action, Expression}
import in.handyman.util.{ParameterisationEngine, ResourceAccess}
import org.slf4j.MarkerFactory

import java.util.HashMap

class JavaAction extends in.handyman.command.Action with LazyLogging {
  val detailMap = new java.util.HashMap[String, String]
  val auditMarker = "JAVA";
  val aMarker = MarkerFactory.getMarker(auditMarker);

  def execute(context: Context, action: Action, actionId: Integer): Context = {
    val javaExecAsIs = action.asInstanceOf[in.handyman.dsl.ExecJava]
    val javaExec: in.handyman.dsl.ExecJava = CommandProxy.createProxy(javaExecAsIs, classOf[in.handyman.dsl.ExecJava], context)
    val className = javaExec.getClassFqn
    val dataSrc = javaExec.getDbSrc
    val name = javaExec.getName

    val clazz = this.getClass.getClassLoader.loadClass(className)
    val sql = javaExec.getValue
    val conn = ResourceAccess.rdbmsConn(dataSrc)
    val stmt = conn.createStatement
    val rs = stmt.executeQuery(sql)
    val totalColumns = rs.getMetaData.getColumnCount
    rs.next()//Assume that there will be definitely one row    
    for( counter <- 1 to totalColumns){
      val paramKey = rs.getMetaData.getColumnLabel(counter);
      val paramValue = rs.getString(paramKey)
      detailMap.put(paramKey, paramValue)        
    }
      
    
    try {
      //Bso here means business specific object
      val javaBso = clazz.newInstance()

      logger.info(aMarker, "Starting custom code execution with param class = {}, dbSrc = {}", className, dataSrc)
      val method = clazz.getDeclaredMethod("execute", classOf[Integer], classOf[String], classOf[Context], classOf[HashMap[String, String]])
      method.invoke(javaBso, actionId, dataSrc, context, detailMap).asInstanceOf[Context]

      context
    } finally {
      detailMap.put("name", javaExec.getName)
      detailMap.put("className", className)
      detailMap.put("dataSrc", dataSrc)
    }
  }

  def executeIf(context: Context, action: Action): Boolean = {
    val fbDuAsIs = action.asInstanceOf[in.handyman.dsl.ExecJava]
    val fbDu: in.handyman.dsl.ExecJava = CommandProxy.createProxy(fbDuAsIs, classOf[in.handyman.dsl.ExecJava], context)

    val name = fbDu.getName
    val dataSrc = fbDu.getDbSrc
    val id = context.getValue("process-id")
    val expression: Expression = fbDu.getCondition
    try {
      val output = ParameterisationEngine.doYieldtoTrue(expression)
      logger.info(aMarker, "Completed evaluation to execute id#{}, name#{}, dbSrc#{}, expression#{},  output = {}", id, name, dataSrc, expression, output.toString())
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
