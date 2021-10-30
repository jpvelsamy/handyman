package in.handyman.raven.lym.access;

import in.handyman.raven.lym.doa.Auditable;
import in.handyman.raven.lym.doa.ConfigEntity;
import in.handyman.raven.lym.doa.ConfigID;
import in.handyman.raven.lym.doa.ConfigType;
import in.handyman.raven.lym.doa.ResourceConnection;
import io.r2dbc.spi.Connection;
import io.r2dbc.spi.Result;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.Statement;
import lombok.extern.log4j.Log4j2;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
public class ConfigAccess {

    private static final String SYS_PACKAGE = "SYS_PACKAGE";

    public static ResourceConnection getResourceConfig(final String name) {
        var connection = getConnection();
        try {
            final Statement statement = connection.createStatement(DBAccess.CONFIG.getString("config.resource_connection_select_stmt"))
                    .bind(0, name);
            //name, created_by, created_date, last_modified_by, last_modified_date, active, config_type, driver_class_name, password, url, user_name
            return Flux.from(statement.execute()).flatMap(res ->
                    res.map((row, metadata) -> {
                        final ResourceConnection resourceConnection = ResourceConnection.builder()
                                .name(row.get(4, String.class))
                                .active(Boolean.TRUE.equals(row.get(5, Boolean.class)))
                                .configType(row.get(6, String.class))
                                .driverClassName(row.get(7, String.class))
                                .password(row.get(8, String.class))
                                .url(row.get(9, String.class))
                                .userName(row.get(10, String.class))
                                .build();
                        addAuditable(row, resourceConnection);
                        return resourceConnection;
                    })).blockFirst();
        } finally {
            connection.close();
        }
    }

    private static Connection getConnection() {
        return DBAccess.getConnection();
    }

    private static void addAuditable(final Row row, final Auditable auditable) {
        auditable.setCreatedBy(row.get(0, Long.class));
        auditable.setCreatedDate(row.get(1, LocalDateTime.class));
        auditable.setLastModifiedBy(row.get(2, Long.class));
        auditable.setLastModifiedDate(row.get(3, LocalDateTime.class));
        log.debug(auditable);
    }

    public static Map<String, String> getAllConfig(final String pipelineName) {
        final String lambdaName = getLambdaName(pipelineName);
        final Map<String, String> pipelineConfig = toMap(findConfigEntities(ConfigType.PIPELINE, pipelineName));
        final Map<String, String> lambdaConfig = toMap(findConfigEntities(ConfigType.LAMBDA, lambdaName));
        final Map<String, String> commonConfig = getCommonConfig();
        final Map<String, String> finalMap = new HashMap<>(pipelineConfig);
        finalMap.putAll(lambdaConfig);
        finalMap.putAll(commonConfig);
        return Map.copyOf(finalMap);
    }

    public static Map<String, String> getCommonConfig() {
        return toMap(findConfigEntities(ConfigType.COMMON));
    }

    public static String findValueCommonConfig(final String configName, final String variable) {
        return findConfigEntities(ConfigType.COMMON, configName, variable).map(ConfigEntity::getValue).orElse(null);
    }

    private static Optional<ConfigEntity> findConfigEntities(final ConfigType configType, final String configName, final String variable) {
        var connection = getConnection();
        try {
            final Statement statement = connection.createStatement(DBAccess.CONFIG.getString("config.config_select_stmt_by_name"))
                    .bind(0, configType.getId()).bind(1, configName)
                    .bind(2, variable);
            //config_type_id, name, variable, active, value
            return getConfigEntities(statement).stream().findFirst();
        } finally {
            connection.close();
        }
    }

    private static List<ConfigEntity> getConfigEntities(final Statement statement) {
        //config_type_id, name, variable, active, value
        return Flux.from(statement.execute())
                .flatMap(ConfigAccess::mappedConfigEntity)
                .toStream().collect(Collectors.toList());
    }

    private static Publisher<ConfigEntity> mappedConfigEntity(final Result res) {
        return res.map((row, metadata) -> {
            final ConfigEntity configEntity = ConfigEntity.builder()
                    .id(ConfigID.builder()
                            .configTypeId(row.get(4, Integer.class))
                            .name(row.get(5, String.class))
                            .variable(row.get(6, String.class))
                            .build())
                    .active(Boolean.TRUE.equals(row.get(5, Boolean.class)))
                    .value(row.get(6, String.class))
                    .build();
            addAuditable(row, configEntity);
            return configEntity;
        });
    }

    public static Set<String> getPackageAction() {
        return findConfigEntities(ConfigType.COMMON, SYS_PACKAGE).stream().map(ConfigEntity::getValue).collect(Collectors.toSet());
    }

    private static List<ConfigEntity> findConfigEntities(final ConfigType configType, final String configName) {
        var connection = getConnection();
        try {
            final Statement statement = connection.createStatement(DBAccess.CONFIG.getString("config.config_select_stmt_by_name"))
                    .bind(0, configType.getId()).bind(1, configName);
            //config_type_id, name, variable, active, value
            return getConfigEntities(statement);
        } finally {
            connection.close();
        }
    }

    private static Map<String, String> toMap(final List<ConfigEntity> configEntities) {
        return configEntities.stream()
                .collect(Collectors
                        .toMap((configEntity -> Optional.ofNullable(configEntity.getId())
                                        .map(ConfigID::getVariable).orElse(null)),
                                ConfigEntity::getValue,
                                (p, q) -> p));
    }

    private static List<ConfigEntity> findConfigEntities(final ConfigType configType) {
        var connection = getConnection();
        try {
            final Statement statement = connection.createStatement(DBAccess.CONFIG.getString("config.config_select_stmt"))
                    .bind(0, configType.getId());
            return getConfigEntities(statement);
        } finally {
            connection.close();
        }
    }

    public static String getLambdaName(final String pipelineName) {
        if (pipelineName != null) {
            if (pipelineName.contains("#")) {
                return pipelineName.substring(0, pipelineName.lastIndexOf("#"));
            }
        }
        return pipelineName;
    }


}
