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
import scala.reflect.io.Directory


class DeleteFolderAction extends in.handyman.command.Action with LazyLogging {
  val detailMap = new java.util.HashMap[String, String]
  val auditMarker = "CheckSum";
  val aMarker = MarkerFactory.getMarker(auditMarker);

  def execute(context: Context, action: in.handyman.dsl.Action, actionId: Integer): Context = {
    val deletefolderAsIs: in.handyman.dsl.DeleteFolder = action.asInstanceOf[in.handyman.dsl.DeleteFolder]
    val deletefolder: in.handyman.dsl.DeleteFolder = CommandProxy.createProxy(deletefolderAsIs, classOf[in.handyman.dsl.DeleteFolder], context)

    val name = deletefolder.getName
    val folderPath = deletefolder.getFoldersource
    val zipFilePath = deletefolder.getZipsource
    
    logger.info("Folder being deleted------>"+folderPath)
    
    var fCount = 0; //count of files for which checksum have been created
    try 
    {
              var direc = new File(folderPath)
              var folder = new Directory(direc)
              folder.deleteRecursively()
              logger.info("Folder "+folderPath+"deleted successfully------>")
              var direc1 = new File(zipFilePath)
              var folder1 = new Directory(direc1)
              folder1.deleteRecursively()
              logger.info("Zip File "+zipFilePath+"deleted successfully------>")
    } 
    catch 
    {
      case ex: Exception => {
        ex.printStackTrace()
      }
    }
    finally 
    {
      detailMap.put("name", name)
      detailMap.put("folder deleted", folderPath)
      
    }
    context

  }
  
  
  def executeIf(context: Context, action: in.handyman.dsl.Action): Boolean = {
    val deletefolderAsIs: in.handyman.dsl.DeleteFolder = action.asInstanceOf[in.handyman.dsl.DeleteFolder]
    val deletefolder: in.handyman.dsl.DeleteFolder = CommandProxy.createProxy(deletefolderAsIs, classOf[in.handyman.dsl.DeleteFolder], context)

    val expression = deletefolder.getCondition
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