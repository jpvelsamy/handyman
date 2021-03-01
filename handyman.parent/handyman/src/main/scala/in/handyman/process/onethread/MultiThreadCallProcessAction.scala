package in.handyman.process.onethread

import com.typesafe.scalalogging.LazyLogging
import in.handyman.command.Action
import in.handyman.command.Context
import in.handyman.util.ResourceAccess
import in.handyman.DslStandaloneSetup
import in.handyman.server.PathResolver
import in.handyman.command.CommandProxy
import in.handyman.util.ParameterisationEngine
import java.util.concurrent.atomic.AtomicInteger
import java.util.HashSet

class MultiThreadCallProcessAction extends in.handyman.command.Action with LazyLogging {
  
  val detailMap = new java.util.HashMap[String, String]
  
  def execute(context: Context, action:  in.handyman.dsl.Action, actionId: Integer): Context = {
    val callProcessAsIs: in.handyman.dsl.Callprocess = action.asInstanceOf[in.handyman.dsl.Callprocess]
    val callProcess: in.handyman.dsl.Callprocess = CommandProxy.createProxy(callProcessAsIs, classOf[in.handyman.dsl.Callprocess], context)

    val name = callProcess.getName
    val fileRelativePath: String = callProcess.getSource
    val processName: String = callProcess.getTarget
    val dbSrc = callProcess.getDatasource
    val sql = callProcess.getValue.replaceAll("\"", "")
    val id = context.getValue("process-id")

    logger.info("Callprocess id#{}, name#{}, calledprocess#{}, calledfile#{}, db=#{}", id, name, processName, fileRelativePath, dbSrc)

    val conn = ResourceAccess.rdbmsConn(dbSrc)
    val stmt = conn.createStatement
    val rs = stmt.executeQuery(sql)
    val columnCount = rs.getMetaData.getColumnCount
    val threadCount:AtomicInteger = new AtomicInteger
    val resultSet:java.util.Set[Int] = new HashSet[Int]
    while (rs.next()) {
      resultSet.add(rs.getRow)
      for (i <- 1 until columnCount + 1) {
        val key = rs.getMetaData.getColumnLabel(i)
        val value = rs.getString(i)
        context.addValue(key, value)
        detailMap.put("query.output." + key, value)
      }
      threadCount.incrementAndGet
    }
    context
  }
  

  def executeIf(context: in.handyman.command.Context, action: in.handyman.dsl.Action): Boolean = {
    val callProcessAsIs: in.handyman.dsl.Callprocess = action.asInstanceOf[in.handyman.dsl.Callprocess]
    val callProcess: in.handyman.dsl.Callprocess = CommandProxy.createProxy(callProcessAsIs, classOf[in.handyman.dsl.Callprocess], context)

    val expression = callProcess.getCondition
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