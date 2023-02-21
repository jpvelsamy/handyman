//package in.handyman.raven.lib;
//
//import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//
//@Slf4j
//class BlankPageRemoverActionTest {
//
//
//    @Test
//            public void setBlankPageRemover() throws Exception {
//        BlankPageRemover blankPageRemover= BlankPageRemover.builder()
//                .processId(String.valueOf(1234567890))
//                .condition(true)
//                .name("blank page remover")
//                .outputDir("/home/anandh.andrews@zucisystems.com/W-space/pr1-lambdas/agadia/OCR Sample Forms/")
//                .querySet("select a.origin_id, a.group_id,a.tenant_id,a.filePath,a.document_id,'${init_process_id.process-id}' as process_id,\"${gen_directory.denoise_dest_path}\" as outputDir\n" +
//                        "\t\t\t\tfrom preprocess.preprocess_payload_queue a where a.status='INPROGRESS'")
//                .resourceConn("intics_agadia_db_conn")
//                .build();
//
//        final ActionExecutionAudit action = ActionExecutionAudit.builder().build();
//        action.setRootPipelineId(11011L);
//        action.getContext().put("copro.blank-page-remover.url", "http://localhost:10189/copro/denoise/blank-page-remover");
//
//        final BlankPageRemoverAction BlankPageRemoverAction = new BlankPageRemoverAction(action, log, blankPageRemover);
//        BlankPageRemoverAction.execute();
//    }
//
//
//}