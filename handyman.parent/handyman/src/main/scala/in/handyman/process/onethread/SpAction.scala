package in.handyman.process.onethread

import com.typesafe.scalalogging.LazyLogging
import in.handyman.command.Context
import in.handyman.command.Action
import in.handyman.command.CommandProxy
import in.handyman.util.ResourceAccess
import org.eclipse.emf.common.util.EList
import in.handyman.util.ParameterisationEngine
import java.util.ArrayList
import java.sql.SQLException
import scala.util.control.Exception.Finally
import java.time.LocalDateTime
import java.sql.CallableStatement
import java.sql.Statement
import java.sql.ResultSet

class SpAction extends in.handyman.command.Action with LazyLogging {
  val detailMap = new java.util.HashMap[String, String]

  def execute(context: in.handyman.command.Context, action: in.handyman.dsl.Action): in.handyman.command.Context = {
    val spAsIs: in.handyman.dsl.Sp = action.asInstanceOf[in.handyman.dsl.Sp]
    val sp: in.handyman.dsl.Sp = CommandProxy.createProxy(spAsIs, classOf[in.handyman.dsl.Sp], context)

    val db = sp.getDb
    val table = sp.getTable
    val name = sp.getName
    val exec: String = sp.getValue.replace("\"", "")
    val id = context.getValue("process-id")
    //val proc = exec.replace("exec", "").replace("call", "").replace("{", "").replace("}", "").replace("\"", "").replace(" ", "")

    val con = ResourceAccess.rdbmsConn(db)
    val istmt:Statement = con.createStatement()
    istmt.executeUpdate("create table ZuciTest.dbo.zzz (sp varchar(100))")
    logger.info("Completed inserting")
    try {

      val stmt: CallableStatement = con.prepareCall(exec)
      val rs:ResultSet = stmt.executeQuery()
      if (rs.next()) {
        val result = rs.getInt(1)
        val insert = "insert into " + table + " (sp,time,result)" + " values (" + "\'" + name + "\', \'" + LocalDateTime.now() + "\', \'" + result + "\');"
        logger.info("WriteCsv id#{}, name#{}, from#{}, sqlList#{}", id, name, db, insert)
        istmt.executeUpdate(insert)
        logger.info("Completed inserting")
      }
    } catch {
      case ex: SQLException => {
        ex.printStackTrace()
      }
    } finally {
      con.close()
      detailMap.put("name", name)
      detailMap.put("db", db)
      detailMap.put("exec", exec)
    }
    context

  }

  def executeIf(context: in.handyman.command.Context, action: in.handyman.dsl.Action): Boolean = {
    val spAsIs = action.asInstanceOf[in.handyman.dsl.Sp]
    val sp: in.handyman.dsl.Sp = CommandProxy.createProxy(spAsIs, classOf[in.handyman.dsl.Sp], context)

    val expression = sp.getCondition
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