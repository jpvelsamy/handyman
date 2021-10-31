package in.handyman.raven.lambda.access;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import in.handyman.raven.exception.HandymanException;
import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;
import io.r2dbc.spi.Connection;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import lombok.extern.log4j.Log4j2;
import org.mariadb.r2dbc.MariadbConnectionConfiguration;
import org.mariadb.r2dbc.MariadbConnectionFactory;
import reactor.core.publisher.Mono;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.time.Duration;
import java.util.Objects;
import java.util.stream.Stream;

@Log4j2
public class DBAccess {

    protected static final Config CONFIG;
    private static final ConnectionPool POOL;
    private static final ConnectionFactory CONNECTION_FACTORY;

    protected static final String CONFIG_DATABASE = "config.database";

    private static final String CONFIG_PASSWORD = "config.password";

    private static final String CONFIG_USER = "config.user";

    private static final String CONFIG_PORT = "config.port";

    private static final String CONFIG_HOST = "config.host";

    private static final String H2_URL = "r2dbc:h2:mem:///";

    static {

        CONFIG = ConfigFactory.parseResources("handyman-raven-configstore.props");
        log.info("Initializing the config store from config file {}", CONFIG.origin().url());

        final String host = CONFIG.getString(CONFIG_HOST);
        final int port = CONFIG.getInt(CONFIG_PORT);
        final String username = CONFIG.getString(CONFIG_USER);
        final String password = CONFIG.getString(CONFIG_PASSWORD);
        final String database = CONFIG.getString(CONFIG_DATABASE);
        var configuration = MariadbConnectionConfiguration.builder()
                .host(host)
                .port(port)
                .username(username)
                .password(password)
                .database(database)
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
        CONNECTION_FACTORY = ConnectionFactories.get(H2_URL + CONFIG.getString(CONFIG_DATABASE));

    }

    public static Connection getConnection() {
        final boolean inMemory = isInMemory();

        var connection = inMemory ? Mono.from(CONNECTION_FACTORY.create()).block() : POOL.create().block();

        if (connection != null) {
            return connection;
        } else {
            throw new HandymanException("Connection not available");
        }
    }

    public static void init() {
        var stringBuffer = new StringBuilder();
        final URL resource = DBAccess.class.getClassLoader().getResource("init.sql");
        try (var fileReader = new FileReader(Objects.requireNonNull(resource).getFile())) {
            try (var bufferedReader = new BufferedReader(fileReader)) {
                bufferedReader.lines().forEach(stringBuffer::append);
            }
            // here is our splitter ! We use ";" as a delimiter for each request
            var queries = stringBuffer.toString().split(";");
            final Connection connection = getConnection();
            Mono.just(connection)
                    .map(connection1 -> {
                        Stream.of(queries).forEach(s1 -> Mono.from(connection.createStatement(s1+";").execute()).doFinally(signalType -> connection1.commitTransaction()).block());
                        return connection1;
                    })
                    .doOnError(throwable -> log.error("Failed",throwable))
                    .doFinally(type -> {
                        log.info(type);
                        connection.close();
                    }).block();

        } catch (Exception e) {
            System.out.println("*** Error : " + e);
            System.out.println("*** ");
            System.out.println("*** Error : ");
            e.printStackTrace();
            System.out.println("################################################");
            System.out.println(stringBuffer);
            log.info(e);
        }

    }

    private static boolean isInMemory() {
        return CONFIG.getBoolean("config.in-memory");
    }


}
