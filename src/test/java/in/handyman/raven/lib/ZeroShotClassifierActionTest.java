package in.handyman.raven.lib;

import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.PhraseMatchPaperFilter;
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
                .querySet("select sot.paper_no, sot.content as page_content, sot.group_id, sot.origin_id, \n" +
                        "'1234' as process_id,t.sor_container_id,t.truth_entity, \n" +
                        "t.keys_to_filter from (select ste.sor_container_id as sor_container_id, \n" +
                        "ste.truth_entity as truth_entity, \n" +
                        "jsonb_agg(st.synonym) as keys_to_filter\n" +
                        "from sor_meta.sor_tsynonym st \n" +
                        "inner join sor_meta.sor_item_truth_entity_mapping sitem \n" +
                        "on sitem.sor_item_name = st.sor_item_name \n" +
                        "and sitem.sor_truth_mapping_id = st.sor_truth_mapping_id \n" +
                        "inner join sor_meta.sor_truth_entity ste \n" +
                        "on ste.truth_entity = sitem.truth_entity  \n" +
                        "where st.is_paper_filter_candidate ='True'\n" +
                        "group by ste.sor_container_id,ste.truth_entity  )t\n" +
                        "cross join info.source_of_truth sot\n" +
                        "where sot.origin_id ='INT-1' limit 2;")
                .resourceConn("intics_agadia_db_conn")
                .build();


        final ActionExecutionAudit action = ActionExecutionAudit.builder()
                .build();
        action.setRootPipelineId(11011L);
        action.getContext().put("copro.paper-filtering-zero-shot-classifier.url", "http://localhost:10189/copro/filtering/zero_shot_classifier");

        final ZeroShotClassifierPaperFilterAction zeroShotClassifierPaperFilterAction = new ZeroShotClassifierPaperFilterAction(action, log, build);
        zeroShotClassifierPaperFilterAction.execute();
    }


    @Test
    void executePhraseMatch() throws Exception {

        final PhraseMatchPaperFilter build = PhraseMatchPaperFilter.builder()
                .condition(true)
                .name("Test PhraseMatch")
                .processID("1234")
                .querySet("select sot.paper_no, sot.content as page_content, sot.group_id, sot.origin_id, \n" +
                        "'1234' as process_id,t.sor_container_id,t.truth_entity, \n" +
                        "t.keys_to_filter from (select ste.sor_container_id as sor_container_id, \n" +
                        "ste.truth_entity as truth_entity, \n" +
                        "jsonb_agg(st.synonym) as keys_to_filter\n" +
                        "from sor_meta.sor_tsynonym st \n" +
                        "inner join sor_meta.sor_item_truth_entity_mapping sitem \n" +
                        "on sitem.sor_item_name = st.sor_item_name \n" +
                        "and sitem.sor_truth_mapping_id = st.sor_truth_mapping_id \n" +
                        "inner join sor_meta.sor_truth_entity ste \n" +
                        "on ste.truth_entity = sitem.truth_entity  \n" +
                        "where st.is_paper_filter_candidate ='True'\n" +
                        "group by ste.sor_container_id,ste.truth_entity  )t\n" +
                        "cross join info.source_of_truth sot\n" +
                        "where sot.origin_id ='INT-1' limit 2;")
                .resourceConn("intics_agadia_db_conn")
                .build();


        final ActionExecutionAudit action = ActionExecutionAudit.builder()
                .build();
        action.setRootPipelineId(11011L);
        action.getContext().put("copro.paper-filtering-phrase-match.url", "http://localhost:10189/copro/filtering/phrase_match");

        final PhraseMatchPaperFilterAction zeroShotClassifierPaperFilterAction = new PhraseMatchPaperFilterAction(action, log, build);
        zeroShotClassifierPaperFilterAction.execute();
    }
}