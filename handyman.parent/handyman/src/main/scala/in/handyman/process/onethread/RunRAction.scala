package in.handyman.process.onethread

import com.typesafe.scalalogging.LazyLogging
import in.handyman.command.Context
import in.handyman.command.Action
import in.handyman.command.CommandProxy
import in.handyman.util.ParameterisationEngine
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.impl.client.DefaultHttpClient
import com.google.gson.Gson
import org.apache.http.entity.StringEntity
import org.json.JSONObject

case class PostData(script: String)

class RunRAction extends in.handyman.command.Action with LazyLogging {
  val detailMap = new java.util.HashMap[String, String]

  def restCaller(script: String): Boolean = {
    try {
      val postData = PostData(script)
      val gson = new Gson
      // create an HttpPost object
      val post = new HttpPost("http://localhost:8008")

      // set the Content-type
      post.setHeader("Content-type", "application/json")

      // add the JSON as a StringEntity
      post.setEntity(new StringEntity(gson.toJson(postData)))
      // send the post request
      val response = (new DefaultHttpClient).execute(post)
      var content = ""
      val entity = response.getEntity()
      if (entity != null) {
        val inputStream = entity.getContent()
        content = scala.io.Source.fromInputStream(inputStream).getLines.mkString
        inputStream.close
      }

      val resultObj = new JSONObject(content.toString())
      if (resultObj.getString("message").equals("success")) {
        println("success")
        true

      } else {
        println("error")
      }
      logger.info("response from python script  #{}", resultObj.getString("message"))

      false
    } catch {
      case e: Exception => false
    }

  }

  def execute(context: in.handyman.command.Context, action: in.handyman.dsl.Action): in.handyman.command.Context = {
    val runR: in.handyman.dsl.RunR = action.asInstanceOf[in.handyman.dsl.RunR]
    val runRActual: in.handyman.dsl.RunR = CommandProxy.createProxy(runR, classOf[in.handyman.dsl.RunR], context)
    var script = runRActual.getValue
    script = script.replace("\"", "")
    val result = restCaller(script)
    context
  }

  def executeIf(context: in.handyman.command.Context, action: in.handyman.dsl.Action): Boolean = {
    val runR: in.handyman.dsl.RunR = action.asInstanceOf[in.handyman.dsl.RunR]
    val runRActual: in.handyman.dsl.RunR = CommandProxy.createProxy(runR, classOf[in.handyman.dsl.RunR], context)
    val expression = runR.getCondition
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