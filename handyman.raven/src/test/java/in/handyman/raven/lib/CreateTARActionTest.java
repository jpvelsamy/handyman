package in.handyman.raven.lib;

import in.handyman.raven.lambda.doa.Action;
import in.handyman.raven.lambda.doa.ResourceConnection;
import in.handyman.raven.lib.model.CreateTAR;
import in.handyman.raven.lib.model.ImportCsvToDB;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class CreateTARActionTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    void execute() throws Exception {
        final CreateTARAction createTARAction = new CreateTARAction(Action.builder().build(),
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
