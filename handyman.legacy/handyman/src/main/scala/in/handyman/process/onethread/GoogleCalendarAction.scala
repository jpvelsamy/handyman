package in.handyman.process.onethread

import java.io.File
import java.util.Arrays
import java.util.Collections
import java.util.Date
import java.util.TimeZone

import in.handyman.command.Context
import in.handyman.dsl.Action
import in.handyman.dsl.GooglecalPUT
import in.handyman.util.ResourceAccess

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.DateTime
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.services.calendar.model.Event
import com.google.api.services.calendar.model.EventDateTime
import com.typesafe.scalalogging.LazyLogging
import in.handyman.command.CommandProxy
import in.handyman.util.ParameterisationEngine
import java.util.concurrent.atomic.AtomicInteger
import in.handyman.config.ConfigurationService
import org.slf4j.MarkerFactory
import in.handyman.audit.AuditService

class GoogleCalendarAction extends in.handyman.command.Action with LazyLogging {
  val DEFAULT_TIMEZONE = "Asia/Kolkata"
  val detailMap = new java.util.HashMap[String, String]

  val DATA_STORE_DIR: java.io.File =
    new java.io.File(System.getProperty("user.home"), ".store/calendar_sample");
  val JSON_FACTORY: JsonFactory = JacksonFactory.getDefaultInstance();
  val CALENDAR_SCOPE = "https://www.googleapis.com/auth/calendar"
  val gcalCreateMarker = "GCALENDART-CREATE";
  val gMarker = MarkerFactory.getMarker(gcalCreateMarker);

  def execute(context: Context, action: Action, actionId: Integer): Context = {
    val calAsIs: GooglecalPUT = action.asInstanceOf[GooglecalPUT]
    val cal: in.handyman.dsl.GooglecalPUT = CommandProxy.createProxy(calAsIs, classOf[in.handyman.dsl.GooglecalPUT], context)

    val dbSrc = cal.getDbSrc
    val accountId = cal.getAccount
    val mail = cal.getImpersonatedUser
    val relativePath = cal.getPtwelveFile
    val key = cal.getPrivateKey
    val project = cal.getProject
    val name = cal.getName

    val authStore = this.getClass.getClassLoader.getResource(relativePath).getPath
    val sql = cal.getValue
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
      setServiceAccountScopes(Collections.singleton(CALENDAR_SCOPE)).
      setServiceAccountUser(mail).build()

    val client = new com.google.api.services.calendar.Calendar.Builder(
      httpTransport, JSON_FACTORY, credential).setApplicationName(project).build();

    val incomingCalenderEvent: AtomicInteger = new AtomicInteger
    val addedCalenderEvent: AtomicInteger = new AtomicInteger
    val timeZoneLabel = ConfigurationService.getGlobalconfig().get("timeZone").getOrElse(DEFAULT_TIMEZONE)
    val timeZone = TimeZone.getTimeZone(timeZoneLabel);

    val eventMap: java.util.Map[Date, CalendarEvent] = new java.util.HashMap[Date, CalendarEvent]()
    val statementId = AuditService.insertStatementAudit(actionId, "contact->" + name, context.getValue("process-name"))
    try {
      while (rs.next()) {

        val calEvent = new CalendarEvent

        calEvent.startDate = rs.getDate("event_start")

        if (eventMap.containsKey(calEvent.startDate)) {
          val title = rs.getString("event_title")
          val description = rs.getString("description")
          val location = rs.getString("event_location")
          val info = title + " " + location
          eventMap.get(calEvent.startDate).contactDetails.add(info)
        } else {
          calEvent.calId = rs.getString("event_calendar_id")
          calEvent.interval = rs.getInt("event_interval")
          calEvent.endDate = rs.getString("event_end")
          calEvent.eventAlertTime = rs.getString("event_alert_time")
          val title = rs.getString("event_title")
          val description = rs.getString("description")
          val location = rs.getString("event_location")
          val info = title + " " + location
          calEvent.contactDetails.add(info)
          eventMap.put(calEvent.startDate, calEvent)
        }
      }
      if (!eventMap.isEmpty) {
        val iter = eventMap.keySet().iterator()
        while (iter.hasNext) {
          incomingCalenderEvent.incrementAndGet()
          val startDateAsKey = iter.next
          val eventPojo: CalendarEvent = eventMap.get(startDateAsKey)
          val calId = eventPojo.calId
          val interval = eventPojo.interval
          val endDate = eventPojo.endDate
          val time = eventPojo.eventAlertTime
          val title = "Schedule to call Juno contacts"
          val contactList = eventPojo.contactDetails
          val location = "NA"

          val event = new Event();
          val startDateG = new Date(startDateAsKey.getTime + 36000)

          val start = new DateTime(startDateG, timeZone)
          val startEventDateTime = new EventDateTime()
          startEventDateTime.setTimeZone(timeZoneLabel).setDateTime(start)
          event.setStart(startEventDateTime)

          val eventEnd = new Date(startDateAsKey.getTime + 60 * 60 * 1000)
          val dailyEnd = new DateTime(eventEnd, TimeZone.getTimeZone(timeZoneLabel));
          event.setEnd(new EventDateTime().setDateTime(dailyEnd).setTimeZone(timeZoneLabel))

          event.setLocation(location)
          event.setSummary(title)
          val descBuilder: StringBuilder = new StringBuilder()
          val description = {
            //contactList.foreach(contact => descBuilder.append(contact).append("<br/>"))
            val iter = contactList.iterator()
            while (iter.hasNext()) {
              descBuilder.append(iter.next()).append("<br/>")
            }
            descBuilder.toString()
          }
          event.setDescription(description)

          val endDateForRecur = endDate.replaceAll("-", "") + "T170000Z"
          event.setRecurrence(Arrays.asList("RRULE:FREQ=DAILY;UNTIL=" + endDateForRecur));

          val result = client.events().insert(calId, event).execute();
          addedCalenderEvent.incrementAndGet
          logger.info(gMarker, "Adding event  {} with for location {} title {} with counter {} with description {} with final date{}", startDateAsKey.toString(), location, title, addedCalenderEvent.get.toString(), description, endDateForRecur)

        }
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
      detailMap.put("incomingContactCounter", incomingCalenderEvent.intValue.toString)
      detailMap.put("addedContactCounter", addedCalenderEvent.intValue.toString)
      AuditService.updateStatementAudit(statementId, addedCalenderEvent.intValue(), incomingCalenderEvent.intValue(), sql, 1)
      stmt.close
      conn.close
    }
    context
  }

  def executeIf(context: Context, action: Action): Boolean = {
    val calAsIs: GooglecalPUT = action.asInstanceOf[GooglecalPUT]
    val cal: in.handyman.dsl.GooglecalPUT = CommandProxy.createProxy(calAsIs, classOf[in.handyman.dsl.GooglecalPUT], context)

    val expression = cal.getCondition
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