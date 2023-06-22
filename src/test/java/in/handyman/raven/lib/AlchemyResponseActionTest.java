package in.handyman.raven.lib;

import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.AlchemyResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class AlchemyResponseActionTest {

    @Test
    void execute() throws Exception {

        AlchemyResponse alchemyResponse = AlchemyResponse.builder()
                .name("alchemy response action")
                .token("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJVc2VyIERldGFpbHMiLCJpc3MiOiJJbnRpY3NBSSBBbGNoZW15IiwiZXhwIjoxNjg3NDYzMDU3LCJpYXQiOjE2ODc0MTk4NTcsImVtYWlsIjoibWFuaWthbmRhbi50bUB6dWNpc3lzdGVtcy5jb20ifQ.FXvWsBa7rGGBGWO9_HfOHg-CQOMEdMRds7JQCWgBrfo")
                .tenantId("TNT-000001")
                .querySet("SELECT bbox, alchemy_origin_id as origin_id, paper_no, tenant_id, root_pipeline_id, confidence_score, extracted_value, sor_item_name, synonym_id FROM outbound.alchemy_response where root_pipeline_id = 37957;")
                .resourceConn("intics_agadia_db_conn")
                .condition(true).build();
        ActionExecutionAudit actionExecutionAudit = new ActionExecutionAudit();
        actionExecutionAudit.getContext().put("alchemy.origin.valuation.url","http://localhost:8189/alchemy/api/v1/valuation/origin");
        actionExecutionAudit.getContext().put("alchemyAuth.token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJVc2VyIERldGFpbHMiLCJpc3MiOiJJbnRpY3NBSSBBbGNoZW15IiwiZXhwIjoxNjg3NDYzMDU3LCJpYXQiOjE2ODc0MTk4NTcsImVtYWlsIjoibWFuaWthbmRhbi50bUB6dWNpc3lzdGVtcy5jb20ifQ.FXvWsBa7rGGBGWO9_HfOHg-CQOMEdMRds7JQCWgBrfo");
        actionExecutionAudit.getContext().put("alchemyAuth.tenantId","TNT-000001");
        actionExecutionAudit.getContext().put("gen_group_id.group_id","1");
        actionExecutionAudit.getContext().put("read.batch.size","1");

        AlchemyResponseAction alchemyResponseAction = new AlchemyResponseAction(actionExecutionAudit, log, alchemyResponse);
        alchemyResponseAction.execute();

    }
}