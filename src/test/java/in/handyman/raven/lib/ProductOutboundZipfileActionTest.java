package in.handyman.raven.lib;

import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.ProductOutboundZipFile;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Map;

@Slf4j
class ProductOutboundZipfileActionTest {

    @Test
    public void unitTestCase() throws Exception {
        ProductOutboundZipFile productOutboundZipfile = ProductOutboundZipFile.builder()
                .name("product outbound zip file details")
                .outputDir("/home/anandh.andrews@zucisystems.com/intics-workspace/testing/output")
                .resultTable("product_outbound.product_outbound_zip_file_details")
                .processId("1234567")
                .resourceConn("intics_zio_db_conn")
                .querySet("SELECT origin_id, root_pipeline_id, group_id, process_id, cleaned_pdf_path, origin_pdf_path, product_json, kvp_response, table_response, tenant_id,file_name\n" +
                        "FROM product_outbound.product_outbound_zip_file_input;")
                .condition(true)
                .build();

        ActionExecutionAudit actionExecutionAudit=new ActionExecutionAudit();

        actionExecutionAudit.getContext().putAll(Map.ofEntries(
                Map.entry("read.batch.size","5"),
                Map.entry("gen_group_id.group_id","5"),
                Map.entry("consumer.API.count","1"),
                Map.entry("write.batch.size","5")));

        ProductOutboundZipfileAction productOutboundZipfileAction=new ProductOutboundZipfileAction(actionExecutionAudit,log,productOutboundZipfile);
        productOutboundZipfileAction.execute();
    }


}