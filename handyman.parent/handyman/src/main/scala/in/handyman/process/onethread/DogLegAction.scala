package in.handyman.process.onethread

import com.typesafe.scalalogging.LazyLogging
import in.handyman.command.{CommandProxy, Context}
import in.handyman.server.ProcessAST
import in.handyman.util.ParameterisationEngine

import java.util.concurrent.{CountDownLatch, ExecutorService, Executors}

class DogLegAction extends in.handyman.command.Action with LazyLogging {

  val detailMap: java.util.Map[String, String] = new java.util.HashMap[String, String]
  val executor: ExecutorService = Executors.newCachedThreadPool();

  def execute(context: Context, action: in.handyman.dsl.Action, actionId: Integer): Context = {
    val dogLegAsIs: in.handyman.dsl.Dogleg = action.asInstanceOf[in.handyman.dsl.Dogleg]
    val dogLeg: in.handyman.dsl.Dogleg = CommandProxy.createProxy(dogLegAsIs, classOf[in.handyman.dsl.Dogleg], context)

    val name = dogLeg.getName
    val id = context.getValue("process-id")
    val inheritContext = {
      if (dogLeg.getInheritContext.equals("true"))
        true
      else
        false
    }
    val processList = dogLeg.getProcessList
    logger.info(s"DogLeg id:$id, name:$name")

    val countDownLatch: CountDownLatch = new CountDownLatch(processList.size());
    if (inheritContext) {
      processList.forEach(process => {
        val processName = process.getName
        val fileRelativePath = process.getTarget
        val runtimeContext = ProcessAST.loadProcessAST(processName, fileRelativePath, context)
        val processWorker = new ForkProcessCallable(runtimeContext, countDownLatch);
        this.executor.submit(processWorker)
      })
    } else {
      processList.forEach(process => {
        val instanceName = process.getName
        val fileRelativePath = process.getTarget
        val runtimeContext = ProcessAST.loadProcessAST(instanceName, "{}")
        val runMode = "in.handyman.process.onethread"
        val processWorker = new SpawnProcessCallable(runtimeContext);
        this.executor.submit(processWorker)
      })
    }
    try {
      countDownLatch.await();
    } catch {
      case ex: InterruptedException => {
        ex.printStackTrace()
        throw ex
      }
    }
    context
  }

  def executeIf(context: in.handyman.command.Context, action: in.handyman.dsl.Action): Boolean = {
    val dogLegAsIs: in.handyman.dsl.Dogleg = action.asInstanceOf[in.handyman.dsl.Dogleg]
    val dogLeg: in.handyman.dsl.Dogleg = CommandProxy.createProxy(dogLegAsIs, classOf[in.handyman.dsl.Dogleg], context)

    val expression = dogLeg.getCondition
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

  def handleError(ex: Throwable) = {
    logger.info("Error section of dogleg ", ex)
    throw ex
  }
}
