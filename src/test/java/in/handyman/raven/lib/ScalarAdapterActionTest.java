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
                .processID("138968829607360172")
                .resultSet("SELECT dp.sor_item_name as sor_key, dp.sor_question as question, dp.answer as input_value,\n" +
                        "\t\t\t\t\t\tsi.allowed_adapter , si.restricted_adapter ,\n" +
                        "\t\t\t\t\t\tsi.word_limit , si.word_threshold ,\n" +
                        "\t\t\t\t\t\tsi.char_limit , si.char_threshold ,\n" +
                        "\t\t\t\t\t\tsi.validator_threshold , si.allowed_characters ,\n" +
                        "\t\t\t\t\t\tsi.comparable_characters, si.restricted_adapter_flag,\n" +
                        "\t\t\t\t\t\tdp.origin_id ,dp.paper_no ,dp.group_id,\n" +
                        "\t\t\t\t\t\tdp.created_user_id, dp.tenant_id\n" +
                        "\t\t\t\tFROM sor_transaction.vqa_transaction dp\n" +
                        "\t\t\t\tJOIN sor_meta.sor_item si ON si.sor_item_name = dp.sor_item_name")
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