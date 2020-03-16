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
import com.dropbox.core.{ DbxAppInfo, DbxRequestConfig, DbxWebAuth }
import com.dropbox.core.v2.DbxClientV2

import scala.util.Try

class DropboxAction extends in.handyman.command.Action with LazyLogging {
  val detailMap = new java.util.HashMap[String, String]

  def execute(context: in.handyman.command.Context, action: in.handyman.dsl.Action): in.handyman.command.Context = {
    val dropboxAsIs: in.handyman.dsl.Dropbox = action.asInstanceOf[in.handyman.dsl.Dropbox]
    val dropbox: in.handyman.dsl.Dropbox = CommandProxy.createProxy(dropboxAsIs, classOf[in.handyman.dsl.Dropbox], context)

    val source = dropbox.getSource
    var target = dropbox.getTarget
    val name = dropbox.getName
    val ddlSql: String = dropbox.getValue.replaceAll("\"", "")
    val id = context.getValue("process-id")
    val db = dropbox.getDb
    val auth = dropbox.getAuth
    var ftype = dropbox.getType

    val dropboxDbConnfrom = ResourceAccess.rdbmsConn(db)
    val dropboxStmtfrom = dropboxDbConnfrom.createStatement
    dropboxDbConnfrom.setAutoCommit(false)
    val now = "now()"

    var outputStream: FileOutputStream = null

    try {

      // azavea/rf-dropbox-test
      val config = new DbxRequestConfig("jcdropboxtest")
      val client = new DbxClientV2(config, auth)
      
      ftype = client.files().getMetadata(source)
      
      //      println("==> TEST BASIC ACCOUNT DETAILS")
      //      val account = client.users.getCurrentAccount
      //      println(s"Linked Account is ${account.getName.getDisplayName}")

      //      val totalSpace = client.users.getSpaceUsage.getAllocation.getIndividualValue.getAllocated
      //      val usedSpace = client.users.getSpaceUsage.getUsed
      //      println(s"${totalSpace - usedSpace} of ${totalSpace} bytes available")
      //      println("==> TEST LISTING FILES")
      //      val items = client.files.listFolder("").getEntries
      //      for (item <- items)
      //        println(item.getPathLower)

      if (ftype == "file") {

        outputStream = new FileOutputStream(target + source)
        val downloadFile = Try {
          client.files
            .download(source)
            .download(outputStream)
          logger.info("Dowload from Dropbox id#{}, name#{}, from#{}, to#{}", id, name, source, target)
          logger.info("Download completed into this location#{}", target)
          if (target.contains("\\")) {
            target = target.replace("\\", "\\\\")
          }
          val query = "insert into " + id + "_dropbox" + " (process_id,name,source,target,time) values " + "(\"" + id + "\",\"" + name + "\",\"" + source + "\",\"" + target + "\"," + now + ");"
          logger.info("Inserted the data into db ")
          dropboxStmtfrom.execute(query)
          dropboxDbConnfrom.commit()
        }

      } else if (ftype == "directory") {

        outputStream = new FileOutputStream(target + source + ".zip")
        val downloadZip = Try {
          client.files
            .downloadZip(source)
            .download(outputStream)
          logger.info("Dowload from Dropbox id#{}, name#{}, from#{}, to#{}", id, name, source, target)
          logger.info("Download completed into this location#{}", target)
          if (target.contains("\\")) {
            target = target.replace("\\", "\\\\")
          }
          val query = "insert into " + id + "_dropbox" + " (process_id,name,source,target,time) values " + "(\"" + id + "\",\"" + name + "\",\"" + source + "\",\"" + target + "\"," + now + ");"
          logger.info("Inserted the data into db ")
          dropboxStmtfrom.execute(query)
          dropboxDbConnfrom.commit()
        }
      }

      //println(s"Downloaded file ${downloadedFile}")

    } catch {
      case ex: SQLException => {
        ex.printStackTrace()
      }
    } finally {
      outputStream.close()
      detailMap.put("name", name)
      detailMap.put("source", source)
      detailMap.put("target", target)
      detailMap.put("ddlSql", ddlSql)
    }
    context
  }

  def executeIf(context: in.handyman.command.Context, action: in.handyman.dsl.Action): Boolean = {
    val dropboxAsIs = action.asInstanceOf[in.handyman.dsl.Dropbox]
    val dropbox: in.handyman.dsl.Dropbox = CommandProxy.createProxy(dropboxAsIs, classOf[in.handyman.dsl.Dropbox], context)

    val expression = dropbox.getCondition
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