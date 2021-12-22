package in.handyman.process.onethread

import java.io.Serializable

import scala.beans.{BeanProperty, BooleanBeanProperty}

//remove if not needed
import scala.collection.JavaConversions._

@SerialVersionUID(6636717210313067637L)
class Lead extends Serializable {

  @BeanProperty
  var email: String = "NA"

  @BeanProperty
  var fullName: String = "NA"

  @BeanProperty
  var profession: String = "NA"

  @BeanProperty
  var company: String = "NA"

  @BeanProperty
  var city: String = "NA"

  @BeanProperty
  var mobile: String = "NA"

}