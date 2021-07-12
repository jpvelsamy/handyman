package in.handyman.server

import in.handyman.command.Context

import scala.beans.BeanProperty

class ProcessResponse extends Serializable{
  
   @BeanProperty
  var context:Context=null
  
   @BeanProperty
  var detailMap:java.util.HashMap[String,java.util.Map[String, String]]=null
  
  @BeanProperty
  var exception:Throwable=null
  
}
