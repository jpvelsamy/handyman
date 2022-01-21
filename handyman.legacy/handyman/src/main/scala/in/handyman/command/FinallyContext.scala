package in.handyman.command

import com.fasterxml.jackson.databind.ObjectMapper

class FinallyContext(val errorContext:ErrorContext) extends Context {
  def getValue(variable: String): String = {
    errorContext.getValue(variable)
  }
  def getMe():java.util.Map[String,String] = {
    errorContext.getMe
  }

  def getMeAsIs():Map[String, String] = {
    errorContext.getMeAsIs
  }
  def addValue(key: String, value: String): Unit = {
    
  }
  
    def getJson(): String = {
    val jsonSerializer = new ObjectMapper
    jsonSerializer.writeValueAsString(getMe())
  }
  
}