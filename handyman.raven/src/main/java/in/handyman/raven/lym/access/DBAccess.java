package in.handyman.raven.lym.access;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DB;
import ch.vorburger.mariadb4j.DBConfigurationBuilder;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import in.handyman.raven.exception.HandymanException;
import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;
import io.r2dbc.spi.Connection;
import lombok.extern.log4j.Log4j2;
import org.mariadb.r2dbc.MariadbConnectionConfiguration;
import org.mariadb.r2dbc.MariadbConnectionFactory;

import java.time.Duration;

@Log4j2
public class DBAccess {

    protected static final Config CONFIG;
    private static final ConnectionPool POOL;

    static {

        CONFIG = ConfigFactory.parseResources("handyman-raven-configstore.props");
        log.info("Initializing the config store from config file {}", CONFIG.origin().url());

        var configuration = MariadbConnectionConfiguration.builder()
                .host(CONFIG.getString("config.host"))
                .port(CONFIG.getInt("config.port"))
                .username(CONFIG.getString("config.user"))
                .password(CONFIG.getString("config.password"))
                .database(CONFIG.getString("config.database"))
                .build();

        final MariadbConnectionFactory factory = new MariadbConnectionFactory(configuration);
        // Configure Connection Pool
        var poolConfig = ConnectionPoolConfiguration
                .builder(factory)
                .maxIdleTime(Duration.ofMinutes(45))
                .maxSize(20)
                .build();

        POOL = new ConnectionPool(poolConfig);
        log.info("Config Loaded {}", CONFIG.entrySet());
    }

    public static Connection getConnection() {
        var connection = POOL.create().block();
        if (connection != null) {
            return connection;
        } else {
            throw new HandymanException("Connection not available");
        }
    }

    public static void init() {
        final boolean inMemory = CONFIG.getBoolean("config.in-memory");
        if (inMemory) {
            try {
                var configBuilder = DBConfigurationBuilder.newBuilder();
                configBuilder.setPort(CONFIG.getInt("config.port")); // OR, default: setPort(0); => auto. detect free port
//                configBuilder.setDataDir(CONFIG.getString("config.in-memory-db-path")); // just an example
                var db = DB.newEmbeddedDB(configBuilder.build());
                final String dbName = CONFIG.getString("config.database");
                db.createDB(dbName, CONFIG.getString("config.user"), CONFIG.getString("config.password"));
                db.start();
                db.source("init.sql", dbName);
            } catch (ManagedProcessException e) {
                throw new HandymanException("IN Memory failed", e);
            }

        }
    }
}
