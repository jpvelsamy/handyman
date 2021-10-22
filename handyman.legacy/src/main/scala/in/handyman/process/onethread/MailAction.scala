package in.handyman.process.onethread

import com.sendgrid.{Method, Request, SendGrid}
import com.sendgrid.helpers.mail.Mail
import com.sendgrid.helpers.mail.objects.{Content, Email}
import com.typesafe.scalalogging.LazyLogging
import in.handyman.audit.AuditService
import in.handyman.command.{CommandProxy, Context}
import in.handyman.util.{ParameterisationEngine, ResourceAccess}
import org.slf4j.MarkerFactory

import java.util.concurrent.atomic.AtomicInteger

class MailAction extends in.handyman.command.Action with LazyLogging {

  val detailMap = new java.util.HashMap[String, String]
  val auditMarker = "SENDMAIL";
  val aMarker = MarkerFactory.getMarker(auditMarker);

  def execute(context: Context, action: in.handyman.dsl.Action, actionId: Integer): Context = {
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

    val incomingMailReq: AtomicInteger = new AtomicInteger
    val sentMailCount: AtomicInteger = new AtomicInteger

    val statementId = AuditService.insertStatementAudit(actionId, "mail->" + name, context.getValue("process-name"))
    logger.error(aMarker, "Attempting to send email using SendMail API, configurations are, name={}, asUser={}, securitykey={}, dbsrc={}", name, asUser, securityKey, dbSrc)
    try {
      while (rs.next()) {
        incomingMailReq.incrementAndGet()
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
            sentMailCount.incrementAndGet()
            detailMap.put(targetEmail, response.getBody)
          } catch {
            case ex: Throwable => {
              logger.error(aMarker, "Error sending email using SendMail API, configurations are, name={}, asUser={}, securitykey={}, dbsrc={}", name, asUser, securityKey, dbSrc)
              detailMap.put(name + ".exception", ex.getMessage)
            }
          }

        } else {
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
      detailMap.put("incomingMailReq", incomingMailReq.intValue().toString())
      detailMap.put("sentMailCount", sentMailCount.intValue().toString())
      AuditService.updateStatementAudit(statementId, incomingMailReq.intValue(), sentMailCount.intValue(), sql, 1)
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
