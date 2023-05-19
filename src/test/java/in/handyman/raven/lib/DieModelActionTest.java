package in.handyman.raven.lib;

import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.DieModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class DieModelActionTest {

    @Test
    public void
    testDieModel() throws Exception {

        DieModel dieModel=DieModel.builder()
                .name("DIE model testing")
                .condition(true)
                .outputDir("dir")
                .resourceConn("intics_agadia_db_conn")
                .forkBatchSize("1")
                .questionSql("   SELECT question,file_path, document_type as paperType FROM\n" +
                        "                macro.doc_impira_tqa_x_synonym_table_12345;")
                .responseAs("impira_attribution_response_12345")
                .build();
        ActionExecutionAudit actionExecutionAudit=new ActionExecutionAudit();
        actionExecutionAudit.getContext().put("copro.die-attribution.url","http://localhost:10182/copro/attribution/kvp-docnet");
        actionExecutionAudit.getContext().put("gen_group_id.group_id","1");
        actionExecutionAudit.setProcessId(138980079308730208L);
        actionExecutionAudit.getContext().putAll(Map.ofEntries(Map.entry("read.batch.size","5"),
                Map.entry("consumer.API.count","1"),
                Map.entry("write.batch.size","5")));

        DieModelAction dieModelAction=new DieModelAction(actionExecutionAudit,log,dieModel);
        dieModelAction.execute();
    }




}