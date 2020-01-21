package in.handyman.process.onethread

import com.typesafe.scalalogging.LazyLogging
import in.handyman.command.Context
import in.handyman.command.Action
import in.handyman.command.CommandProxy
import in.handyman.util.ResourceAccess
import org.eclipse.emf.common.util.EList
import in.handyman.util.ParameterisationEngine
import java.util.ArrayList
import java.sql.SQLException
import scala.util.control.Exception.Finally
import java.util.{ Date, Properties }
import javax.mail._
import javax.mail.internet.{ InternetAddress, MimeMessage }

class MailAction extends in.handyman.command.Action with LazyLogging {
  val detailMap = new java.util.HashMap[String, String]

  def execute(context: in.handyman.command.Context, action: in.handyman.dsl.Action): in.handyman.command.Context = {
    val mailAsIs: in.handyman.dsl.Mail = action.asInstanceOf[in.handyman.dsl.Mail]
    val mail: in.handyman.dsl.Mail = CommandProxy.createProxy(mailAsIs, classOf[in.handyman.dsl.Mail], context)

    val source = mail.getSource
    val from = mail.getFrom
    val name = mail.getName
    val sql: String = mail.getValue.replaceAll("\"", "")
    val id = context.getValue("process-id")
    val pass = mail.getPass
    val subject = mail.getSubject
    val content = mail.getBody
    val to = mail.getTo
    val cc = mail.getCc
    val bcc = mail.getBcc

    try {

      var message: Message = null

      message = createMessage
      message.setFrom(new InternetAddress(from))
      setToCcBccRecipients

      message.setSentDate(new Date())
      message.setSubject(subject)
      message.setText(content)
      sendMessage      

      // throws MessagingException
      def sendMessage {
        Transport.send(message)
      }

      def createMessage: Message = {
        val properties = new Properties()
        properties.put("mail.smtp.host", "smtp.gmail.com")
        properties.put("mail.smtp.port", "587")
        properties.put("mail.smtp.auth", "true")
        properties.put("mail.smtp.starttls.enable", "true") //TLS

        val session = Session.getInstance(properties, new Authenticator() {
          override protected def getPasswordAuthentication = new PasswordAuthentication(from,pass)
        });
        return new MimeMessage(session)
      }

      // throws AddressException, MessagingException
      def setToCcBccRecipients {
        setMessageRecipients(to, Message.RecipientType.TO)
        if (cc != null) {
          setMessageRecipients(cc, Message.RecipientType.CC)
        }
        if (bcc != null) {
          setMessageRecipients(bcc, Message.RecipientType.BCC)
        }
      }

      // throws AddressException, MessagingException
      def setMessageRecipients(recipient: String, recipientType: Message.RecipientType) {
        // had to do the asInstanceOf[...] call here to make scala happy
        val addressArray = buildInternetAddressArray(recipient).asInstanceOf[Array[Address]]
        if ((addressArray != null) && (addressArray.length > 0)) {
          message.setRecipients(recipientType, addressArray)
        }
      }

      // throws AddressException
      def buildInternetAddressArray(address: String): Array[InternetAddress] = {
        // could test for a null or blank String but I'm letting parse just throw an exception
        return InternetAddress.parse(address)
      }

    } catch {
      case ex: SQLException => {
        ex.printStackTrace()
      }
    } finally {
//      mailStmtfrom.close()
//      mailDbConnfrom.close()
      detailMap.put("name", name)
      detailMap.put("source", source)
      detailMap.put("ddlSql", sql)
    }
    context

  }

  def executeIf(context: in.handyman.command.Context, action: in.handyman.dsl.Action): Boolean = {
    val mailAsIs = action.asInstanceOf[in.handyman.dsl.Mail]
    val mail: in.handyman.dsl.Mail = CommandProxy.createProxy(mailAsIs, classOf[in.handyman.dsl.Mail], context)

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
    detailMap
  }
}