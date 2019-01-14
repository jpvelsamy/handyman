package in.handyman.command

trait Context {
  
  def getValue(variable:String):String
  def getMe():java.util.Map[String, String]
  def getMeAsIs():Map[String, String]
  def addValue(key:String, value:String)
  def getJson():String
}