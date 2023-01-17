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
                //.forkBatchSize("1")
                .resultSet(" SELECT dp.sor_id, dp.sor_key , dp.question, dp.answer as input_value,\n" +
                        "                             si.allowed_adapter , si.restricted_adapter ,\n" +
                        "                             si.word_limit , si.word_threshold ,\n" +
                        "                             si.char_limit , si.char_threshold ,\n" +
                        "                             si.validator_threshold , si.allowed_characters ,\n" +
                        "                             si.comparable_characters, si.restricted_adapter_flag,\n" +
                        "                             dp.file_ref_id ,dp.paper_no ,dp.group_id, dp.sor_item_id,\n" +
                        "                             dp.created_user_id, dp.tenant_id\n" +
                        "               FROM macro.docnet_processor dp\n" +
                        "               JOIN sor_meta.sor_item si ON si.sor_key = dp.sor_key")
                .resourceConn("intics_agadia_db_conn")
                .build();


        final ActionExecutionAudit action = ActionExecutionAudit.builder()
                .build();
        action.setRootPipelineId(11011L);
        //action.getContext().put("copro.text-validation.url", "http://localhost:10189/copro/text-validation/patient");
        final ScalarAdapterAction scalarAdapterAction = new ScalarAdapterAction(action, log, build);
        scalarAdapterAction.execute();

    }
}