package in.handyman.process.onethread

import com.typesafe.scalalogging.LazyLogging
import in.handyman.command.CommandProxy
import com.facebook.ads.sdk.APIContext
import com.facebook.ads.sdk.AdAccount
import com.facebook.ads.sdk.Campaign
import in.handyman.util.ResourceAccess
import in.handyman.util.ParameterisationEngine
import java.util.concurrent.atomic.AtomicInteger
import java.sql.SQLException
import java.util.regex.Pattern
import com.facebook.ads.sdk.Ad
import java.util.HashMap
import java.time.LocalDateTime
import java.time.ZoneOffset
import in.handyman.config.ConfigurationService
import in.handyman.util.ExceptionUtil
import org.slf4j.MarkerFactory
import in.handyman.audit.AuditService

/**
 * //https://developers.facebook.com/docs/marketing-api/guides/lead-ads/retrieving/v2.9
 * //https://developers.facebook.com/docs/marketing-api/guides/lead-ads/retrieving/v2.9
 * //1.https://developers.facebook.com/apps
 * // -- App display name - in.junome.fubar.app (https://developers.facebook.com/apps/318205931969703/settings/)
 * // -- Under marketing api/tools
 * // -----ads_management, ads_read, manage_notifications, manage_pages, read_insights, rsvp_event
 * // App secret - 632b2b7e5a1d1a86348aa3ec73606cfd
 * // Client token - 4097cb4c0fff4c502dff24f31c36f0e6
 * // Access token - EAAEhaBeKXKcBAE991IXtywQPyCSOSOtOKo7n1UhLZCZCQQPg2Ps5dTZB9gPMGte41u0q0yzjLfEqqkXTaxKNHZA7CMTLq8cRRupt8l1Kmg2EzhcR9ZCXBuOsZB9JpzmLGX1ZBubDyjRwM6q4UlqzXmYv3qbazgBVeJHe13SGV7bWkcVIPtqVx4r
 * // Account id - 1385910988364458
 * public static final String ACCESS_TOKEN = "EAAEhaBeKXKcBAE991IXtywQPyCSOSOtOKo7n1UhLZCZCQQPg2Ps5dTZB9gPMGte41u0q0yzjLfEqqkXTaxKNHZA7CMTLq8cRRupt8l1Kmg2EzhcR9ZCXBuOsZB9JpzmLGX1ZBubDyjRwM6q4UlqzXmYv3qbazgBVeJHe13SGV7bWkcVIPtqVx4r";
 * public static final Long ACCOUNT_ID = 1385910988364458L;
 * public static final String APP_SECRET = "632b2b7e5a1d1a86348aa3ec73606cfd";
 */
class FBLeadCreateAction extends in.handyman.command.Action with LazyLogging {

  val NAME = 1
  val EMAIL = 2
  val MOBILE = 3
  val ALT_MOBILE = 4
  val LOCALITY = 5
  val TARGETED_CITY = 6
  val LEADSOURCE_CAMPAIGN = 7;
  val LEADSOURCE_CHANNEL = 8;
  val COMPANY = 9;
  val LEADGEN_DATE = 10;
  val COA_APROX = 11;
  val PROFESSION = 12;
  val BUDGET = 13;
  val LEADSOURCE_METADATA = 14;
  val INTENT_METADATA = 15;
  val BATCH_ID = 16;

