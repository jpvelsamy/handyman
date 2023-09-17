package in.handyman.raven.lib.tritonServerTest;

import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.NerAdapter;
import in.handyman.raven.lib.NerAdapterAction;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Map;

@Slf4j
class NerAdapterActionTest {

    @Test
    void testCase() throws Exception {
        NerAdapter nerAdapter= NerAdapter.builder()
                .name("ner")
                .condition(true)
                .resourceConn("intics_agadia_db_conn")
                .resultSet("\n" +
                        "            SELECT dp.sor_item_name as sor_key, dp.sor_question as question, dp.answer as input_value, '12' as weight,\n" +
                        "                        si.allowed_adapter , si.restricted_adapter ,'124567' as process_id,\n" +
                        "                        si.word_limit , si.word_threshold ,\n" +
                        "                        si.char_limit , si.char_threshold ,\n" +
                        "                        si.validator_threshold , si.allowed_characters ,\n" +
                        "                        si.comparable_characters, si.restricted_adapter_flag,\n" +
                        "                        dp.origin_id ,dp.paper_no ,dp.group_id,\n" +
                        "                        dp.created_user_id, dp.tenant_id,dp.b_box\n" +
                        "                FROM sor_transaction.vqa_transaction dp\n" +
                        "                JOIN sor_meta.sor_item si ON si.sor_item_name = dp.sor_item_name\n" +
                        "                WHERE dp.origin_id IN ('INT-1') AND si.allowed_adapter ='ner'\n")
                .resultTable("sor_transaction.adapter_result_123456")

                .build();

        ActionExecutionAudit actionExecutionAudit=new ActionExecutionAudit();
        actionExecutionAudit.getContext().put("copro.text-validation.url","http://localhost:10190/copro/text-validation/patient");
        actionExecutionAudit.setProcessId(138980079308730208L);
        actionExecutionAudit.getContext().putAll(Map.ofEntries(Map.entry("read.batch.size","5"),
                Map.entry("consumer.API.count","1"),
                Map.entry("write.batch.size","5")));

        NerAdapterAction action=new NerAdapterAction(actionExecutionAudit,log,nerAdapter);
        action.execute();
    }



    @Test
    void tritonServer() throws Exception {
        NerAdapter nerAdapter= NerAdapter.builder()
                .name("ner")
                .condition(true)
                .resourceConn("intics_agadia_db_conn")
                .resultSet("\n" +
                        "            SELECT dp.sor_item_name as sor_key, dp.sor_question as question, dp.answer as input_value, '12' as weight,\n" +
                        "                        si.allowed_adapter , si.restricted_adapter ,'124567' as process_id,\n" +
                        "                        si.word_limit , si.word_threshold ,\n" +
                        "                        si.char_limit , si.char_threshold ,\n" +
                        "                        si.validator_threshold , si.allowed_characters ,\n" +
                        "                        si.comparable_characters, si.restricted_adapter_flag,\n" +
                        "                        dp.origin_id ,dp.paper_no ,dp.group_id,\n" +
                        "                        dp.created_user_id, dp.tenant_id,dp.b_box\n" +
                        "                FROM sor_transaction.vqa_transaction dp\n" +
                        "                JOIN sor_meta.sor_item si ON si.sor_item_name = dp.sor_item_name\n" +
                        "                WHERE dp.origin_id IN ('INT-1') AND si.allowed_adapter ='ner'\n")
                .resultTable("sor_transaction.adapter_result_123456")

                .build();

        ActionExecutionAudit actionExecutionAudit=new ActionExecutionAudit();
        actionExecutionAudit.getContext().put("copro.text-validation.url","http://localhost:10190/copro/text-validation/patient");
        actionExecutionAudit.setProcessId(138980079308730208L);
        actionExecutionAudit.getContext().putAll(Map.ofEntries(Map.entry("read.batch.size","5"),
                Map.entry("ner.consumer.API.count","1"),
                Map.entry("write.batch.size","5")));

        NerAdapterAction action=new NerAdapterAction(actionExecutionAudit,log,nerAdapter);
        action.execute();
    }

}