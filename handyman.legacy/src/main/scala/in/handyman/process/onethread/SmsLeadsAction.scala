package in.handyman.process.onethread

import com.typesafe.scalalogging.LazyLogging
import in.handyman.command.Context
import in.handyman.command.CommandProxy
import in.handyman.util.ParameterisationEngine
import in.handyman.util.ResourceAccess
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.client.methods.HttpGet
import java.net.URLEncoder
import org.apache.commons.text.StrSubstitutor
import org.slf4j.MarkerFactory
import in.handyman.audit.AuditService
import java.util.concurrent.atomic.AtomicInteger
import org.apache.http.impl.nio.client.HttpAsyncClients

//https://hc.apache.org/httpcomponents-asyncclient-ga/quickstart.html
class SmsLeadsAction extends in.handyman.command.Action with LazyLogging {

  val detailMap = new java.util.HashMap[String, String]
  val auditMarker = "SENDSMS";
  val aMarker = MarkerFactory.getMarker(auditMarker);
  val client = HttpAsyncClients.createDefault()
  client.start();
  
  def execute(context: Context, action: in.handyman.dsl.Action, actionId: Integer): Context = {
    val smsAsIs: in.handyman.dsl.SmsLeadSms = action.asInstanceOf[in.handyman.dsl.SmsLeadSms]
    val sms: in.handyman.dsl.SmsLeadSms = CommandProxy.createProxy(smsAsIs, classOf[in.handyman.dsl.SmsLeadSms], context)
    
    
    val name = sms.getName
    val sender = sms.getSender
    val url = sms.getUrl
    val dbSrc = sms.getDbSrc
    val user = sms.getAccount
    val password = sms.getPrivateKey
    val dryRun = sms.getDryrunNumber
    val sql = sms.getValue
    val conn = ResourceAccess.rdbmsConn(dbSrc)
    val stmt = conn.createStatement
    val rs = stmt.executeQuery(sql.trim())
    var urlString: String = ""

    logger.info(aMarker, "Executing query to retreive the essentials {}", sql.trim())

    val incomingSMSReq: AtomicInteger = new AtomicInteger
    val sentSMSCount: AtomicInteger = new AtomicInteger

    val statementId = AuditService.insertStatementAudit(actionId, "sms->" + name, context.getValue("process-name"))

    try {
      while (rs.next()) {
        incomingSMSReq.incrementAndGet()
        val targetMobileNumber = rs.getString("target_mobile_number")
        val targetAltNumber = rs.getString("target_alternate_number")
        val body = rs.getString("body")

        val mobile = {
          if (dryRun != null && !dryRun.isEmpty())
            dryRun.trim
          else {
            if (targetMobileNumber.equals(targetAltNumber)) {
              removePlusNineOne(targetMobileNumber)
            } else if (targetAltNumber.trim().isEmpty()){
              removePlusNineOne(targetMobileNumber)
            }
            else {
              removePlusNineOne(targetMobileNumber) + "," + removePlusNineOne(targetAltNumber)
            }
          }
        }

        val output = body
        if (output != null) {
          val encodedMessage = URLEncoder.encode(output)
          urlString = url + "username=" + user + "&password=" + password + "&sender=" + sender + "&numbers=" + mobile + "&message=" + encodedMessage
          val request = new HttpGet(urlString);
          request.addHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36");
          request.addHeader("Upgrade-Insecure-Requests", "1")
          request.addHeader("Host", "smsleads.in")
          request.addHeader("Accept-Language", "en-IN,en-GB;q=0.9,en-US;q=0.8,en;q=0.7")
          request.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
          val response = client.execute(request, null)
          
          sentSMSCount.incrementAndGet()
          logger.info(aMarker, "Sent sms using url {} with responsecode {}", urlString, response)

        } else {
          logger.info(aMarker, "Skipping sending sms using url {} with username {} as there is not text", url, user)
        }
      }
    } finally {
      detailMap.put("name", name)
      detailMap.put("dbSrc", dbSrc)
      detailMap.put("user", user)
      detailMap.put("password", password)
      detailMap.put("target", dryRun)
      detailMap.put("url", urlString)
      detailMap.put("sender", sender)
      detailMap.put("sql", sql)
      detailMap.put("incomingSMSReq", incomingSMSReq.intValue().toString())
      detailMap.put("sentSMSCount", sentSMSCount.intValue().toString())
      AuditService.updateStatementAudit(statementId, incomingSMSReq.intValue(), sentSMSCount.intValue(), sql, 1)
      stmt.close
      conn.close
    }
    context
  }

  def removePlusNineOne(number: String): String = {
    if (number.startsWith("+91"))
      number.substring(3, number.length())
    else
      number
  }

  def executeIf(context: in.handyman.command.Context, action: in.handyman.dsl.Action): Boolean = {
    val smsAsIs: in.handyman.dsl.SmsLeadSms = action.asInstanceOf[in.handyman.dsl.SmsLeadSms]
    val sms: in.handyman.dsl.SmsLeadSms = CommandProxy.createProxy(smsAsIs, classOf[in.handyman.dsl.SmsLeadSms], context)
    val expression = sms.getCondition
    try {
      val output = ParameterisationEngine.doYieldtoTrue(expression)
      val name = sms.getName
      val id = context.getValue("process-id")
      logger.info(aMarker, "Completed evaluation to execute id#{}, name#{}, dbSrc#{}, expression#{}", id, name, expression)
      detailMap.put("name", name)
      detailMap.putIfAbsent("condition-output", output.toString())
      output
    } finally {
      if (expression != null)
        detailMap.putIfAbsent("condition", "LHS=" + expression.getLhs + ", Operator=" + expression.getOperator + ", RHS=" + expression.getRhs)

    }
  }

  def generateAudit(): java.util.Map[String, String] = {
    this.detailMap
  }

}