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
                .pipelineName("fax.processing.producer")
                .processLoadType(HRequestResolver.LoadType.FILE.name())
                        .inheritedContext(Map.of("request","{ \"faxDocument\": [ { \"faxReferenceId\": 123456789, \"faxFileUri\": \"https://www.tutorialspoint.com/java/pdf/java_interfaces.pdf\", \"callBackUrl\": \"https://pahub.intics.ai.callback.url\", \"fileSize\": \"12kb\", \"fileFormat\": \"pdf\", \"faxChecksum\": 144788 } ] }"))
                .build());
    }

}
