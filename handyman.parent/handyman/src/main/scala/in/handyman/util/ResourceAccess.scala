package in.handyman.util

import java.sql.Connection
import in.handyman.config.ConfigurationService
import in.handyman.config.Resource
import java.sql.DriverManager


object ResourceAccess {
  
 
  def rdbmsConn(name: String): Connection = 
  {
    val connResource: Resource = ConfigurationService.getResourceConfig(name)
    val conn = DriverManager.
      getConnection(connResource.url,
        connResource.userName, connResource.password)
    conn.setAutoCommit(false)
    conn
  }
}