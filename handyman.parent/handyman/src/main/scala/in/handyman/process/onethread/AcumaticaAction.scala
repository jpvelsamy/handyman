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
import java.util.HashMap
import java.util.List
import java.util.Map
import java.io.IOException
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import org.json.JSONArray
import org.json.JSONObject
import java.io.InputStreamReader
import java.io.BufferedReader

class AcumaticaAction extends in.handyman.command.Action with LazyLogging {
  val detailMap = new java.util.HashMap[String, String]

  def execute(context: in.handyman.command.Context, action: in.handyman.dsl.Action): in.handyman.command.Context = {
    val acumaticaAsIs: in.handyman.dsl.Acumatica = action.asInstanceOf[in.handyman.dsl.Acumatica]
    val acumatica: in.handyman.dsl.Acumatica = CommandProxy.createProxy(acumaticaAsIs, classOf[in.handyman.dsl.Acumatica], context)
    val source = acumatica.getSource
    val name = acumatica.getName
    val aurl = acumatica.getAurl
    val burl = acumatica.getBurl
    val user = acumatica.getUser
    val token = acumatica.getToken
    val ddlSql: String = acumatica.getValue.replaceAll("\"", "")
    val sql = ddlSql.split(":")
    val id = context.getValue("process-id")
    val endpoints = burl.split("/")
    val endpoint = endpoints(endpoints.length - 1)

    val acumaticaDbConnfrom = ResourceAccess.rdbmsConn(source)
    

    try {

      val auth = new JSONObject
      auth.put("name", user)
      auth.put("password", token)
      val acumaticaLoginUrl = new URL(aurl)
      val loginConnection: HttpURLConnection =
        acumaticaLoginUrl.openConnection().asInstanceOf[HttpURLConnection]
      loginConnection.setRequestMethod("POST");
      loginConnection.setRequestProperty("Content-Type", "application/json")
      val json = auth.toString()
      loginConnection.setDoOutput(true);
      val os = loginConnection.getOutputStream();
      val osw = new OutputStreamWriter(os, "UTF-8");
      osw.write(json);
      osw.flush();
      osw.close();
      os.close();
      
       if(loginConnection.getResponseCode() != 204)
			{
				val isReader = new InputStreamReader(loginConnection.getErrorStream())
				val reader = new BufferedReader(isReader)
				 val errorResponse = new StringBuffer()
			      var errorString = ""
			      while((errorString = reader.readLine())!= null){
			         errorResponse.append(errorString)
			      }
			      System.out.println(errorResponse)
			      logger.info("Log:Response Error Message " + errorResponse)
			}
       
       else{
       var headerValues: Map[String, List[String]] = new HashMap[String, List[String]]()
      headerValues = loginConnection.getHeaderFields
      val setCookie: String = headerValues.get("Set-Cookie").toString()
      val cookies: Array[String] = setCookie.split(",")
      cookies(0) = cookies(0).replace("path=/; HttpOnly", "").replace("[", "")
      cookies(3) = cookies(3).replace("path=/", "")
      cookies(4) = cookies(3).replace("path=/","")
      cookies(5) = cookies(5).replace("path=/; HttpOnly","").replace("]","")
      val authId: String = cookies(5)+cookies(4)+cookies(3)+cookies(1)+";"+cookies(0)
      
        val acumaticaEndPointUrl: URL = new URL(burl)
        val endpointConnection: HttpURLConnection =
          acumaticaEndPointUrl.openConnection().asInstanceOf[HttpURLConnection]
        endpointConnection.setRequestMethod("PUT")
        endpointConnection.setRequestProperty("Content-Type", "application/json")
        endpointConnection.setRequestProperty("Cookie", authId)
        val jsoninput: String = getData.toString
        println("The Json"+jsoninput);
        endpointConnection.setDoOutput(true)
        val os1: OutputStream = endpointConnection.getOutputStream
        val osw2: OutputStreamWriter = new OutputStreamWriter(os1, "UTF-8")
        osw2.write(jsoninput)
        osw2.flush()
        osw2.close()
        os1.close()
        println("Response Message " + endpointConnection.getResponseMessage)
        logger.info("Log:Response Message " + endpointConnection.getResponseMessage)
       if(endpointConnection.getResponseCode() != 200)
			{
				val isReader = new InputStreamReader(endpointConnection.getErrorStream())
				val reader = new BufferedReader(isReader)
				 val errorResponse = new StringBuffer()
			      var errorString = ""
			      while((errorString = reader.readLine())!= null){
			         errorResponse.append(errorString)
			      }
			      System.out.println(errorResponse)
			      logger.info("Log:Response Error Message " + errorResponse)
			}
        loginConnection.disconnect()
        endpointConnection.disconnect()
       }
        
    
     
      

      def getData(): JSONObject = {
        val rootObject = new JSONObject
        try {
       
          val detailsList: List[JSONObject] = new ArrayList[JSONObject]()
          if (endpoint.equals("SalesOrder")) {
            val acumaticaBaseStmt = acumaticaDbConnfrom.createStatement
            val rs = acumaticaBaseStmt.executeQuery(sql(0))
            val meta = rs.getMetaData
            println("Column Count"+meta.getColumnCount);

            while (rs.next()) {
              for (i <- 1 until meta.getColumnCount+1) {
                val childObject: JSONObject = new JSONObject()
                childObject.put("value", rs.getString(i))
                rootObject.put(meta.getColumnName(i), childObject)
              }
            }  
            val acumaticaAdditionalStmt = acumaticaDbConnfrom.createStatement
            val ars = acumaticaAdditionalStmt.executeQuery(sql(1))
            val ameta = ars.getMetaData
            var nestedRootObject: JSONObject = new JSONObject()
            while (ars.next()) {
              for (i <- 1 until ameta.getColumnCount+1) {
                val childObject: JSONObject = new JSONObject()
                childObject.put("value", ars.getString(i))
                nestedRootObject.put(ameta.getColumnName(i), childObject)
              }
              detailsList.add(nestedRootObject)
              nestedRootObject = new JSONObject()
            }
            rootObject.put("Details",detailsList)
            acumaticaAdditionalStmt.close()
            acumaticaBaseStmt.close()
          } 
          else {
            val acumaticaCustomerStmt = acumaticaDbConnfrom.createStatement
            val customerRs = acumaticaCustomerStmt.executeQuery(ddlSql)
            val customerMeta = customerRs.getMetaData

            while (customerRs.next()) {
              for (i <- 1 until customerMeta.getColumnCount+1) {
                val childObject: JSONObject = new JSONObject()
                childObject.put("value", customerRs.getString(i))
                rootObject.put(customerMeta.getColumnName(i), childObject)
              }
            }
            acumaticaCustomerStmt.close();
          }
          
        } catch {
          case e: Exception => println(e)
        }
        rootObject
      
      }

    } catch {
      case ex: SQLException => {
        ex.printStackTrace()
      }
    } finally {
      
      acumaticaDbConnfrom.close()
      detailMap.put("name", name)
      detailMap.put("source", source)
    }
    context

  }

  def executeIf(context: in.handyman.command.Context, action: in.handyman.dsl.Action): Boolean = {
    val acumaticaAsIs = action.asInstanceOf[in.handyman.dsl.Acumatica]
    val acumatica: in.handyman.dsl.Acumatica = CommandProxy.createProxy(acumaticaAsIs, classOf[in.handyman.dsl.Acumatica], context)

    val expression = acumatica.getCondition
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