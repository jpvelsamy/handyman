package in.handyman.raven.lambda.access.repo;

import com.typesafe.config.ConfigFactory;
import in.handyman.raven.lambda.doa.Action;
import in.handyman.raven.lambda.doa.ActionExecutionAudit;
import in.handyman.raven.lambda.doa.ConfigEntity;
import in.handyman.raven.lambda.doa.ConfigType;
import in.handyman.raven.lambda.doa.LambdaExecutionAudit;
import in.handyman.raven.lambda.doa.Pipeline;
import in.handyman.raven.lambda.doa.ResourceConnection;
import in.handyman.raven.lambda.doa.Statement;
import io.r2dbc.spi.ConnectionFactories;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Query;
import org.springframework.data.relational.core.query.Update;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.data.relational.core.query.Criteria.where;

@Log4j2
public class HandymanRepoR2Impl extends AbstractAccess implements HandymanRepo {

    protected static final String CONFIG_DATABASE = "config.r2.database";
    private static final String CONFIG_PASSWORD = "config.r2.password";
    private static final String CONFIG_USER = "config.r2.user";
    private static final String CONFIG_DRIVER = "config.r2.driver";
    private static final String CONFIG_PORT = "config.r2.port";
    private static final String CONFIG_HOST = "config.r2.host";
    private static final String CONFIG_TYPE_ID = "config_type_id";
    private static final String NAME = "name";

    private final R2dbcEntityTemplate r2dbcEntityTemplate;

    public HandymanRepoR2Impl() {
        var CONFIG = ConfigFactory.parseResources("handyman-raven-configstore.props");
        log.info("Initializing the config store from config file {}", CONFIG.origin().url());

        final String driver = CONFIG.getString(CONFIG_DRIVER);
        final String username = CONFIG.getString(CONFIG_USER);
        final String password = CONFIG.getString(CONFIG_PASSWORD);
        final String host = CONFIG.getString(CONFIG_HOST);
        final int port = CONFIG.getInt(CONFIG_PORT);
        final String database = CONFIG.getString(CONFIG_DATABASE);

        var connectionFactory = ConnectionFactories.get(String.format("r2dbc:%s://%s:%s@%s:%s/%s?option1=value", driver, username, password, host, port, database));
        r2dbcEntityTemplate = new R2dbcEntityTemplate(connectionFactory);
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
        return r2dbcEntityTemplate
                .select(ConfigEntity.class)
                .matching(Query.query(where(NAME)
                        .is(configName).and(where(CONFIG_TYPE_ID)
                                .is(configType.getId()))))
                .all().toStream().collect(Collectors.toList());
    }

    @Override
    public Map<String, String> getCommonConfig() {
        final List<ConfigEntity> configEntities = r2dbcEntityTemplate
                .select(ConfigEntity.class)
                .matching(Query.query(where(CONFIG_TYPE_ID)
                        .is(ConfigType.COMMON.getId())))
                .all().toStream().collect(Collectors.toList());
        return toMap(configEntities);
    }

    @Override
    public ResourceConnection getResourceConfig(final String name) {
        return r2dbcEntityTemplate
                .select(ResourceConnection.class)
                .matching(Query.query(where(NAME)
                        .is(name))).one().blockOptional().orElse(null);
    }

    @Override
    public String findValueCommonConfig(final String configName, final String variable) {
        return r2dbcEntityTemplate
                .select(ConfigEntity.class)
                .matching(Query.query(where(CONFIG_TYPE_ID)
                        .is(ConfigType.COMMON.getId())
                        .and(where(NAME).is(SYS_PACKAGE))))
                .one().blockOptional().map(ConfigEntity::getValue).orElse(null);
    }

    @Override
    public Set<String> getPackageAction() {
        final List<ConfigEntity> configEntities = r2dbcEntityTemplate
                .select(ConfigEntity.class)
                .matching(Query.query(where(CONFIG_TYPE_ID)
                        .is(ConfigType.COMMON.getId())
                        .and(where(NAME).is(SYS_PACKAGE))))
                .all().toStream().collect(Collectors.toList());
        return configEntities.stream().map(ConfigEntity::getValue).collect(Collectors.toSet());
    }

    @Override
    public void insertPipeline(final Pipeline audit) {
        audit.setLastModifiedDate(LocalDateTime.now());
        r2dbcEntityTemplate.insert(Pipeline.class).using(audit).block();
    }

    @Override
    public void insertAction(final Action audit) {
        audit.setLastModifiedDate(LocalDateTime.now());
        r2dbcEntityTemplate.insert(Action.class).using(audit).block();

    }

    @Override
    public void insertStatement(final Statement audit) {
        audit.setLastModifiedDate(LocalDateTime.now());
        r2dbcEntityTemplate.insert(Statement.class).using(audit).block();
    }

    @Override
    public void insert(final LambdaExecutionAudit audit) {
        audit.setLastModifiedDate(LocalDateTime.now());
        r2dbcEntityTemplate.insert(LambdaExecutionAudit.class).using(audit).block();
    }

    @Override
    public void insert(final ActionExecutionAudit audit) {
        audit.setLastModifiedDate(LocalDateTime.now());
        r2dbcEntityTemplate.insert(ActionExecutionAudit.class).using(audit).block();
    }

    @Override
    public void update(final Pipeline audit) {
        audit.setLastModifiedDate(LocalDateTime.now());
        r2dbcEntityTemplate.update(Pipeline.class)
                .matching(Query.query(where("pipeline_id").is(audit.getPipelineId())))
                .apply(Update.update("execution_status_id", audit.getExecutionStatusId())
                        .set("last_modified_date", audit.getLastModifiedDate())
                        .set("context_node", audit.getContextNode())).block();
    }

    @Override
    public void update(final Action audit) {
        audit.setLastModifiedDate(LocalDateTime.now());
        r2dbcEntityTemplate.update(Action.class)
                .matching(Query.query(where("action_id").is(audit.getActionId())))
                .apply(Update.update("execution_status_id", audit.getExecutionStatusId())
                        .set("last_modified_date", audit.getLastModifiedDate())
                        .set("context_node", audit.getContextNode())
                        .set("input_node", audit.getInputNode())
                        .set("lambda_name", audit.getLambdaName())
                        .set("execution_group_id", audit.getExecutionGroupId())
                        .set("log", audit.getLog())
                        .set("pipeline_id", audit.getPipelineId())
                        .set("action_name", audit.getActionName())
                        .set("pipeline_name", audit.getPipelineName())
                        .set("parent_action_name", audit.getParentActionName())
                        .set("parent_action_id", audit.getParentActionId())
                        .set("parent_pipeline_name", audit.getParentPipelineName())
                        .set("parent_pipeline_id", audit.getParentPipelineId())).block();
    }


}
