package in.handyman.raven.lib.tritonServerTest;

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
                .processId("1234")
                .outputTable("qr_extraction.qr_extraction_result_1234")
                .querySet("SELECT '/data/output/pdf_to_image/SYNT_166838894_c1/SYNT_166838894_c1_1.jpg' as file_path, 'INT-1' as origin_id, 1 as file_id, 1 as paper_no, 1 as group_id, 1 as root_pipeline_id")
                .build();

        ActionExecutionAudit actionExecutionAudit = new ActionExecutionAudit();
        actionExecutionAudit.getContext().put("copro.qr-attribution.url", "http://192.168.10.245:8700/v2/models/qr-service/versions/1/infer");
        actionExecutionAudit.setProcessId(138980079308730208L);
        actionExecutionAudit.getContext().putAll(Map.ofEntries(Map.entry("read.batch.size", "5"),
                Map.entry("qr.consumer.API.count", "1"),
                Map.entry("write.batch.size", "5"),
                Map.entry("okhttp.client.timeout", "10")));


      QrExtractionAction action1 = new QrExtractionAction(actionExecutionAudit, log, build);
        action1.execute();

    }
}

