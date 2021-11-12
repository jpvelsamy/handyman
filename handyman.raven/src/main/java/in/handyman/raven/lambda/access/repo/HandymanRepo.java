package in.handyman.raven.lambda.access.repo;

import in.handyman.raven.lambda.doa.Action;
import in.handyman.raven.lambda.doa.ActionExecutionAudit;
import in.handyman.raven.lambda.doa.ConfigStore;
import in.handyman.raven.lambda.doa.ConfigType;
import in.handyman.raven.lambda.doa.LambdaExecutionAudit;
import in.handyman.raven.lambda.doa.Pipeline;
import in.handyman.raven.lambda.doa.ResourceConnection;
import in.handyman.raven.lambda.doa.Statement;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface HandymanRepo {

    String SYS_PACKAGE = "SYS_PACKAGE";

    Map<String, String> getAllConfig(final String pipelineName);

    Map<String, String> getCommonConfig();

    ResourceConnection getResourceConfig(final String name);

    String findValueCommonConfig(final String configName, final String variable);

    Set<String> getPackageAction();

    List<ConfigStore> findConfigEntities(final ConfigType configType, final String configName);

    List<ConfigStore> findConfigEntitiesByVariable(final ConfigType configType, final String variable);

    Optional<ConfigStore> findConfigEntities(final ConfigType configType, final String configName, final String variable);

    void update(final ConfigType configType, final String configName, final String variable);

    void insert(final ConfigType configType, final String configName, final String variable);

    void insertPipeline(final Pipeline audit);

    void insertAction(final Action audit);

    void insertStatement(final Statement audit);

    void insert(final LambdaExecutionAudit audit);

    void insert(final ActionExecutionAudit audit);

    void update(final Pipeline audit);

    void update(final Action audit);

    Optional<Pipeline> findPipeline(final Long pipelineId);

    List<Action> findActions(final Long pipelineId);

    List<Pipeline> findPipelines(final Long parentActionId);

    List<Pipeline> findAllPipelines();
}