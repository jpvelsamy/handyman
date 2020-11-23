package in.handyman.process.onethread

import java.io._
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException
import java.nio.file.Files
import java.nio.file.attribute.BasicFileAttributes
import java.nio.file.attribute.FileTime
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Date
import java.util.List
import java.util.zip.GZIPOutputStream
import org.apache.commons.compress.archivers.tar.TarArchiveEntry
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream
import org.apache.commons.compress.utils.IOUtils
import org.apache.commons.io.FileUtils
import org.json.JSONArray
import org.json.JSONObject
import com.typesafe.scalalogging.LazyLogging
import in.handyman.command.CommandProxy
import in.handyman.util.ParameterisationEngine
import in.handyman.util.ResourceAccess
import java.math.BigInteger
import java.util.UUID

class TarGenerationAction extends in.handyman.command.Action with LazyLogging {
  val detailMap = new java.util.HashMap[String, String]
  var rs: ResultSet = null
  var connection: Connection = null
  var date: Date = new Date()
  var formattedDate: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
  var s = java.util.UUID.randomUUID().toString();
  var csvColumnCount: Int = 0
  var csvRowCount: Int = 0
  var csvFileSize: String = null
  var tarFilePath = System.getProperty("user.dir") + "/" + "ZIO_CI_" + s + "/"
  var csvName = "ZIO_CI" + s + ".csv"
  var jsonName = "ZIO_CI" + s + ".json"
  var csvFileName = System.getProperty("user.dir") + "/" + "ZIO_CI_" + s + "/" + csvName
  var jsonFileName = System.getProperty("user.dir") + "/" + "ZIO_CI_" + s + "/" + jsonName
  var filecreatedDate: String = null
  var start: String = null
  var end: String = null
  var status: String = null
  var hostName: String = null
  var startDate: Date = null
  var endDate: Date = null

  var processName: String = null
  var fileLocationPath = System.getProperty("user.dir") + "/" + "ZIO_CI_" + s

