package in.handyman

import in.handyman.command.Context

//https://stackoverflow.com/questions/38243530/custom-exception-in-scala
class AbortException (message: String, action:String, context:Context) extends Exception(message) {

  
  
  def this(message: String, cause: Throwable, action:String, context:Context) {
    this(message, action, context)
    initCause(cause)
  }

  def this(cause: Throwable, action:String, context:Context) {
    this(Option(cause).map(_.toString).orNull, cause, action, context)
  }

  def this() {
    this(null: String, null, null, null)
  }
} 
