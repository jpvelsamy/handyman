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
import com.sendgrid.SendGrid
import com.sendgrid.Request
import com.sendgrid.Method
import com.sendgrid.Email
import com.sendgrid.Content
import com.sendgrid.Mail
import org.slf4j.MarkerFactory

class MailAction extends in.handyman.command.Action with LazyLogging {

  val detailMap = new java.util.HashMap[String, String]
  val auditMarker = "SENDMAIL";
  val aMarker = MarkerFactory.getMarker(auditMarker);

  def execute(context: Context, action: in.handyman.dsl.Action): Context = {
    val mailAsIs: in.handyman.dsl.SendMail = action.asInstanceOf[in.handyman.dsl.SendMail]
    val mail: in.handyman.dsl.SendMail = CommandProxy.createProxy(mailAsIs, classOf[in.handyman.dsl.SendMail], context)

    val name = mail.getName
    val asUser = mail.getImpersonatedUser
    val securityKey = mail.getPrivateKey
    val dbSrc = mail.getDbSrc
    val sql = mail.getValue
    val conn = ResourceAccess.rdbmsConn(dbSrc)
    val stmt = conn.createStatement
    val rs = stmt.executeQuery(sql.trim())
    val sg = new SendGrid(securityKey);

    logger.error(aMarker, "Attempting to send email using SendMail API, configurations are, name={}, asUser={}, securitykey={}, dbsrc={}", name, asUser, securityKey, dbSrc)
    try {
      while (rs.next()) {
        val targetSubject = rs.getString("subject")
        val targetEmail = rs.getString("email")
        val body = rs.getString("body")
        if (body != null) {
          val request = new Request()
          val from = new Email(asUser);
          val to = new Email(targetEmail);
          val content = new Content("text/html", body);
          val mail = new Mail(from, targetSubject, to, content);
          logger.info(aMarker, "Sending email with contents subject={}, emailid={}, from={}", targetSubject, targetEmail, body)
          try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            val response = sg.api(request);
            detailMap.put(targetEmail, response.getBody)
          } catch {
            case ex: Throwable => {
              logger.error(aMarker, "Error sending email using SendMail API, configurations are, name={}, asUser={}, securitykey={}, dbsrc={}", name, asUser, securityKey, dbSrc)
            }
          }

        }else {
          logger.info(aMarker, "Skipping email with contents subject={}, emailid={}, from={}", targetSubject, targetEmail, body)
        }
      }
    } finally {

      detailMap.put("dbSrc", dbSrc)
      detailMap.put("impersonateduser", asUser)
      detailMap.put("keypath", securityKey)
      detailMap.put("sql", sql)
      detailMap.put("name", mail.getName)
      detailMap.put("asUser", asUser)
      detailMap.put("securityKey", securityKey)

      stmt.close
      conn.close
    }
    logger.error(aMarker, "Completed  sending email using SendMail API, configurations are, name={}, asUser={}, securitykey={}, dbsrc={}", name, asUser, securityKey, dbSrc)
    context
  }

  def executeIf(context: in.handyman.command.Context, action: in.handyman.dsl.Action): Boolean = {
    val mailAsIs: in.handyman.dsl.SendMail = action.asInstanceOf[in.handyman.dsl.SendMail]
    val mail: in.handyman.dsl.SendMail = CommandProxy.createProxy(mailAsIs, classOf[in.handyman.dsl.SendMail], context)
    val expression = mail.getCondition
    val dbSrc = mail.getDbSrc
    val name = mail.getName
    val id = context.getValue("process-id")
    try {
      val output = ParameterisationEngine.doYieldtoTrue(expression)
      logger.info(aMarker, "Completed evaluation to execute id#{}, name#{}, dbSrc#{}, expression#{}", id, name, dbSrc, expression)
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