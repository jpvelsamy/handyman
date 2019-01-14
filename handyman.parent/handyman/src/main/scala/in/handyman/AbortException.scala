package in.handyman

//https://stackoverflow.com/questions/38243530/custom-exception-in-scala
class AbortException (message: String) extends Exception(message) {

  def this(message: String, cause: Throwable) {
    this(message)
    initCause(cause)
  }

  def this(cause: Throwable) {
    this(Option(cause).map(_.toString).orNull, cause)
  }

  def this() {
    this(null: String)
  }
} 
