package in.handyman.process.onethread

import com.typesafe.scalalogging.LazyLogging
import in.handyman.command.Context
import in.handyman.command.CommandProxy
import in.handyman.util.ParameterisationEngine
import in.handyman.util.ResourceAccess
import org.slf4j.MarkerFactory
import java.util.concurrent.atomic.AtomicInteger
import in.handyman.audit.AuditService
import org.apache.commons.net.PrintCommandListener
import org.apache.commons.net.ftp.FTP
import org.apache.commons.net.ftp.FTPClient
import org.apache.commons.net.ftp.FTPFile
import org.apache.commons.net.ftp.FTPReply
import java.io._
import java.text.MessageFormat
import org.apache.commons.codec.digest.DigestUtils

class FtpAction extends in.handyman.command.Action with LazyLogging {

  val detailMap = new java.util.HashMap[String, String]
  val auditMarker = "GETFILES";
  val aMarker = MarkerFactory.getMarker(auditMarker);

  def execute(context: Context, action: in.handyman.dsl.Action, actionId: Integer): Context = {
    val ftpAsIs: in.handyman.dsl.Ftp = action.asInstanceOf[in.handyman.dsl.Ftp]
    val ftp: in.handyman.dsl.Ftp = CommandProxy.createProxy(ftpAsIs, classOf[in.handyman.dsl.Ftp], context)

    val id = context.getValue("process-id")
    val name = ftp.getName
    val db = ftp.getDb
    val domain: String = ftp.getDomain
    val port = ftp.getPort.toInt
    val user = ftp.getUser
    val key = ftp.getKey
    val rpath = ftp.getRpath
    val lpath = ftp.getLpath
    val actiontype = ftp.getAction
    val method = ftp.getMethod
    val sql = ftp.getValue.replaceAll("\"", "")

    val FTP_TIMEOUT_MILLIS: Int = 20 * 1000
    val FTP_PROTOCOL_DEBUGGING: Boolean = true

    val conn = ResourceAccess.rdbmsConn(db)
    val stmt = conn.createStatement
    val rs = stmt.executeQuery(sql.trim())

    val incomingFtpReq: AtomicInteger = new AtomicInteger

    val statementId = AuditService.insertStatementAudit(actionId, "ftp->" + name, context.getValue("process-name"))
    logger.error(aMarker, "Attempting to get files from ftp, name={}, asUser={}, securitykey={}, dbsrc={}, domain={}", name, user, key, db, domain)

    val startTimeMillis: Long = System.currentTimeMillis()
    val ftpc: FTPClient = new FTPClient()
    val writer: PrintWriter = new PrintWriter(System.out)
    try {

      // Redirect FTP commands to stdout if flag set.

      if (FTP_PROTOCOL_DEBUGGING) {
        ftpc.addProtocolCommandListener(new PrintCommandListener(writer))
      }

      // Connect/login.
      println("Connecting to ftp host: {0} on port: {1}", domain, port)
      ftpc.connect(domain, port)
      ftpc.login(user, key)
      if (!FTPReply.isPositiveCompletion(ftpc.getReplyCode)) {
        throw new RuntimeException("Cannot connect to FTP_HOST: " + domain)
      }

      // Enter pasive (download mode) and set file type as binary data.
      ftpc.setDataTimeout(FTP_TIMEOUT_MILLIS)
      ftpc.setFileType(FTP.BINARY_FILE_TYPE)
      ftpc.enterLocalPassiveMode()
      // Change directory to the directory containing the files we wish to transfer.
      if (rpath == "parent") {
        ftpc.changeToParentDirectory()
        if (!FTPReply.isPositiveCompletion(ftpc.getReplyCode)) {
          throw new RuntimeException(
            "Something is wrong with the directory: " + rpath + "error code is " + ftpc.getReplyCode)
        }
      } else if (rpath != null) {
        ftpc.changeWorkingDirectory(rpath);
        if (!FTPReply.isPositiveCompletion(ftpc.getReplyCode)) {
          throw new RuntimeException(
            "Cannot change directory: " + rpath + "error code is " + ftpc.getReplyCode)
        }
      } else {
        throw new RuntimeException("Please check the specified location")
      }

      if (actiontype == "download") {

        if (method == "auto") {

          // Iteratively download all files in the directory.
          for (file <- ftpc.listFiles()) {
            println(
              MessageFormat.format(
                "Transferring remote file: {0} to local directory: {1}",
                file.getName,
                lpath))
            val target: File = new File(lpath + file.getName)
            val outputStream: OutputStream = new BufferedOutputStream(
              new FileOutputStream(target))
            ftpc.retrieveFile(file.getName, outputStream)
            val inputStream = new FileInputStream(lpath + file.getName)
            val checksum: String = DigestUtils.md5Hex(inputStream)
            inputStream.close()
            outputStream.close()

            if (!FTPReply.isPositiveCompletion(ftpc.getReplyCode)) {
              println(
                MessageFormat.format(
                  "Download for file: {0} failed",
                  file.getName))
            }
          }
        } else {
          while (rs.next()) {
            val target: File = new File(lpath + rs.getString("file"))
            val outputStream: OutputStream = new BufferedOutputStream(
              new FileOutputStream(target))
            ftpc.retrieveFile(rs.getString("file"), outputStream)
            val inputStream = new FileInputStream(lpath + rs.getString("file"))
            val checksum: String = DigestUtils.md5Hex(inputStream)
            inputStream.close()
            outputStream.close()
            if (!FTPReply.isPositiveCompletion(ftpc.getReplyCode)) {
              println(
                MessageFormat.format(
                  "Download for file: {0} failed",
                  rs.getString("file")))
            }
          }
        }
      } else {

        if (method == "auto") {

          val path: File = new File(lpath);
          for (file <- path.listFiles()) {

            val input: InputStream = new FileInputStream(
              new File(lpath + file.getName))

            val inputStream = new FileInputStream(lpath + file.getName)
            val checksum: String = DigestUtils.md5Hex(inputStream)
            inputStream.close()

            // Upload file to FTP server.
            println(
              MessageFormat.format(
                "Transferring file: {0} to FTP host from local directory: {1}",
                file.getName,
                lpath))
            ftpc.storeFile(file.getName, input)
            if (!FTPReply.isPositiveCompletion(ftpc.getReplyCode)) {
              println(
                MessageFormat.format(
                  "Upload for file: {0} failed",
                  file.getName))
            }

          }

        } else {

          while (rs.next()) {

            // Create new input stream for the file to transfer.
            val input: InputStream = new FileInputStream(
              new File(lpath + rs.getString("file")))
            val inputStream = new FileInputStream(lpath + rs.getString("file"))
            val checksum: String = DigestUtils.md5Hex(inputStream)
            inputStream.close()
            // Upload file to FTP server.
            println(
              MessageFormat.format(
                "Transferring file: {0} to FTP host from local directory: {1}",
                rs.getString("file"),
                lpath))
            ftpc.storeFile(rs.getString("file"), input)
            if (!FTPReply.isPositiveCompletion(ftpc.getReplyCode)) {
              println(
                MessageFormat.format(
                  "Upload for file: {0} failed",
                  rs.getString("file")))
            }
          }
        }
      }
    } catch {
      case ex: IOException => {
        println("Error: " + ex.getMessage)
        ex.printStackTrace()
      }

    } finally {

      val endTimeMillis: Long = System.currentTimeMillis()
      val totalTimemillis: Long = endTimeMillis - startTimeMillis
      println("upload time" + totalTimemillis)
      try {
        if (ftpc.isConnected()) {
          ftpc.logout()
          ftpc.disconnect()
        }
        writer.flush()
        writer.close()
      } catch {
        case ex: IOException => ex.printStackTrace()

      }
      detailMap.put("name", name)
      detailMap.put("db", db)
      detailMap.put("domain", domain)
      detailMap.put("user", user)
      detailMap.put("keypath", key)
      detailMap.put("remotepath", rpath)
      detailMap.put("localpath", lpath)
      detailMap.put("action", actiontype)
      detailMap.put("method", method)
      detailMap.put("sql", sql)

      detailMap.put("incomingFtpReq", incomingFtpReq.intValue().toString())
      AuditService.updateStatementAudit(statementId, incomingFtpReq.intValue(), 1, sql, 1)
      stmt.close
      conn.close

    }
    logger.error(aMarker, "Completed  downloading file from ftp server, name={}, asUser={}, securitykey={}, dbsrc={}, domain={}", name, user, key, db, domain)
    context
  }

  def executeIf(context: in.handyman.command.Context, action: in.handyman.dsl.Action): Boolean = {
    val ftpAsIs: in.handyman.dsl.Ftp = action.asInstanceOf[in.handyman.dsl.Ftp]
    val ftp: in.handyman.dsl.Ftp = CommandProxy.createProxy(ftpAsIs, classOf[in.handyman.dsl.Ftp], context)
    val expression = ftp.getCondition
    val db = ftp.getDb
    val name = ftp.getName
    val id = context.getValue("process-id")
    try {
      val output = ParameterisationEngine.doYieldtoTrue(expression)
      logger.info(aMarker, "Completed evaluation to execute id#{}, name#{}, dbSrc#{}, expression#{}", id, name, db, expression)
      detailMap.put("name", name)
      detailMap.putIfAbsent("condition-output", output.toString())
      output
    } finally {
      if (expression != null)
        detailMap.putIfAbsent("condition", "LHS=" + expression.getLhs + ", Operator=" + expression.getOperator + ", RHS=" + expression.getRhs)

    }
  }

  def generateAudit(): java.util.Map[String, String] = {
    this.detailMap
  }
}