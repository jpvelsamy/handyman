package in.handyman.raven.connection;

import com.zaxxer.hikari.HikariDataSource;
import in.handyman.raven.config.ConfigurationService;
import in.handyman.raven.config.Resource;
import in.handyman.raven.exception.HandymanException;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ResourceAccess {

    public static HikariDataSource rdbmsConn(final String resourceName) {
        final Resource resource = ConfigurationService.getResourceConfig(resourceName);
        try {
            return DataSource.createHP(resource.getUrl(), resource.getDriverClassName(),
                    resource.getUserName(), resource.getPassword());
        } catch (ClassNotFoundException e) {
            log.warn(resource);
            throw new HandymanException("Resource " + resourceName + " failed to connect", e);
        }
    }

}
