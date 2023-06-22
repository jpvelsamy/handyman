package in.handyman.raven.lib;

import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.AlchemyInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;


@Slf4j
class AlchemyInfoActionTest {

    @Test
    void execute() throws Exception {


        AlchemyInfo alchemyInfo = AlchemyInfo.builder()
                .tenantId("TNT-000001")
                .condition(true)
                .name("alchemy info action")
                .querySet("SELECT so.tenant_id, so.origin_id, so.root_pipeline_id, so.group_id, at.file_path from info.source_of_origin so join info.asset at on so.file_id = at.file_id where so.root_pipeline_id = 34567;")
                .resourceConn("intics_agadia_db_conn")
                .token("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJVc2VyIERldGFpbHMiLCJpc3MiOiJJbnRpY3NBSSBBbGNoZW15IiwiZXhwIjoxNjg3Mzg4NjUxLCJpYXQiOjE2ODczNDU0NTEsImVtYWlsIjoibWFuaWthbmRhbi50bUB6dWNpc3lzdGVtcy5jb20ifQ.kLxPnGea4L4Ytjrhyqd7gLHga3cRtn4xXzLQjakKH0s")
                .build();
        ActionExecutionAudit actionExecutionAudit=new ActionExecutionAudit();

        actionExecutionAudit.getContext().put("alchemy.origin.upload.url","http://localhost:8189/alchemy/api/v1/info/origin/upload");
        actionExecutionAudit.getContext().put("alchemyAuth.token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJVc2VyIERldGFpbHMiLCJpc3MiOiJJbnRpY3NBSSBBbGNoZW15IiwiZXhwIjoxNjg3NDYzMDU3LCJpYXQiOjE2ODc0MTk4NTcsImVtYWlsIjoibWFuaWthbmRhbi50bUB6dWNpc3lzdGVtcy5jb20ifQ.FXvWsBa7rGGBGWO9_HfOHg-CQOMEdMRds7JQCWgBrfo");
        actionExecutionAudit.getContext().put("alchemyAuth.tenantId","TNT-000001");
        actionExecutionAudit.getContext().put("gen_group_id.group_id","1");
        actionExecutionAudit.getContext().put("read.batch.size","1");

        AlchemyInfoAction alchemyInfoAction = new AlchemyInfoAction(actionExecutionAudit, log, alchemyInfo);
        alchemyInfoAction.execute();
    }
}