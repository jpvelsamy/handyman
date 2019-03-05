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

class MailAction extends in.handyman.command.Action with LazyLogging {

  val detailMap = new java.util.HashMap[String, String]
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

    try {
      while (rs.next()) {
        val targetSubject = rs.getString("subject")
        val targetEmail = rs.getString("email")
        val body = rs.getString("body")
        val request = new Request()
        val from = new Email(asUser);        
        val to = new Email(targetEmail);
        val content = new Content("text/plain", body);
        val mail = new Mail(from, targetSubject, to, content);
        try {
          request.setMethod(Method.POST);
          request.setEndpoint("mail/send");
          request.setBody(mail.build());
          val response = sg.api(request);
          detailMap.put(targetEmail, response.getBody)
        } catch {
          case ex: Throwable => {
            ex.printStackTrace()
          }
        }

      }
    } finally {

      detailMap.put("dbSrc", dbSrc)
      detailMap.put("impersonateduser", asUser)
      detailMap.put("keypath", securityKey)

      detailMap.put("sql", sql)
      stmt.close
      conn.close
    }
    context
  }

  def executeIf(context: in.handyman.command.Context, action: in.handyman.dsl.Action): Boolean = {
    val mailAsIs: in.handyman.dsl.SendMail = action.asInstanceOf[in.handyman.dsl.SendMail]
    val mail: in.handyman.dsl.SendMail = CommandProxy.createProxy(mailAsIs, classOf[in.handyman.dsl.SendMail], context)
    val expression = mail.getCondition
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
    this.detailMap
  }

}