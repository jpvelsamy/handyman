package in.handyman.raven.lib.tritonServerTest;

import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.DataExtraction;
import in.handyman.raven.lib.DataExtractionAction;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Map;

@Slf4j
class DataExtractionActionTest {
    @Test
    void dataExtraction() throws Exception {
        DataExtraction dataExtraction= DataExtraction.builder()
                .name("data extraction after copro optimization")
                .resourceConn("intics_agadia_db_conn")
                .condition(true)
                .processId("138980184199100180")
                .resultTable("data_extraction")
                .querySet("select origin_id,group_id,processed_file_path as file_path,paper_no,tenant_id,template_id,process_id from info.paper_itemizer where status='COMPLETED' and origin_id='INT-1';;")
                .build();
        ActionExecutionAudit actionExecutionAudit=new ActionExecutionAudit();
        actionExecutionAudit.getContext().put("copro.data-extraction.url","http://localhost:10182/copro/preprocess/text-extraction");
        actionExecutionAudit.setProcessId(138980079308730208L);
        actionExecutionAudit.getContext().putAll(Map.ofEntries(Map.entry("read.batch.size","5"),
                Map.entry("okhttp.client.timeout","20"),
                Map.entry("consumer.API.count","1"),
                Map.entry("write.batch.size","5")));
        DataExtractionAction dataExtractionAction=new DataExtractionAction(actionExecutionAudit,log,dataExtraction);
        dataExtractionAction.execute();

    }


    @Test
    void tritonServer() throws Exception {
        DataExtraction dataExtraction= DataExtraction.builder()
                .name("data extraction after copro optimization")
                .resourceConn("intics_agadia_db_conn")
                .condition(true)
                .processId("138980184199100180")
                .resultTable("info.data_extraction")
                .querySet("select a.process_id, a.tenant_id, a.template_id, a.group_id, a.origin_id, a.paper_no, a.processed_file_path as file_path,b.root_pipeline_id,c.template_name")
                .build();
        ActionExecutionAudit actionExecutionAudit=new ActionExecutionAudit();
        actionExecutionAudit.getContext().put("copro.data-extraction.url","http://localhost:8300/v2/models/text-extractor-service/versions/1/infer");
        actionExecutionAudit.setProcessId(138980079308730208L);
        actionExecutionAudit.getContext().putAll(Map.ofEntries(Map.entry("read.batch.size","5"),
                Map.entry("okhttp.client.timeout","20"),
                Map.entry("text.extraction.consumer.API.count","1"),
                Map.entry("write.batch.size","5")));
        DataExtractionAction dataExtractionAction=new DataExtractionAction(actionExecutionAudit,log,dataExtraction);
        dataExtractionAction.execute();

    }


}