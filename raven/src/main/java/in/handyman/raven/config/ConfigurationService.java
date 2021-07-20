package in.handyman.raven.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import in.handyman.raven.connection.DataSource;
import in.handyman.raven.exception.HandymanException;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Log4j2
public class ConfigurationService {

    private static final Config conf = ConfigFactory.parseResources("configstore.props");
    private static final String keyQueryBatch = conf.getString("config.key_global_sql");
    private static final String keyQueryProcess = conf.getString("config.key_process_sql");
    private static final String allQueryBatch = conf.getString("config.batch_sql");
    private static final String allQueryProcess = conf.getString("config.process_sql");
    private static final String allQueryInstance = conf.getString("config.instance_sql");
    private static final String resourceConfig = conf.getString("config.resource_sql");
    private static final String scanPackageLambda = conf.getString("config.scan_package_lambda");

    public static String getProcessConfig(final String processName, final String key) {
        try (final Connection conn = DataSource.getConnection()) {
            final String processConfigValue = getProcessConfigValue(conn, processName, key);
            if (Objects.nonNull(processConfigValue) && !processConfigValue.isEmpty()) {
                return getGlobalConfig(conn, key);
            }
        } catch (SQLException e) {
            log.error(e);
            throw new HandymanException("Connection failed", e);
        }
        throw new HandymanException("Config failed");
    }

    public static String getCommonConfig(final String key) {
        try (final Connection conn = DataSource.getConnection()) {
            return getGlobalConfig(conn, key);
        } catch (SQLException e) {
            log.error(e);
            throw new HandymanException("Connection failed", e);
        }
    }

    private static String getProcessConfigValue(final Connection conn, final String processName, final String key) {
        try (final PreparedStatement stmt = conn.prepareStatement(keyQueryProcess)) {
            stmt.setString(1, processName);
            stmt.setString(2, key);
            try (final ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString(1);
                }
            } catch (SQLException e) {
                log.error("Error reading information from config store {}", conn);
                throw new HandymanException("ProcessConfig RS failed", e);
            }
        } catch (SQLException e) {
            log.error("Error reading information from config store {}", conn);
            throw new HandymanException("ProcessConfig failed", e);
        }
        throw new HandymanException("ProcessConfig failed");
    }

    private static String getGlobalConfig(final Connection conn, final String key) {
        try (final PreparedStatement stmt = conn.prepareStatement(keyQueryBatch)) {
            stmt.setString(1, key);
            try (final ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString(1);
                }
            } catch (SQLException e) {
                log.error("Error reading information from config store {}", conn);
                throw new HandymanException("BatchConfig RS failed", e);
            }
        } catch (SQLException e) {
            log.error("Error reading information from config store {}", conn);
            throw new HandymanException("BatchConfig failed", e);
        }
        throw new HandymanException("BatchConfig failed");
    }

    public static Map<String, String> getGlobalConfig() {
        try (final Connection conn = DataSource.getConnection()) {
            try (final PreparedStatement stmt = conn.prepareStatement(allQueryBatch)) {
                return getMap(conn, stmt);
            } catch (SQLException e) {
                log.error("Error reading information from config store {}", conn);
                throw new HandymanException("Global Config failed", e);
            }
        } catch (SQLException e) {
            log.error(e);
            throw new HandymanException("Connection failed", e);
        }
    }

    private static Map<String, String> getMap(final Connection conn, final PreparedStatement stmt) {
        try (final ResultSet rs = stmt.executeQuery()) {
            final Map<String, String> configInfo = new HashMap<>();
            while (rs.next()) {
                configInfo.put(rs.getString(1), rs.getString(2));
            }
            return Map.copyOf(configInfo);
        } catch (SQLException e) {
            log.error("Error reading information from config store {}", conn);
            throw new HandymanException("Map RS failed", e);
        }
    }

    public static Map<String, String> getAllConfig(final String instanceName) {
        final String processName = findProcessName(instanceName);
        final Map<String, String> finalMap = new HashMap<>();
        finalMap.putAll(getAllProcessConfigValue(processName));
        finalMap.putAll(getAllInstanceConfigValue(instanceName));
        finalMap.putAll(getGlobalConfig());
        return Map.copyOf(finalMap);
    }

    private static Map<String, String> getAllProcessConfigValue(final String processFqn) {
        try (final Connection conn = DataSource.getConnection()) {
            try (final PreparedStatement stmt = conn.prepareStatement(allQueryProcess)) {
                stmt.setString(1, processFqn);
                return getMap(conn, stmt);
            } catch (SQLException e) {
                log.error("Error reading information from config store {}", conn);
                throw new HandymanException("Process config failed", e);
            }
        } catch (SQLException e) {
            log.error(e);
            throw new HandymanException("Connection failed", e);
        }
    }

    private static Map<String, String> getAllInstanceConfigValue(final String instanceFqn) {
        try (final Connection conn = DataSource.getConnection()) {
            try (final PreparedStatement stmt = conn.prepareStatement(allQueryInstance)) {
                stmt.setString(1, instanceFqn);
                return getMap(conn, stmt);
            } catch (SQLException e) {
                log.error("Error reading information from config store {}", conn);
                throw new HandymanException("Instance config failed", e);
            }
        } catch (SQLException e) {
            log.error(e);
            throw new HandymanException("Connection failed", e);
        }
    }

    private static String findProcessName(final String instanceName) {
        if (instanceName.contains("#")) {
            return instanceName.substring(0, instanceName.lastIndexOf("#"));
        }
        return instanceName;
    }

    public static Resource getResourceConfig(final String name) {
        try (final Connection conn = DataSource.getConnection()) {
            try (final PreparedStatement stmt = conn.prepareStatement(resourceConfig)) {
                stmt.setString(1, name);
                try (final ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return new Resource(rs.getString(1), rs.getString(2), rs.getString(3),
                                rs.getString(4), rs.getString(5), name);
                    } else {
                        throw new HandymanException("Issue fetching configuration from spw_resource_config for " + name);
                    }
                } catch (SQLException e) {
                    log.error("Error reading information from config store {}", conn);
                    throw new HandymanException("Issue fetching configuration from spw_resource_config for " + name, e);
                }
            } catch (SQLException e) {
                log.error("Error reading information from config store {}", conn);
                throw new HandymanException("Issue fetching configuration from spw_resource_config for " + name, e);
            }
        } catch (SQLException e) {
            log.error(e);
            throw new HandymanException("Issue fetching configuration from spw_resource_config for " + name, e);
        }
    }

    public static Set<String> getPackageLambda() {
        try (final Connection conn = DataSource.getConnection()) {
            try (final PreparedStatement stmt = conn.prepareStatement(scanPackageLambda)) {
                try (final ResultSet rs = stmt.executeQuery()) {
                    final Set<String> result = new HashSet<>();
                    while (rs.next()) {
                        result.add(rs.getString(1));
                    }
                    return result;
                } catch (SQLException e) {
                    log.error("Error reading information from config store {}", conn);
                    throw new HandymanException("Issue fetching configuration from Lambda Scan Package Name", e);
                }
            } catch (SQLException e) {
                log.error("Error reading information from config store {}", conn);
                throw new HandymanException("Issue fetching configuration from  Lambda Scan Package Name", e);
            }
        } catch (SQLException e) {
            log.error(e);
            throw new HandymanException("Issue fetching configuration from  Lambda Scan Package Name", e);
        }
    }

}
