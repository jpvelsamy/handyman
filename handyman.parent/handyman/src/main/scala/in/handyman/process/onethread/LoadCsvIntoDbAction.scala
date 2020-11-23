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
import com.dropbox.core.v2.files.Metadata
import com.dropbox.core.v2.files.FileMetadata
import com.dropbox.core.v2.files.FolderMetadata
import org.slf4j.MarkerFactory

import java.io.FileWriter
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

import java.io.Writer
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.io.BufferedOutputStream
import java.io._
import com.opencsv.CSVReader
import java.util._


import java.util.logging.Level
import java.util.logging.Logger
import scala.util.control._
import scala.util.control.Breaks._
import scala.reflect.io.Directory


class LoadCsvIntoDbAction extends in.handyman.command.Action with LazyLogging {
  val detailMap = new java.util.HashMap[String, String]
  val auditMarker = "DROPBOX";
  val aMarker = MarkerFactory.getMarker(auditMarker);

  def execute(context: Context, action: in.handyman.dsl.Action, actionId: Integer): Context = {
    val loadcsvAsIs = action.asInstanceOf[in.handyman.dsl.LoadCsv]
    val loadcsv: in.handyman.dsl.LoadCsv = CommandProxy.createProxy(loadcsvAsIs, classOf[in.handyman.dsl.LoadCsv], context)

    val date = new Date
    val csvFile = loadcsv.getSource
    val db = loadcsv.getTo
    val name = loadcsv.getName
    val id = context.getValue("incoming")
    val sqlList = loadcsv.getValue.replaceAll("\"", "")
    val ddlSql = /*loadcsv.getValue*/"select extractLocation from 204_dropbox;"
    val delim = loadcsv.getDelim
    var limit = loadcsv.getLimit.toInt
    val dbSchema = loadcsv.getDbschema

    var currentFileName =""
    var count: Int = 0
    var st: Statement = null
    val con : Connection = null
    var totalcount: Int = 0
    var values: String = null
    var cquery: String = ""
    var iquery: String = ""
    var dquery: String = ""
    var ct: String = ""
    var excep = false
    

    logger.info("LoadCsv id#{}, name#{}, from#{}, sqlList#{}", id, name, db)

    
    val outer = new Breaks;
    val inner = new Breaks;
    val innermost = new Breaks;
    
    val loadCsvSFDBconn = ResourceAccess.rdbmsConn("sf")
    val loadCsvSFDBstmt = loadCsvSFDBconn.createStatement
    loadCsvSFDBconn.setAutoCommit(false)
      
    
    try 
    {
                val con = ResourceAccess.rdbmsConn(db)
                st = con.createStatement()
                con.setAutoCommit(false)
                var datatTrackTableName=""
                if(csvFile.contains("\\"))
                {
                   var findTableName = csvFile.split("\\\\")
                   var decideTableName = findTableName(findTableName.length-1).split("-")
                   datatTrackTableName = decideTableName(0) + "_payload" 
                }
                else if(csvFile.contains("/"))
                {
                    var findTableName = csvFile.split("/")
                    var decideTableName = findTableName(findTableName.length-1).split("-")
                    datatTrackTableName = decideTableName(0) + "_payload" 
                }
                logger.info("Payload Queue for the data loading process for the folder "+csvFile +" is:" + datatTrackTableName)
                
                
                var updatequery = "update " + id + "_dropbox" + " set DataLoadingStarttime=now(), DataLoadingStatus ='" + "Started"  + "' where ExtractLocation ='" +csvFile.replace("\\", "\\\\") +"';"
                logger.info("Update Query for Data Loading Status: " + updatequery)  
                loadCsvSFDBstmt.execute(updatequery)
                logger.info("Started the data loading process for the folder "+csvFile )
                //loadCsvSFDBconn.commit()
                
                var createquery = "CREATE TABLE IF NOT EXISTS "+ datatTrackTableName +" (Process_id INT, FolderName varchar(255), FileName varchar(255), RecordsInFile varchar(255), RowsInserted varchar(255),Status varchar(255));"
                logger.info("Create Query for Data Loading Tracking: " + createquery)  
                loadCsvSFDBstmt.execute(createquery)
                logger.info("Created the data track payload queue to track the data load process into the DB ")
                
                loadCsvSFDBconn.commit()
                
                
                var direc = new File(csvFile)
                var countOfFilesInDirectory = direc.listFiles().length
                println("Number of CSV files in the folder---------->"+ countOfFilesInDirectory)
                
               
                
                var isDirectory = direc.isDirectory()
                var listOfFiles = direc.listFiles()
                var countOfFiles = 0;
                logger.info("File path: "+ csvFile)
                logger.info("File name: "+direc.getName)
                logger.info("count of CSV's in the foler: "+listOfFiles.length)
                inner.breakable
                {
                    for(csv<-listOfFiles)
                    {
                      //println(csv)
                        var filepath = csv.getAbsolutePath
                        var checkFileSize = new File(filepath)
                        breakable 
                        {
                          if(checkFileSize.length()==0)
                          {
                              countOfFiles +=1;
                              logger.info("Size of the file "+checkFileSize.getName + " is" + checkFileSize.length())
                              logger.info("No content in the file "+ checkFileSize.getName + "--Moving onto the next file.....")
                              
                              var insertQuery = "insert into "+datatTrackTableName + " (Process_id, FolderName, FileName, RecordsInFile,Status,RowsInserted) values ('"+id+"','"+csvFile.replace("\\", "\\\\")+"','"+checkFileSize.getName+"','0','No Data','0');"        
                              logger.info("Insert Query for the file"+checkFileSize.getName+" in the data track payload queue: " + insertQuery)  
                              loadCsvSFDBstmt.execute(insertQuery)
                              logger.info("Inserted record for the file "+checkFileSize.getName+" in the data track payload queue ")
                              loadCsvSFDBconn.commit()
                              logger.info("Number of files from "+ filepath +" loaded into the Database -------------->"+countOfFiles)
                              break()
                          }
                          else
                          {
                                //var colTypes = getColumnDatatypes(filepath)
                                filepath = filepath.replace("\\", "/")
                                var counter: Int = filepath.length - filepath.replaceAll("\\/", "").length
                                var file: Array[String] = filepath.split("/", counter + 1)
                                var fileName: String = file(counter)
                                currentFileName=fileName
                                val reader: CSVReader = new CSVReader(new FileReader(filepath))
                                val readeEntireFile: CSVReader = new CSVReader(new FileReader(filepath))
                                var countOfRecordsInCSV = (readeEntireFile.readAll().size()-1).toString()
                                
                                var insertQuery = "insert into "+datatTrackTableName + " (Process_id, FolderName, FileName, RecordsInFile,Status) values ('"+id+"','"+csvFile.replace("\\", "\\\\")+"','"+fileName+"','"+countOfRecordsInCSV+"','Started');"        
                                logger.info("Insert Query for the file"+fileName+" in the data track payload queue: " + insertQuery)  
                                loadCsvSFDBstmt.execute(insertQuery)
                                logger.info("Inserted record for the file "+fileName+" in the data track payload queue ")
                                loadCsvSFDBconn.commit()
                                
                                var nextLine: Array[String] = null
                                val firstLine: Array[String] = reader.readNext()
                                
                                var atos: String = firstLine.mkString("\t")
                                //println("atos :"+atos)
                                atos = atos.replace("\t", ",")
                                var stoa: Array[String] = atos.split(",")
                                var dbCommit : Boolean = false
                                if (fileName.contains(".csv")) 
                                {
                                      var checkTableQuery = "SELECT EXISTS (SELECT table_name  FROM information_schema.tables WHERE  table_schema = '"+dbSchema+"' AND table_name   = 'mcasuite_"+fileName.replace(".csv", "")+"') AS check;"
                                      var checkIfTableExists = st.executeQuery(checkTableQuery)
                                      var DDLdecison = ""
                                      if(checkIfTableExists.next())
                                      {
                                        DDLdecison = checkIfTableExists.getString("check")
                                      }
                                      
                                      
                                      val column: String = convertArrayToInsertLine(firstLine, "\",\"")
                                      ct = convertArrayToInsertLine(firstLine, "\" VARCHAR(65535),\"")
                                      
                                      if(DDLdecison.equals("false"))
                                      {
                                          logger.info("Creating the table mcasuite_"+fileName.replace(".csv", "")+" in the MCA Suite Database")
                                          cquery = "CREATE TABLE "+ dbSchema + ".mcasuite_" + fileName.replace(".csv", "") + " (\"" + ct + ");"
                                          logger.info("Create query - "+ cquery)
                                          st.execute(cquery)
                                      }
                                      else
                                      {
                                          logger.info("Table mcasuite_"+fileName.replace(".csv", "")+" already present in the Database")
                                          var truncateDecison =0
                                          var dataLoadedQuery = "select COUNT(*) as 'dataloaded' from "+datatTrackTableName +" where FileName ='"+fileName+"' and CONVERT(RowsInserted,INTEGER)>0;"
                                          var dataLoaded = loadCsvSFDBstmt.executeQuery(dataLoadedQuery)
                                          
                                          if(dataLoaded.next())
                                          {
                                            truncateDecison = dataLoaded.getInt("dataloaded")
                                          }
                                          if(truncateDecison ==0)
                                          {
                                            logger.info("Truncating the Table mcasuite_"+fileName.replace(".csv", ""))
                                            var tquery = "truncate "+ dbSchema + ".mcasuite_" + fileName.replace(".csv", "")
                                            logger.info("Truncate query - "+ tquery)
                                            st.execute(tquery)
                                            logger.info("Truncating the Table mcasuite_"+fileName.replace(".csv", "")+" Completed.")
                                          }
                                          
                                      }
                                      
                                      var selQuery = "select RowsInserted as 'rows' from "+datatTrackTableName +" where Process_id = (Select Max(Process_id) from "+ datatTrackTableName +" where RowsInserted IS NOT NULL and Status !='Already Completed' and FileName= '"+fileName +"') and FileName ='"+fileName+"';"
                                      logger.info("Query to fetch rows already inserted from Payload -"+selQuery)
                                      var resSet = loadCsvSFDBstmt.executeQuery(selQuery)
                                      var fetchCountOfRowsFromPayload = 0
                                      if(resSet.next())
                                      {
                                        fetchCountOfRowsFromPayload = resSet.getString("rows").toInt
                                      }
                                      logger.info("Number of rows already loaded into the DB  for the file "+fileName+"-------------->"+fetchCountOfRowsFromPayload)
                                      var countOfRowsAdded = 0
                                      innermost.breakable
                                      {
                                        if(fetchCountOfRowsFromPayload == countOfRecordsInCSV.toInt)
                                        {
                                            logger.info("Data Completely loaded from the file "+fileName+" already....Moving to the next file")
                                            var updateQuery = "update " +datatTrackTableName + " set status="+"'Already Completed'"+", RowsInserted ='" + (countOfRowsAdded).toString()  + "' where Process_id="+id+" and FileName ='" +fileName +"';"         
                                            logger.info("Update Query for updating the rows Inserted("+(countOfRowsAdded)+") in the data track payload queue: " + updateQuery)  
                                            loadCsvSFDBstmt.execute(updateQuery)
                                            logger.info("Updated the number of rows loaded into the Database in the data track payload queue for the file "+fileName)
                                            loadCsvSFDBconn.commit()
                                            innermost.break
                                        }
                                        
                                        while ({ nextLine = reader.readNext(); nextLine != null }) 
                                        {
                                            countOfRowsAdded +=1
                                            
                                            breakable 
                                            {
                                                  if(countOfRowsAdded>fetchCountOfRowsFromPayload.toInt)
                                                  {
                                                        dbCommit = false
                                                        for( i <- 0 to nextLine.length-1) 
                                                        { 
                                                            nextLine(i) = nextLine(i).replace("'", "\\'")
                                                        }  
                                                        values = convertDataArrayToInsertLine(nextLine, "','")
                                                        count += 1
                                                        totalcount += 1
                                                        values = values.replace("''", "'NULL'").replace("00:00:00.0", "")
                                                        values = values.replace('', '1')
                                                        values = values.replace("?", "'+CHR(63)+'")
                                                        
                                                        var colquotes = firstLine.length
                                                        var rowquotes = nextLine.length
                                                        
                                                        
                                                        /*println(column)
                                                        println(values)
                                                        println(colquotes + " - "+ rowquotes)*/
                                                        if(colquotes !=rowquotes)
                                                        {
                                                          values = equateValuesToColumns(column,values)
                                                        }
                                                        iquery = iquery + "('" + values + "),"
                                              
                                                        if (countOfRowsAdded % limit == 0) 
                                                        {
                                                            iquery = "INSERT INTO  "+ dbSchema + ".mcasuite_" + fileName.replace(".csv", "") + "  (" + "\"" + column + ")" + "VALUES " + iquery
                                                            iquery = iquery.substring(0, iquery.length() - 1) + ";"
                                                            logger.info("LoadCsv id#{}, name#{}, from#{}, sqlList#{}", id, name, db, iquery)
                                                            st.execute(iquery)
                                                            iquery = ""
                                                            con.commit()
                                                            
                                                            var status = "In Progress"
                                                            if(countOfRowsAdded == countOfRecordsInCSV.toInt)
                                                            {
                                                                status = "Completed"
                                                            }
                                                            var updateQuery = "update " +datatTrackTableName + " set status='"+status+"', RowsInserted ='" + (countOfRowsAdded).toString()  + "' where Process_id="+id+" and FileName ='" +fileName +"';"        
                                                            logger.info("Update Query for updating the rows Inserted("+(countOfRowsAdded)+") in the data track payload queue: " + updateQuery)  
                                                            loadCsvSFDBstmt.execute(updateQuery)
                                                            logger.info("Updated the number of rows loaded into the Database in the data track payload queue for the file "+fileName)
                                                            loadCsvSFDBconn.commit()
                                                            dbCommit = true
                                                        }
                                                        
                                                        
                                                        if(countOfRowsAdded == countOfRecordsInCSV.toInt) /*countOfRecordsInCSV.toInt)*/ 
                                                        {
                                                          logger.info(countOfRowsAdded+" rows loaded from the "+ fileName)
                                                          logger.info("Breaking the innermost loop after uploading "+countOfRowsAdded+" records for the file "+fileName)
                                                          innermost.break
                                                        }
                                                  }
                                                  else
                                                  {
                                                    break
                                                  }
                                              }
                                            
                                        }
                                      }
                                      if(!dbCommit && countOfRowsAdded>fetchCountOfRowsFromPayload.toInt)
                                      {
                                          iquery = "INSERT INTO  "+ dbSchema + ".mcasuite_" + fileName.replace(".csv", "") + "  (" + "\"" + column + ")" + "VALUES " + iquery
                                          iquery = iquery.substring(0, iquery.length() - 1) + ";"
                                          logger.info("LoadCsv id#{}, name#{}, from#{}, sqlList#{}", id, name, db, iquery)
                                          st.execute(iquery)
                                          iquery = ""
                                          con.commit()
                                          
                                          var status = "In Progress"
                                          if(countOfRowsAdded == countOfRecordsInCSV.toInt)
                                          {
                                              status = "Completed"
                                          }
                                          var updateQuery = "update " +datatTrackTableName + " set status='"+status+"', RowsInserted ='" + (countOfRowsAdded).toString()  + "' where Process_id="+id+" and FileName ='" +fileName +"';"         
                                          logger.info("Update Query for updating the rows Inserted("+(countOfRowsAdded)+") in the data track payload queue: " + updateQuery)  
                                          loadCsvSFDBstmt.execute(updateQuery)
                                          logger.info("Updated the number of rows loaded into the Database in the data track payload queue for the file "+fileName)
                                          loadCsvSFDBconn.commit()
                                          //countOfRowsAdded = 0
                                      }
                                      
                                } 
                                else if (fileName.contains(".tsv")) 
                                {
                                      val column: String = convertArrayToInsertLine(stoa, "`,`")
                                      ct = convertArrayToInsertLine(stoa, "`VARCHAR(344),`")
                                      dquery = "drop table if exists " + fileName.replace(".tsv", "") + ";"
                                      logger.info("LoadCsv id#{}, name#{}, from#{}, sqlList#{}", id, name, db, dquery)
                                      st.execute(dquery)
                                      cquery = "CREATE TABLE `" + fileName.replace(".tsv", "") + "` (" + "`" + ct + ");"
                                      logger.info("LoadCsv id#{}, name#{}, from#{}, sqlList#{}", id, name, db, cquery)
                                      st.execute(cquery)
                                      var countOfRowsAdded =0
                                      innermost.breakable
                                      {
                                          while ({ nextLine = reader.readNext(); nextLine != null })
                                          {
                                              dbCommit = false
                                              var row: String = nextLine.mkString("")
                                              row = row.replace("\t", "~ ").replace("\"\"","\\\"")
                                              val rowa: Array[String] = row.split("~")
                                              values = convertDataArrayToInsertLine(rowa, "','")
                                              count += 1
                                              totalcount += 1
                                              values = values.replace("''", "'NULL'").replace("00:00:00.0", "")
                                              iquery = iquery + "('" + values + "),"
                                              if (count % limit == 0) {
                                                iquery = "INSERT IGNORE INTO  `" + fileName.replace(".tsv", "") + "`  (" + "`" + column + ")" + "VALUES " + iquery
                                                iquery = iquery.substring(0, iquery.length() - 1) + ";"
                                                logger.info("LoadCsv id#{}, name#{}, from#{}, sqlList#{}", id, name, db, iquery)
                                                st.execute(iquery)
                                                iquery = ""
                                                con.commit()
                                                dbCommit = true
                                              }
                                              countOfRowsAdded +=1
                                              if(countOfRowsAdded == limit) 
                                              {
                                                logger.info(countOfRowsAdded+" rows loaded from the "+ fileName)
                                                logger.info("Breaking the innermost loop after uploading 5 records for the file "+fileName)
                                                innermost.break
                                              }
                                          }
                                      } 
                                      if(!dbCommit)
                                      {
                                          iquery = "INSERT IGNORE INTO  `" + fileName.replace(".csv", "") + "`  (" + "`" + column + ")" + "VALUES " + iquery
                                          iquery = iquery.substring(0, iquery.length() - 1) + ";"
                                          logger.info("LoadCsv id#{}, name#{}, from#{}, sqlList#{}", id, name, db, iquery)
                                          st.execute(iquery)
                                          con.commit()
                                      }
                                     /* iquery = "INSERT IGNORE INTO  `" + fileName.replace(".tsv", "") + "`  (" + "`" + column + ")" + "VALUES " + iquery
                                      iquery = iquery.substring(0, iquery.length() - 1) + ";"
                                      logger.info("LoadCsv id#{}, name#{}, from#{}, sqlList#{}", id, name, db, iquery)
                                      st.execute(iquery)
                                      con.commit()*/
                                }
                                else
                                {
                                      logger.info("File format is invalid")
                                }  
                                countOfFiles +=1
                                logger.info("Number of files from "+ csvFile +" loaded into the Database -------------->"+countOfFiles)
                                /*if(countOfFiles ==10)
                                {
                                    logger.info("Breaking the inner loop after uploading"+ countOfFiles +" file(s) from the zip "+filepath)
                                    inner.break
                                }*/
                                
                          }
                        }
                      if(countOfFiles == countOfFilesInDirectory) //countOfFilesInDirectory 
                      {
                        var auditquery = "update dropbox" + " set TotalRowsLoaded ="+totalcount+" ,ProcessStatus = 'Complete', DataLoadingStatus ='Completed' where ExtractLocation ='" +csvFile.replace("\\", "\\\\") +"' and Process_id="+id +";"
                        var query = "update " + id + "_dropbox" + " set TotalRowsLoaded ="+totalcount+" , ProcessStatus = 'Complete', DataLoadingStatus ='Completed' where ExtractLocation ='" +csvFile.replace("\\", "\\\\") +"';"
                        logger.info("Update Query for Data Loading Status: " + query)  
                        logger.info("Update Query for Data Loading Status in the Audit trail table: " + auditquery)
                        loadCsvSFDBstmt.execute(query)
                        loadCsvSFDBstmt.execute(auditquery)
                        loadCsvSFDBconn.commit()
                        logger.info("Completed the data loading for the file "+csvFile )
                      }  
                        
                        
                    }
                }
                
                /*countOfZipsLoaded +=1
                logger.info("Number of zips -------------->"+countOfZipsLoaded)
                if(countOfZipsLoaded ==1)
                {
                    logger.info("Breaking the outer loop after uploading 1 zip file")  
                    outer.break
                }*/
                
                /*var counter: Int = csvFile.length - csvFile.replaceAll("\\/", "").length
                var file: Array[String] = csvFile.split("/", counter + 1)
                var fileName: String = file(counter)*/
                
                /*val reader: CSVReader = new CSVReader(new FileReader(csvFile))
                var nextLine: Array[String] = null
                val firstLine: Array[String] = reader.readNext()
                var atos: String = firstLine.mkString("\t")
                atos = atos.replace("\t", ",")
                var stoa: Array[String] = atos.split(",")*/
                
           // }
      //}
      /*if(countOfFiles == countOfFilesInDirectory) //countOfFilesInDirectory 
      {
        var auditquery = "update dropbox" + " set ProcessStatus = 'Complete', DataLoadingStatus ='Completed' where ExtractLocation ='" +csvFile.replace("\\", "\\\\") +"' and Process_id="+id +";"
        var query = "update " + id + "_dropbox" + " set ProcessStatus = 'Complete', DataLoadingStatus ='Completed' where ExtractLocation ='" +csvFile.replace("\\", "\\\\") +"';"
        logger.info("Update Query for Data Loading Status: " + query)  
        logger.info("Update Query for Data Loading Status in the Audit trail table: " + auditquery)
        loadCsvSFDBstmt.execute(query)
        loadCsvSFDBstmt.execute(auditquery)
        loadCsvSFDBconn.commit()
        logger.info("Completed the data loading for the file "+csvFile )
      }*/
      
      
    } 
    catch 
    {
      case ex: SQLException => {
        excep = true
        val lgr: Logger = Logger.getLogger(classOf[App].getName)
        lgr.log(Level.SEVERE, ex.getMessage, ex)
      }
      case fnf: java.io.FileNotFoundException =>{
        excep = true
        fnf.printStackTrace()
      }
      case e: Exception =>{
        excep = true
        e.printStackTrace()
      }
    } 
    finally  
    {
      if (st != null) {
          st.close()
        }
      if (con != null) {
          con.close()
        }
      if(excep == true)
      {
          var auditquery = "update dropbox" + " set TotalRowsLoaded ="+totalcount+" ,ProcessStatus = 'Data Loading Failed', DataLoadingStatus ='Failed' where ExtractLocation ='" +csvFile.replace("\\", "\\\\") +"' and Process_id="+id +";"
          var query = "update " + id + "_dropbox" + " set TotalRowsLoaded ="+totalcount+" ,ProcessStatus = 'Data Loading Failed', DataLoadingStatus ='Failed' where ExtractLocation ='" +csvFile.replace("\\", "\\\\") +"';"
          logger.info("Update Query in case of exception: "+query)
          logger.info("Update Query in case of exception in the Audit Trails table: "+auditquery)
          loadCsvSFDBstmt.execute(query)
          loadCsvSFDBstmt.execute(auditquery)
          loadCsvSFDBconn.commit()
          logger.info("Updated the status of failed process in the DB. ")
      }
      detailMap.put("name", name)
      detailMap.put("Name of the zip file", csvFile)
      detailMap.put("DB", db)
      detailMap.put("DB Schema", dbSchema)
      detailMap.put("Total Number of rows inserted", totalcount.toString())
    } 
      
    println(totalcount)
    
    context
  }
  
