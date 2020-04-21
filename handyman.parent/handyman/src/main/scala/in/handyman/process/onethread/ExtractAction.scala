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
    val ddlSql: String = /*extract.getValue.replaceAll("\"", "")*//*"select filepath,source from 204_dropbox;"*/"select table_name from information_schema.tables where table_schema in ('jc');"
    val id = context.getValue("process-id")
    val db = extract.getDb
    var sdirectory = extract.getSdirectory
    var ddirectory = extract.getDdirectory
    var file = extract.getFile

    val extractDbConnfrom = ResourceAccess.rdbmsConn(db)
    val extractStmtfrom = extractDbConnfrom.createStatement
    extractDbConnfrom.setAutoCommit(false)
    val now = "now()"

    var outputStream: FileOutputStream = null

    try {

      val rs = extractStmtfrom.executeQuery(ddlSql)

      while (rs.next()) {

        sdirectory = rs.getString("table_name")
        var fileName = ""
        //outputStream = new FileOutputStream(ddirectory)
        if(sdirectory.contains("Hourly Data"))
        {
          ddirectory = ddirectory + "\\Hourly Extracts"
          fileName = rs.getString("source").stripPrefix("MCASuiteHourlyBackup/")
        }
        //outputStream = new FileOutputStream(ddirectory)
        val folderPath: Path = Paths.get(ddirectory+"\\"+fileName)
        val tmpDir: Path = Files.createTempDirectory(folderPath,"")
        val zipFile = new ZipFile(sdirectory);
        zipFile.extractAll(ddirectory);
        
        
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
    } catch {
      case ex @ (_: ZipException | _: SQLException) => {
        ex.printStackTrace()
      }
    } finally {
      //outputStream.close()
      detailMap.put("name", name)
      detailMap.put("source directory", sdirectory)
      detailMap.put("destination directory", ddirectory)
      detailMap.put("file", file)
      detailMap.put("ddlSql", ddlSql)
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