  def execute(context: in.handyman.command.Context, action: in.handyman.dsl.Action, actionId: Integer): in.handyman.command.Context =
    {
      val sampleDataAsIs: in.handyman.dsl.TarGeneration = action.asInstanceOf[in.handyman.dsl.TarGeneration]
      val sampleData: in.handyman.dsl.TarGeneration = CommandProxy.createProxy(sampleDataAsIs, classOf[in.handyman.dsl.TarGeneration], context)
      val db = sampleData.getSource
      val instanceDb = sampleData.getAuditVal
      val dropboxDbConnfrom = ResourceAccess.rdbmsConn(db)
      val dropboxStmtfrom = dropboxDbConnfrom.createStatement
      val processId = sampleData.getProcess_id
      val table = sampleData.getValue
      dropboxDbConnfrom.setAutoCommit(false)
      val instanceDbConnfrom = ResourceAccess.rdbmsConn(instanceDb)
      val instanceStmtfrom = instanceDbConnfrom.createStatement
      val incomingJson = context.getValue("myjson")
      context.addValue("fileDownloadPath", fileLocationPath)
      instanceDbConnfrom.setAutoCommit(false)
      readDataFromDB()

      def readDataFromDB(): Unit = {
        val sqlWithoutQuotes = table.replaceAll("\"", "")
        val sqlList = sqlWithoutQuotes.split(";")
        val jsonArray: JSONObject = new JSONObject()
        val colData: List[String] = new ArrayList[String]()
        try {
          val rs: ResultSet = dropboxStmtfrom.executeQuery(sqlWithoutQuotes)
          val col: Int = rs.getMetaData.getColumnCount
          val processDetails = "select hostname,instance_name, start,end,status from instance_audit where instance_id=" + processId;
          val instanceRS: ResultSet = instanceStmtfrom.executeQuery(processDetails);
          val instanceCol = instanceRS.getMetaData.getColumnCount
          while (instanceRS.next()) {
            processName = instanceRS.getString("instance_name")
            start = instanceRS.getString("start")
            end = instanceRS.getString("end")
            status = instanceRS.getString("status")
            hostName = instanceRS.getString("hostname")
            startDate = formattedDate.parse(start)
            endDate = formattedDate.parse(end)

          }
          csvColumnCount = col
          val file: File = addDataToJSONArray(jsonArray, colData, rs, col)
          writeIntoJSONFile(jsonArray)
          createTarFile(file, s)
          removeDirectoryIfExists(file)
        } catch {
          case e: SQLException => e.printStackTrace()
        }
      }
      def addDataToJSONArray(
        jsonArray: JSONObject,
        colData: List[String],
        rs: ResultSet,
        col: Int): File = {
        val columnDataArray: JSONArray = new JSONArray()
        var i: Int = 1
        while (i <= col) {
          val obj: JSONObject = new JSONObject()
          colData.add(rs.getMetaData.getColumnLabel(i))
          obj.put("columnName", rs.getMetaData.getColumnLabel(i))
          obj.put("dataType", rs.getMetaData.getColumnTypeName(i))
          obj.put("length", rs.getMetaData.getColumnDisplaySize(i))
          columnDataArray.put(obj)
          i += 1
        }
        println(System.getProperty("user.dir") + "/" + "ZIO_CI_" + s)
        val file: File = new File(
          System.getProperty("user.dir") + "/" + "ZIO_CI_" + s)
        file.mkdir()
        val checkSumVal: String = convertTableDataToCSVFile(colData, rs, col, file)
        calculateFileSizeRowAndColumnCount(file)
        val csvFileDetails: JSONObject = new JSONObject()
        csvFileDetails.put("fileName", csvName)
        csvFileDetails.put("fileLocation", csvFileName)
        csvFileDetails.put("fileSize", csvFileSize)
        csvFileDetails.put("rowCount", csvRowCount)
        csvFileDetails.put("columnCount", csvColumnCount)
        csvFileDetails.put("createdOn", filecreatedDate)
        csvFileDetails.put("tarFileName", "ZIO_CI_" + s.concat(".tar.gz"))
        csvFileDetails.put("minBoundary", "")
        csvFileDetails.put("maxBoundary", "")
        csvFileDetails.put("checksum", checkSumVal)
        val userDetails: JSONObject = new JSONObject(incomingJson)
        userDetails.put("organizationName", "")
        var sigmaId: String = String.valueOf(System.nanoTime());
        userDetails.put("sigmaId", sigmaId)
        val macroRuntimeDetailsObj: JSONObject = new JSONObject()
        macroRuntimeDetailsObj.put("processId", processId)
        macroRuntimeDetailsObj.put("processName", processName)
        macroRuntimeDetailsObj.put("status", "")
        macroRuntimeDetailsObj.put("errorDescription", "")
        macroRuntimeDetailsObj.put("startOn", startDate)
        macroRuntimeDetailsObj.put("endedOn", endDate)
        var startTime = formattedDate.parse(start).getTime;
        var endTime = formattedDate.parse(end).getTime
        macroRuntimeDetailsObj.put("totalDuration", endTime - startTime)
        csvFileDetails.put("executionContext", macroRuntimeDetailsObj)
        csvFileDetails.put("userDetails", userDetails)
        csvFileDetails.put("columns", columnDataArray)
        jsonArray.put("data", csvFileDetails)
        file
      }

      def writeIntoJSONFile(jsonArray: JSONObject): Unit = {
        val jsonFile: FileWriter = new FileWriter(jsonFileName)
        jsonFile.write(jsonArray.toString(4))
        jsonFile.close()
      }

      def removeDirectoryIfExists(file: File): Unit = {
        if (file.isDirectory && file.exists()) {
          FileUtils.cleanDirectory(file)
          FileUtils.forceDelete(file)
        }
      }

      def calculateFileSizeRowAndColumnCount(file: File): Unit = {
        var sizeBig: BigInteger = null
        if (csvFileName.endsWith(".csv")) {
          sizeBig = FileUtils.sizeOfDirectoryAsBigInteger(file)
          csvFileSize = FileUtils.byteCountToDisplaySize(sizeBig)
          val br: BufferedReader = new BufferedReader(new FileReader(csvFileName))
          var count: Int = 0
          while ((br.readLine()) != null) { count += 1; count - 1 }
          csvRowCount = count
        }
      }

      def convertTableDataToCSVFile(
        colData: List[String],
        rs: ResultSet,
        col: Int,
        file: File): String = {
        val fileWriter: BufferedWriter = new BufferedWriter(
          new FileWriter(csvFileName))
        getFileCreatedDateAndTime(file)
        val checkSumVal: String = calChecksum(csvFileName).toString
        val sb: StringBuilder = new StringBuilder()
        var i: Int = 1
        while (i <= col) {
          sb.append(rs.getMetaData.getColumnLabel(i))
          if (i < col) sb.append(',') else sb.append('\n')
          i += 1
        }
        while (rs.next()) {
          colData.forEach((arg0) =>
            try {
              sb.append(rs.getString(arg0))
              sb.append(",")
            } catch {
              case e: SQLException => e.printStackTrace()

            })
          sb.append("\r\n")
        }
        fileWriter.write(sb.toString)
        fileWriter.close()
        checkSumVal
      }

      def getFileCreatedDateAndTime(file: File): Unit = {
        var attrs: BasicFileAttributes = null
        attrs = Files.readAttributes(file.toPath(), classOf[BasicFileAttributes])
        val time: FileTime = attrs.creationTime()
        val pattern: String = "yyyy-MM-dd HH:mm:ss"
        val simpleDateFormat: SimpleDateFormat = new SimpleDateFormat(pattern)
        filecreatedDate = simpleDateFormat.format(new Date(time.toMillis()))
      }

      def calChecksum(filepath: String): StringBuffer = {
        var messageDigest: MessageDigest = null
        var sb: StringBuffer = null
        try {
          messageDigest = MessageDigest.getInstance("SHA1")
          val fileInput: FileInputStream = new FileInputStream(filepath)
          val dataBytes = Array.ofDim[Byte](1024)
          var bytesRead: Int = 0
          while ((fileInput.read(dataBytes)) != -1)
            messageDigest.update(dataBytes, 0, bytesRead)
          val digestBytes: Array[Byte] = messageDigest.digest()
          sb = new StringBuffer("")
          for (i <- 0 until digestBytes.length) {
            sb.append(
              java.lang.Integer
                .toString((digestBytes(i) & 0xff) + 0x100, 16)
                .substring(1))
          }
          fileInput.close()
        } catch {
          case e @ (_: NoSuchAlgorithmException | _: IOException) =>
            e.printStackTrace()
        }
        sb
      }

      def createTarFile(sourceDir: File, s: String): Unit = {
        var tarOs: TarArchiveOutputStream = null
        try {
          val source = sourceDir
          val fos: FileOutputStream = new FileOutputStream(
            source.getAbsolutePath.concat(".tar.gz"))
          val gos: GZIPOutputStream = new GZIPOutputStream(
            new BufferedOutputStream(fos))
          tarOs = new TarArchiveOutputStream(gos)
          addFilesToTarGZ(tarFilePath, "", tarOs)
        } catch {
          case e: IOException => e.printStackTrace()

        } finally try tarOs.close()
        catch {
          case e: IOException => e.printStackTrace()
        }
      }

      def addFilesToTarGZ(
        filePath: String,
        parent: String,
        tarArchive: TarArchiveOutputStream): Unit = {
        val file: File = new File(filePath)
        val entryName: String = parent + file.getName
        tarArchive.putArchiveEntry(new TarArchiveEntry(file, entryName))
        if (file.isFile) {
          val fis: FileInputStream = new FileInputStream(file)
          val bis: BufferedInputStream = new BufferedInputStream(fis)
          IOUtils.copy(bis, tarArchive)
          tarArchive.closeArchiveEntry()
          bis.close()
        } else if (file.isDirectory) {
          tarArchive.closeArchiveEntry()
          for (f <- file.listFiles()) {
            addFilesToTarGZ(
              f.getAbsolutePath,
              entryName + File.separator,
              tarArchive)
          }
        }
      }

      context
    }

  def executeIf(context: in.handyman.command.Context, action: in.handyman.dsl.Action): Boolean =
    {
      val sampleDataAsIs: in.handyman.dsl.TarGeneration = action.asInstanceOf[in.handyman.dsl.TarGeneration]
      val sampleFile: in.handyman.dsl.TarGeneration = CommandProxy.createProxy(sampleDataAsIs, classOf[in.handyman.dsl.TarGeneration], context)

      val expression = sampleFile.getCondition
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