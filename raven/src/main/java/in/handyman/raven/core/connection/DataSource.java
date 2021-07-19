package in.handyman.raven.core.connection;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import in.handyman.raven.exception.HandymanException;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.SQLException;

@Log4j2
public class DataSource {

    private static final HikariDataSource SOURCE;

    static {
        final Config conf = ConfigFactory.parseResources("configstore.props");
        log.info("Initializing the config store from config file {}", conf.origin().url());
        final String url = conf.getString("config.url");
        final String driver = conf.getString("config.driver");
        final String user = conf.getString("config.user");
        final String password = conf.getString("config.passwd");
        try {
            SOURCE = createHP(url, driver, user, password);
        } catch (ClassNotFoundException e) {
            throw new HandymanException("DB loading failed", e);
        }
    }

    protected static HikariDataSource createHP(final String url, final String driver, final String user, final String password)
            throws ClassNotFoundException {
        Class.forName(driver);
        final HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(user);
        config.setPassword(password);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        return new HikariDataSource(config);
    }

    private DataSource() {
    }

    public static Connection getConnection() throws SQLException {
        return SOURCE.getConnection();
    }
}
