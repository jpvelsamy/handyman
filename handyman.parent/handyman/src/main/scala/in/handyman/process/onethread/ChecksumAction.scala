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
import java.io.{ BufferedReader, FileOutputStream, InputStreamReader }
import org.slf4j.MarkerFactory
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

import java.io.File
import java.nio.file.Files
import java.security.{DigestInputStream, MessageDigest}


class ChecksumAction extends in.handyman.command.Action with LazyLogging {
  val detailMap = new java.util.HashMap[String, String]
  val auditMarker = "CheckSum";
  val aMarker = MarkerFactory.getMarker(auditMarker);

  def execute(context: Context, action: in.handyman.dsl.Action, actionId: Integer): Context = {
    val checkSumAsIs: in.handyman.dsl.Checksum = action.asInstanceOf[in.handyman.dsl.Checksum]
    val checksum: in.handyman.dsl.Checksum = CommandProxy.createProxy(checkSumAsIs, classOf[in.handyman.dsl.Checksum], context)

    val name = checksum.getName
    val ddlSql: String = /*checksum.getValue.replaceAll("\"", "")*/"select filepath from 105_dropbox;"
    val id = context.getValue("process-id")
    val db = checksum.getDb
    
    val cstype = checksum.getCstype
    var directory: File = null
    var file = ""

    val checksumDbConnfrom = ResourceAccess.rdbmsConn(db)
    val checksumStmtfrom = checksumDbConnfrom.createStatement
    checksumDbConnfrom.setAutoCommit(false)
    val now = "now()"
    
    var fileCount = 0; //count of files for which checksum have been created
    try {
      var obj: ChecksumAction = new ChecksumAction
      val rs = checksumStmtfrom.executeQuery(ddlSql)
      //var ct=0
      while (rs.next()) 
      {
        //ct +=1
        file = rs.getString("filepath")
        if (file.contains("\\")) 
        {
      			file = file.replace("\\", "\\\\")
      	}
        file = file.stripSuffix(".zip")
        directory = new File(file)
        
        /*if(ct>1)
        {*/
          var generatedChecksum = obj.md5(directory)
          println(generatedChecksum)                 
       		fileCount +=1
         		
       		val query = "update " + id + "_dropbox" + " set checksum ='" + generatedChecksum  + "' where filepath ='" +file +"';"
          
          checksumStmtfrom.execute(query)
          checksumDbConnfrom.commit()
          logger.info("Updated the checksum for the file "+directory.getName +"in the table "+id+"_dropbox")
       // }
        
          

        
        
      }
    } 
    catch {
      case ex: Exception => {
        ex.printStackTrace()
      }
    }
     finally {
      detailMap.put("name", name)
      detailMap.put("db", db)
      detailMap.put("ddlSql", ddlSql)
      detailMap.put("Number of files for which checksum generated",fileCount.toString)
    }
    context

  }
  
  def md5(roots: File*): String = 
        {
            val md = MessageDigest.getInstance("MD5")
            roots.foreach { root =>
              Files.walk(root.toPath).filter(!_.toFile.isDirectory).forEach { path =>
                val dis = new DigestInputStream(Files.newInputStream(path), md)
                // fully consume the inputstream
                while (dis.available > 0) {
                  dis.read
                }
                dis.close
              }
            }
            md.digest.map(b => String.format("%02x", Byte.box(b))).mkString
        }

  def executeIf(context: Context, action: in.handyman.dsl.Action): Boolean = {
    val checkSumAsIs: in.handyman.dsl.Checksum = action.asInstanceOf[in.handyman.dsl.Checksum]
    val checksum: in.handyman.dsl.Checksum = CommandProxy.createProxy(checkSumAsIs, classOf[in.handyman.dsl.Checksum], context)

    val expression = checksum.getCondition
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