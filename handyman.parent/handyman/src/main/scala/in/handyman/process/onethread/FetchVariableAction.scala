package in.handyman.process.onethread

import com.typesafe.scalalogging.LazyLogging
import in.handyman.command.{CommandProxy, Context}
import in.handyman.dsl.Action
import in.handyman.util.{ParameterisationEngine, ResourceAccess}
import org.slf4j.MarkerFactory

class FetchVariableAction extends in.handyman.command.Action with LazyLogging {
  val detailMap = new java.util.HashMap[String, String]
  val auditMarker = "ASSIGN";
  val aMarker = MarkerFactory.getMarker(auditMarker);

  def execute(context: Context, action: Action, actionId: Integer): Context = {
    val fetchAsIs: in.handyman.dsl.Fetch = action.asInstanceOf[in.handyman.dsl.Fetch]
    val fetch: in.handyman.dsl.Fetch = CommandProxy.createProxy(fetchAsIs, classOf[in.handyman.dsl.Fetch], context)

    val source = fetch.getSource
    val name = fetch.getName
    val sql = fetch.getValue.trim
    val id = context.getValue("process-id")
    val sqlList = sql.split(";")
    logger.info(aMarker, "Fetch id#{}, name#{}, sql#{}, db=#{}", id, name, sqlList, source)
    val conn = ResourceAccess.rdbmsConn(source)
    val stmt = conn.createStatement
    try {
      sqlList.foreach(sqlItem => {
          logger.info(aMarker, "Execution query sql#{} on db=#{}", sqlItem.trim(), source)
          val rs = stmt.executeQuery(sqlItem.trim)
          val columnCount = rs.getMetaData.getColumnCount

          while (rs.next()) {

            for (i <- 1 until columnCount + 1) {
              val key = rs.getMetaData.getColumnLabel(i)
              val value = rs.getString(i)
              logger.info(aMarker, "Adding value {} for key {} from query sql#{} on db=#{}", value, name + "." + key, sqlItem, source)
              context.addValue(name + "." + key, value)
            }
          }       
          detailMap.put("source", source)
          detailMap.put("sql", sqlItem)
      })
    } finally {

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
      if (expression != null)
        detailMap.putIfAbsent("condition", "LHS=" + expression.getLhs + ", Operator=" + expression.getOperator + ", RHS=" + expression.getRhs)

    }
  }

  def generateAudit(): java.util.Map[String, String] = {
    detailMap
  }

}
