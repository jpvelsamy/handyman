package in.handyman.raven.lambda.access;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.node.ArrayNode;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.doa.ConfigEntity;
import in.handyman.raven.lambda.doa.ConfigID;
import in.handyman.raven.lambda.doa.ConfigType;
import in.handyman.raven.lambda.doa.ResourceConnection;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
public class ConfigAccess {

    private static final String SYS_PACKAGE = "SYS_PACKAGE";

    static {
        ElasticsearchAccessApi.createIndices(Set.of(ResourceConnection.class, ConfigEntity.class));
    }

    public static ResourceConnection getResourceConfig(final String name) {
        final String format = String.format(DBAccess.CONFIG.getString("config.resource_connection_select_stmt"), name);
        final ArrayNode fetch = ElasticsearchAccessApi.fetch(format);
        final List<ResourceConnection> connections = getList(fetch);
        return connections.stream().findFirst().orElseThrow(() -> new HandymanException("Not found"));
    }

    private static List<ResourceConnection> getList(final ArrayNode fetch) {
        return ElasticsearchAccessApi.MAPPER.convertValue(fetch, new TypeReference<>() {
        });
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
        final String format = String.format(DBAccess.CONFIG.getString("config.config_select_stmt_by_var"), configType.getId(), configName, variable);
        final ArrayNode fetch = ElasticsearchAccessApi.fetch(format);
        final List<ConfigEntity> configEntity = ElasticsearchAccessApi.MAPPER.convertValue(fetch, new TypeReference<>() {
        });
        return configEntity.stream().findFirst();
    }


    public static Set<String> getPackageAction() {
        return findConfigEntities(ConfigType.COMMON, SYS_PACKAGE).stream().map(ConfigEntity::getValue).collect(Collectors.toSet());
    }

    private static List<ConfigEntity> findConfigEntities(final ConfigType configType, final String configName) {
        final String format = String.format(DBAccess.CONFIG.getString("config.config_select_stmt_by_name"), configType.getId(), configName);
        final ArrayNode fetch = ElasticsearchAccessApi.fetch(format);
        return ElasticsearchAccessApi.MAPPER.convertValue(fetch, new TypeReference<>() {
        });
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
        final String format = String.format(DBAccess.CONFIG.getString("config.config_select_stmt"), configType.getId());
        final ArrayNode fetch = ElasticsearchAccessApi.fetch(format);
        return ElasticsearchAccessApi.MAPPER.convertValue(fetch, new TypeReference<>() {
        });
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
