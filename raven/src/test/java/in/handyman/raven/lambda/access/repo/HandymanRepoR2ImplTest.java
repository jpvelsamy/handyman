package in.handyman.raven.lambda.access.repo;

import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lambda.doa.audit.PipelineExecutionAudit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class HandymanRepoR2ImplTest {

    private final HandymanRepoR2Impl repoR2 = new HandymanRepoR2Impl();

    @Test
    void findConfigEntities() {
        repoR2.findAllCommonConfigs();
        repoR2.findAllInstances();
        repoR2.findAllProcesses();
        repoR2.findAllResourceConfigs();
        log.info("Hello ");
    }

    @Test
    void insertPipeline() {
        log.debug("Hello ");
        repoR2.insertPipeline(PipelineExecutionAudit.builder().build());
    }

    @Test
    void insertAction() {
        final ActionExecutionAudit build = ActionExecutionAudit.builder().build();
        repoR2.insertAction(build);
        build.setLog("Hello");
        repoR2.update(build);
        log.error("Hello ");
    }
}
