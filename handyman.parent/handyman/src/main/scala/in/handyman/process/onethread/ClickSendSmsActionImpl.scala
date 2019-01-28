package org.etl.process.onethread

import com.typesafe.scalalogging.LazyLogging
import org.etl.command.CommandProxy
import org.etl.util.ResourceAccess
import com.clicksend.ClickSendClient
import com.clicksend.models.SmsMessage
import com.clicksend.models.SmsMessageCollection
import java.util.ArrayList
import org.etl.util.ParameterisationEngine
import java.util.HashMap
import org.apache.commons.text.StrSubstitutor
import org.etl.config.ConfigurationService
import java.util.concurrent.atomic.AtomicInteger

class SmsActionImpl extends org.etl.command.Action with LazyLogging {

  val detailMap = new java.util.HashMap[String, String]

  def execute(context: org.etl.command.Context, action: org.etl.sparrow.Action): org.etl.command.Context = {
    val smsAsIs: org.etl.sparrow.ClickSendSms = action.asInstanceOf[org.etl.sparrow.ClickSendSms]
    val sms: org.etl.sparrow.ClickSendSms = CommandProxy.createProxy(smsAsIs, classOf[org.etl.sparrow.ClickSendSms], context)

    val userId = sms.getUserid
    val securityKey = sms.getSecurityKey
    val sql = sms.getValue
    val dbSrc = sms.getTarget
    val shouldDryRun = ConfigurationService.getGlobalconfig().get("should_dryrun_sms").getOrElse("false")
    val dryRunNum = ConfigurationService.getGlobalconfig().get("dryrun_num").getOrElse("")
    val domainName = ConfigurationService.getGlobalconfig().get("target_domain").getOrElse("")

    detailMap.put("userId", userId)
    detailMap.put("securityKey", securityKey)
    detailMap.put("sql", sql)
    detailMap.put("dbSrc", dbSrc)
    detailMap.put("shouldDryRun", shouldDryRun)
    detailMap.put("dryRunNum", dryRunNum)
    detailMap.put("domainName", domainName)

    val conn = ResourceAccess.rdbmsConn(dbSrc)
    val stmt = conn.createStatement
    val rs = stmt.executeQuery(sql)
    val clickSendClient = new ClickSendClient(userId, securityKey);

    val smsController = clickSendClient.getSMS();
    val messageCollection = new SmsMessageCollection();
    val messages = new ArrayList[SmsMessage]();
    val incomingSms: AtomicInteger = new AtomicInteger
    val addedCalenderSms: AtomicInteger = new AtomicInteger

    try {
      while (rs.next()) {
        incomingSms.incrementAndGet
        val comment = rs.getString("comment")
        val targetName = rs.getString("target_full_name")
        val targetStatus = rs.getString("target_status")
        val targetId = rs.getInt("target_id")
        val targetDescription = rs.getString("target_description")
        val targetLocation = rs.getString("target_location")
        val targetMobileNumber = rs.getString("target_mobile_number")
        val targetAltNumber = rs.getString("target_alternate_number")
        val targetCity = rs.getString("target_city")
        val targetBudget = rs.getString("target_budget")

        val body = rs.getString("body")

        val paramMap: java.util.Map[String, String] = new java.util.HashMap[String, String]
        paramMap.put("target_full_name", targetName)
        paramMap.put("target_status", targetStatus)
        paramMap.put("target_id", targetId.toString())
        paramMap.put("target_description", targetDescription)
        paramMap.put("target_location", targetLocation)
        paramMap.put("target_mobile_number", targetMobileNumber)
        paramMap.put("target_alternate_number", targetAltNumber)
        paramMap.put("target_city", targetCity)
        paramMap.put("target_budget", targetBudget)
        paramMap.put("target_domain", domainName)

        val mobile = {
          if (shouldDryRun.equals("true"))
            dryRunNum.trim
          else
            rs.getString("mobile")
        }
        val country = rs.getString("country")
        val source = rs.getString("source")

        val paramEngine = new StrSubstitutor(paramMap)
        val output = paramEngine.replace(body)
        val smsMessage = new SmsMessage();
        smsMessage.setSource(source);
        smsMessage.setBody(output);
        smsMessage.setTo(mobile);
        smsMessage.setCountry(country);
        messages.add(smsMessage)
      }
      messageCollection.setMessages(messages)
      val response = smsController.sendSms(messageCollection);
      addedCalenderSms.incrementAndGet
    } finally {
      detailMap.put("userId", userId)
      detailMap.put("securityKey", securityKey)
      detailMap.put("sql", sql)
      detailMap.put("dbSrc", dbSrc)
      detailMap.put("shouldDryRun", shouldDryRun)
      detailMap.put("dryRunNum", dryRunNum)
      detailMap.put("domainName", domainName)
      stmt.close
      conn.close
    }
    context
  }

  def executeIf(context: org.etl.command.Context, action: org.etl.sparrow.Action): Boolean = {
    val smsAsIs: org.etl.sparrow.ClickSendSms = action.asInstanceOf[org.etl.sparrow.ClickSendSms]
    val sms: org.etl.sparrow.ClickSendSms = CommandProxy.createProxy(smsAsIs, classOf[org.etl.sparrow.ClickSendSms], context)

    val expression = sms.getCondition
    try {
      val output = ParameterisationEngine.doYieldtoTrue(expression)
      detailMap.putIfAbsent("condition-output", output.toString())
      output
    } finally {
       if(expression!=null)
      detailMap.putIfAbsent("condition", "LHS=" + expression.getLhs + ", Operator=" + expression.getOperator + ", RHS=" + expression.getRhs)

    }
  }

  def generateAudit(): java.util.Map[String, String] = {
    detailMap
  }
}