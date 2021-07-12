package in.handyman.process.onethread

import com.typesafe.scalalogging.LazyLogging
import in.handyman.command.CommandProxy
import in.handyman.util.{ParameterisationEngine, ResourceAccess}

import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths, StandardOpenOption}

class DoozleAction extends in.handyman.command.Action with LazyLogging {
  val detailMap = new java.util.HashMap[String, String]
  
  def execute(context: in.handyman.command.Context, action: in.handyman.dsl.Action, actionId:Integer): in.handyman.command.Context = {
    val doozleAsIs: in.handyman.dsl.Doozle = action.asInstanceOf[in.handyman.dsl.Doozle]
    val doozle: in.handyman.dsl.Doozle = CommandProxy.createProxy(doozleAsIs, classOf[in.handyman.dsl.Doozle], context)

    val dbSrc = doozle.getOn
    val table = doozle.getTarget
    val name = doozle.getName
    val ddlSql = doozle.getValue.replaceAll("\"", "")
    val id = context.getValue("process-id")

    logger.info("Doozle id#{}, name#{}, table#{}, db=#{}", id, name, table, dbSrc)

    val incomingJson = context.getValue("myjson")
    val storagePath = context.getValue("json.storagepath")
    storeJson(incomingJson, storagePath, table, id)

    logger.info("Sql=" + ddlSql)
    val conn = ResourceAccess.rdbmsConn(dbSrc)
    val stmt = conn.createStatement
    try {
      stmt.execute(ddlSql)
      logger.info("Completed doozle id#{}, name#{}, table#{}, db=#{}", id, name, table, dbSrc)
    } finally {
      stmt.close
      conn.close
      detailMap.put("name", name)
      detailMap.put("table", table)
      detailMap.put("dbSrc", dbSrc)
      detailMap.put("ddlSql", ddlSql)
      detailMap.put("storagePath", storagePath)
      
    }
    context
  }

  def executeIf(context: in.handyman.command.Context, action: in.handyman.dsl.Action): Boolean = {
    val doozleAsIs: in.handyman.dsl.Doozle = action.asInstanceOf[in.handyman.dsl.Doozle]
    val doozle: in.handyman.dsl.Doozle = CommandProxy.createProxy(doozleAsIs, classOf[in.handyman.dsl.Doozle], context)

    val expression = doozle.getCondition
    try {
      val output = ParameterisationEngine.doYieldtoTrue(expression)
      detailMap.putIfAbsent("condition-output", output.toString())
      output
    } finally {
       if(expression!=null)
      detailMap.putIfAbsent("condition", "LHS=" + expression.getLhs + ", Operator=" + expression.getOperator + ", RHS=" + expression.getRhs)

    }
  }

  def storeJson(incomingJson: String, storagePath: String, name: String, id: String) = {
    val finalPath = storagePath + "/" + name + ".json"
    Files.write(Paths.get(finalPath), incomingJson.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE)
    logger.info("File stored for doozle id#{}, path#{}", id, finalPath)
  }

  def generateAudit(): java.util.Map[String, String] = {
    detailMap
  }
  
  
}
