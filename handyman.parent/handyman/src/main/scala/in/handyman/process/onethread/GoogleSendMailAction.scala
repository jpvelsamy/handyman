package org.etl.process.onethread

import com.typesafe.scalalogging.LazyLogging
import org.etl.command.CommandProxy
import org.etl.sparrow.GooglecontactPUT
import org.etl.util.ParameterisationEngine
import org.etl.util.ResourceAccess
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.jackson2.JacksonFactory
import java.io.File
import java.util.Collections
import com.google.api.services.people.v1.PeopleServiceScopes
import com.google.api.services.people.v1.model.Person
import com.google.api.services.people.v1.model.PhoneNumber
import com.google.api.services.people.v1.model.EmailAddress
import com.google.api.services.people.v1.model.Name
import com.google.api.services.people.v1.PeopleService
import java.util.concurrent.atomic.AtomicInteger
import com.google.api.services.people.v1.model.Source
import com.google.api.services.people.v1.model.Address
import com.google.api.services.people.v1.model.ProfileMetadata
import com.google.api.services.people.v1.model.Url
import com.google.api.services.gmail.Gmail
import com.google.api.services.gmail.model.Message
import javax.mail.internet.MimeMessage
import org.etl.sparrow.GoogleSendMail
import java.util.Properties
import javax.mail.Session
import javax.mail.internet.InternetAddress
import java.io.ByteArrayOutputStream
import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64

class GoogleSendMailAction extends org.etl.command.Action with LazyLogging {

  val detailMap = new java.util.HashMap[String, String]
  val JSON_FACTORY: JsonFactory = JacksonFactory.getDefaultInstance();

  //https://developers.google.com/gmail/api/quickstart/java
  def execute(context: org.etl.command.Context, action: org.etl.sparrow.Action): org.etl.command.Context = {
    val contactPut: GoogleSendMail = action.asInstanceOf[GoogleSendMail]
    val contact: org.etl.sparrow.GoogleSendMail = CommandProxy.createProxy(contactPut, classOf[org.etl.sparrow.GoogleSendMail], context)

    val dbSrc = contact.getDbSrc
    val accountId = contact.getAccount
    val mail = contact.getImpersonatedUser
    val relativePath = contact.getPtwelveFile
    val key = contact.getPrivateKey
    val project = contact.getProject
    val authStore = this.getClass.getClassLoader.getResource(relativePath).getPath
    val sql = contact.getValue
    val conn = ResourceAccess.rdbmsConn(dbSrc)
    val stmt = conn.createStatement
    val rs = stmt.executeQuery(sql)
    val httpTransport = GoogleNetHttpTransport.newTrustedTransport();

    val credential: GoogleCredential = new GoogleCredential.Builder().setTransport(httpTransport).
      setJsonFactory(JSON_FACTORY).
      setServiceAccountId(accountId).
      setServiceAccountPrivateKeyId(key).
      setServiceAccountPrivateKeyFromP12File(new File(authStore)).
      setServiceAccountProjectId(project).
      setServiceAccountScopes(Collections.singleton(PeopleServiceScopes.CONTACTS)).
      setServiceAccountUser(mail).build()

    val service = new Gmail.Builder(httpTransport, JSON_FACTORY, credential)
      .setApplicationName(project)
      .build();
    try {
      while (rs.next()) {
        val name = rs.getString("name")
        val email = rs.getString("email")
        val subject = rs.getString("subject")
        val body = rs.getString("body")
        val orderUrl = rs.getString("source")
        val content = createMimeMessage(mail, email, subject, body)
        val message = createEMail(content)
        service.users().messages().send(accountId, message).execute()
      }
    } finally {
      detailMap.put("accountId", accountId)
      detailMap.put("dbSrc", dbSrc)
      detailMap.put("mail", mail)
      detailMap.put("relativePath", relativePath)
      detailMap.put("key", key)
      detailMap.put("project", project)
      detailMap.put("authStore", authStore)
      detailMap.put("sql", sql)

      stmt.close
      conn.close
    }
    context
  }

  def createEMail(emailContent: MimeMessage): Message = {
    val buffer = new ByteArrayOutputStream();
    emailContent.writeTo(buffer);
    val bytes = buffer.toByteArray();
    val encodedEmail = Base64.encodeBase64URLSafeString(bytes);
    val message = new Message();
    message.setRaw(encodedEmail);
    return message;
  }

  def createMimeMessage(from: String, to: String, subject: String, bodyText: String): MimeMessage = {
    val props = new Properties();
    val session = Session.getDefaultInstance(props, null);

    val email = new MimeMessage(session);

    email.setFrom(new InternetAddress(from));
    email.addRecipient(
      javax.mail.Message.RecipientType.TO,
      new InternetAddress(to));
    email.setSubject(subject);
    email.setText(bodyText);
    return email;
  }

  def executeIf(context: org.etl.command.Context, action: org.etl.sparrow.Action): Boolean = {
    val contactPut: GoogleSendMail = action.asInstanceOf[GoogleSendMail]
    val contact: org.etl.sparrow.GoogleSendMail = CommandProxy.createProxy(contactPut, classOf[org.etl.sparrow.GoogleSendMail], context)
    val expression = contact.getCondition
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