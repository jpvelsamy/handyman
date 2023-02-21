package in.handyman.raven.lib;

import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.AutoRotation;
import in.handyman.raven.lib.model.BlankPageRemover;
import in.handyman.raven.lib.model.DataExtraction;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class PreprocessTest {

    @Test
    void blankPageRemovalExecute() throws Exception {

        final BlankPageRemover blankPageRemover = BlankPageRemover.builder()
                .name("blankpage removal test")
                .resourceConn("intics_agadia_db_conn")
                .processId("138962483503220136")
                .querySet("SELECT a.*,b.file_path\n" +
                        "FROM info.source_of_origin a join info.asset b on a.file_id=b.file_id;\n")
                .outputDir("/home/anandh.andrews@zucisystems.com/W-space/pr1-lambdas/agadia/agadia_output/")
                .condition(true)
                .forkBatchSize("5")
                .build();


        final ActionExecutionAudit action = ActionExecutionAudit.builder().build();
        action.setRootPipelineId(11011L);
        action.getContext().put("copro.blank-page-remover.url", "http://localhost:10189/copro/denoise/blank-page-remover");

        final BlankPageRemoverAction BlankPageRemoverAction = new BlankPageRemoverAction(action, log, blankPageRemover);
        BlankPageRemoverAction.execute();
    }

    @Test
    void autoRotationExecute() throws Exception {

       final AutoRotation autoRotation= AutoRotation.builder()
               .name("Auto rotation")
               .outputDir("/home/anandh.andrews@zucisystems.com/W-space/pr1-lambdas/agadia/agadia_output/")
               .processId("138962483503220136")
               .querySet("SELECT id, group_id, origin_id, processed_file_path, created_on\n" +
                       "FROM info.blank_page_removal_138962483503220136;\n")
               .condition(true)
               .build()


        final ActionExecutionAudit action = ActionExecutionAudit.builder().build();
        action.setRootPipelineId(11011L);
        action.getContext().put("copro.autorotation.url", "http://localhost:10189/copro/denoise/autorotation");

        final AutoRotationAction AutoRotationAction = new AutoRotationAction(action, log, autoRotation);
        AutoRotationAction.execute();
    }

    @Test
    void dataExtractionExecute() throws Exception {

        final DataExtraction build = DataExtraction.builder()
                .condition(true)
                .name("Test DataExtraction")
                .querySet("SELECT ar.id, ar.group_id, ar.origin_id, ar.processed_file_path as processedFilePath, created_on\n" + "FROM info.auto_rotation_1234 ar;").resourceConn("intics_agadia_db_conn")
                .build();


        final ActionExecutionAudit action = ActionExecutionAudit.builder().build();
        action.setRootPipelineId(11011L);
        action.getContext().put("copro.data-extraction.url", "http://localhost:10189/copro/preprocess/text_extraction");

        final DataExtractionAction DataExtractionAction = new DataExtractionAction(action, log, build);
        DataExtractionAction.execute();
    }
}

