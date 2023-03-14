package in.handyman.raven.lib;

import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.PaperItemization;
import in.handyman.raven.lib.model.PaperItemizer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
class PaperItemizerActionTest {

    @Test
    void paperItemizerTest() throws Exception {
        PaperItemizer paperItemizer= PaperItemizer.builder()
                .name("Data extraction macro test after copro optimization")
                .resourceConn("intics_agadia_db_conn")
                .condition(true)
                .processId("138980184199100180")
                .resultTable("info.")
                .outputDir("/home/anandh.andrews@zucisystems.com/W-space/pr1-lambdas/agadia/output/")
                .querySet("  SELECT a.origin_id, a.group_id ,c.file_path,b.tenant_id,b.document_id as template_id,a.producer_process_id as process_id \n" +
                        " from info.preprocess_payload_queue a \n" +
                        " join info.source_of_origin b on a.origin_id=b.origin_id  \n" +
                        " join info.asset c on b.file_id=c.file_id  \n" +
                        " where a.status='IN_PROGRESS'\n")
                .build();
        ActionExecutionAudit actionExecutionAudit=new ActionExecutionAudit();

        actionExecutionAudit.getContext().putAll(Map.ofEntries(Map.entry("copro.paper-itemizer.url","http://localhost:10181/copro/denoise/paperiterator"),
                Map.entry("read.batch.size","5"),
                Map.entry("consumer.API.count","1"),
                Map.entry("write.batch.size","5")));

        PaperItemizerAction paperItemizerAction=new PaperItemizerAction(actionExecutionAudit,log,paperItemizer);
        paperItemizerAction.execute();
    }

}