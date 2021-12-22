package in.handyman.process.onethread

import java.io.Serializable
import scala.beans.{ BeanProperty, BooleanBeanProperty }
//remove if not needed
import scala.collection.JavaConversions._
import java.util.Date
import java.util.ArrayList

@SerialVersionUID(6636717210313067637L)
class CalendarEvent extends Serializable {

  @BeanProperty
  var calId: String = "NA"

  @BeanProperty
  var interval: Int = 0

  @BeanProperty
  var eventAlertTime: String = "NA"

  @BeanProperty
  var contactDetails: ArrayList[String] = new java.util.ArrayList[String]()
  
  @BeanProperty
  var startDate:Date=new Date(System.currentTimeMillis())
  
  @BeanProperty
  var endDate:String="NA"
  
  

}