package in.handyman.raven.lib.modelTest;

import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.UrgencyTriageAction;
import in.handyman.raven.lib.UrgencyTriageModelAction;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Map;
@Slf4j
public class UrgencyTriageModelActionTest {

    @Test
    void tritonServer() throws Exception {

        final UrgencyTriageAction build = UrgencyTriageAction.builder()
                .condition(true)
                .name("urgencyTriageModel")
                .resourceConn("intics_agadia_db_conn")
                .outputDir("/home/balasoundarya.thanga@zucisystems.com/workspace/pr1-lambdas/output")
                .build();




        ActionExecutionAudit actionExecutionAudit = new ActionExecutionAudit();
        actionExecutionAudit.getContext().put("copro.autorotation.url", "/copro/preprocess/autorotation");
        actionExecutionAudit.setProcessId(138980079308730208L);
        actionExecutionAudit.getContext().putAll(Map.ofEntries(Map.entry("read.batch.size", "5"),
                Map.entry("consumer.API.count", "1"),
                Map.entry("write.batch.size", "5")));

     UrgencyTriageModelAction action1 = new UrgencyTriageModelAction(actionExecutionAudit, log, build);
        action1.execute();



    }
}
