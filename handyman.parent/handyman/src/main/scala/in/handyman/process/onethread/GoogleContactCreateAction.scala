package in.handyman.process.onethread

import com.typesafe.scalalogging.LazyLogging
import in.handyman.command.CommandProxy
import in.handyman.dsl.GooglecontactPUT
import in.handyman.util.ParameterisationEngine
import in.handyman.util.ResourceAccess
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

class GContactCreateAction extends in.handyman.command.Action with LazyLogging {
  val detailMap = new java.util.HashMap[String, String]
  val JSON_FACTORY: JsonFactory = JacksonFactory.getDefaultInstance();
  def execute(context: in.handyman.command.Context, action: in.handyman.dsl.Action): in.handyman.command.Context = {
    val contactPut: GooglecontactPUT = action.asInstanceOf[GooglecontactPUT]
    val contact: in.handyman.dsl.GooglecontactPUT = CommandProxy.createProxy(contactPut, classOf[in.handyman.dsl.GooglecontactPUT], context)
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
      
    val peopleService: PeopleService =
      new PeopleService.Builder(httpTransport, JSON_FACTORY, credential).setApplicationName(project).build();

    val incomingContactCounter: AtomicInteger = new AtomicInteger
    val addedContactCounter: AtomicInteger = new AtomicInteger
    try {
      while (rs.next()) {
        incomingContactCounter.incrementAndGet

        val name = rs.getString("name")
        val email = rs.getString("email")
        val location = rs.getString("location")
        val mobile = rs.getString("mobile")
        val orderUrl = rs.getString("source")

        val person = new Person
        val phoneList = new java.util.ArrayList[PhoneNumber]
        val phone = new PhoneNumber
        phone.setValue(mobile)
        phoneList.add(phone)

        val nameList = new java.util.ArrayList[Name]
        val personName = new Name
        personName.setDisplayName(name)
        personName.setFamilyName(location)
        personName.setGivenName(name)

        nameList.add(personName)

        val emailList = new java.util.ArrayList[EmailAddress]
        val personEmail = new EmailAddress
        personEmail.setDisplayName(email)
        personEmail.setValue(email)
        emailList.add(personEmail)
        
        val sourceList = new java.util.ArrayList[Url]
        val source = new Url
        source.setValue(orderUrl)
        sourceList.add(source)

        person.setPhoneNumbers(phoneList)
        person.setNames(nameList)
        person.setEmailAddresses(emailList)
        person.setUrls(sourceList)

        val contact = peopleService.people.createContact(person)
        val output = contact.execute()
        addedContactCounter.incrementAndGet
        logger.info("Adding lead $personName with for number $mobile from $location with counter $addedContactCounter.get")
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
      detailMap.put("incomingContactCounter", incomingContactCounter.intValue.toString)
      detailMap.put("addedContactCounter", addedContactCounter.intValue.toString)
      stmt.close
      conn.close
    }
    context
  }

  def executeIf(context: in.handyman.command.Context, action: in.handyman.dsl.Action): Boolean = {
    val contactPut: GooglecontactPUT = action.asInstanceOf[GooglecontactPUT]
    val contact: in.handyman.dsl.GooglecontactPUT = CommandProxy.createProxy(contactPut, classOf[in.handyman.dsl.GooglecontactPUT], context)
    val expression = contact.getCondition
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