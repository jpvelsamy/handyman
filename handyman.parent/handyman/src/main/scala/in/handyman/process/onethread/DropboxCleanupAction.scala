package in.handyman.process.onethread

import com.typesafe.scalalogging.LazyLogging
import in.handyman.command.Context
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
import com.dropbox.core.{ DbxAppInfo, DbxRequestConfig, DbxWebAuth }
import com.dropbox.core.v2.DbxClientV2
import scala.util.Try
import com.dropbox.core.v2.files.Metadata
import com.dropbox.core.v2.files.FileMetadata
import com.dropbox.core.v2.files.FolderMetadata
import org.slf4j.MarkerFactory
import scala.collection.JavaConversions._
import scala.Array
import scala.util.control._
import java.io.File
import java.util.zip._
import java.time.format.DateTimeFormatter
import java.time.LocalDate

class DropboxCleanupAction extends in.handyman.command.Action with LazyLogging {
  val detailMap = new java.util.HashMap[String, String]
  val auditMarker = "DROPBOX";
  val aMarker = MarkerFactory.getMarker(auditMarker);

  def execute(context: Context, action: in.handyman.dsl.Action, actionId: Integer): Context = {
    val dropboxCleanupAsIs: in.handyman.dsl.DropboxCleanup = action.asInstanceOf[in.handyman.dsl.DropboxCleanup]
    val dropboxCleanup: in.handyman.dsl.DropboxCleanup = CommandProxy.createProxy(dropboxCleanupAsIs, classOf[in.handyman.dsl.DropboxCleanup], context)
    
    val name = dropboxCleanup.getName
    val auth = dropboxCleanup.getAuth
    val filetype = dropboxCleanup.getType
    val temp = dropboxCleanup.getValue
    val config = new DbxRequestConfig("jcdropboxtest")
    val client = new DbxClientV2(config, auth)
    var ftype: Metadata = null
    
    var types = filetype.split(" ")
    var sequence = types(0)
    var fileorFolder = types(1)
    
    var filesCleanedUp= 0
    val format = new java.text.SimpleDateFormat("yyyy-MM-dd")
    var targetDate:LocalDate = null
    if(name.contains("Daily"))
    {
      targetDate=LocalDate.now().minusDays(7);
    }
    else
    {
      targetDate=LocalDate.now().minusDays(1);
    }
    
    logger.info("Target Date: "+targetDate)
    try
    {
        val items = client.files.listFolder("").getEntries
        
        for (item <- items)
        {
              var fileName = "/" + item.getName();
              ftype = client.files().getMetadata(fileName)
              
              if(fileName.contains("Hourly") && sequence.equalsIgnoreCase("hourly") && fileorFolder.equalsIgnoreCase("files"))
              {
                  if (ftype.isInstanceOf[FolderMetadata])
                  {
                      val items = client.files.listFolder(fileName).getEntries
                      
                      for (item <- items) 
                      {              
                          var fname = "/" + item.getName();
                          var filetype = client.files().getMetadata(fileName+fname)
                          
                          val itemMetadata =item.toString().split(",")
                          var clientModifiedTime :Array[String]=null
                          var serverModifiedTime :Array[String]=null
                          for (i <- 0.until(itemMetadata.length)) 
                          {
                              
                              if(itemMetadata(i).contains("client_modified"))
                              {
                                clientModifiedTime= itemMetadata(i).split(":",2)
                              }
                              if(itemMetadata(i).contains("server_modified"))
                              {
                                serverModifiedTime= itemMetadata(i).split(":",2)
                              }
                          }
                          var cmtime = clientModifiedTime(1).replace("\"", "").replace(",", "").replace("T"," ").replace("Z", "").dropRight(9)
                          var smtime = serverModifiedTime(1).replace("\"", "").replace(",", "").replace("T"," ").replace("Z", "").dropRight(9)
                          
                          var chk = format.parse(targetDate.toString).compareTo(format.parse(cmtime))
                          var chk1 = format.parse(targetDate.toString).compareTo(format.parse(smtime))
                          if(chk==1 || chk1==1)
                          {
                              val downloadFile = Try {
                                  client.files.delete(fileName+fname)
                              }
                              logger.info("File "+ fileName+fname +" cleaned up from Dropbox")
                              filesCleanedUp +=1
                          }
                          else
                          {
                            logger.info("File "+fileName+fname+" uploaded today")
                          }
                      }
                  }
              }
              
              if(!fileName.contains("Hourly") && sequence.equalsIgnoreCase("daily") && ftype.isInstanceOf[FileMetadata])
              {
                      val itemMetadata =item.toString().split(",")
                      var clientModifiedTime :Array[String]=null
                      var serverModifiedTime :Array[String]=null
                      for (i <- 0.until(itemMetadata.length)) 
                      {
                        //println(itemMetadata(i))
                          
                          if(itemMetadata(i).contains("client_modified"))
                          {
                            clientModifiedTime= itemMetadata(i).split(":",2)
                          }
                          if(itemMetadata(i).contains("server_modified"))
                          {
                            serverModifiedTime= itemMetadata(i).split(":",2)
                          }
                      }
                      var cmtime = clientModifiedTime(1).replace("\"", "").replace("T", " ").replace(",", "").replace("Z", "").dropRight(9)
                      var smtime = serverModifiedTime(1).replace("\"", "").replace("T", " ").replace(",", "").replace("Z", "").dropRight(9)
                      
                      var cmformatted = java.time.LocalDate.parse(cmtime, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                      var smformatted = java.time.LocalDate.parse(smtime.replace("T", " "), DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                      var chk = format.parse(targetDate.toString).compareTo(format.parse(cmtime))
                      var chk1 = format.parse(targetDate.toString).compareTo(format.parse(smtime))
                      if(chk==1 || chk1==1)
                      {
                          val downloadFile = Try {
                              client.files.delete(fileName)
                          }
                          logger.info("File "+ fileName +" cleaned up from Dropbox")
                          filesCleanedUp +=1
                      }
                      else
                      {
                        logger.info("File "+fileName+" uploaded within a week")
                      }
                      
              }
        }
        
        
        
    }
    catch
    {
      case ex : com.dropbox.core.NetworkIOException => {
        //excep = true
        ex.printStackTrace()
      }
      case ex : Exception => {
        //excep = true
        ex.printStackTrace()
      }
    }
    finally
    {
        detailMap.put("name", name)
        detailMap.put("Files Cleaned Up",filesCleanedUp.toString)
        
    }
    
    
    
  context

  }
  
  def executeIf(context: Context, action: in.handyman.dsl.Action): Boolean = {
    val dropboxCleanupAsIs: in.handyman.dsl.DropboxCleanup = action.asInstanceOf[in.handyman.dsl.DropboxCleanup]
    val dropboxCleanup: in.handyman.dsl.DropboxCleanup = CommandProxy.createProxy(dropboxCleanupAsIs, classOf[in.handyman.dsl.DropboxCleanup], context)

    val expression = dropboxCleanup.getCondition
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