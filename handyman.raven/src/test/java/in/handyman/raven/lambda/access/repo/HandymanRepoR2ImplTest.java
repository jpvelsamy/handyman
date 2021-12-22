package in.handyman.raven.lambda.access.repo;

import in.handyman.raven.lambda.doa.Action;
import in.handyman.raven.lambda.doa.ConfigStore;
import in.handyman.raven.lambda.doa.ConfigType;
import in.handyman.raven.lambda.doa.Pipeline;
import org.junit.jupiter.api.Test;

import java.util.List;

class HandymanRepoR2ImplTest {

    private final HandymanRepoR2Impl repoR2 = new HandymanRepoR2Impl();

    @Test
    void findConfigEntities() {
        final List<ConfigStore> configEntities = repoR2.findConfigEntities(ConfigType.PIPELINE, "RestApiActionTest");
        assert !configEntities.isEmpty();
    }

    @Test
    void insertPipeline() {
        repoR2.insertPipeline(Pipeline.builder().build());
    }

    @Test
    void insertAction() {
        final Action build = Action.builder().build();
        repoR2.insertAction(build);
        build.setLog("Hello");
        repoR2.update(build);
    }
}
