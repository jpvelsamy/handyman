package in.handyman.process.onethread

import com.typesafe.scalalogging.LazyLogging
import in.handyman.command.Context
import scala.collection.JavaConversions._
import in.handyman.command.Action
import in.handyman.command.CommandProxy
import in.handyman.util.ResourceAccess
import org.eclipse.emf.common.util.EList
import in.handyman.util.ParameterisationEngine
import java.util.ArrayList
import java.sql.SQLException
import scala.util.control.Exception.Finally
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.AmazonClientException
import com.amazonaws.AmazonServiceException
import java.io.File
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.FileOutputStream
import org.apache.commons.io.IOUtils

import scala.util.Try
import com.amazonaws.services.s3.transfer.MultipleFileUpload
import com.amazonaws.services.s3.transfer.TransferManager

class S3Action extends in.handyman.command.Action with LazyLogging {
  val detailMap = new java.util.HashMap[String, String]

  def execute(context: in.handyman.command.Context, action: in.handyman.dsl.Action): in.handyman.command.Context = {
    val s3AsIs: in.handyman.dsl.S3 = action.asInstanceOf[in.handyman.dsl.S3]
    val s3: in.handyman.dsl.S3 = CommandProxy.createProxy(s3AsIs, classOf[in.handyman.dsl.S3], context)

    val name = s3.getName
    val ddlSql: String = s3.getValue.replaceAll("\"", "")
    val id = context.getValue("process-id")
    val db = s3.getDb
    val key = s3.getKey
    val token = s3.getToken
    val bucket = s3.getBucket
    val ftype = s3.getType
    var directory: File = null
    var file = ""

    val s3DbConnfrom = ResourceAccess.rdbmsConn(db)
    val s3Stmtfrom = s3DbConnfrom.createStatement
    s3DbConnfrom.setAutoCommit(false)
    val now = "now()"

    try {

      val awsCredentials = new BasicAWSCredentials(key, token)
      val amazonS3Client = new AmazonS3Client(awsCredentials)
      val tm = new TransferManager(amazonS3Client);

      val rs = s3Stmtfrom.executeQuery(ddlSql)

      if (ftype == "directory") {
        while (rs.next()) {
          directory = new File(rs.getString("target"))
          val upDirectory = tm.uploadDirectory(bucket, id, directory, true);
        }
      } else if (ftype == "file") {
        while (rs.next()) {
          directory = new File(rs.getString("directory"))
          file = rs.getString("file")
          // upload file

          val upFile = new File(directory+file)
          amazonS3Client.putObject(bucket, file, upFile)
          logger.info("Upload completed into S3 bucket#{},file#{}", bucket, file)

        }
      }
    } catch {
      case ase: AmazonServiceException => System.err.println("Exception: " + ase.toString)
      case ace: AmazonClientException => System.err.println("Exception: " + ace.toString)
      case ex: SQLException => {
        ex.printStackTrace()
      }
    } finally {
      detailMap.put("name", name)
      detailMap.put("db", db)
      detailMap.put("bucket", bucket)
      detailMap.put("ddlSql", ddlSql)
    }
    context

  }

  def executeIf(context: in.handyman.command.Context, action: in.handyman.dsl.Action): Boolean = {
    val s3AsIs = action.asInstanceOf[in.handyman.dsl.S3]
    val s3: in.handyman.dsl.S3 = CommandProxy.createProxy(s3AsIs, classOf[in.handyman.dsl.S3], context)

    val expression = s3.getCondition
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