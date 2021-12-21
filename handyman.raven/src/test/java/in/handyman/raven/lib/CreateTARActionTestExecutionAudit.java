package in.handyman.raven.lib;

import in.handyman.raven.lambda.doa.ActionExecutionAudit;
import in.handyman.raven.lib.model.CreateTAR;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class CreateTARActionTestExecutionAudit {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    void execute() throws Exception {
        final CreateTARAction createTARAction = new CreateTARAction(ActionExecutionAudit.builder().build(),
                logger, CreateTAR.builder()
                .condition(true)
                .name("CCCU_DNA.dbo.MOCK_DATA")
                .destination("/workspace/temp/")
                .source("/workspace/temp/50482514116669")
                .extension("tar.gz")
                .build());

        createTARAction.execute();

    }
}
