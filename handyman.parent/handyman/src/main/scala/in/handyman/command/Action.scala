package in.handyman.command

trait Action {
  
  def execute(context: Context, action:in.handyman.dsl.Action) :Context
  
  def executeIf(context: Context, action:in.handyman.dsl.Action) :Boolean
  
  def generateAudit():java.util.Map[String,String]
}