  def convertArrayToInsertLine(firstLine: Array[String],delimiter: String): String = {
    
    val sb: StringBuilder = new StringBuilder()
    if(firstLine !=null)
    for (str <- firstLine)
      sb.append(str.replace(" ", "")).append(delimiter)
    sb.substring(0, sb.length - 2)
  }
  
  def convertDataArrayToInsertLine(firstLine: Array[String],delimiter: String): String = {
    
    val sb: StringBuilder = new StringBuilder()
    if(firstLine !=null)
    for (str <- firstLine)
      sb.append(str.replace("", "")).append(delimiter)
    sb.substring(0, sb.length - 2)
  }
  
  def countOccurrences(src: String, tgt: String): Int ={
  src.sliding(tgt.length).count(window => window == tgt)
  }
  def equateValuesToColumns(column: String,values: String): String = {
    logger.info("Equating Values and Columns...")
    var updatedValuesString = ""
    if(values.contains(",,"))
    {
        var pos = values.indexOf(",,")
        var changedString = values.substring(pos)
        var splitString = values.split(",,")
        updatedValuesString = splitString(0) + "''" + changedString
    }
    else
    {
       updatedValuesString = values + "," + "''"
    }
    logger.info("Updated Value String- "+ updatedValuesString)
    updatedValuesString
  }
  
