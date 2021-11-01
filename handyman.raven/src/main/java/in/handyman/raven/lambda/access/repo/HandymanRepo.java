package in.handyman.raven.lambda.access.repo;

import in.handyman.raven.lambda.doa.Action;
import in.handyman.raven.lambda.doa.ActionExecutionAudit;
import in.handyman.raven.lambda.doa.ConfigEntity;
import in.handyman.raven.lambda.doa.ConfigType;
import in.handyman.raven.lambda.doa.Pipeline;
import in.handyman.raven.lambda.doa.PipelineExecutionAudit;
import in.handyman.raven.lambda.doa.ResourceConnection;
import in.handyman.raven.lambda.doa.Statement;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface HandymanRepo {

    String SYS_PACKAGE = "SYS_PACKAGE";

    Map<String, String> getAllConfig(final String pipelineName);

    Map<String, String> getCommonConfig();

    ResourceConnection getResourceConfig(final String name);

    String findValueCommonConfig(final String configName, final String variable);

    Set<String> getPackageAction();

    List<ConfigEntity> findConfigEntities(final ConfigType configType, final String configName);

    void insertPipeline(final Pipeline audit);

    void insertAction(final Action audit);

    void insertStatement(final Statement audit);

    void insert(final PipelineExecutionAudit audit);

    void insert(final ActionExecutionAudit audit);

    void update(final Pipeline audit);

    void update(final Action audit);

}