  val InsertSql = "replace into leads_soft (  name, email, mobile, alt_mobile, locality, targeted_city, leadsource_campaign, leadsource_channel, company, leadgen_date, coa_aprox, profession, budget, leadsource_metadata, intent_metadata, batch_id, updated_date) values (?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?,?,now());"
  val detailMap = new java.util.HashMap[String, String]
  val fbMarkerText = "FB-LEAD-INGESTION";
  val fbMarker = MarkerFactory.getMarker(fbMarkerText);
  //https://stackoverflow.com/questions/11352037/scala-utc-timestamp-in-seconds-since-january-1st-1970
  def execute(context: in.handyman.command.Context, action: in.handyman.dsl.Action, actionId:Integer): in.handyman.command.Context = {
    val fbAsIs: in.handyman.dsl.FBCLead = action.asInstanceOf[in.handyman.dsl.FBCLead]
    val fb: in.handyman.dsl.FBCLead = CommandProxy.createProxy(fbAsIs, classOf[in.handyman.dsl.FBCLead], context)

    val accessToken = fb.getAccessToken
    val appSecret = fb.getAppSecret
    val accountId = fb.getAccountId
    val campaignIdList = {
      if (fb.getCampaignId.contains(","))
        fb.getCampaignId.split(",")
      else
        Array(fb.getCampaignId)
    }
    val fieldsToSelect = fb.getValue
    val fieldArray = fieldsToSelect.split(",")
    val dbTarget = fb.getTarget
    val name = fb.getName
    val tgtConn = ResourceAccess.rdbmsConn(dbTarget)
    val stmt = tgtConn.prepareStatement(InsertSql)

    val fbContext = new APIContext(accessToken, appSecret).enableDebug(true);
    val fbAccount = new AdAccount(accountId, fbContext)
    val nameCleanup = ConfigurationService.getGlobalconfig().get("cleanSpecialChar").getOrElse("false")
    
     
    val incomingLeadCount:AtomicInteger=new AtomicInteger
    val insertedLeadCount:AtomicInteger = new AtomicInteger
    
    logger.info(fbMarker, "Campaign id list as is {} with account id {} and with db {}" , campaignIdList, accountId, dbTarget)
    val statementId = AuditService.insertStatementAudit(actionId, "fblc->"+name, context.getValue("process-name"))
    campaignIdList.foreach {
      campaignId =>
        {
          val campaign = Campaign.fetchById(campaignId, fbContext)
          val adList = campaign.getAds.requestNameField().execute()

          //Need to know how to use the lamba and keep my life simple
          if (!adList.isEmpty()) {
            val adListIter = adList.iterator()
            while (adListIter.hasNext()) {
              val ad = adListIter.next()

              val leadList = ad.getLeads.requestAllFields().execute();
              if (!leadList.isEmpty()) {

                val leadListIter = leadList.withAutoPaginationIterator(true).iterator()
                logger.info(fbMarker, "Iterating through campaign {} for account {}", campaignId, accountId)
                
                val leadCounter: AtomicInteger = new AtomicInteger;
                
                while (leadListIter.hasNext()) {

                  val lead = leadListIter.next
                  val createdAt = lead.getFieldCreatedTime
                  
                  
                  incomingLeadCount.incrementAndGet()
                  val leadSourceMeta = "ad_id=" + lead.getFieldAdId + ",<br/> ad_name=" + lead.getFieldAdName + "<br/>, adset_id=" + lead.getFieldAdsetId +
                    "<br/>, adset_name=" + lead.getFieldAdsetName + "<br/>, campaign_id=" + lead.getFieldCampaignId + "<br/>,form_id=" +
                    lead.getFieldFormId + "<br/>,id=" + lead.getFieldId + "<br/>, dailybudget=" + 0
                  //ad.getFieldAdset.getFieldDailyBudget - will try later

                  val userGenData = lead.getFieldFieldData

                  val intentMeta = new StringBuilder
                  val myLead: Lead = new Lead
                  if (!userGenData.isEmpty) {
                    val usergeniter = userGenData.listIterator

                    while (usergeniter.hasNext()) 
                    {
                      val userData = usergeniter.next()
                      val name = userData.getFieldName.trim
                      val value = userData.getFieldValues.toArray().mkString(",")
                      if (name.equals("email")) 
                      {
                        myLead.setEmail(value)
                      } else if (name.equals("full_name")) 
                      {
                          val leadName = {
                            if(nameCleanup.equalsIgnoreCase("true"))
                              cleanTextContent(value)
                            else
                              value
                          }
                          
                          val existingVal = myLead.getFullName
                          if(existingVal!=null)
                            myLead.setFullName(existingVal+"/"+value)
                          else
                            myLead.setFullName(leadName)
                      } else if (name.toLowerCase().contains("name")) 
                      {
                          val leadName = {
                            if(nameCleanup.equalsIgnoreCase("true"))
                              cleanTextContent(value)
                            else
                              value
                          }
                          val existingVal = myLead.getFullName
                          if(existingVal!=null)
                            myLead.setFullName(existingVal+"/"+value)        
                          else
                            myLead.setFullName(leadName)
                      }
                      else if (name.equals("city")) {
                        myLead.setCity(value)
                      } else if (name.equals("company_name")) 
                      {
                        myLead.setCompany(value)
                      } else if (name.equals("phone_number")) {
                        val prunedValue = {
                          if (value.length > 10)
                            value.substring(value.length - 10, value.length)
                          else
                            value
                        }
                        myLead.setMobile(prunedValue)
                      } else if (name.equals("job_title")) {
                        myLead.setProfession(value)
                      } else {
                        intentMeta.append(name).append("=").append(value).append("\n")
                      }
                      usergeniter.remove
                    }
                  }
                  stmt.setString(EMAIL, myLead.getEmail)
                  stmt.setString(NAME, myLead.getFullName)
                  stmt.setString(TARGETED_CITY, myLead.getCity)
                  stmt.setString(LOCALITY, myLead.getCity)
                  stmt.setString(COMPANY, myLead.getCompany)
                  stmt.setString(MOBILE, myLead.getMobile)
                  stmt.setString(ALT_MOBILE, myLead.getMobile)
                  stmt.setString(PROFESSION, myLead.getProfession)
                  stmt.setString(INTENT_METADATA, intentMeta.toString)
                  stmt.setString(LEADSOURCE_METADATA, leadSourceMeta)
                  stmt.setString(LEADSOURCE_CAMPAIGN, lead.getFieldCampaignName)
                  stmt.setInt(BUDGET, 0)
                  stmt.setInt(COA_APROX, 0)
                  stmt.setString(LEADSOURCE_CHANNEL, "FB")
                  stmt.setString(LEADGEN_DATE, createdAt)
                  val processid = context.getValue("process-id")
                  stmt.setInt(BATCH_ID, Integer.parseInt(processid))
                  try {
                    logger.info(fbMarker, "Adding lead with name {} , phone {}, email {}, location {}",myLead.fullName, myLead.mobile, myLead.email, myLead.city)
                    stmt.executeUpdate
                    tgtConn.commit
                    insertedLeadCount.incrementAndGet()
                  } catch {

                    case ex: SQLException => {
                      logger.error(fbMarker, "Error inserting name {} , phone {}, email {}, location {} with campaign {} and conter {}",myLead.fullName, myLead.mobile, myLead.email, myLead.city, campaignId, leadCounter.incrementAndGet.toString, ex)
                      detailMap.put("exception", ExceptionUtil.completeStackTraceex(ex))
                    }
                    case ex: Throwable => {
                      logger.error(fbMarker, "Error inserting name {} , phone {}, email {}, location {} with campaign {} and conter {}",myLead.fullName, myLead.mobile, myLead.email, myLead.city, campaignId, leadCounter.incrementAndGet.toString, ex)
                      detailMap.put("exception", ExceptionUtil.completeStackTraceex(ex))
                    }                    
                  }
                  detailMap.put(campaignId,leadCounter.intValue().toString)
                } //leadList.iterator().hasNext()
              } //!leadList.isEmpty()
            } //adListIter.hasNext()
          } //!adList.isEmpty()
          
        } //campaign closure
    } //campaign iteration
    
    try {}
    finally {
      stmt.close
      tgtConn.close
      detailMap.put("accountId", accountId)
      detailMap.put("campaignIdList", campaignIdList.toString())
      detailMap.put("fieldsToSelect", fieldsToSelect)
      detailMap.put("fieldArray", fieldArray.toString())
      detailMap.put("dbTarget", dbTarget)
      detailMap.put("nameCleanup",nameCleanup)
      detailMap.put("incomingLeadCount", incomingLeadCount.intValue.toString)
      detailMap.put("insertedLeadCount", insertedLeadCount.intValue.toString)
      AuditService.updateStatementAudit(statementId, insertedLeadCount.intValue(), incomingLeadCount.intValue(), fieldsToSelect, 1)
    }
    context
  }

