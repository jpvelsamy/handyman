package in.handyman.process.onethread

import com.typesafe.scalalogging.LazyLogging
import in.handyman.dsl.Action
import in.handyman.command.Context
import in.handyman.util.ParameterisationEngine
import in.handyman.command.CommandProxy
import in.handyman.util.ResourceAccess

class FetchVariableAction extends in.handyman.command.Action with LazyLogging {
  val detailMap = new java.util.HashMap[String, String]
  def execute(context: Context, action: Action): Context = {
    val fetchAsIs: in.handyman.dsl.Fetch = action.asInstanceOf[in.handyman.dsl.Fetch]
    val fetch: in.handyman.dsl.Fetch = CommandProxy.createProxy(fetchAsIs, classOf[in.handyman.dsl.Fetch], context)

    val source = fetch.getSource
    val name = fetch.getName
    val sql = fetch.getValue.trim
    val id = context.getValue("process-id")

    logger.info("Fetch id#{}, name#{}, sql#{}, db=#{}", id, name, sql, source)
    val conn = ResourceAccess.rdbmsConn(source)
    val stmt = conn.createStatement
    try {
      val rs = stmt.executeQuery(sql)
      val columnCount = rs.getMetaData.getColumnCount

      while (rs.next()) {

        for (i <- 1 until columnCount + 1) {
          val key = rs.getMetaData.getColumnLabel(i)
          val value = rs.getString(i)
          context.addValue(name + "." + key, value)          
        }
      }
    } finally {
      detailMap.put("source", source)
      detailMap.put("sql", sql)
      stmt.close
      conn.close
    }
    logger.info("Completed fetch id#{}, name#{}, sql#{}, db=#{}", id, name, sql, source)

    context
  }

  def executeIf(context: Context, action: Action): Boolean = {
    val fetchAsIs: in.handyman.dsl.Fetch = action.asInstanceOf[in.handyman.dsl.Fetch]
    val fetch: in.handyman.dsl.Fetch = CommandProxy.createProxy(fetchAsIs, classOf[in.handyman.dsl.Fetch], context)

    val expression = fetch.getCondition
    try {
      val output = ParameterisationEngine.doYieldtoTrue(expression)
      detailMap.putIfAbsent("condition-output", output.toString())
      output
    } finally {
       if(expression!=null)
      detailMap.putIfAbsent("condition", "LHS=" + expression.getLhs + ", Operator=" + expression.getOperator + ", RHS=" + expression.getRhs)

    }
  }

  def generateAudit(): java.util.Map[String, String] = {
    detailMap
  }

}