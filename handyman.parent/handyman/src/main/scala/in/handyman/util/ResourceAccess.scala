package in.handyman.util

import java.sql.Connection
import in.handyman.config.ConfigurationService
import in.handyman.config.Resource
import java.sql.DriverManager

/**
 * 	Migration script added on Feb 19 2021 - 
 * <ul>
 * 	<li>alter table spw_resource_config add column driver_class varchar(100);</li>
 *   <li>INSERT INTO spw_resource_config(config_type, config_name, auth_info, resource_url, active, created_date, user_name, password, driver_class)  values ('2','casey/schema_pim_kpi','','jdbc:sql4es://casey.store:9300/schema_pim_kpi?cluster.name=es-docker-cluster','Y',NOW(),'','','in.pazmysaz.essql.jdbc.ESDriver');</li>
 *   </ul>
 */

object ResourceAccess {
  
 
  def rdbmsConn(name: String): Connection = 
  {
    val connResource: Resource = ConfigurationService.getResourceConfig(name)
    if(connResource.driverClassName!=null)
    {
      Class.forName(connResource.driverClassName)
    }
    val conn = DriverManager.
      getConnection(connResource.url,
        connResource.userName, connResource.password)
    conn.setAutoCommit(false)
    conn
  }
  
    
}