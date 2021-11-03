package in.handyman.raven.lambda.access;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.doa.ResourceConnection;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ResourceAccess {

    public static HikariDataSource rdbmsConn(final String resourceName) {
        final ResourceConnection resource = ConfigAccess.getResourceConfig(resourceName);
        try {
            return createHP(resource.getUrl(), resource.getDriverClassName(),
                    resource.getUserName(), resource.getPassword());
        } catch (ClassNotFoundException e) {
            log.warn("{} => {}",resourceName,resource);
            throw new HandymanException("Resource " + resourceName + " failed to connect", e);
        }
    }

    protected static HikariDataSource createHP(final String url, final String driver, final String user, final String password)
            throws ClassNotFoundException {
        Class.forName(driver);
        final HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(user);
        config.setPassword(password);
        config.setMinimumIdle(0);
        config.setConnectionTimeout(30000);
        config.setIdleTimeout(35000);
        config.setMaxLifetime(45000);
        config.setMaximumPoolSize(2);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        return new HikariDataSource(config);
    }

}
