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
import scala.util.Try
import java.io.File
import net.lingala.zip4j.exception.ZipException
import net.lingala.zip4j.ZipFile

import java.nio.file.{Path, Paths, Files}


class ExtractAction extends in.handyman.command.Action with LazyLogging {
  val detailMap = new java.util.HashMap[String, String]
  val auditMarker = "EXTRACT";
  val aMarker = MarkerFactory.getMarker(auditMarker);

  def execute(context: Context, action: in.handyman.dsl.Action, actionId: Integer): Context = {
    val extractAsIs: in.handyman.dsl.Extract = action.asInstanceOf[in.handyman.dsl.Extract]
    val extract: in.handyman.dsl.Extract = CommandProxy.createProxy(extractAsIs, classOf[in.handyman.dsl.Extract], context)

    val name = extract.getName
    val ddlSql: String = extract.getValue.replaceAll("\"", "")/*"select filepath,source from 204_dropbox;"*/
    val id = context.getValue("process-id")
    val db = extract.getDb
    var sdirectory = extract.getSdirectory
    var ddirectory = extract.getDdirectory
    var file = extract.getFile
    var excep = false

    val extractDbConnfrom1 = ResourceAccess.rdbmsConn(db)
    val extractStmtfrom1 = extractDbConnfrom1.createStatement
    extractDbConnfrom1.setAutoCommit(false)
    val now = "now()"

    var outputStream: FileOutputStream = null
    var filesExtracted = 0
    try {
      val extractDbConnfrom = ResourceAccess.rdbmsConn(db)
      val extractStmtfrom = extractDbConnfrom.createStatement
      extractDbConnfrom.setAutoCommit(false)

      val rs = extractStmtfrom.executeQuery(ddlSql)
      
      while (rs.next()) {
        
        sdirectory = rs.getString("filepath")
        var fileName = rs.getString("source")
        //outputStream = new FileOutputStream(ddirectory)
        if(sdirectory.contains("\\"))
        {
            if(sdirectory.contains("Hourly Data"))
            {
              ddirectory = ddirectory + "\\Hourly Extracts"
              fileName = rs.getString("source").stripPrefix("MCASuiteHourlyBackup/")
            }
        }
        
        //outputStream = new FileOutputStream(ddirectory)
        val folderPath: Path = Paths.get(ddirectory/*+"\\"+fileName*/)
        val tmpDir: Path = Files.createTempDirectory(folderPath,fileName.stripSuffix(".zip")+"-")
        val zipFile = new ZipFile(sdirectory);
        zipFile.extractAll(tmpDir.toString());
        filesExtracted +=1
        logger.info("Files Extracted--------------->" + filesExtracted)
        
        
        var query = "update " + id + "_dropbox" + " set ExtractLocation ='" + tmpDir.toString().replace("\\", "\\\\")  + "', ExtractTime= now(), ProcessStatus = 'Extract Complete' where Filepath ='" +sdirectory.replace("\\", "\\\\") +"';"
        var auditQuery = "update dropbox" + " set ExtractLocation ='" + tmpDir.toString().replace("\\", "\\\\")  + "', ExtractTime= now(), ProcessStatus = 'Extract Complete' where Filepath ='" +sdirectory.replace("\\", "\\\\") +"' and Process_id="+id +";"
        logger.info("Update Query: " + query) 
        logger.info("Update Query for Extract Location in the Audit trail table: " + auditQuery)
        extractStmtfrom1.execute(query)
        extractStmtfrom1.execute(auditQuery)
        extractDbConnfrom1.commit()
        logger.info("Updated the extract location for the file "+sdirectory +"in the table "+id+"_dropbox")
        ddirectory = ddirectory.stripSuffix("\\Hourly Extracts")
        
        /*for (file <- new java.io.File(sdirectory).listFiles if file.getName endsWith ".zip") {

          val zipFile = new ZipFile(file);
          zipFile.extractAll(ddirectory);

        }*/

      }

      /*if (file == null || file == "") {

        for (file <- new java.io.File(sdirectory).listFiles if file.getName endsWith ".zip") {

          val zipFile = new ZipFile(file);
          zipFile.extractAll(ddirectory);

        }
      } else if (sdirectory == null || sdirectory == "") {

        val zipFile = new ZipFile(file);
        zipFile.extractAll(ddirectory);

      }*/
    } 
    catch 
    {
      case ex @ (_: ZipException | _: SQLException | _: Exception) => {
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
          extractStmtfrom1.execute(query)
          extractStmtfrom1.execute(auditQuery)
          extractDbConnfrom1.commit()
          logger.info("Updated the status of failed process in the DB. ")
      }
      //outputStream.close()
      detailMap.put("name", name)
      detailMap.put("source directory", sdirectory)
      detailMap.put("destination directory", ddirectory)
      detailMap.put("file", file)
      detailMap.put("ddlSql", ddlSql)
      detailMap.put("Files Extracted", filesExtracted.toString())
    }
    context

  }

  def executeIf(context: Context, action: in.handyman.dsl.Action): Boolean = {
    val extractAsIs: in.handyman.dsl.Extract = action.asInstanceOf[in.handyman.dsl.Extract]
    val extract: in.handyman.dsl.Extract = CommandProxy.createProxy(extractAsIs, classOf[in.handyman.dsl.Extract], context)

    val expression = extract.getCondition
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