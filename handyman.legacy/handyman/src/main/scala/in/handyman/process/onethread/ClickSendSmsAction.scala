package in.handyman.process.onethread

import com.clicksend.ClickSendClient
import com.clicksend.models.{SmsMessage, SmsMessageCollection}
import com.fasterxml.jackson.core.JsonProcessingException
import com.typesafe.scalalogging.LazyLogging
import in.handyman.command.{CommandProxy, Context}
import in.handyman.dsl.Action
import in.handyman.util.{ParameterisationEngine, ResourceAccess}
import org.slf4j.MarkerFactory
import scala.collection.JavaConverters._
import java.util
import java.util.Optional
import scala.collection.convert.ImplicitConversions.`collection AsScalaIterable`

class ClickSendSmsAction extends in.handyman.command.Action with LazyLogging {
  val detailMap = new java.util.HashMap[String, String]
  val auditMarker = "CLICK_SEND";
  val aMarker = MarkerFactory.getMarker(auditMarker);

  def execute(context: Context, action: Action, actionId: Integer): Context = {

    val clickSendSmsAsIs: in.handyman.dsl.ClickSendSms = action.asInstanceOf[in.handyman.dsl.ClickSendSms]
    val clickSendSms: in.handyman.dsl.ClickSendSms = CommandProxy.createProxy(clickSendSmsAsIs, classOf[in.handyman.dsl.ClickSendSms], context)
    val source = clickSendSms.getTarget
    val name = clickSendSms.getName

    val sql = clickSendSms.getValue.trim

    val id = context.getValue("process-id")
    val sqlList = sql.split(";")

    logger.info(aMarker, "click-send id#{}, name#{}, sql#{}, db=#{}", id, name, sqlList, source)
    val conn = ResourceAccess.rdbmsConn(source)
    val stmt = conn.createStatement

    val clicks: util.ArrayList[ClickSendMsg] = new util.ArrayList[ClickSendMsg]()

    try {


      sqlList.foreach(sqlItem => {
        logger.info(aMarker, "Execution query sql#{} on db=#{}", sqlItem.trim(), source)
        val rs = stmt.executeQuery(sqlItem.trim)
        val columnCount = rs.getMetaData.getColumnCount

        while (rs.next()) {

          val map: java.util.Map[String, String] = new util.HashMap()

          for (i <- 1 until columnCount + 1) {
            val key = rs.getMetaData.getColumnLabel(i)
            val value = rs.getString(i)
            logger.info(aMarker, "Adding value {} for key {} from query sql#{} on db=#{}", value, name + "." + key, sqlItem, source)
            map.put(key, value)
          }

          val countryCode = Optional.ofNullable(map.get("country_code")).orElseThrow()
          val targetMobileNumber = Optional.ofNullable(map.get("target_mobile_number")).orElseThrow()
          val targetMessage = Optional.ofNullable(map.get("target_message")).orElseThrow()
          clicks.add(new ClickSendMsg(countryCode = countryCode, mobileNumber = targetMobileNumber, message = targetMessage))
        }

        detailMap.put("source", source)
        detailMap.put("sql", sqlItem)
      })
    } finally {

      stmt.close()
      conn.close()
    }
    logger.info("Completed fetch id#{}, name#{}, sql#{}, db=#{}", id, name, sql, source)

    val clickSendClient = new ClickSendClient(clickSendSms.getUserid, clickSendSms.getSecurityKey)
    val smsMessages = new SmsMessageCollection
    val list = clicks.map(x => createMsg(x)).toList.asJava
    smsMessages.setMessages(new util.ArrayList[SmsMessage](list))
    val str = clickSendClient.getSMS.sendSms(smsMessages)
    print(str)
    context
  }

  private def createMsg(click: ClickSendSmsAction.this.ClickSendMsg) = {
    val msg = new SmsMessage
    val message = click.getMessage
    try {

      val cc: String = Optional.ofNullable(click.getCountryCode).filter(s => !s.isBlank).orElseThrow(() => new UninitializedFieldError())
      val to: String = Optional.ofNullable(click.getMobileNumber).filter(s => !s.isBlank).orElseThrow(() => new UninitializedFieldError())

      logger.info("Sending sms to {} with content {}", to, message)

      msg.setBody(message)
      msg.setCountry(cc)
      msg.setTo(to)

    } catch {
      case e: JsonProcessingException =>
        logger.info("Error Sending sms to {} with content {}", click.getMobileNumber, message, e)
      case e: Throwable =>
        logger.info("Error Sending sms to {} with content {}", click.getMobileNumber, message,
          e)
    }

    msg
  }

  def executeIf(context: Context, action: Action): Boolean = {
    val clickSendSmsAsIs: in.handyman.dsl.ClickSendSms = action.asInstanceOf[in.handyman.dsl.ClickSendSms]
    val clickSendSms: in.handyman.dsl.ClickSendSms = CommandProxy.createProxy(clickSendSmsAsIs, classOf[in.handyman.dsl.ClickSendSms], context)

    val expression = clickSendSms.getCondition
    try {
      val output = ParameterisationEngine.doYieldtoTrue(expression)
      detailMap.putIfAbsent("condition-output", output.toString())
      output
    } finally {
      if (expression != null)
        detailMap.putIfAbsent("condition", "LHS=" + expression.getLhs + ", Operator=" + expression.getOperator + ", RHS=" + expression.getRhs)

    }
  }

  def generateAudit(): java.util.Map[String, String] = {
    detailMap
  }

  class ClickSendMsg(countryCode: String, mobileNumber: String, message: String) {

    def getCountryCode: String = {
      this.countryCode
    }

    def getMobileNumber: String = {
      this.mobileNumber
    }

    def getMessage: String = {
      this.message
    }

  }

}