package in.handyman.config

import com.typesafe.config._
import com.typesafe.scalalogging.LazyLogging
import in.handyman.HandymanException

import java.sql._

/**
 * @author jpvel
 *         TODO I would like to use immutable map instead of mutable map
 *
 */
case class Resource(configType: String, driverClassName: String, userName: String, password: String, url: String, name: String)

object ConfigurationService extends LazyLogging {
  //config_type, auth_info, resource_url
  private val conf = ConfigFactory.parseResources("configstore.props")
  logger.info(s"Initializing the config store from config file $conf.origin.url")
  private val url = conf.getString("config.url")
  private val driver = conf.getString("config.driver")
  private val user = conf.getString("config.user")
  private val password = conf.getString("config.passwd")
  private val keyQueryBatch = conf.getString("config.key_global_sql")
  private val keyQueryProces = conf.getString("config.key_process_sql")
  private val keyQueryInstance = conf.getString("config.key_instance_sql")
  private val allQueryBatch = conf.getString("config.batch_sql")
  private val allQueryProcess = conf.getString("config.process_sql")
  private val allQueryInstance = conf.getString("config.instance_sql")
  private val resourceConfig = conf.getString("config.resource_sql")

  Class.forName(driver)

  def getConfig(processName: String, key: String): String = {
    val conn = DriverManager.getConnection(url, user, password)
    var value: String = null
    try {
      value = getProcessConfigValue(conn, processName, key)
      if (value == null || value.isEmpty())
        value = getBatchConfigValue(conn, key)

    } catch {
      case t: Throwable => logger.error("Error reading information from config store{}", url, t)
    } finally {
      conn.close()
    }
    value
  }

  private def getBatchConfigValue(conn: Connection, key: String): String = {
    val stmt = conn.prepareStatement(keyQueryBatch)
    var valueRet: String = null
    try {
      stmt.setString(1, key)
      val rs = stmt.executeQuery()
      try {
        if (rs.next()) {
          val value = rs.getString(1)
          valueRet = value
        }
      } finally {
        rs.close()
      }
    } finally {
      stmt.closeOnCompletion()
    }
    valueRet
  }

  private def getProcessConfigValue(conn: Connection, processName: String, key: String): String = {
    val stmt = conn.prepareStatement(keyQueryProces)
    var valueRet: String = null
    try {
      stmt.setString(1, processName)
      stmt.setString(2, key)
      val rs = stmt.executeQuery()
      try {
        if (rs.next()) {
          val value = rs.getString(1)
          valueRet = value
        }
      } finally {
        rs.close()
      }
    } finally {
      stmt.closeOnCompletion()
    }
    valueRet
  }

  def getGlobalconfig(): scala.collection.immutable.Map[String, String] = {
    val conn = DriverManager.getConnection(url, user, password)
    val valueList = getAllBatchConfigValue(conn)
    valueList
  }

  def getAllConfig(instanceName: String): scala.collection.immutable.Map[String, String] = {
    val conn = DriverManager.getConnection(url, user, password)
    val processName = findProcessName(instanceName)
    val valueList = getAllBatchConfigValue(conn) ++ getAllProcessConfigValue(conn, processName) ++ getAllInstanceConfigValue(conn, instanceName)
    valueList
  }

  private def getAllBatchConfigValue(conn: Connection): scala.collection.immutable.HashMap[String, String] = {
    val stmt = conn.createStatement()
    var configInfo = scala.collection.immutable.HashMap[String, String]()
    try {
      val rs = stmt.executeQuery(allQueryBatch)
      try {
        while (rs.next()) {
          configInfo += ((rs.getString(1), rs.getString(2)))
        }
      } finally {
        rs.close()
      }
    } finally {
      stmt.closeOnCompletion()
    }
    configInfo
  }

  private def getAllProcessConfigValue(conn: Connection, processFqn: String): scala.collection.immutable.HashMap[String, String] = {
    val stmt = conn.prepareStatement(allQueryProcess)
    var configInfo = scala.collection.immutable.HashMap[String, String]()
    try {
      stmt.setString(1, processFqn)
      val rs = stmt.executeQuery()
      try {
        while (rs.next()) {
          configInfo += ((rs.getString(1), rs.getString(2)))
        }
      } finally {
        rs.close()
      }
    } finally {
      stmt.closeOnCompletion()
    }
    configInfo
  }

  private def getAllInstanceConfigValue(conn: Connection, instanceFqn: String): scala.collection.immutable.HashMap[String, String] = {
    val stmt = conn.prepareStatement(allQueryInstance)
    var configInfo = scala.collection.immutable.HashMap[String, String]()
    try {
      stmt.setString(1, instanceFqn)
      val rs = stmt.executeQuery()
      try {
        while (rs.next()) {
          configInfo += ((rs.getString(1), rs.getString(2)))
        }
      } finally {
        rs.close()
      }
    } finally {
      stmt.closeOnCompletion()
    }
    configInfo
  }

  def findProcessName(instanceName: String) = {

    if (instanceName.contains("#")) instanceName.substring(0, instanceName.lastIndexOf("#")) else instanceName
  }

  def getResourceConfig(name: String): Resource = {
    val conn = DriverManager.getConnection(url, user, password)
    val stmt = conn.prepareStatement(resourceConfig)

    try {
      stmt.setString(1, name)
      val rs = stmt.executeQuery()
      rs.next()
      //TODO need to try catch throw to handle exception scenarios
      Resource(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), name)
    }
    catch {
      case e: SQLException => throw new HandymanException("Issue fetching configuration from spw_resource_config for " + name, e)

    }
    finally {
      stmt.closeOnCompletion()
      conn.close
    }

  }

  private def getInstanceConfigVaue(conn: Connection, instanceName: String, key: String): String = {
    val stmt = conn.prepareStatement(keyQueryInstance)
    var valueRet: String = null
    try {
      stmt.setString(1, instanceName)
      stmt.setString(2, key)
      val rs = stmt.executeQuery()
      try {
        if (rs.next()) {
          val value = rs.getString(1)
          valueRet = value
        }
      } finally {
        rs.close()
      }
    } finally {
      stmt.closeOnCompletion()
    }
    valueRet
  }

}