  /*def getColumnDatatypes(filename: String): List[String] ={
    var cellType:List[String] = null
    var xlsFile = new File(path);
    var reader = new CSVReader(new FileReader(xlsFile), '\t');
    try{
      var fis = new FileInputStream(filename)
      var workbook = new HSSFWorkbook(fis);
      var sheet = workbook.getSheetAt(0);
      
      var rows = sheet.rowIterator();
      while (rows.hasNext()) {
          var row =  rows.next();
          var cells = row.cellIterator();
          while (cells.hasNext()) {
              var cell = cells.next();

              cellType.add(cell.getCellType().toString()) 
          }
      }
      
    }
    catch 
      {
        case ex: Exception => {
          val lgr: Logger = Logger.getLogger(classOf[App].getName)
          lgr.log(Level.WARNING, ex.getMessage, ex)
        }
      }
   cellType
  }*/
  def executeIf(context: Context, action: in.handyman.dsl.Action): Boolean = {
    val loadcsvAsIs = action.asInstanceOf[in.handyman.dsl.LoadCsv]
    val loadcsv: in.handyman.dsl.LoadCsv = CommandProxy.createProxy(loadcsvAsIs, classOf[in.handyman.dsl.LoadCsv], context)

    val expression = loadcsv.getCondition
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
  //target : String , source : String , auth : String , id :String, name: String, dropboxStmtfrom : Statement

}