package in.handyman.raven.lib

import in.handyman.raven.lambda.action.{ActionExecution, IActionExecution}

@ActionExecution(actionName = "Test")
class Test extends IActionExecution {

  override def execute(): Unit = {
    println("IActionExecution");
  }

  override def executeIf(): Boolean = true;
}
