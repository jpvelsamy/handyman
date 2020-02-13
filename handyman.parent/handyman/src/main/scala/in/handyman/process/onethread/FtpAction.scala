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
import java.util.Date

class FtpAction extends in.handyman.command.Action with LazyLogging {
  val detailMap = new java.util.HashMap[String, String]

  def execute(context: in.handyman.command.Context, action: in.handyman.dsl.Action): in.handyman.command.Context = {
    val ftpAsIs: in.handyman.dsl.Ftp = action.asInstanceOf[in.handyman.dsl.Ftp]
    val ftp: in.handyman.dsl.Ftp = CommandProxy.createProxy(ftpAsIs, classOf[in.handyman.dsl.Ftp], context)

    val id = context.getValue("process-id")
    val name = ftp.getName
    val db = ftp.getDb
    val domain: String = ftp.getDomain
    val port = ftp.getPort.toInt
    val user = ftp.getUser
    val key = ftp.getKey
    var rpath = ftp.getRpath
    var lopath = ftp.getLpath
    val actiontype = ftp.getAction
    val method = ftp.getMethod
    val sql = ftp.getValue.replaceAll("\"", "")

    val FTP_TIMEOUT_MILLIS: Int = 20 * 1000
    val FTP_PROTOCOL_DEBUGGING: Boolean = true
    val fileMap = new java.util.HashMap[String, String]
    var checksum = ""
    var size: Double = 0l
    var ftype = ""
    var date: Date = null
    var modified = ""
    var author = ""

    val conn = ResourceAccess.rdbmsConn(db)
    val stmt = conn.createStatement
    val rs = stmt.executeQuery(sql.trim())

    val incomingFtpReq: AtomicInteger = new AtomicInteger

    //val statementId = AuditService.insertStatementAudit(actionId, "ftp->" + name, context.getValue("process-name"))
    logger.error("Attempting to get files from ftp, name={}, asUser={}, securitykey={}, dbsrc={}, domain={}", name, user, key, db, domain)

    val startTimeMillis: Long = System.currentTimeMillis()
    val ftpc: FTPClient = new FTPClient()
    val writer: PrintWriter = new PrintWriter(System.out)
    try {

      if (FTP_PROTOCOL_DEBUGGING)
        ftpc.addProtocolCommandListener(new PrintCommandListener(writer))

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

      def download(ftpc: FTPClient, rapth: String, lpath: String): Unit = {

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

        // Iteratively download all files in the directory.

        // need to pass the remote path for changing directory, not doing it now since changing directory is not working

        for (file <- ftpc.listFiles()) {
          println(
            MessageFormat.format(
              "Transferring remote file: {0} to local directory: {1}",
              file.getName,
              lpath))

          if (file.isFile()) {
            val target: File = new File(lpath + file.getName)
            val outputStream: OutputStream = new BufferedOutputStream(
              new FileOutputStream(target))
            ftpc.retrieveFile(file.getName, outputStream)
            val inputStream = new FileInputStream(lpath + file.getName)
            checksum = DigestUtils.md5Hex(inputStream)
            size = file.getSize
            size = size
            date = file.getTimestamp.getTime
            ftype = if (file.isDirectory) "Directory" else "File"
            author = file.getUser
            fileMap.put(file.getName, " checksum : " + checksum + ", size : " + size + " bytes" + ", date : " + date + ", type : " + ftype
              + ", modified : " + modified + ", author : " + author)
            inputStream.close()
            outputStream.close()

            if (!FTPReply.isPositiveCompletion(ftpc.getReplyCode)) {
              println(
                MessageFormat.format(
                  "Download for file: {0} failed",
                  file.getName))
            }
          } else if (file.isDirectory()) {

            rpath = rpath + file.getName
            lopath = lopath + file.getName
            val dir: File = new File(lpath + file.getName)
            if (!dir.exists())
              dir.mkdirs()
            download(ftpc, rpath, lopath)
          } else {
            throw new RuntimeException("Invalid Directory")
          }
        }
      }

      if (actiontype == "download") {

        if (method == "auto") {

          download(ftpc, rpath, lopath)

        } else {
          while (rs.next()) {

            if (rs.getString("type") == "file") {

              val target: File = new File(lopath + rs.getString("file"))
              val outputStream: OutputStream = new BufferedOutputStream(
                new FileOutputStream(target))
              ftpc.retrieveFile(rs.getString("file"), outputStream)
              val inputStream = new FileInputStream(lopath + rs.getString("file"))
              checksum = DigestUtils.md5Hex(inputStream)
              inputStream.close()
              outputStream.close()
              if (!FTPReply.isPositiveCompletion(ftpc.getReplyCode)) {
                println(
                  MessageFormat.format(
                    "Download for file: {0} failed",
                    rs.getString("file")))
              }
            } else if (rs.getString("type") == "directory") {
              rpath = rpath + rs.getString("file")
              lopath = lopath + rs.getString("file")
              val dir: File = new File(lopath + rs.getString("file"))
              if (!dir.exists())
                dir.mkdirs()
              download(ftpc, rpath, lopath)

            }
          }
        }
      } else {

        if (method == "auto") {

          val path: File = new File(lopath);
          for (file <- path.listFiles()) {

            val input: InputStream = new FileInputStream(
              new File(lopath + file.getName))

            val inputStream = new FileInputStream(lopath + file.getName)
            checksum = DigestUtils.md5Hex(inputStream)
            inputStream.close()

            // Upload file to FTP server.
            println(
              MessageFormat.format(
                "Transferring file: {0} to FTP host from local directory: {1}",
                file.getName,
                lopath))
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
              new File(lopath + rs.getString("file")))
            val inputStream = new FileInputStream(lopath + rs.getString("file"))
            checksum = DigestUtils.md5Hex(inputStream)
            inputStream.close()
            // Upload file to FTP server.
            println(
              MessageFormat.format(
                "Transferring file: {0} to FTP host from local directory: {1}",
                rs.getString("file"),
                lopath))
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
      detailMap.put("localpath", lopath)
      detailMap.put("action", actiontype)
      detailMap.put("method", method)
      detailMap.put("sql", sql)
      detailMap.put("checksum", fileMap.toString())

      detailMap.put("incomingFtpReq", incomingFtpReq.intValue().toString())
      //AuditService.updateStatementAudit(statementId, incomingFtpReq.intValue(), 1, sql, 1)
      stmt.close
      conn.close

    }
    logger.error("Completed  downloading file from ftp server, name={}, asUser={}, securitykey={}, dbsrc={}, domain={}", name, user, key, db, domain)
    context
  }

  def executeIf(context: in.handyman.command.Context, action: in.handyman.dsl.Action): Boolean = {
    val copydataAsIs = action.asInstanceOf[in.handyman.dsl.Copydata]
    val copydata: in.handyman.dsl.Copydata = CommandProxy.createProxy(copydataAsIs, classOf[in.handyman.dsl.Copydata], context)

    val expression = copydata.getCondition
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