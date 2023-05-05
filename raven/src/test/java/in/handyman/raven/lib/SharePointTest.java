package in.handyman.raven.lib;

import in.handyman.raven.lambda.process.HRequestResolver;
import in.handyman.raven.lambda.process.LContext;
import in.handyman.raven.lambda.process.LambdaEngine;
import org.junit.jupiter.api.Test;

class SharePointTest {

    @Test
    void execute() {
        LambdaEngine.start(LContext.builder()
                .pipelineName("com.test.sharepoint")
                .processLoadType(HRequestResolver.LoadType.FILE.name())
                .build());
    }
}
