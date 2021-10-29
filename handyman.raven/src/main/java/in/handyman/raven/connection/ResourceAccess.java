package in.handyman.raven.connection;

import com.zaxxer.hikari.HikariDataSource;
import in.handyman.raven.lym.access.ConfigAccess;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lym.doa.ResourceConnection;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ResourceAccess {

    public static HikariDataSource rdbmsConn(final String resourceName) {
        final ResourceConnection resource = ConfigAccess.getResourceConfig(resourceName);
        try {
            return DataSource.createHP(resource.getUrl(), resource.getDriverClassName(),
                    resource.getUserName(), resource.getPassword());
        } catch (ClassNotFoundException e) {
            log.warn(resource);
            throw new HandymanException("Resource " + resourceName + " failed to connect", e);
        }
    }

}
