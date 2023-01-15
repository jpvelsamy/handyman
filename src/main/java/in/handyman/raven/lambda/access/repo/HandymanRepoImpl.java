package in.handyman.raven.lambda.access.repo;

import in.handyman.raven.lambda.doa.DoaConstant;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lambda.doa.audit.ActionExecutionStatusAudit;
import in.handyman.raven.lambda.doa.audit.PipelineExecutionAudit;
import in.handyman.raven.lambda.doa.audit.PipelineExecutionStatusAudit;
import in.handyman.raven.lambda.doa.audit.StatementExecutionAudit;
import in.handyman.raven.lambda.doa.config.SpwCommonConfig;
import in.handyman.raven.lambda.doa.config.SpwInstanceConfig;
import in.handyman.raven.lambda.doa.config.SpwProcessConfig;
import in.handyman.raven.lambda.doa.config.SpwResourceConfig;
import in.handyman.raven.util.PropertyHandler;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class HandymanRepoImpl extends AbstractAccess implements HandymanRepo {

    public static final String DOT = ".";
    protected static final String CONFIG_URL = "raven.db.url";
    private static final String CONFIG_PASSWORD = "raven.db.password";
    private static final String CONFIG_USER = "raven.db.user";
    private static final Jdbi JDBI;

    static {

        final String username = PropertyHandler.get(CONFIG_USER);
        final String password = PropertyHandler.get(CONFIG_PASSWORD);
        final String url = PropertyHandler.get(CONFIG_URL);
        log.info("Connected {} {} {}", url, username, password);
        JDBI = Jdbi.create(url, username, password);
        JDBI.installPlugin(new SqlObjectPlugin());

    }

    @Override
    public Map<String, String> getAllConfig(final String pipelineName) {
        final String lambdaName = getLambdaName(pipelineName);
        final Map<String, String> pipelineConfig = findAllByInstance(pipelineName).stream()
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
    public List<SpwInstanceConfig> findAllByInstance(final String instance) {
        return JDBI.withHandle(handle -> {
            var repo = handle.attach(SpwInstanceConfigRepo.class);
            return repo.findAllByInstance(instance);
        });
    }

    @Override
    public List<SpwProcessConfig> findAllByProcess(final String process) {
        return JDBI.withHandle(handle -> {
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
        return JDBI.withHandle(handle -> {
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
        return JDBI.withHandle(handle -> {
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
        JDBI.useHandle(handle -> {
            audit.setLastModifiedDate(LocalDateTime.now());
            var repo = handle.attach(PipelineExecutionAuditRepo.class);
            repo.insert(audit);
        });
    }

    @Override
    public void insertAction(final ActionExecutionAudit audit) {

        JDBI.useHandle(handle -> {
            audit.setLastModifiedDate(LocalDateTime.now());
            var repo = handle.attach(ActionExecutionAuditRepo.class);
            repo.insert(audit);
        });

    }


    @Override
    public void update(final ActionExecutionAudit audit) {

        JDBI.useHandle(handle -> {
            audit.setLastModifiedDate(LocalDateTime.now());
            var repo = handle.attach(ActionExecutionAuditRepo.class);
            repo.update(audit);
        });

    }

    @Override
    public List<ActionExecutionAudit> findActions(final Long pipelineId) {
        return JDBI.withHandle(handle -> {
            var repo = handle.attach(ActionExecutionAuditRepo.class);
            return repo.findAllActionsByPipelineId(pipelineId);
        });
    }

    @Override
    public List<ActionExecutionAudit> findAllActionsByRootPipelineId(final Long rootPipelineId) {
        return JDBI.withHandle(handle -> {
            var repo = handle.attach(ActionExecutionAuditRepo.class);
            return repo.findAllActionsByRootPipelineId(rootPipelineId);
        });
    }

    @Override
    public void insertStatement(final StatementExecutionAudit audit) {
        audit.setLastModifiedDate(LocalDateTime.now());
        JDBI.useHandle(handle -> handle.createUpdate("INSERT INTO " + DoaConstant.AUDIT_SCHEMA_NAME + DOT + DoaConstant.SEA_TABLE_NAME + " (statement_id, created_by, created_date, last_modified_by, last_modified_date, action_id, rows_processed, rows_read, rows_written, statement_content, time_taken,root_pipeline_id) VALUES(:statementId, :createdBy, :createdDate, :lastModifiedBy, :lastModifiedDate, :actionId, :rowsProcessed, :rowsRead, :rowsWritten, :statementContent, :timeTaken,:rootPipelineId);")
                .bindBean(audit).execute());
    }

    @Override
    public void save(final PipelineExecutionStatusAudit audit) {
        audit.setLastModifiedDate(LocalDateTime.now());
        JDBI.useHandle(handle -> handle.createUpdate("INSERT INTO " + DoaConstant.AUDIT_SCHEMA_NAME + DOT + DoaConstant.PESA_TABLE_NAME + " (id, created_by, created_date, last_modified_by, last_modified_date, execution_status_id, pipeline_id,root_pipeline_id) VALUES(:id, :createdBy, :createdDate, :lastModifiedBy, :lastModifiedDate, :executionStatusId, :pipelineId,:rootPipelineId);")
                .bindBean(audit).execute());
    }

    @Override
    public void save(final ActionExecutionStatusAudit audit) {
        audit.setLastModifiedDate(LocalDateTime.now());
        JDBI.useHandle(handle -> handle.createUpdate("INSERT INTO " + DoaConstant.AUDIT_SCHEMA_NAME + DOT + DoaConstant.AESA_TABLE_NAME + " (id, created_by, created_date, last_modified_by, last_modified_date, action_id, execution_status_id, pipeline_id,root_pipeline_id) VALUES(:id, :createdBy, :createdDate, :lastModifiedBy, :lastModifiedDate, :actionId, :executionStatusId, :pipelineId,:rootPipelineId);")
                .bindBean(audit).execute());
    }

    @Override
    public void update(final PipelineExecutionAudit audit) {
        JDBI.useHandle(handle -> {
            audit.setLastModifiedDate(LocalDateTime.now());
            var repo = handle.attach(PipelineExecutionAuditRepo.class);
            repo.update(audit);
        });
    }


    @Override
    public Optional<PipelineExecutionAudit> findPipeline(final Long pipelineId) {
        return JDBI.withHandle(handle -> {
            var repo = handle.attach(PipelineExecutionAuditRepo.class);
            return repo.findOneByPipelineId(pipelineId);
        });
    }


    @Override
    public List<PipelineExecutionAudit> findAllPipelinesByRootPipelineId(final Long rootPipelineId) {
        return JDBI.withHandle(handle -> {
            var repo = handle.attach(PipelineExecutionAuditRepo.class);
            return repo.findAllPipelinesByRootPipelineId(rootPipelineId);
        });
    }


    @Override
    public List<PipelineExecutionAudit> findAllPipelinesByParentActionId(final Long parentActionId) {
        return JDBI.withHandle(handle -> {
            var repo = handle.attach(PipelineExecutionAuditRepo.class);
            return repo.findAllPipelinesByParentActionId(parentActionId);
        });
    }

    @Override
    public List<PipelineExecutionAudit> findAllPipelines() {
        return JDBI.withHandle(handle -> {
            var repo = handle.attach(PipelineExecutionAuditRepo.class);
            return repo.findAllPipelines();
        });
    }

    @Override
    public List<PipelineExecutionAudit> findAllByPipelineName(final String pipelineName) {
        return JDBI.withHandle(handle -> {
            var repo = handle.attach(PipelineExecutionAuditRepo.class);
            return repo.findAllByPipelineName(pipelineName);
        });
    }

    @Override
    public List<PipelineExecutionAudit> findAllProcessName(final String processName) {
        return JDBI.withHandle(handle -> {
            var repo = handle.attach(PipelineExecutionAuditRepo.class);
            return repo.findAllByProcessName(processName);
        });
    }

    // Spw Instance

    @Override
    public void insert(final SpwInstanceConfig spwInstanceConfig) {
        JDBI.useHandle(handle -> {
            var repo = handle.attach(SpwInstanceConfigRepo.class);
            final Long nextVersion = repo.getNextVersion(spwInstanceConfig);
            spwInstanceConfig.setVersion(nextVersion.intValue());
            repo.insert(spwInstanceConfig);
        });
    }

    @Override
    public void update(final SpwInstanceConfig spwInstanceConfig) {
        JDBI.useHandle(handle -> {
            var repo = handle.attach(SpwInstanceConfigRepo.class);
            repo.update(spwInstanceConfig);
        });
    }

    @Override
    public List<SpwInstanceConfig> findAllInstances() {
        return JDBI.withHandle(handle -> {
            var repo = handle.attach(SpwInstanceConfigRepo.class);
            return repo.findAll();
        });
    }

    @Override
    public List<SpwInstanceConfig> findAllByInstanceVariable(final String variable) {
        return JDBI.withHandle(handle -> {
            var repo = handle.attach(SpwInstanceConfigRepo.class);
            return repo.findAllByInstanceVariable(variable);
        });
    }

    @Override
    public Optional<SpwInstanceConfig> findOneInstance(final String instance, final String variable) {
        return JDBI.withHandle(handle -> {
            var repo = handle.attach(SpwInstanceConfigRepo.class);
            return repo.findOne(instance, variable);
        });
    }

    @Override
    public void insert(final SpwProcessConfig spwProcessConfig) {
        JDBI.useHandle(handle -> {
            var repo = handle.attach(SpwProcessConfigRepo.class);
            final Long nextVersion = repo.getNextVersion(spwProcessConfig);
            spwProcessConfig.setVersion(nextVersion.intValue());
            repo.insert(spwProcessConfig);
        });
    }

    @Override
    public void update(final SpwProcessConfig spwProcessConfig) {
        JDBI.useHandle(handle -> {
            var repo = handle.attach(SpwProcessConfigRepo.class);
            repo.update(spwProcessConfig);
        });
    }

    @Override
    public List<SpwProcessConfig> findAllProcesses() {
        return JDBI.withHandle(handle -> {
            var repo = handle.attach(SpwProcessConfigRepo.class);
            return repo.findAll();
        });
    }

    @Override
    public Optional<SpwProcessConfig> findOneProcess(final String process, final String variable) {
        return JDBI.withHandle(handle -> {
            var repo = handle.attach(SpwProcessConfigRepo.class);
            return repo.findOne(process, variable);
        });
    }

    @Override
    public void insert(final SpwCommonConfig spwCommonConfig) {
        JDBI.useHandle(handle -> {
            var repo = handle.attach(SpwCommonConfigRepo.class);
            final Long nextVersion = repo.getNextVersion(spwCommonConfig);
            spwCommonConfig.setVersion(nextVersion.intValue());
            repo.insert(spwCommonConfig);
        });
    }

    @Override
    public void update(final SpwCommonConfig spwCommonConfig) {
        JDBI.useHandle(handle -> {
            var repo = handle.attach(SpwCommonConfigRepo.class);
            repo.update(spwCommonConfig);
        });
    }

    @Override
    public Optional<SpwCommonConfig> findOneCommonConfig(final String variable) {
        return JDBI.withHandle(handle -> {
            var repo = handle.attach(SpwCommonConfigRepo.class);
            return repo.findOne(variable);
        });
    }

    @Override
    public void insert(final SpwResourceConfig spwResourceConfig) {
        JDBI.useHandle(handle -> {
            var repo = handle.attach(SpwResourceConfigRepo.class);
            final Long nextVersion = repo.getNextVersion(spwResourceConfig);
            spwResourceConfig.setVersion(nextVersion.intValue());
            repo.insert(spwResourceConfig);
        });
    }

    @Override
    public void update(final SpwResourceConfig spwResourceConfig) {
        JDBI.useHandle(handle -> {
            var repo = handle.attach(SpwResourceConfigRepo.class);
            repo.update(spwResourceConfig);
        });
    }

    @Override
    public List<SpwResourceConfig> findAllResourceConfigs() {
        return JDBI.withHandle(handle -> {
            var repo = handle.attach(SpwResourceConfigRepo.class);
            return repo.findAll();
        });
    }
}
