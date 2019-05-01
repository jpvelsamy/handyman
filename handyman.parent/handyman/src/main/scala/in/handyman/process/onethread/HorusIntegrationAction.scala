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

class HorusIntegrationAction extends in.handyman.command.Action with LazyLogging {
  val detailMap = new java.util.HashMap[String, String]

  def execute(context: in.handyman.command.Context, action: in.handyman.dsl.Action): in.handyman.command.Context = {
    val trelloAsIs: in.handyman.dsl.TrelloGET = action.asInstanceOf[in.handyman.dsl.TrelloGET]
    val trello: in.handyman.dsl.TrelloGET = CommandProxy.createProxy(trelloAsIs, classOf[in.handyman.dsl.TrelloGET], context)
    val className = trello.getClasstg
    val dataSrc = trello.getSource
    val sql = trello.getValue.replaceAll("\"", "")
    val name = trello.getName
    val conn = ResourceAccess.rdbmsConn(dataSrc)
    val stmt = conn.createStatement
    
    val clazz = this.getClass.getClassLoader.loadClass(className)

    try {
      //Bso here means business specific object
      val trello = clazz.newInstance()
      
      val rs = stmt.executeQuery(sql)
      rs.next()
      val boardid = rs.getString("board_id")
      val key = rs.getString("key")
      val token = rs.getString("token")
      
      logger.info("Starting the firebase database update custom code execution with param class = {}, dbSrc = {}", className, dataSrc)
      val method = clazz.getDeclaredMethod("execute", classOf[String], classOf[String], classOf[String], classOf[String], classOf[Context], classOf[HashMap[String, String]])
      method.invoke(trello, boardid, key, token,context, detailMap).asInstanceOf[Context]
      logger.info("Starting the firebase database update custom code execution with param class = {}, dbSrc = {}", className, dataSrc)
      context
    } finally {
      detailMap.put("name", trello.getName)
      detailMap.put("className", className)
      detailMap.put("dataSrc", dataSrc)
      detailMap.put("sql", sql)
    }
  }

  def executeIf(context: in.handyman.command.Context, action: in.handyman.dsl.Action): Boolean = {
    val trelloAsIs = action.asInstanceOf[in.handyman.dsl.TrelloGET]
    val trello: in.handyman.dsl.TrelloGET = CommandProxy.createProxy(trelloAsIs, classOf[in.handyman.dsl.TrelloGET], context)

    val dbSrc = trello.getSource
    val name = trello.getName
    val id = context.getValue("process-id")
    val expression: Expression = trello.getCondition
    try {
      val output = ParameterisationEngine.doYieldtoTrue(expression)
      logger.info("Completed evaluation to execute id#{}, name#{}, dbSrc#{}, expression#{},  output = {}", id, name, dbSrc, expression, output.toString())
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