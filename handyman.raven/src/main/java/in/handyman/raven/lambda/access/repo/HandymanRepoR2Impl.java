package in.handyman.raven.lambda.access.repo;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lambda.doa.audit.ActionExecutionStatusAudit;
import in.handyman.raven.lambda.doa.audit.PipelineExecutionAudit;
import in.handyman.raven.lambda.doa.audit.PipelineExecutionStatusAudit;
import in.handyman.raven.lambda.doa.audit.StatementExecutionAudit;
import in.handyman.raven.lambda.doa.config.SpwCommonConfig;
import in.handyman.raven.lambda.doa.config.SpwInstanceConfig;
import in.handyman.raven.lambda.doa.config.SpwProcessConfig;
import in.handyman.raven.lambda.doa.config.SpwResourceConfig;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.io.File;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class HandymanRepoR2Impl extends AbstractAccess implements HandymanRepo {

    protected static final String CONFIG_URL = "config.url";
    private static final String CONFIG_PASSWORD = "config.password";
    private static final String CONFIG_USER = "config.user";
    private static final Config CONFIG;

    private static final Jdbi jdbi;

    static {

        final String getenv = Optional.ofNullable(System.getenv("handyman.config.location")).orElse("config.props");
        final File file = new File(getenv);

        if (!file.exists()) {
            throw new HandymanException("Config file not found");
        }

        CONFIG = ConfigFactory.parseFile(file);
        log.info("Initializing the config store from config file {}", CONFIG.origin().url());
        final String username = CONFIG.getString(CONFIG_USER);
        final String password = CONFIG.getString(CONFIG_PASSWORD);
        final String url = CONFIG.getString(CONFIG_URL);
        jdbi = Jdbi.create(url, username, password);
        jdbi.installPlugin(new SqlObjectPlugin());

    }

    @Override
    public Map<String, String> getAllConfig(final String pipelineName) {
        final String lambdaName = getLambdaName(pipelineName);

        final Map<String, String> pipelineConfig = findAllByInstance(pipelineName, lambdaName).stream()
                .collect(Collectors
                        .toMap((SpwInstanceConfig::getVariable),
                                SpwInstanceConfig::getValue,
                                (p, q) -> p));

        final Map<String, String> lambdaConfig = findAllByProcess(lambdaName).stream()
                .collect(Collectors
                        .toMap((SpwProcessConfig::getVariable),
                                SpwProcessConfig::getValue,
                                (p, q) -> p));

        final Map<String, String> commonConfig = getCommonConfig();

        final Map<String, String> finalMap = new HashMap<>(pipelineConfig);
        finalMap.putAll(lambdaConfig);
        finalMap.putAll(commonConfig);

        return Map.copyOf(finalMap);
    }

    @Override
    public List<SpwInstanceConfig> findAllByInstance(final String instance, final String process) {
        return jdbi.withHandle(handle -> {
            var repo = handle.attach(SpwInstanceConfigRepo.class);
            return repo.findAllByInstance(instance, process);
        });
    }

    @Override
    public List<SpwProcessConfig> findAllByProcess(final String process) {
        return jdbi.withHandle(handle -> {
            var repo = handle.attach(SpwProcessConfigRepo.class);
            return repo.findAllByProcess(process);
        });
    }

    @Override
    public Map<String, String> getCommonConfig() {
        return findAllCommonConfigs().stream()
                .collect(Collectors
                        .toMap((SpwCommonConfig::getVariable),
                                SpwCommonConfig::getValue,
                                (p, q) -> p));
    }

    @Override
    public List<SpwCommonConfig> findAllCommonConfigs() {
        return jdbi.withHandle(handle -> {
            var repo = handle.attach(SpwCommonConfigRepo.class);
            return repo.findAll();
        });
    }

    @Override
    public SpwResourceConfig getResourceConfig(final String name) {
        return findOneResourceConfig(name).orElseThrow();
    }

    @Override
    public Optional<SpwResourceConfig> findOneResourceConfig(final String configName) {
        return jdbi.withHandle(handle -> {
            var repo = handle.attach(SpwResourceConfigRepo.class);
            return repo.findOne(configName);
        });
    }

    @Override
    public Set<String> getPackageAction() {
        return findAllByProcess(SYS_PACKAGE).stream().map(SpwProcessConfig::getValue).collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public void insertPipeline(final PipelineExecutionAudit audit) {
        audit.setLastModifiedDate(LocalDateTime.now());
        jdbi.useHandle(handle -> handle.createUpdate("INSERT INTO  (pipeline_id, created_by, created_date, last_modified_by, last_modified_date,pipeline_name," +
                        " context_node, execution_status_id, lambda_name, parent_action_id, parent_action_name, parent_pipeline_id, parent_pipeline_name,  file_content, host_name, mode_of_execution, pipeline_load_type, relative_path, request_body, thread_name,process_name,root_pipeline_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)")
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
                .bind(21, audit.getRootPipelineId())
                .execute());
    }

    @Override
    public void insertAction(final ActionExecutionAudit audit) {

        jdbi.useHandle(handle -> {
            audit.setLastModifiedDate(LocalDateTime.now());
            var repo = handle.attach(ActionExecutionAuditRepo.class);
            repo.insert(audit);
        });

    }


    @Override
    public void update(final ActionExecutionAudit audit) {

        jdbi.useHandle(handle -> {
            audit.setLastModifiedDate(LocalDateTime.now());
            var repo = handle.attach(ActionExecutionAuditRepo.class);
            repo.update(audit);
        });

    }

    @Override
    public List<ActionExecutionAudit> findActions(final Long pipelineId) {
        return jdbi.withHandle(handle -> {
            var repo = handle.attach(ActionExecutionAuditRepo.class);
            return repo.findAllActionsByPipelineId(pipelineId);
        });
    }

    @Override
    public List<ActionExecutionAudit> findAllActionsByRootPipelineId(final Long rootPipelineId) {
        return jdbi.withHandle(handle -> {
            var repo = handle.attach(ActionExecutionAuditRepo.class);
            return repo.findAllActionsByRootPipelineId(rootPipelineId);
        });
    }

    @Override
    public void insertStatement(final StatementExecutionAudit audit) {


        audit.setLastModifiedDate(LocalDateTime.now());
        jdbi.useHandle(handle -> handle.createUpdate("INSERT INTO " + StatementExecutionAudit.SCHEMA_NAME + "." + StatementExecutionAudit.TABLE_NAME + " (statement_id, created_by, created_date, last_modified_by, last_modified_date, action_id, rows_processed, rows_read, rows_written, statement_content, time_taken,root_pipeline_id) VALUES(:statementId, :createdBy, :createdDate, :lastModifiedBy, :lastModifiedDate, :actionId, :rowsProcessed, :rowsRead, :rowsWritten, :statementContent, :timeTaken,:rootPipelineId);")
                .bindBean(audit).execute());
    }

    @Override
    public void save(final PipelineExecutionStatusAudit audit) {
        audit.setLastModifiedDate(LocalDateTime.now());
        jdbi.useHandle(handle -> handle.createUpdate("INSERT INTO lambda_execution_audit (id, created_by, created_date, last_modified_by, last_modified_date, execution_status_id, pipeline_id,root_pipeline_id) VALUES(:id, :createdBy, :createdDate, :lastModifiedBy, :lastModifiedDate, :executionStatusId, :pipelineId,:rootPipelineId);")
                .bindBean(audit).execute());
    }

    @Override
    public void save(final ActionExecutionStatusAudit audit) {
        audit.setLastModifiedDate(LocalDateTime.now());
        jdbi.useHandle(handle -> handle.createUpdate("INSERT INTO " + ActionExecutionStatusAudit.SCHEMA_NAME + "." + ActionExecutionStatusAudit.TABLE_NAME + " (id, created_by, created_date, last_modified_by, last_modified_date, action_id, execution_status_id, pipeline_id,root_pipeline_id) VALUES(:id, :createdBy, :createdDate, :lastModifiedBy, :lastModifiedDate, :actionId, :executionStatusId, :pipelineId,:rootPipelineId);")
                .bindBean(audit).execute());
    }

    @Override
    public void update(final PipelineExecutionAudit audit) {
        audit.setLastModifiedDate(LocalDateTime.now());
        jdbi.useHandle(handle -> handle.createUpdate("UPDATE pipeline SET created_by = :createdBy, created_date = :createdDate, last_modified_by = :lastModifiedBy, last_modified_date = :lastModifiedDate, context_node = :contextNode, execution_status_id = :executionStatusId, lambda_name = :lambdaName, parent_action_id = :parentActionId, parent_action_name = :parentActionName, parent_pipeline_id = :parentPipelineId, parent_pipeline_name = :parentPipelineName, pipeline_name = :pipelineName, file_content = :fileContent, host_name = :hostName, mode_of_execution = :modeOfExecution, pipeline_load_type = :pipelineLoadType , relative_path = :relativePath, request_body = :requestBody,  process_name = :processName , root_pipeline_id = :rootPipelineId WHERE pipeline_id = :pipelineId ;")
                .bindBean(audit).execute());
    }


    @Override
    public Optional<PipelineExecutionAudit> findPipeline(final Long pipelineId) {
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM pipeline where pipeline_id = ?")
                .bind(0, pipelineId)
                .mapToBean(PipelineExecutionAudit.class)
                .findOne());
    }


    @Override
    public List<PipelineExecutionAudit> findPipelines(final Long parentActionId) {
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM pipeline where parent_action_id = ?")
                .bind(0, parentActionId)
                .mapToBean(PipelineExecutionAudit.class)
                .list());
    }

    @Override
    public List<PipelineExecutionAudit> findAllPipelines() {
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM pipeline")
                .mapToBean(PipelineExecutionAudit.class)
                .list());
    }

    @Override
    public List<PipelineExecutionAudit> findAllPipelines(final String pipelineName) {
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM pipeline where pipeline_name =? ")
                .bind(0, pipelineName)
                .mapToBean(PipelineExecutionAudit.class)
                .list());
    }

    // Spw Instance

    @Override
    public void insert(final SpwInstanceConfig spwInstanceConfig) {
        jdbi.useHandle(handle -> {
            var repo = handle.attach(SpwInstanceConfigRepo.class);
            final Long nextVersion = repo.getNextVersion(spwInstanceConfig);
            spwInstanceConfig.setVersion(nextVersion.intValue());
            repo.insert(spwInstanceConfig);
        });
    }

    @Override
    public void update(final SpwInstanceConfig spwInstanceConfig) {
        jdbi.useHandle(handle -> {
            var repo = handle.attach(SpwInstanceConfigRepo.class);
            repo.update(spwInstanceConfig);
        });
    }

    @Override
    public List<SpwInstanceConfig> findAllInstances() {
        return jdbi.withHandle(handle -> {
            var repo = handle.attach(SpwInstanceConfigRepo.class);
            return repo.findAll();
        });
    }

    @Override
    public List<SpwInstanceConfig> findAllByInstanceVariable(final String variable) {
        return jdbi.withHandle(handle -> {
            var repo = handle.attach(SpwInstanceConfigRepo.class);
            return repo.findAllByInstanceVariable(variable);
        });
    }

    @Override
    public Optional<SpwInstanceConfig> findOneInstance(final String instance, final String process, final String variable) {
        return jdbi.withHandle(handle -> {
            var repo = handle.attach(SpwInstanceConfigRepo.class);
            return repo.findOne(instance, process, variable);
        });
    }

    @Override
    public void insert(final SpwProcessConfig spwProcessConfig) {
        jdbi.useHandle(handle -> {
            var repo = handle.attach(SpwProcessConfigRepo.class);
            final Long nextVersion = repo.getNextVersion(spwProcessConfig);
            spwProcessConfig.setVersion(nextVersion.intValue());
            repo.insert(spwProcessConfig);
        });
    }

    @Override
    public void update(final SpwProcessConfig spwProcessConfig) {
        jdbi.useHandle(handle -> {
            var repo = handle.attach(SpwProcessConfigRepo.class);
            repo.update(spwProcessConfig);
        });
    }

    @Override
    public List<SpwProcessConfig> findAllProcesses() {
        return jdbi.withHandle(handle -> {
            var repo = handle.attach(SpwProcessConfigRepo.class);
            return repo.findAll();
        });
    }

    @Override
    public Optional<SpwProcessConfig> findOneProcess(final String process, final String variable) {
        return jdbi.withHandle(handle -> {
            var repo = handle.attach(SpwProcessConfigRepo.class);
            return repo.findOne(process, variable);
        });
    }

    @Override
    public void insert(final SpwCommonConfig spwCommonConfig) {
        jdbi.useHandle(handle -> {
            var repo = handle.attach(SpwCommonConfigRepo.class);
            final Long nextVersion = repo.getNextVersion(spwCommonConfig);
            spwCommonConfig.setVersion(nextVersion.intValue());
            repo.insert(spwCommonConfig);
        });
    }

    @Override
    public void update(final SpwCommonConfig spwCommonConfig) {
        jdbi.useHandle(handle -> {
            var repo = handle.attach(SpwCommonConfigRepo.class);
            repo.update(spwCommonConfig);
        });
    }

    @Override
    public Optional<SpwCommonConfig> findOneCommonConfig(final String variable) {
        return jdbi.withHandle(handle -> {
            var repo = handle.attach(SpwCommonConfigRepo.class);
            return repo.findOne(variable);
        });
    }

    @Override
    public void insert(final SpwResourceConfig spwResourceConfig) {
        jdbi.useHandle(handle -> {
            var repo = handle.attach(SpwResourceConfigRepo.class);
            final Long nextVersion = repo.getNextVersion(spwResourceConfig);
            spwResourceConfig.setVersion(nextVersion.intValue());
            repo.insert(spwResourceConfig);
        });
    }

    @Override
    public void update(final SpwResourceConfig spwResourceConfig) {
        jdbi.useHandle(handle -> {
            var repo = handle.attach(SpwResourceConfigRepo.class);
            repo.update(spwResourceConfig);
        });
    }

    @Override
    public List<SpwResourceConfig> findAllResourceConfigs() {
        return jdbi.withHandle(handle -> {
            var repo = handle.attach(SpwResourceConfigRepo.class);
            return repo.findAll();
        });
    }
}
