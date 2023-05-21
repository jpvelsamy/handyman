package in.handyman.raven.lib;

import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.TemplateDetection;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class TemplateDetectionActionTest {

    @Test
    public void detectionAction() throws Exception {

        TemplateDetection templateDetection=TemplateDetection.builder()
                .condition(true)
                .name("template detection")
                .coproUrl("http://localhost:10189/copro/attribution/kvp-printed-old")
                .inputTable("info.auto_rotation")
                .ouputTable("macro.template_detection_response_12345")
                .resourceConn("intics_agadia_db_conn")
                .processId("12345")
                .querySet("select  ar.origin_id ,ar.paper_no ,ar.group_id ,ar.processed_file_path as file_path, ar.tenant_id\n" +
                        "\t\t\t\t,ar.template_id ,ar.process_id ,ar.root_pipeline_id ,string_agg(sq.question,',')  as questions\n" +
                        "\t\t\t\tfrom info.auto_rotation ar\n" +
                        "\t\t\t\tjoin sor_meta.sor_tsynonym st on 1=1\n" +
                        "\t\t\t\tjoin sor_meta.sor_question sq on st.sor_synonym_id =sq.sor_synonym_id\n" +
                        "\t\t\t\twhere st.synonym ='Template Name' and ar.status ='COMPLETED' and ar.group_id='1'\n" +
                        "\t\t\t\tgroup by ar.origin_id ,ar.paper_no ,ar.group_id ,ar.processed_file_path , ar.tenant_id ,ar.template_id ,ar.process_id ,ar.root_pipeline_id ;\n")
                                                                                        .build();

        ActionExecutionAudit actionExecutionAudit=new ActionExecutionAudit();
        actionExecutionAudit.getContext().put("copro.data-extraction.url","http://localhost:10182/copro/preprocess/text_extraction");
        actionExecutionAudit.setProcessId(138980079308730208L);
        actionExecutionAudit.getContext().putAll(Map.ofEntries(Map.entry("read.batch.size","5"),
                Map.entry("consumer.API.count","1"),
                Map.entry("write.batch.size","5")));

        TemplateDetectionAction templateDetectionAction=new TemplateDetectionAction(actionExecutionAudit,log,templateDetection);
        templateDetectionAction.execute();
    }
}