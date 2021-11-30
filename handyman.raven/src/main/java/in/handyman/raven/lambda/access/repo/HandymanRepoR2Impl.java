package in.handyman.raven.lambda.access.repo;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import in.handyman.raven.lambda.doa.Action;
import in.handyman.raven.lambda.doa.ActionExecutionAudit;
import in.handyman.raven.lambda.doa.ConfigStore;
import in.handyman.raven.lambda.doa.ConfigType;
import in.handyman.raven.lambda.doa.LambdaExecutionAudit;
import in.handyman.raven.lambda.doa.Pipeline;
import in.handyman.raven.lambda.doa.ResourceConnection;
import in.handyman.raven.lambda.doa.Statement;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.Jdbi;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class HandymanRepoR2Impl extends AbstractAccess implements HandymanRepo {

    protected static final String CONFIG_URL = "config.r2.url";
    private static final String CONFIG_PASSWORD = "config.r2.password";
    private static final String CONFIG_USER = "config.r2.user";
    private static final Config CONFIG = ConfigFactory.parseResources("handyman-raven-configstore.props");

    private static final Jdbi jdbi;

    static {
        log.info("Initializing the config store from config file {}", CONFIG.origin().url());
        final String username = CONFIG.getString(CONFIG_USER);
        final String password = CONFIG.getString(CONFIG_PASSWORD);
        final String url = CONFIG.getString(CONFIG_URL);
        jdbi = Jdbi.create(url, username, password);
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
    public List<ConfigStore> findConfigEntities(final ConfigType configType, final String configName) {
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM config_store where config_type_id = ? and name = ? and active=true ")
                .bind(0, configType.getId())
                .bind(1, configName)
                .mapToBean(ConfigStore.class)
                .list());
    }

    @Override
    public Map<String, String> getCommonConfig() {
        final List<ConfigStore> configEntities = getCommonConfigEntities();
        return toMap(configEntities);
    }

    private List<ConfigStore> getCommonConfigEntities() {
        return findConfigEntities(ConfigType.COMMON);
    }

    public List<ConfigStore> findConfigEntities(final ConfigType configType) {
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM config_store where config_type_id = ? and active=true ")
                .bind(0, configType.getId())
                .mapToBean(ConfigStore.class)
                .list());
    }

    @Override
    public List<ConfigStore> getAllConfigStores(final String pipelineName) {
        final String lambdaName = getLambdaName(pipelineName);
        final List<ConfigStore> pipelineConfig = findConfigEntities(ConfigType.PIPELINE, pipelineName);
        final List<ConfigStore> lambdaConfig = findConfigEntities(ConfigType.LAMBDA, lambdaName);
        final List<ConfigStore> commonConfig = getCommonConfigEntities();
        final List<ConfigStore> finalMap = new ArrayList<>(pipelineConfig);
        finalMap.addAll(lambdaConfig);
        finalMap.addAll(commonConfig);
        return List.copyOf(finalMap);
    }

    @Override
    public List<ConfigStore> findConfigEntitiesByVariable(final ConfigType configType, final String variable) {
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM config_store where config_type_id = ? and variable = ? and active=true ")
                .bind(0, configType.getId())
                .bind(1, variable)
                .mapToBean(ConfigStore.class)
                .list());
    }

    @Override
    public ResourceConnection getResourceConfig(final String name) {
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM resource_connection where name = ? and active=true  ")
                .bind(0, name)
                .mapToBean(ResourceConnection.class)
                .findOne().orElse(null));
    }

    @Override
    public String findValueCommonConfig(final String configName, final String variable) {
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM config_store where variable = ? and name = ? and active=true ")
                .bind(0, variable)
                .bind(1, configName)
                .mapToBean(ConfigStore.class)
                .findOne().map(ConfigStore::getValue).orElse(null));
    }

    @Override
    public Set<String> getPackageAction() {
        return findConfigEntities(ConfigType.COMMON, SYS_PACKAGE).stream().map(ConfigStore::getValue).collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public Optional<ConfigStore> findConfigEntities(final ConfigType configType, final String configName, final String variable) {
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM config_store where config_type_id = ? and name = ? and variable=? and active=true order by version desc limit 1")
                .bind(0, configType.getId())
                .bind(1, configName)
                .bind(2, variable)
                .mapToBean(ConfigStore.class)
                .findOne());
    }

    @Override
    public void save(final ConfigStore configStore) {
        final List<ConfigStore> stores = findConfigStore(ConfigType.get(configStore.getConfigTypeId()),
                configStore.getName(), configStore.getVariable());
        final int version = stores.size() + 1;
        stores.forEach(configStore1 -> {
            configStore1.setLastModifiedDate(LocalDateTime.now());
            configStore1.setActive(false);
            jdbi.useHandle(handle -> handle.createUpdate("UPDATE config_store SET active = :active WHERE id = :id;")
                    .bindBean(configStore1).execute());
        });
        configStore.setLastModifiedDate(LocalDateTime.now());
        configStore.setActive(true);
        configStore.setVersion(version);
        jdbi.useHandle(handle -> handle.createUpdate("INSERT INTO config_store (id, active, config_type_id, name, value, variable, created_by, created_date, last_modified_by, last_modified_date, version) VALUES(:id, :active, :configTypeId, :name, :value, :variable, :createdBy, :createdDate, :lastModifiedBy, :lastModifiedDate, :version);")
                .bindBean(configStore).execute());
    }

    @Override
    public List<ConfigStore> findConfigStore(final ConfigType configType, final String configName, final String variable) {
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM config_store where config_type_id = ? and name = ? and variable=? ")
                .bind(0, configType.getId())
                .bind(1, configName)
                .bind(2, variable)
                .mapToBean(ConfigStore.class)
                .list());
    }

    @Override
    public void save(final ResourceConnection resourceConnection) {
        final List<ResourceConnection> list = getResourceConfigList(resourceConnection.getName());
        final int version = list.size() + 1;
        list.forEach(connection -> {
            connection.setLastModifiedDate(LocalDateTime.now());
            connection.setActive(false);
            jdbi.useHandle(handle -> handle.createUpdate("UPDATE resource_connection SET active = :active WHERE name = :name;")
                    .bindBean(connection).execute());
        });
        resourceConnection.setLastModifiedDate(LocalDateTime.now());
        resourceConnection.setActive(true);
        resourceConnection.setVersion(version);
        jdbi.useHandle(handle -> handle.createUpdate("INSERT INTO resource_connection (name, created_by, created_date, last_modified_by, last_modified_date, active, config_type, driver_class_name, password, url, user_name, version) " +
                        " VALUES( :name, :createdBy, :createdDate, :lastModifiedBy, :lastModifiedDate, :active, :configType, :driverClassName, :password, :url, :userName, :version);")
                .bindBean(resourceConnection).execute());
    }

    @Override
    public List<ResourceConnection> getResourceConfigList(final String name) {
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM resource_connection where name = ? ")
                .bind(0, name)
                .mapToBean(ResourceConnection.class)
                .list());
    }

    @Override
    public void insertPipeline(final Pipeline audit) {
        audit.setLastModifiedDate(LocalDateTime.now());
        jdbi.useHandle(handle -> handle.createUpdate("INSERT INTO pipeline (pipeline_id, created_by, created_date, last_modified_by, last_modified_date,pipeline_name," +
                        " context_node, execution_status_id, lambda_name, parent_action_id, parent_action_name, parent_pipeline_id, parent_pipeline_name,  file_content, host_name, mode_of_execution, pipeline_load_type, relative_path, request_body, thread_name,process_name) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)")
                .bind(0, audit.getPipelineId())
                .bind(1, audit.getCreatedBy())
                .bind(2, audit.getCreatedDate())
                .bind(3, audit.getLastModifiedBy())
                .bind(4, audit.getLastModifiedDate())
                .bind(5, audit.getPipelineName())
                .bind(6, audit.getContextNode())
                .bind(7, audit.getExecutionStatusId())
                .bind(8, audit.getLambdaName())
                .bind(9, audit.getParentActionId())
                .bind(10, audit.getParentActionName())
                .bind(11, audit.getParentPipelineId())
                .bind(12, audit.getParentPipelineName())
                .bind(13, audit.getFileContent())
                .bind(14, audit.getHostName())
                .bind(15, audit.getModeOfExecution())
                .bind(16, audit.getPipelineLoadType())
                .bind(17, audit.getRelativePath())
                .bind(18, audit.getRequestBody())
                .bind(19, audit.getThreadName())
                .bind(20, audit.getProcessName())
                .execute());
    }

    @Override
    public void insertAction(final Action audit) {
        audit.setLastModifiedDate(LocalDateTime.now());
        jdbi.useHandle(handle -> handle.createUpdate("INSERT INTO action (action_id, created_by, created_date, last_modified_by, last_modified_date, context_node, execution_status_id, lambda_name, parent_action_id, parent_action_name, parent_pipeline_id, parent_pipeline_name, pipeline_name, action_name, execution_group_id, input_node, log, pipeline_id) VALUES(:actionId, :createdBy, :createdDate, :lastModifiedBy, :lastModifiedDate, :contextNode, :executionStatusId, :lambdaName, :parentActionId, :parentActionName, :parentPipelineId, :parentPipelineName, :pipelineName, :actionName, :executionGroupId, :inputNode, :log, :pipelineId);")
                .bindBean(audit).execute());
    }

    @Override
    public void insertStatement(final Statement audit) {
        audit.setLastModifiedDate(LocalDateTime.now());
        jdbi.useHandle(handle -> handle.createUpdate("INSERT INTO statement (statement_id, created_by, created_date, last_modified_by, last_modified_date, action_id, rows_processed, rows_read, rows_written, statement_content, time_taken) VALUES(:statementId, :createdBy, :createdDate, :lastModifiedBy, :lastModifiedDate, :actionId, :rowsProcessed, :rowsRead, :rowsWritten, :statementContent, :timeTaken);")
                .bindBean(audit).execute());
    }

    @Override
    public void save(final LambdaExecutionAudit audit) {
        audit.setLastModifiedDate(LocalDateTime.now());
        jdbi.useHandle(handle -> handle.createUpdate("INSERT INTO lambda_execution_audit (id, created_by, created_date, last_modified_by, last_modified_date, execution_status_id, pipeline_id) VALUES(:id, :createdBy, :createdDate, :lastModifiedBy, :lastModifiedDate, :executionStatusId, :pipelineId);")
                .bindBean(audit).execute());
    }

    @Override
    public void save(final ActionExecutionAudit audit) {
        audit.setLastModifiedDate(LocalDateTime.now());
        jdbi.useHandle(handle -> handle.createUpdate("INSERT INTO action_execution_audit (id, created_by, created_date, last_modified_by, last_modified_date, action_id, execution_status_id, pipeline_id) VALUES(:id, :createdBy, :createdDate, :lastModifiedBy, :lastModifiedDate, :actionId, :executionStatusId, :pipelineId);")
                .bindBean(audit).execute());
    }

    @Override
    public void update(final Pipeline audit) {
        audit.setLastModifiedDate(LocalDateTime.now());
        jdbi.useHandle(handle -> handle.createUpdate("UPDATE pipeline SET created_by = :createdBy, created_date = :createdDate, last_modified_by = :lastModifiedBy, last_modified_date = :lastModifiedDate, context_node = :contextNode, execution_status_id = :executionStatusId, lambda_name = :lambdaName, parent_action_id = :parentActionId, parent_action_name = :parentActionName, parent_pipeline_id = :parentPipelineId, parent_pipeline_name = :parentPipelineName, pipeline_name = :pipelineName, file_content = :fileContent, host_name = :hostName, mode_of_execution = :modeOfExecution, pipeline_load_type = :pipelineLoadType , relative_path = :relativePath, request_body = :requestBody,  process_name = :processName WHERE pipeline_id = :pipelineId ;")
                .bindBean(audit).execute());
    }

    @Override
    public void update(final Action audit) {
        audit.setLastModifiedDate(LocalDateTime.now());
        jdbi.useHandle(handle -> handle.createUpdate("UPDATE action SET created_by = :createdBy, created_date = :createdDate, last_modified_by = :lastModifiedBy, last_modified_date = :lastModifiedDate, context_node = :contextNode, execution_status_id = :executionStatusId, lambda_name = :lambdaName, parent_action_id = :parentActionId, parent_action_name = :parentActionName, parent_pipeline_id = :parentPipelineId, parent_pipeline_name = :parentPipelineName, pipeline_name = :pipelineName, action_name = :actionName, execution_group_id = :executionGroupId, input_node = :inputNode, log = :log, pipeline_id = :pipelineId,thread_name = :threadName WHERE action_id = :actionId ;")
                .bindBean(audit).execute());
    }

    @Override
    public Optional<Pipeline> findPipeline(final Long pipelineId) {
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM pipeline where pipeline_id = ?")
                .bind(0, pipelineId)
                .mapToBean(Pipeline.class)
                .findOne());
    }

    @Override
    public List<Action> findActions(final Long pipelineId) {
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM action where pipeline_id = ?;\n")
                .bind(0, pipelineId)
                .mapToBean(Action.class)
                .list());
    }

    @Override
    public List<Pipeline> findPipelines(final Long parentActionId) {
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM pipeline where parent_action_id = ?")
                .bind(0, parentActionId)
                .mapToBean(Pipeline.class)
                .list());
    }

    @Override
    public List<Pipeline> findAllPipelines() {
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM pipeline")
                .mapToBean(Pipeline.class)
                .list());
    }

    @Override
    public List<Pipeline> findAllPipelines(final String pipelineName) {
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM pipeline where pipeline_name =? ")
                .bind(0, pipelineName)
                .mapToBean(Pipeline.class)
                .list());
    }
}
