package in.handyman.raven.lib;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import in.handyman.raven.lambda.process.HRequestResolver;
import in.handyman.raven.lambda.process.LContext;
import in.handyman.raven.lambda.process.LambdaEngine;
import lombok.extern.slf4j.Slf4j;
import org.apache.maven.plugin.lifecycle.io.xpp3.LifecycleMappingsXpp3Reader;
import org.junit.jupiter.api.Test;
import java.util.Map;
@Slf4j
class RestApiActionTestExecutionAudit {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void execute() {

        ObjectNode objectNode = objectMapper.createObjectNode();
        ObjectNode node = objectMapper.createObjectNode();

        node.put("inputFile","/home/dinesh.krishna@zucisystems.com/workspace/output/pdf_to_image/bdb1824b-43d2-11ed-82c2-19466cac24ca/1.jpg");
        node.put("urls","");
        node.put("modelFilePath","/home/dinesh.krishna@zucisystems.com/workspace/Models/document_classification/document_classification_model.h5");
        node.put("outputDir","/home/dinesh.krishna@zucisystems.com/workspace/output/");
        node.put("labels","'urgent','non-urgent'");

        objectNode.putPOJO("documentClassification",node);

        LContext request = LContext.builder()
                .pipelineName("task.attribution.documentClassification")
                .processLoadType(HRequestResolver.LoadType.FILE.name())
                .inheritedContext(Map.of("request",objectNode.toString()))
                .build();

        log.info(request.toString());
        LambdaEngine.start(request);
    }

}
