package in.handyman.raven.lambda.access.repo;

import in.handyman.raven.lambda.doa.ActionExecutionAudit;
import in.handyman.raven.lambda.doa.ActionExecutionStatusAudit;
import in.handyman.raven.lambda.doa.ConfigStore;
import in.handyman.raven.lambda.doa.ConfigType;
import in.handyman.raven.lambda.doa.PipelineExecutionStatusAudit;
import in.handyman.raven.lambda.doa.Pipeline;
import in.handyman.raven.lambda.doa.ResourceConnection;
import in.handyman.raven.lambda.doa.StatementExecutionAudit;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface HandymanRepo {

    String SYS_PACKAGE = "SYS_PACKAGE";

    Map<String, String> getAllConfig(final String pipelineName);

    List<ConfigStore> getAllConfigStores(final String pipelineName);

    Map<String, String> getCommonConfig();

    ResourceConnection getResourceConfig(final String name);

    List<ResourceConnection> getResourceConfigList(final String name);

    String findValueCommonConfig(final String configName, final String variable);

    Set<String> getPackageAction();

    List<ConfigStore> findConfigEntities(final ConfigType configType, final String configName);

    List<ConfigStore> findConfigEntitiesByVariable(final ConfigType configType, final String variable);

    Optional<ConfigStore> findConfigEntities(final ConfigType configType, final String configName, final String variable);

    List<ConfigStore> findConfigStore(final ConfigType configType, final String configName, final String variable);

    List<ConfigStore> findConfigEntities(final ConfigType configType);

    void save(final ConfigStore configStore);

    void save(final ResourceConnection resourceConnection);

    void insertPipeline(final Pipeline audit);

    void insertAction(final ActionExecutionAudit audit);

    void insertStatement(final StatementExecutionAudit audit);

    void save(final PipelineExecutionStatusAudit audit);

    void save(final ActionExecutionStatusAudit audit);

    void update(final Pipeline audit);

    void update(final ActionExecutionAudit audit);

    Optional<Pipeline> findPipeline(final Long pipelineId);

    List<ActionExecutionAudit> findActions(final Long pipelineId);

    List<Pipeline> findPipelines(final Long parentActionId);

    List<Pipeline> findAllPipelines();

    List<Pipeline> findAllPipelines(final String pipelineName);

}
