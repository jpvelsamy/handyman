package in.handyman.command

import com.fasterxml.jackson.databind.ObjectMapper

import java.io.{PrintWriter, StringWriter}

class ErrorContext(val tryContext:Context) extends Context {
  
  private[this] var executionException:Throwable=null 
  def getValue(variable: String): String = {
    tryContext.getValue(variable)
  }
  def getMe():java.util.Map[String,String] = {
    tryContext.getMe
  }
  def getMeAsIs():Map[String, String] = {
    tryContext.getMeAsIs
  }
  def addValue(key: String, value: String): Unit = {
    tryContext.addValue(key, value)
  }
  def exception = executionException
  def exception_=(t:Throwable) {
    // write require condition if you need require(0 <= x && x < 60)
    executionException=t
  }
  
  def completeStackTrace():String={
    val errors = new StringWriter();
    val print = new PrintWriter(errors)
    if(executionException!=null) executionException.printStackTrace(print) else print.print("NONE");
    errors.toString();
    
  }

  def getJson(): String = {
    val jsonSerializer = new ObjectMapper
    jsonSerializer.writeValueAsString(getMe())
  }
  
}
