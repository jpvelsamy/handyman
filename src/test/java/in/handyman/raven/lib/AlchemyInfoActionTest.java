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
                .tenantId("1")
                .condition(true)
                .name("alchemy info action")
                .querySet("SELECT so.tenant_id::bigint, so.origin_id, ampq.root_pipeline_id, so.group_id, at.file_path from info.source_of_origin so join info.asset at on so.file_id = at.file_id\n" +
                        "            join alchemy_migration.alchemy_migration_payload_queue ampq on ampq.origin_id = so.origin_id;")
                .resourceConn("intics_agadia_db_conn")
                .token("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJVc2VyIERldGFpbHMiLCJpc3MiOiJJbnRpY3NBSSBBbGNoZW15IiwiZXhwIjoxNjg5MzU1MjA2LCJpYXQiOjE2ODkzMTIwMDYsImVtYWlsIjoiZGVtb0BpbnRpY3MuY29tIn0.0AUSfuHkA1iEGHr0qQ0EYl2zdJci0ZhOFWylE1wdHxM")
                .build();
        ActionExecutionAudit actionExecutionAudit=new ActionExecutionAudit();

        actionExecutionAudit.getContext().put("alchemy.origin.upload.url","http://localhost:8189/alchemy/api/v1/info/origin/upload");
        actionExecutionAudit.getContext().put("alchemyAuth.token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJVc2VyIERldGFpbHMiLCJpc3MiOiJJbnRpY3NBSSBBbGNoZW15IiwiZXhwIjoxNjg5MzU1MjA2LCJpYXQiOjE2ODkzMTIwMDYsImVtYWlsIjoiZGVtb0BpbnRpY3MuY29tIn0.0AUSfuHkA1iEGHr0qQ0EYl2zdJci0ZhOFWylE1wdHxM");
        actionExecutionAudit.getContext().put("alchemyAuth.tenantId","1");
        actionExecutionAudit.getContext().put("gen_group_id.group_id","1");
        actionExecutionAudit.getContext().put("read.batch.size","1");

        AlchemyInfoAction alchemyInfoAction = new AlchemyInfoAction(actionExecutionAudit, log, alchemyInfo);
        alchemyInfoAction.execute();
    }
}