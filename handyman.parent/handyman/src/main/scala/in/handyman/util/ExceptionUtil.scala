package in.handyman.util

import java.io.{PrintWriter, StringWriter}

object ExceptionUtil {
  
  def completeStackTraceex(executionException:Throwable):String={
    val errors = new StringWriter();
    val print = new PrintWriter(errors)
    if(executionException!=null) executionException.printStackTrace(print) else print.print("NONE");
    errors.toString();
    
  }
  
  def completeSQLWarning(sqlWarnings:java.sql.SQLWarning):String={
    val errors = new StringWriter();
    val print = new PrintWriter(errors);
    if(sqlWarnings!=null) sqlWarnings.printStackTrace(print) else print.print("NONE");
    errors.toString();
  }
}
