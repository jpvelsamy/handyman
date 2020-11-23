package in.handyman.process.onethread

import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.DefaultHttpClient
import org.json.JSONObject

import com.google.gson.Gson
import com.typesafe.scalalogging.LazyLogging

import in.handyman.command.CommandProxy
import in.handyman.util.ParameterisationEngine
import java.io.IOException


class ModelTrainingAction extends in.handyman.command.Action with LazyLogging {
  val detailMap = new java.util.HashMap[String, String]
  def execute(context: in.handyman.command.Context, action: in.handyman.dsl.Action, actionId: Integer): in.handyman.command.Context = {

    val modelTrainingAsIs: in.handyman.dsl.Mahout = action.asInstanceOf[in.handyman.dsl.Mahout]
    val modelTraining: in.handyman.dsl.Mahout = CommandProxy.createProxy(modelTrainingAsIs, classOf[in.handyman.dsl.Mahout], context)
    try {
      val postData = modelTraining.getValue
      val gson = new Gson
      // create an HttpPost object
      // val url = modelTraining.getUrl
      val post = new HttpPost("http://localhost:8008")
      //  val hkey = modelTraining.getHkey
      //  val hvalue = modelTraining.getHvalue

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

    context
  }

  def executeIf(context: in.handyman.command.Context, action: in.handyman.dsl.Action): Boolean = {
    val modelTrainingAsIs: in.handyman.dsl.Mahout = action.asInstanceOf[in.handyman.dsl.Mahout]
    val modelTraining: in.handyman.dsl.Mahout = CommandProxy.createProxy(modelTrainingAsIs, classOf[in.handyman.dsl.Mahout], context)

    val expression = modelTraining.getCondition
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