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
import com.amazonaws.services.s3.transfer.MultipleFileUpload
import com.amazonaws.services.s3.transfer.TransferManager

import scala.util.control.Breaks._
import com.amazonaws.services.s3.model.PutObjectRequest
import com.amazonaws.AmazonWebServiceRequest
import com.amazonaws.services.s3.model.AbstractPutObjectRequest
import scala.util.control._
import java.time.LocalDate

class S3Action extends in.handyman.command.Action with LazyLogging {
  val detailMap = new java.util.HashMap[String, String]
  val auditMarker = "S3";
  val aMarker = MarkerFactory.getMarker(auditMarker);

  def execute(context: Context, action: in.handyman.dsl.Action, actionId: Integer): Context = {
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
    val limit = s3.getLimit
    var directory: File = null
    var file = ""
    var S3Bucket = ""
    var excep = false
    
    val s3DbConnfrom = ResourceAccess.rdbmsConn(db)
    val s3Stmtfrom = s3DbConnfrom.createStatement
    s3DbConnfrom.setAutoCommit(false)
    val now = "now()"
    var filesUploaded = 0
    val failedUploads: ArrayList[Int] = null
    //var erroneousFiles : List[Int] = new List[Int]
    var resp : com.amazonaws.services.s3.model.PutObjectResult  = null
    try {
      
     
      val rs = s3Stmtfrom.executeQuery(ddlSql)
      var ct=0
      breakable
      {
          while (rs.next()) 
          {
            
                file = rs.getString("Filepath")
                if (file.contains("\\"))
                {
                    file = file.replace("\\", "\\\\")
              
                }
                directory = new File(file)
                var uploadName = ""
                S3Bucket = ""
                if (file.contains("\\"))
                {
                      if (file.contains("Hourly"))
                      {
                          S3Bucket = bucket + "/Hourly"
                          uploadName = directory.getName + "-as of "+LocalDate.now().toString()  
                      }
                      else if(file.contains("Daily"))
                      {
                          S3Bucket = bucket+"/Daily"
                          uploadName = directory.getName
                      }
                }
                else if (file.contains("/"))
                {
                    if (directory.getName.charAt(directory.getName.length()-7)=='_' || file.contains("Hourly"))
                    {
                        S3Bucket = bucket + "/Hourly"
                        uploadName = directory.getName + "-as of "+LocalDate.now().toString()
                    }
                    else
                    {
                        S3Bucket = bucket+"/Daily"
                        uploadName = directory.getName
                    }
                }
                
                
                         
                val awsCredentials = new BasicAWSCredentials(key, token)
                val amazonS3Client = new AmazonS3Client(awsCredentials)
                logger.info("Amazon S3 Client started ")
                val tm = new TransferManager(amazonS3Client);
                logger.info("Transfer Manager started ")
                logger.info("File to be uploaded :"+directory.getName + " from the path "+file)
                logger.info("S3 Bucket :"+S3Bucket)
                resp=amazonS3Client.putObject(new PutObjectRequest(S3Bucket, uploadName, directory))
                var S3fileSize = amazonS3Client.getObjectMetadata(S3Bucket, uploadName).getContentLength().toString()
                var payloadFileSize =rs.getString("Filesize").stripSuffix(" KB")
                
                logger.info("Content Length from S3: "+S3fileSize);
                logger.info("Content Length from payload: "+payloadFileSize);
                if(S3fileSize.equals(payloadFileSize))
                {
                  filesUploaded +=1
                  logger.info("Number of files uploaded---------------> "+ filesUploaded) 
                  logger.info("Upload to S3 id#{}, name#{}, from#{}, to#{}", id, name, file, S3Bucket)
                  logger.info("Upload completed into this location#{}", S3Bucket)
                  var processStatus = "S3 Upload Complete"
                  if(name.contains("Hourly"))
                  {
                      processStatus = "Complete"
                  }
                  var query = "update " + id + "_dropbox" + " set UploadStatus ='Completed' , S3Uploadtime= now(), ProcessStatus = '"+processStatus+"'" + " where Filepath ='" +file +"';"
                  var auditQuery = "update dropbox" + " set UploadStatus ='Completed' , S3Uploadtime= now(), ProcessStatus = '"+processStatus+"'" + " where Filepath ='" +file +"' and Process_id="+id +";"
                  logger.info("Update Query for S3 Upload Details: " + query)  
                  logger.info("Update Query for S3 Upload Details in the Audit trail table: " + auditQuery)
                  s3Stmtfrom.execute(query)
                  s3Stmtfrom.execute(auditQuery)
                  s3DbConnfrom.commit()
                  logger.info("Updated the upload details for the file "+directory.getName +"in the table "+id+"_dropbox")
                }
                /*println("Content ETag from S3: "+amazonS3Client.getObjectMetadata(S3Bucket, directory.getName).getETag);
                println("Content Length from S3: "+);
                println("Values: "+resp.getMetadata().getRawMetadata.values().toString())*/
                
                tm.shutdownNow()
                logger.info("Transfer Manager Shutdown ")
                amazonS3Client.shutdown()
                logger.info("Amazon S3 Client Shutdown ")
                if(filesUploaded == limit.toInt)
                {
                    logger.info("-------------->Breaking loop after uploading "+limit+" file(s)!"+"<--------------")
                    break                  
                }  
                  
    
            /*if (directory.isDirectory()) {
              println("Directory")
              val upDirectory = tm.uploadDirectory(bucket, "Test"directory.getName(), directory, true);*/
    
          }
      }
      
    } 
    catch 
    {
      case ase: AmazonServiceException => {
        excep = true
        System.err.println("Amazon Service Exception: " + ase.toString)
      }
      case ace: AmazonClientException => {
        excep = true
        System.err.println("Amazon Client Exception: " + ace.toString)
      }
      case fnf: java.io.FileNotFoundException => {
        excep = true
        fnf.printStackTrace()
      }
      case ex: SQLException => {
        excep = true
        ex.printStackTrace()
      }
      case ex: Exception => {
        excep = true
        ex.printStackTrace()
      }
    }
    finally 
    {
      if(excep == true)
      {
          var query = "update " + id + "_dropbox" + " set UploadStatus ='Failed' , ProcessStatus = 'S3 Upload Failed'" + " where Filepath ='" +file +"';"
          var auditQuery = "update dropbox" + " set UploadStatus ='Failed' , ProcessStatus = 'S3 Upload Failed'" + " where Filepath ='" +file +"' and Process_id="+id +";"
          logger.info("Update Query in case of exception: "+query)
          logger.info("Update Query in case of exception in the Audit Trails table: "+auditQuery)
          s3Stmtfrom.execute(query)
          s3Stmtfrom.execute(auditQuery)
          s3DbConnfrom.commit()
          logger.info("Updated the status of failed process in the DB. ")
      }
      detailMap.put("name", name)
      detailMap.put("db", db)
      detailMap.put("bucket", S3Bucket)
      detailMap.put("ddlSql", ddlSql)
      detailMap.put("Files Uploaded",filesUploaded.toString)
    }
    context

  }

  def executeIf(context: Context, action: in.handyman.dsl.Action): Boolean = {
    val s3AsIs: in.handyman.dsl.S3 = action.asInstanceOf[in.handyman.dsl.S3]
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