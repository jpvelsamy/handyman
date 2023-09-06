package in.handyman.raven.lib;

import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.TableExtraction;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Map;

@Slf4j
class TableExtractionActionTest {

    @Test
    void tableExtractionTest() throws Exception {
        TableExtraction tableExtraction= TableExtraction.builder()
                .name("Text extraction macro test after copro optimization")
                .resourceConn("intics_agadia_db_conn")
                .condition(true)
                .processId("999")
                .resultTable("table-extraction.")
                .outputDir("/home/logesh.b@zucisystems.com/workspace/dev/intics-agadia/pipeline/data/output/")
                .querySet("  SELECT a.origin_id, a.group_id ,c.file_path,b.tenant_id,b.document_id as template_id,a.producer_process_id as process_id,a.root_pipeline_id\n" +
                        "                  from preprocess.preprocess_payload_queue a\n" +
                        "                  join info.source_of_origin b on a.origin_id=b.origin_id\n" +
                        "                   join info.asset c on b.file_id=c.file_id\n" +
                        "                   where a.status='IN_PROGRESS' and a.group_id='${group_id}';")
                .build();
        ActionExecutionAudit actionExecutionAudit=new ActionExecutionAudit();

        actionExecutionAudit.getContext().putAll(Map.ofEntries(Map.entry("copro.table-extraction.url","http://localhost:10194/copro/table-extraction"),
                Map.entry("read.batch.size","5"),
                Map.entry("consumer.API.count","1"),
                Map.entry("write.batch.size","5")));

        TableExtractionAction tableExtractionAction=new TableExtractionAction(actionExecutionAudit,log,tableExtraction);
        tableExtractionAction.execute();
    }

}