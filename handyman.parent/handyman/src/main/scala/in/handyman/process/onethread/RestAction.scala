package in.handyman.process.onethread

import org.eclipse.emf.common.util.EList
import in.handyman.command.CommandProxy
import in.handyman.rest.HandymanRestClient
import in.handyman.rest.SpoorsRestClient
import in.handyman.dsl.RestPart
import in.handyman.util.ParameterisationEngine
import in.handyman.util.ResourceAccess
import org.json.JSONArray
import org.json.JSONObject

import com.typesafe.scalalogging.LazyLogging

/**
 * 1. Authenticate
 * 	- Handle http and https
 *  - Handle alltrust and strict evaluation
 * 2. Add headers
 * 3. Add parent part
 * 4. Add child part
 * 5. Print the graph and headers
 * 6. Make the call
 * 7. Receive the ack
 * 8. Store the ack
 */
class RestAction extends in.handyman.command.Action with LazyLogging {

  val detailMap = new java.util.HashMap[String, String]
  
  def execute(context: in.handyman.command.Context, action: in.handyman.dsl.Action): in.handyman.command.Context = {
    val restAsIs: in.handyman.dsl.Rest = action.asInstanceOf[in.handyman.dsl.Rest]
    val rest: in.handyman.dsl.Rest = CommandProxy.createProxy(restAsIs, classOf[in.handyman.dsl.Rest], context)

    val name = rest.getName
    val url = rest.getUrl
    val method = rest.getMethod
    val authResource = rest.getAuthtoken
    val id = context.getValue("process-id")

    logger.info("Rest id#{}, name#{}, url#{}, method#{}, autinfo#{}", id, name, method, authResource)

    val restDbSrc = rest.getResourcedatafrom
    val restResSql = rest.getUrldata
    val restDbConn = ResourceAccess.rdbmsConn(restDbSrc)
    val restStmt = restDbConn.createStatement
    val restRs = restStmt.executeQuery(restResSql.replaceAll("\"", ""))
    restRs.next
    val restUrl = restRs.getString(1)

    restRs.close
    restStmt.close
    restDbConn.close

    logger.info("Rest id#{}, restdbsrc#{}, resturl#{}, sql#{}", id, restDbSrc, restUrl, restResSql)
    detailMap.put("restDbSrc", restDbSrc)
    detailMap.put("resturl", restUrl)
    detailMap.put("restResSql", restResSql)
    detailMap.put("method", method)
    detailMap.put("authResource", authResource)
    
    val headerDbSrc = rest.getHeaderdatafrom
    val headerSql = rest.getHeaderdata
    val headerConn = ResourceAccess.rdbmsConn(headerDbSrc)
    val headerStmt = headerConn.createStatement
    val headerRs = headerStmt.executeQuery(headerSql.replaceAll("\"", ""))
    val headerColumnCount = headerRs.getMetaData.getColumnCount
    val headerMap: java.util.Map[String, String] = new java.util.HashMap[String, String]

    while (headerRs.next()) {
      val headerKey = headerRs.getString(1)
      val headerValue = headerRs.getString(2)
      headerMap.put(headerKey, headerValue)

    }
    headerRs.close
    headerStmt.close
    headerConn.close

    logger.info("Rest id#{}, headerDbSrc#{}, headerSql#{}, headerMap#{}", id, headerDbSrc, headerSql, headerMap)
    val parentName = rest.getParentName
    val bodyDbSrc = rest.getPostdatafrom
    val parentData = rest.getParentdata

    logger.info("Rest id#{}, bodyDbSrc#{}, parentName#{}, parentDataSql#{}", id, bodyDbSrc, parentName, parentData)

    val bodayConn = ResourceAccess.rdbmsConn(bodyDbSrc)
    val bodyStmt = bodayConn.createStatement
    val bodyResultset = bodyStmt.executeQuery(parentData.replaceAll("\"", ""))
    bodyResultset.next

    val jsonPayload = new JSONObject

    val parentColumnCount = bodyResultset.getMetaData.getColumnCount
    for (i <- 1 until parentColumnCount + 1) {
      val key = bodyResultset.getMetaData.getColumnLabel(i)
      val value = bodyResultset.getString(i)
      jsonPayload.put(key, value)
    }

    bodyResultset.close

    val parts: EList[RestPart] = rest.getParts
    val iter = parts.iterator()
    while (iter.hasNext) {
      val partSrc = iter.next()
      val partAsIs: in.handyman.dsl.RestPart = partSrc.asInstanceOf[in.handyman.dsl.RestPart]
      val part: in.handyman.dsl.RestPart = CommandProxy.createProxy(partAsIs, classOf[in.handyman.dsl.RestPart], context)
      val partName = part.getPartName
      val query = part.getPartData
      val partRs = bodyStmt.executeQuery(query.replaceAll("\"", ""))
      val partColCount = partRs.getMetaData.getColumnCount
      val partArray = new JSONArray;
      logger.info("Rest id#{}, partSrc#{}, partName#{}, partSql#{}", id, partSrc, partName, query)
      while (partRs.next) {
        val partObj = new JSONObject
        for (j <- 1 until partColCount + 1) {
          val fieldName = partRs.getMetaData.getColumnLabel(j)
          val fieldValue = partRs.getString(j)
          partObj.put(fieldName, fieldValue)

        }
        partArray.put(partObj)
      }
      if (parentName.contains(".")) {
        val parentArr = parentName.split("\\.")
        val partHolder = parentArr.apply(1)
        logger.info("Rest id#{}, adding shell holder as {}", partHolder)
        val jsonPartHolderPayload = new JSONObject
        jsonPartHolderPayload.put(partName, partArray)
        jsonPayload.put(partHolder, jsonPartHolderPayload)
      } else {
        logger.info("Rest id#{}, adding adding array as is to parent")
        jsonPayload.put(partName, partArray)
      }
      partRs.close
    }
    bodyStmt.close
    bodayConn.close
    val jsonObject = jsonPayload.toString
    logger.info("Rest id#{}, outbound json object #{}", id, jsonObject.toString());

    if (authResource.contains("jwt")) {
      val authArr = authResource.split(":")
      val restClient = new SpoorsRestClient(url, authArr.apply(1), "jwt")
      /*restClient.createAuthToken*/
      val output = restClient.post(restUrl, jsonObject)

    } else {
      val restClient = new HandymanRestClient(url, authResource)
      restClient.createAuthToken
      val output = restClient.post(restUrl, jsonObject)
      
    }

    val ackSql = rest.getAckdata
    val ackTarget = rest.getAckdatato
    
    context
  }

  def executeIf(context: in.handyman.command.Context, action: in.handyman.dsl.Action): Boolean = {
    val restAsIs: in.handyman.dsl.Rest = action.asInstanceOf[in.handyman.dsl.Rest]
    val rest: in.handyman.dsl.Rest = CommandProxy.createProxy(restAsIs, classOf[in.handyman.dsl.Rest], context)

    val expression = rest.getCondition
    try {
      val output = ParameterisationEngine.doYieldtoTrue(expression)
      detailMap.putIfAbsent("condition-output", output.toString())
      output
    } finally {
       if(expression!=null)
      detailMap.putIfAbsent("condition", "LHS=" + expression.getLhs + ", Operator=" + expression.getOperator + ", RHS=" + expression.getRhs)

    }
  }

  def append[T](xs: List[T], ys: List[T]): List[T] =
    xs match {
      case List() => ys
      case x :: xs1 => x :: append(xs1, ys)
    }
  
  def generateAudit(): java.util.Map[String, String] = {
    detailMap
  }
}


