package in.handyman.raven.lib;

import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.ScalarAdapter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class ScalarAdapterActionTest {

    @Test
    void execute() throws Exception {

        final ScalarAdapter build = ScalarAdapter.builder()
                .condition(true)
                .name("Test ScalarAdapter")
                .forkBatchSize("10")
                .resultSet("SELECT x.* FROM macro.docnet_processor_138925734021660226 x")
                .resourceConn("intics_agadia_db_conn")
                .build();


        final ActionExecutionAudit action = ActionExecutionAudit.builder()
                .build();
        action.setRootPipelineId(11011L);
        action.getContext().put(DonutDocQaAction.ATTRIBUTION_URL, "http://localhost:10189/copro/attribution/kvp-docnet");
        final ScalarAdapterAction scalarAdapterAction = new ScalarAdapterAction(action, log, build);
        scalarAdapterAction.execute();

    }
}