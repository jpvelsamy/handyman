package in.handyman.process.onethread

import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.FileWriter
import java.io.IOException
import java.math.BigInteger
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.attribute.BasicFileAttributes
import java.nio.file.attribute.FileTime
import java.security.DigestInputStream
import java.security.MessageDigest
import java.sql.Connection
import java.sql.ResultSet
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Date
import java.util.StringJoiner
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

class ModelGenerationAction extends in.handyman.command.Action with LazyLogging {
  val detailMap = new java.util.HashMap[String, String]
  var rs: ResultSet = null
  var connection: Connection = null
  var date: Date = new Date()
  var formattedDate: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
  var s = java.util.UUID.randomUUID().toString()
  var folderDetails: JSONObject = new JSONObject()
  var fileDetailsArray: JSONArray = new JSONArray()
  var PARENT_DIRECTORY: String = null
  var startDate: Date = null
  var endDate: Date = null
  var processName: String = null
  var start: String = null
  var end: String = null
  var totalDuration: String = null
  var status: String = null
  var hostName: String = null
  var filecreatedDate: String = null
  var toFile: String = null
  var folderPath: String = null
  def execute(context: in.handyman.command.Context, action: in.handyman.dsl.Action, actionId: Integer): in.handyman.command.Context =
    {
      val modelGenerationDataAsIs: in.handyman.dsl.ModelGeneration = action.asInstanceOf[in.handyman.dsl.ModelGeneration]
      val modelGenerationData: in.handyman.dsl.ModelGeneration = CommandProxy.createProxy(modelGenerationDataAsIs, classOf[in.handyman.dsl.ModelGeneration], context)
      val db = modelGenerationData.getSource
      val instanceDb = modelGenerationData.getAuditVal
      val dropboxDbConnfrom = ResourceAccess.rdbmsConn(db)
      val dropboxStmtfrom = dropboxDbConnfrom.createStatement
      val processId = modelGenerationData.getProcess_id
      val table = modelGenerationData.getValue

      val incomingJson = context.getValue("myjson")
      val sqlWithoutQuotes = table.replaceAll("\"", "")
      val rs: ResultSet = dropboxStmtfrom.executeQuery(sqlWithoutQuotes)
      dropboxDbConnfrom.setAutoCommit(false)
      val instanceDbConnfrom = ResourceAccess.rdbmsConn(instanceDb)
      val instanceStmtfrom = instanceDbConnfrom.createStatement
      instanceDbConnfrom.setAutoCommit(false)
      var jsonName = "ZIO_MODEL_" + ".json"
      var jsonFileName: String = null

      while (rs.next()) {
        PARENT_DIRECTORY = rs.getString("model_path")
        println(PARENT_DIRECTORY)
      }
      val processDetails = "select hostname,instance_name, start,end,status from instance_audit where instance_id=" + processId;
      val instanceRS: ResultSet = instanceStmtfrom.executeQuery(processDetails);
      val instanceCol = instanceRS.getMetaData.getColumnCount
      while (instanceRS.next()) {
        processName = instanceRS.getString("instance_name")
        start = instanceRS.getString("start")
        println("start  " + start)
        end = instanceRS.getString("end")
        println("end  " + end)
        status = instanceRS.getString("status")
        hostName = instanceRS.getString("hostname")
        startDate = formattedDate.parse(start)
        endDate = formattedDate.parse(end)
        totalDuration = timeDifference(formattedDate.format(startDate), formattedDate.format(endDate))
        println(totalDuration)
      }

      def loadFileAsResource(filePath: String): Path = {
        val path: Path = Paths.get(filePath)
        println("Actual Path : " + path)
        val normalizedPath: Path = path.normalize()
        normalizedPath
      }

      createTar()
      def createTar(): Unit = {
        val filePath: Path = loadFileAsResource(PARENT_DIRECTORY)
        println("path  " + filePath.toUri())
        val file: File = new File(filePath.toUri())
        println(file)
        val fileCreatedDate: String = getFileCreatedDateAndTime(file)
        val macroRuntimeDetailsObj: JSONObject = new JSONObject()
        macroRuntimeDetailsObj.put("processId", processId)
        macroRuntimeDetailsObj.put("processName", processName)
        macroRuntimeDetailsObj.put("status", "")
        macroRuntimeDetailsObj.put("errorDescription", "")
        macroRuntimeDetailsObj.put("startOn", startDate)
        macroRuntimeDetailsObj.put("endedOn", endDate)
        macroRuntimeDetailsObj.put("totalDuration", totalDuration)
        macroRuntimeDetailsObj.put("fileCreatedDate", fileCreatedDate)
        folderDetails.put("executionContext", macroRuntimeDetailsObj)
        val userDetails: JSONObject = new JSONObject(incomingJson)
        userDetails.put("organizationName", "")
        var sigmaId: String = String.valueOf(System.nanoTime());
        userDetails.put("sigmaId", sigmaId)
        folderDetails.put("userDetails", userDetails)
        multipartFileUpload(file)
        println("folder path  " + folderPath)
        folderDetails.put("folderPath", folderPath)
        context.addValue("fileDownloadPath", toFile)
        calculateFileSize(new File(toFile))
        for (f <- new File(toFile).listFiles()) {
          println("Files " + new File(toFile).listFiles())
          if (f.isDirectory()) {
            calculateFileSize(f)
            for (listFile <- f.listFiles()) {
              println("Files " + f.listFiles())
              calculateFileSize(listFile)
            }
          } else {
            calculateFileSize(f)
          }
        }
        folderDetails.put("folder", fileDetailsArray)
        println(folderDetails)
        jsonFileName = toFile + jsonName
        writeIntoJSONFile(folderDetails)
        createTarArchive(toFile)
        println("tar file has been createdF")
        removeDirectoryIfExists(new File(toFile))
      }

      def addToArchive(
        filePath: String,
        parent: String,
        tarArchive: TarArchiveOutputStream): Unit = {
        val file: File = new File(filePath)
        val entryName: String = parent + file.getName
        //        calculateFileSize(file)
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
            addToArchive(f.getAbsolutePath, entryName + File.separator, tarArchive)
          }
        }
      }

      def calculateFileSize(file: File): Unit = {
        var fileTpe: String = null
        var checkSumVal: String = null
        var sizeBig: BigInteger = null
        val fileNameObj: JSONObject = new JSONObject()
        var `val`: Long = 0
        try {
          if (file.isDirectory) {
            sizeBig = FileUtils.sizeOfDirectoryAsBigInteger(file)
            fileNameObj.put("size", FileUtils.byteCountToDisplaySize(sizeBig))
          } else {
            `val` = FileUtils.sizeOf(file)
            fileNameObj.put("size", FileUtils.byteCountToDisplaySize(`val`))
          }
          if (file.isDirectory) {
            fileTpe = "Directory"
          } else if (file.isFile) {
            fileTpe = "File"
          }
          checkSumVal = calculateChecksum(file)
          fileNameObj.put("name", file.getName)
          fileNameObj.put("type", fileTpe)
          fileNameObj.put("location", file.getAbsolutePath)
          fileNameObj.put("checkSum", checkSumVal)
          fileDetailsArray.put(fileNameObj)
        } catch {
          case e: IOException => e.printStackTrace()

        }
      }

      def createTarArchive(parentDir: String): Unit = {
        var tarArchive: TarArchiveOutputStream = null
        try {
          println("parent directory " + parentDir)
          val root: File = new File(parentDir)
          val fos: FileOutputStream = new FileOutputStream(
            root.getAbsolutePath.concat(".tar.gz"))
          val gzipOS: GZIPOutputStream = new GZIPOutputStream(
            new BufferedOutputStream(fos))
          tarArchive = new TarArchiveOutputStream(gzipOS)
          //          tarArchive.setLongFileMode(TarArchiveOutputStream.LONGFILE_POSIX);

          addToArchive(parentDir, "", tarArchive)
        } catch {
          case e: IOException => e.printStackTrace()

        } finally try tarArchive.close()
        catch {
          case e: IOException => e.printStackTrace()

        }
      }

      def calculateChecksum(roots: File*): String = {
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
      def getFileCreatedDateAndTime(file: File): String = {
        var attrs: BasicFileAttributes = null
        attrs = Files.readAttributes(file.toPath(), classOf[BasicFileAttributes])
        val time: FileTime = attrs.creationTime()
        val pattern: String = "yyyy-MM-dd HH:mm:ss"
        val simpleDateFormat: SimpleDateFormat = new SimpleDateFormat(pattern)
        filecreatedDate = simpleDateFormat.format(new Date(time.toMillis()))
        filecreatedDate
      }

      def timeDifference(startTime: String, endTime: String): String = {
        val dateTimeFormatter: DateTimeFormatter =
          DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        var initialTime: LocalTime = LocalTime.parse(startTime, dateTimeFormatter)
        val finalTime: LocalTime = LocalTime.parse(endTime, dateTimeFormatter)
        val joiner: StringJoiner = new StringJoiner(":")
        val hours: Long = initialTime.until(finalTime, ChronoUnit.HOURS)
        initialTime = initialTime.plusHours(hours)
        val minutes: Long = initialTime.until(finalTime, ChronoUnit.MINUTES)
        initialTime = initialTime.plusMinutes(minutes)
        val seconds: Long = initialTime.until(finalTime, ChronoUnit.SECONDS)
        joiner.add(String.valueOf(hours + "HOURS"))
        joiner.add(String.valueOf(minutes + "MINUTES"))
        joiner.add(String.valueOf(seconds + "SECONDS"))
        joiner.toString
      }

      def writeIntoJSONFile(jsonArray: JSONObject): Unit = {
        println(jsonFileName)
        val jsonFile: FileWriter = new FileWriter(jsonFileName)
        jsonFile.write(jsonArray.toString(4))
        jsonFile.close()
      }
      def multipartFileUpload(filePath: File): Unit = {
        println(System.getProperty("user.dir"))
        println(filePath.exists())
        val fileName: String = filePath.getName
        folderPath = System.getProperty("user.dir") + "/" + fileName
        println(folderPath)
        toFile = System.getProperty("user.dir") + "/" + fileName + "_" +
          s +
          "/"
        val file: File = new File(toFile)
        var success: Boolean = false
        try {
          if (file.exists() && filePath.exists()) {
            //            success = filePath.renameTo(new File(file, filePath.getName))
            FileUtils.copyDirectory(filePath, file);
            println(success)
          } else if (filePath.exists()) {
            file.mkdir()
            FileUtils.copyDirectory(filePath, file);
            println("file copied" + success)
          }
        } catch {
          case e: Exception => e.printStackTrace()

        }

      }

      def removeDirectoryIfExists(file: File): Unit = {
        if (file.isDirectory && file.exists()) {
          FileUtils.cleanDirectory(file)
          FileUtils.forceDelete(file)
        }
      }

      context
    }

  def executeIf(context: in.handyman.command.Context, action: in.handyman.dsl.Action): Boolean =
    {
      val modelGenerationDataAsIs: in.handyman.dsl.ModelGeneration = action.asInstanceOf[in.handyman.dsl.ModelGeneration]
      val modelGenerationData: in.handyman.dsl.ModelGeneration = CommandProxy.createProxy(modelGenerationDataAsIs, classOf[in.handyman.dsl.ModelGeneration], context)

      val expression = modelGenerationData.getCondition
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
