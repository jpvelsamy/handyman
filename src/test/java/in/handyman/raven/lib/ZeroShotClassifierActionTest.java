package in.handyman.raven.lib;

import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.ZeroShotClassifierPaperFilter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class ZeroShotClassifierActionTest {

    @Test
    void execute() throws Exception {

        final ZeroShotClassifierPaperFilter build = ZeroShotClassifierPaperFilter.builder()
                .condition(true)
                .name("Test ZeroShotClassifier")
                .processID("1234")
                .querySet("SELECT paper_no, page_content, group_id, origin_id, process_id, sor_container_id, truth_entity,  keys_to_filter " +
                        "FROM public.test_zsc limit 2;")
                .resourceConn("intics_agadia_db_conn")
                .build();


        final ActionExecutionAudit action = ActionExecutionAudit.builder()
                .build();
        action.setRootPipelineId(11011L);
        action.getContext().put("copro.paper-filtering-zero-shot-classifier.url", "http://localhost:10189/copro/filtering/zero_shot_classifier");

        final ZeroShotClassifierPaperFilterAction zeroShotClassifierPaperFilterAction = new ZeroShotClassifierPaperFilterAction(action, log, build);
        zeroShotClassifierPaperFilterAction.execute();
    }
}