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
import java.util.concurrent.atomic.AtomicInteger
import in.handyman.audit.AuditService
import java.sql.SQLException
import scala.util.control.Exception.Finally
import java.util.{ Date, Properties }
import javax.mail._
import javax.mail.internet.{ InternetAddress, MimeMessage }
import java.sql.Statement

class EMailAction extends in.handyman.command.Action with LazyLogging {

  val detailMap = new java.util.HashMap[String, String]
  val auditMarker = "SENDMAIL";
  val aMarker = MarkerFactory.getMarker(auditMarker);

  def execute(context: Context, action: in.handyman.dsl.Action, actionId: Integer): Context = {
    val mailAsIs: in.handyman.dsl.Mail = action.asInstanceOf[in.handyman.dsl.Mail]
    val mail: in.handyman.dsl.Mail = CommandProxy.createProxy(mailAsIs, classOf[in.handyman.dsl.Mail], context)

    val source = mail.getSource
    
    val name = mail.getName
    
    
    val id = context.getValue("process-id")
    val payloadID = context.getValue("root-ref")
    val sql: String = mail.getValue.replaceAll("\"", "")/* + payloadID + ";"*/
    var from = ""
    var pass = ""
    var subject = /*mail.getSubject*/""
    var content = ""
    var to = ""
    var cc = ""
    var bcc = ""
    var ptype = ""
    var st: Statement = null

    try {
      
      //subject = id 
      val con = ResourceAccess.rdbmsConn(source)
      st = con.createStatement()
      con.setAutoCommit(false)
      
      val rs = st.executeQuery(sql)
      while(rs.next)
      {
        from = rs.getString("sender")
        pass = rs.getString("securityKey")
        subject = rs.getString("subject") /*+ " with id " + payloadID +"-"+ rs.getString("status")*/
        to = rs.getString("to_email")
        cc = rs.getString("cc")
        bcc = rs.getString("bcc")
        content = rs.getString("body")
        ptype = rs.getString("Process_type")
      }
      var procinfoquery = "select Process_id,Name,ProcessStatus from dropbox where Process_id=(select max(Process_id) from dropbox where Name='"+ptype+"')"+" and Name ='"+ptype+"';"
      var fetchLatestProcessInfo = st.executeQuery(procinfoquery)
      var rerun = 0;
      while(fetchLatestProcessInfo.next)
      {
        /*rerun = fetchLatestProcessInfo.getString("Rerun_Count").toInt +1*/
        subject = subject +"- "+ fetchLatestProcessInfo.getString("Name") + " Status" 
        if(fetchLatestProcessInfo.getString("ProcessStatus").equals("Complete"))
        {
          content = content.replace("Process_Name", fetchLatestProcessInfo.getString("Name")).replace("Process_id", fetchLatestProcessInfo.getString("Process_id")).replace("Process_Status","<b><font color = \"green\">"+fetchLatestProcessInfo.getString("ProcessStatus")+"</font></b>.")
        }
        else
        {
          content = content.replace("Process_Name", fetchLatestProcessInfo.getString("Name")).replace("Process_id", fetchLatestProcessInfo.getString("Process_id")).replace("Process_Status","<b><font color = \"red\">"+fetchLatestProcessInfo.getString("ProcessStatus")+"</font></b>.")
        }
         
        /*if(fetchLatestProcessInfo.getString("ProcessStatus").equals("Complete")) 
        {
          content = content+" and was successful on the "+rerun+"th attempt."
        }*/
        
      }
      
      
      var message: Message = null

      message = createMessage
      message.setFrom(new InternetAddress(from))
      setToCcBccRecipients

      message.setSentDate(new Date())
      message.setSubject(subject)
      //message.setText(content)
      //message.setText(content)
      message.setContent(content,"text/html;charset=utf-8");
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
          override protected def getPasswordAuthentication = new PasswordAuthentication(from, pass)
        });
        return new MimeMessage(session)
      }

      // throws AddressException, MessagingException
      def setToCcBccRecipients {
        setMessageRecipients(to, Message.RecipientType.TO)
        if (cc != null /*|| bcc!="" ||*/ && cc.length()>0) {
          setMessageRecipients(cc, Message.RecipientType.CC)
        }
        if (bcc != null /*|| bcc!="" ||*/ && bcc.length()>0) {
          setMessageRecipients(bcc, Message.RecipientType.BCC)
        }
        logger.info("To - " +to)
        logger.info("Copy to -" +cc)
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
    val mailAsIs: in.handyman.dsl.Mail = action.asInstanceOf[in.handyman.dsl.Mail]
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
    this.detailMap
  }

}