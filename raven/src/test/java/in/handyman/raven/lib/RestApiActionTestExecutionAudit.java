package in.handyman.raven.lib;

import in.handyman.raven.lambda.process.HRequestResolver;
import in.handyman.raven.lambda.process.LContext;
import in.handyman.raven.lambda.process.LambdaEngine;
import org.junit.jupiter.api.Test;

import java.util.Map;

class RestApiActionTestExecutionAudit {

    @Test
    void execute() {
        LambdaEngine.start(LContext.builder()
                .pipelineName("qr.grouping.process")
                .processLoadType(HRequestResolver.LoadType.FILE.name())
                        .inheritedContext(Map.of("batch_id","12"))
                .build());
    }

}
