package in.handyman.process.onethread
import com.typesafe.scalalogging.LazyLogging

import in.handyman.command.CommandProxy
import in.handyman.util.ParameterisationEngine
import java.util.concurrent.LinkedBlockingDeque
import in.handyman.util.ResourceAccess
import in.handyman.audit.AuditService
import java.sql.SQLSyntaxErrorException
import in.handyman.util.ExceptionUtil
import java.sql.SQLException


class EsUpdateAction extends in.handyman.command.Action with LazyLogging {
  val detailMap = new java.util.HashMap[String, String]
  val  inboundPsmFormQueue = new LinkedBlockingDeque[ColumnInARow]

  def execute(context: in.handyman.command.Context, action: in.handyman.dsl.Action, actionId: Integer): in.handyman.command.Context = {
    val esUpdateAsIs: in.handyman.dsl.EsUpdate = action.asInstanceOf[in.handyman.dsl.EsUpdate]
    val esUpdate: in.handyman.dsl.EsUpdate = CommandProxy.createProxy(esUpdateAsIs, classOf[in.handyman.dsl.EsUpdate], context)

    
    val id = context.getValue("process-id")
    val name=esUpdate.getName
    val source=esUpdate.getSource
    val target=esUpdate.getTarget
    val fetchSize=esUpdate.getFetchSize
    val writeSize=esUpdate.getWriteSize
    val threadCount=esUpdate.getWriteThreadCount
    val sqlList=esUpdate.getValue
    logger.info(s"EsUpdate action input variables id:$id,name: $name, source-database:$source, target-database:$target ")
    logger.info(s"EsUpdate Sql input post parameter ingestion \n :$sqlList")
    val sourceConnection = ResourceAccess.rdbmsConn(source)
    val stmt = sourceConnection.createStatement
    val iter = sqlList.iterator
    try {
      while (iter.hasNext) {
        val sqlWithoutQuotes = iter.next.replaceAll("\"", "")
        val sqlList = sqlWithoutQuotes.split(";")
        sqlList.foreach { sql =>
          if (!sql.trim.isEmpty()) {
            val sqlToExecute = sql.trim
            logger.info(s"EsUpdate with id:$id, executing script $sqlToExecute")
            val statementId = AuditService.insertStatementAudit(actionId, "esupdate->"+name, context.getValue("process-name"))
            try {
             
             
             
             
             
              stmt.clearWarnings();
            } catch {
              
              case ex:SQLSyntaxErrorException=>{
                logger.error(s"Stopping execution, General Error executing sql:$sql ", ex)
                detailMap.put(sql.trim + ".exception", ExceptionUtil.completeStackTraceex(ex))
                throw ex
              }
              
              case ex: SQLException => {
                logger.error(s"Stopping execution, General Error executing sql:$sql ", ex)
                detailMap.put(sql.trim + ".exception", ExceptionUtil.completeStackTraceex(ex))
                
              }
              
              case ex: Throwable => {
                 logger.error(s"Stopping execution, General Error executing sql:$sql ", ex)
                detailMap.put(sql.trim + ".exception", ExceptionUtil.completeStackTraceex(ex))
                throw ex
              }
            }
          }
        }
      }
      
      logger.info(s"Completed EsUpdate id:$id, name:$name, source:$source,target:$target, sqlList:$sqlList")
    } finally {
      stmt.close
      sourceConnection.close
    }

    context
  }

  def executeIf(context: in.handyman.command.Context, action: in.handyman.dsl.Action): Boolean = {
    val esUpdateAsIs: in.handyman.dsl.EsUpdate = action.asInstanceOf[in.handyman.dsl.EsUpdate]
    val esUpdate: in.handyman.dsl.EsUpdate = CommandProxy.createProxy(esUpdateAsIs, classOf[in.handyman.dsl.EsUpdate], context)
    val expression: in.handyman.dsl.Expression = esUpdate.getCondition
    try {
      val output = ParameterisationEngine.doYieldtoTrue(expression)
      detailMap.putIfAbsent("condition-output", output.toString())
      output
    } finally {
      if (expression != null)
        detailMap.putIfAbsent("condition", "LHS=" + expression.getLhs + ", Operator=" + expression.getOperator + ", RHS=" + expression.getRhs)

    }
    true
  }

  def generateAudit(): java.util.Map[String, String] = {
    detailMap
  }
}