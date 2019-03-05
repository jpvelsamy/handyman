package in.handyman.process.onethread

import in.handyman.dsl._
import in.handyman.server.ProcessRuntime
import com.typesafe.scalalogging.LazyLogging
import com.fasterxml.jackson.databind.ObjectMapper
import in.handyman.command._



class UniThreadProcessRuntime(name:String, id:Int) extends ProcessRuntime with LazyLogging{
  val jsonSerializer = new ObjectMapper
  @throws(classOf[Exception])
  def execute(process:in.handyman.dsl.Process, context:Context): Unit={
    var errorContext:ErrorContext=new ErrorContext(context.asInstanceOf[TryContext])
    try {
      
     executeChain(process.getTry.getAction, context)
    } catch {
      case ex: in.handyman.AbortException =>{
        logError(ex)
        throw ex
      }
      case ex: Throwable => {
        logError(ex)
        val onError = process.getCatch
        errorContext=executeCatch(onError, context.asInstanceOf[TryContext])
        errorContext.exception=ex
        throw ex
      }
      
    } finally {
      val onFinally = process.getFinally
      executeFinally(onFinally, errorContext)
    }
    //Send context + commandDetailAsMap + exception stack trace back to client
  }
  
  @throws(classOf[Exception])
  def executeChain(actionList:org.eclipse.emf.common.util.EList[in.handyman.dsl.Action], context:Context)={
    
    val iterator = actionList.iterator
    while(iterator.hasNext)
    {
      val action = iterator.next
      val actionRuntime = CommandFactory.create(action.eClass.getName)
      if(actionRuntime.executeIf(context, action))
      {
        val actionId = in.handyman.audit.AuditService.insertCommandAudit(id, action.eClass().getName+"->"+action.getName, name)
        actionRuntime.execute(context, action)
        //TODO still need to fix the status part
        val commandDetailAsMap = actionRuntime.generateAudit()
        val commandDetail = jsonSerializer.writeValueAsString(commandDetailAsMap)
        in.handyman.audit.AuditService.updateCommandAudit(actionId, 1, commandDetail)
      }
    }
    
  }
  
  def logError(ex: Throwable) = {
    logger.error("Error executing process", ex)  
  }

  def executeCatch(onError: Catch, context:TryContext):ErrorContext = {
    val errorContext:ErrorContext = new ErrorContext(context)
    executeChain(onError.getAction, errorContext)
    errorContext
  }

  def executeFinally(onFinally: Finally, errorContext: ErrorContext):FinallyContext = {
    val finallyContext:FinallyContext = new FinallyContext(errorContext)
    executeChain(onFinally.getAction, finallyContext)
    val processId:String = errorContext.getValue("process-id")
    val contextLog:String = errorContext.getJson()
    val status:Int = if(contextLog.isEmpty) 1 else -1
    in.handyman.audit.AuditService.updateProcessAudit(Integer.parseInt(processId), status, contextLog, name)
    finallyContext
  }


}