  def executeIf(context: in.handyman.command.Context, action: in.handyman.dsl.Action): Boolean = {
    val fbAsIs: in.handyman.dsl.FBCLead = action.asInstanceOf[in.handyman.dsl.FBCLead]
    val fb: in.handyman.dsl.FBCLead = CommandProxy.createProxy(fbAsIs, classOf[in.handyman.dsl.FBCLead], context)
    val expression:in.handyman.dsl.Expression = fb.getCondition
     try {
      val output=ParameterisationEngine.doYieldtoTrue(expression)
      detailMap.putIfAbsent("condition-output", output.toString())
      output
    } finally {
       if(expression!=null)
        detailMap.putIfAbsent("condition", "LHS=" +expression.getLhs+", Operator="+expression.getOperator+", RHS="+expression.getRhs)
        
    }
  }
  
  //https://howtodoinjava.com/regex/java-clean-ascii-text-non-printable-chars/
   def  cleanTextContent(text:String):String=
    {
       var output=text;
      // strips off all non-ASCII characters
      output = output.replaceAll("[^\\x00-\\x7F]", ""); 
        // erases all the ASCII control characters
      output = output.replaceAll("[\\p{Cntrl}&&[^\r\n\t]]", "");       
      // removes non-printable characters from Unicode
      output = output.replaceAll("\\p{C}", ""); 
      return output.trim();
    }

  //email	full_name	phone_number	city	company_name	job_title
   def generateAudit(): java.util.Map[String, String] = {
    detailMap
  }

}