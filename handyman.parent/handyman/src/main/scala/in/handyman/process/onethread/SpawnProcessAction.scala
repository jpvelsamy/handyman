package in.handyman.process.onethread

import com.typesafe.scalalogging.LazyLogging
import in.handyman.command.{CommandProxy, Context}
import in.handyman.dsl.Action
import in.handyman.server.ProcessAST
import in.handyman.util.ParameterisationEngine

import java.util.concurrent.{ExecutorService, Executors}

class SpawnProcessAction extends in.handyman.command.Action with LazyLogging {
  val detailMap = new java.util.HashMap[String, String]
  val executor: ExecutorService = Executors.newCachedThreadPool();

  def generateAudit(): java.util.Map[String, String] = {
    detailMap
  }

  def executeIf(context: Context, action: Action): Boolean = {
    val spawnAsIs = action.asInstanceOf[in.handyman.dsl.Spawnprocess]
    val spawn: in.handyman.dsl.Spawnprocess = CommandProxy.createProxy(spawnAsIs, classOf[in.handyman.dsl.Spawnprocess], context)

    val id = context.getValue("process-id")
    val expression = spawn.getCondition
    try {
      val output = ParameterisationEngine.doYieldtoTrue(expression)
      logger.info(s"Completed evaluation to execute id:$id, expression:$expression for spawn action")
      detailMap.putIfAbsent("condition-output", output.toString())
      output
    } finally {
      if (expression != null)
        detailMap.putIfAbsent("condition", "LHS=" + expression.getLhs + ", Operator=" + expression.getOperator + ", RHS=" + expression.getRhs)
    }

  }

  def execute(context: Context, action: Action, actionId: Integer): Context = {
    val spawnAsIs = action.asInstanceOf[in.handyman.dsl.Spawnprocess]
    val spawn: in.handyman.dsl.Spawnprocess = CommandProxy.createProxy(spawnAsIs, classOf[in.handyman.dsl.Spawnprocess], context)
    val name = spawn.getName
    val fileRelativePath: String = spawn.getSource
    val instanceName: String = spawn.getTarget
    val id = context.getValue("process-id")

    logger.info(s"Spawnprocess id:$id, name:$name, calledprocess:$instanceName, calledfile:$fileRelativePath")
    val runtimeContext = ProcessAST.loadProcessAST(instanceName, "{}")
    val runMode = "in.handyman.process.onethread"
    val processWorker = new SpawnProcessCallable(runtimeContext);
    this.executor.submit(processWorker)
    context
  }

}
