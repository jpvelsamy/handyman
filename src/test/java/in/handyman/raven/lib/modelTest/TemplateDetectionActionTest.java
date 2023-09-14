package in.handyman.raven.lib.modelTest;

import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.TemplateDetection;
import in.handyman.raven.lib.TemplateDetectionAction;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Map;

@Slf4j
class TemplateDetectionActionTest {

    @Test
    public void detectionAction() throws Exception {

        TemplateDetection templateDetection=TemplateDetection.builder()
                .condition(true)
                .name("template detection")
                .coproUrl("http://localhost:10193/copro/attribution/kvp-printed-old")
                .inputTable("info.auto_rotation")
                .ouputTable("macro.template_detection_response_12345")
                .resourceConn("intics_agadia_db_conn")
                .processId("12345")
                .querySet("select  ar.origin_id ,ar.paper_no ,ar.group_id ,ar.processed_file_path as file_path, ar.tenant_id\n" +
                        ",ar.template_id ,ar.process_id ,ar.root_pipeline_id ,array_agg(sq.question)  as questions\n" +
                        "from info.auto_rotation ar\n" +
                        "join sor_meta.sor_tsynonym st on 1=1\n" +
                        "join sor_meta.sor_question sq on st.synonym_id  =sq.synonym_id\n" +
                        "where st.synonym ='Template Name' and ar.status ='COMPLETED' and ar.group_id='19'\n" +
                        "group by ar.origin_id ,ar.paper_no ,ar.group_id ,ar.processed_file_path , ar.tenant_id \n" +
                        ",ar.template_id ,ar.process_id ,ar.root_pipeline_id ;")
                                                                                        .build();

        ActionExecutionAudit actionExecutionAudit=new ActionExecutionAudit();
        actionExecutionAudit.getContext().put("copro.data-extraction.url","http://localhost:10182/copro/preprocess/text_extraction");
        actionExecutionAudit.setProcessId(138980079308730208L);
        actionExecutionAudit.getContext().putAll(Map.ofEntries(Map.entry("read.batch.size","5"),
                Map.entry("consumer.API.count","1"),
                Map.entry("write.batch.size","5")));

        TemplateDetectionAction templateDetectionAction=new TemplateDetectionAction(actionExecutionAudit,  log,templateDetection);
        templateDetectionAction.execute();
    }


    @Test
    void tritonServer() throws Exception {
        TemplateDetection templateDetection=TemplateDetection.builder()
                .condition(true)
                .name("template detection")
                .coproUrl("http://localhost:10193/copro/attribution/kvp-printed-old")
                .inputTable("info.auto_rotation")
                .ouputTable("macro.template_detection_response_12345")
                .resourceConn("intics_agadia_db_conn")
                .processId("12345")
                .querySet("select  ar.origin_id ,ar.paper_no ,ar.group_id ,ar.processed_file_path as file_path, ar.tenant_id\n" +
                        ",ar.template_id ,ar.process_id ,ar.root_pipeline_id ,array_agg(sq.question)  as questions\n" +
                        "from info.auto_rotation ar\n" +
                        "join sor_meta.sor_tsynonym st on 1=1\n" +
                        "join sor_meta.sor_question sq on st.synonym_id  =sq.synonym_id\n" +
                        "where st.synonym ='Template Name' and ar.status ='COMPLETED' and ar.group_id='19'\n" +
                        "group by ar.origin_id ,ar.paper_no ,ar.group_id ,ar.processed_file_path , ar.tenant_id \n" +
                        ",ar.template_id ,ar.process_id ,ar.root_pipeline_id ;")
                .build();

        ActionExecutionAudit actionExecutionAudit=new ActionExecutionAudit();
        actionExecutionAudit.getContext().put("copro.data-extraction.url","http://localhost:10182/copro/preprocess/text_extraction");
        actionExecutionAudit.setProcessId(138980079308730208L);
        actionExecutionAudit.getContext().putAll(Map.ofEntries(Map.entry("read.batch.size","5"),
                Map.entry("consumer.API.count","1"),
                Map.entry("write.batch.size","5")));

        TemplateDetectionAction templateDetectionAction=new TemplateDetectionAction(actionExecutionAudit,  log,templateDetection);
        templateDetectionAction.execute();
    }
    }