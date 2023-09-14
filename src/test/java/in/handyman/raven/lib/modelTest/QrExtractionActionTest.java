package in.handyman.raven.lib.modelTest;

import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.QrExtraction;
import in.handyman.raven.lib.QrExtractionAction;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Map;

@Slf4j
public class QrExtractionActionTest {

    @Test
    void tritonServer() throws Exception {

        final QrExtraction build = QrExtraction.builder()
                .condition(true)
                .name("qr-extraction")
                .resourceConn("intics_agadia_db_conn")
                .outputTable("qr_extraction.qr_extraction_result_${init_process_id.process_id}")
                .querySet("\t\t\t\tSELECT asset.file_path,sot.origin_id,asset.file_id,sot.paper_no,sot.group_id,'${gen_id.root_pipeline_id}' as root_pipeline_id\n")
                .build();

        ActionExecutionAudit actionExecutionAudit = new ActionExecutionAudit();
        actionExecutionAudit.getContext().put("copro.autorotation.url", "/copro/preprocess/autorotation");
        actionExecutionAudit.setProcessId(138980079308730208L);
        actionExecutionAudit.getContext().putAll(Map.ofEntries(Map.entry("read.batch.size", "5"),
                Map.entry("consumer.API.count", "1"),
                Map.entry("write.batch.size", "5")));

      QrExtractionAction action1 = new QrExtractionAction(actionExecutionAudit, log, build);
        action1.execute();

    }
}


