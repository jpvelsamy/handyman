package in.handyman.raven.lambda.access.repo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.doa.Action;
import in.handyman.raven.lambda.doa.ActionExecutionAudit;
import in.handyman.raven.lambda.doa.ConfigEntity;
import in.handyman.raven.lambda.doa.ConfigType;
import in.handyman.raven.lambda.doa.LambdaExecutionAudit;
import in.handyman.raven.lambda.doa.Pipeline;
import in.handyman.raven.lambda.doa.ResourceConnection;
import in.handyman.raven.lambda.doa.Statement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class HandymanRepoESImpl extends AbstractAccess implements HandymanRepo {

    private final Config config;

    public HandymanRepoESImpl() {
        ElasticsearchAccessApi.createIndices(Set.of(ResourceConnection.class, ConfigEntity.class, Pipeline.class,
                Action.class, Statement.class));
        config = ConfigFactory.parseResources("handyman-raven-configstore.props");
    }

    @Override
    public Map<String, String> getAllConfig(final String pipelineName) {
        final String lambdaName = getLambdaName(pipelineName);
        final Map<String, String> pipelineConfig = toMap(findConfigEntities(ConfigType.PIPELINE, pipelineName));
        final Map<String, String> lambdaConfig = toMap(findConfigEntities(ConfigType.LAMBDA, lambdaName));
        final Map<String, String> commonConfig = getCommonConfig();
        final Map<String, String> finalMap = new HashMap<>(pipelineConfig);
        finalMap.putAll(lambdaConfig);
        finalMap.putAll(commonConfig);
        return Map.copyOf(finalMap);
    }

    @Override
    public List<ConfigEntity> findConfigEntities(final ConfigType configType, final String configName) {
        final String format = String.format(config.getString("config.config_select_stmt_by_name"), configType.getId(), configName);
        final ArrayNode fetch = ElasticsearchAccessApi.fetch(format);
        return ElasticsearchAccessApi.MAPPER.convertValue(fetch, new TypeReference<>() {
        });
    }

    @Override
    public Map<String, String> getCommonConfig() {
        return toMap(findConfigEntities(ConfigType.COMMON));
    }

    private List<ConfigEntity> findConfigEntities(final ConfigType configType) {
        final String format = String.format(config.getString("config.config_select_stmt"), configType.getId());
        final ArrayNode fetch = ElasticsearchAccessApi.fetch(format);
        return ElasticsearchAccessApi.MAPPER.convertValue(fetch, new TypeReference<>() {
        });
    }

    @Override
    public ResourceConnection getResourceConfig(final String name) {
        final String format = String.format(config.getString("config.resource_connection_select_stmt"), name);
        final ArrayNode fetch = ElasticsearchAccessApi.fetch(format);
        final List<ResourceConnection> connections = getList(fetch);
        return connections.stream().findFirst().orElseThrow(() -> new HandymanException("Not found"));
    }

    private List<ResourceConnection> getList(final ArrayNode fetch) {
        return ElasticsearchAccessApi.MAPPER.convertValue(fetch, new TypeReference<>() {
        });
    }

    @Override
    public String findValueCommonConfig(final String configName, final String variable) {
        return findConfigEntities(ConfigType.COMMON, configName, variable).map(ConfigEntity::getValue).orElse(null);
    }

    private Optional<ConfigEntity> findConfigEntities(final ConfigType configType, final String configName, final String variable) {
        final String format = String.format(config.getString("config.config_select_stmt_by_var"), configType.getId(), configName, variable);
        final ArrayNode fetch = ElasticsearchAccessApi.fetch(format);
        final List<ConfigEntity> configEntity = ElasticsearchAccessApi.MAPPER.convertValue(fetch, new TypeReference<>() {
        });
        return configEntity.stream().findFirst();
    }

    @Override
    public Set<String> getPackageAction() {
        return findConfigEntities(ConfigType.COMMON, SYS_PACKAGE).stream().map(ConfigEntity::getValue).collect(Collectors.toSet());
    }

    @Override
    public void insertPipeline(final Pipeline audit) {
        ElasticsearchAccessApi.saveIndex(String.valueOf(audit.getPipelineId()), audit);
    }

    @Override
    public void insertAction(final Action audit) {
        ElasticsearchAccessApi.saveIndex(String.valueOf(audit.getActionId()), audit);
    }

    @Override
    public void insertStatement(final Statement audit) {
        ElasticsearchAccessApi.saveIndex(String.valueOf(audit.getStatementId()), audit);
    }

    @Override
    public void insert(final LambdaExecutionAudit audit) {

    }

    @Override
    public void insert(final ActionExecutionAudit audit) {

    }

    @Override
    public void update(final Pipeline audit) {

    }

    @Override
    public void update(final Action audit) {

    }


}
