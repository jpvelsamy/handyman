package in.handyman.raven.lib;

import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.DonutDocQa;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
class DonutDocQaActionTest {

    @Test
    void execute() throws Exception {

        final DonutDocQa build = DonutDocQa.builder()
                .condition(true)
                .name("Test Donut DocQa")
                .forkBatchSize("2")
                .questionSql("select '/data/2/HUMANA_FORM_1_1.JPG - 348907/preprocess/autorotation/auto-rotation/5238f1aa-8e74-11ed-8a9d-0242ac130005/Humana_Form_1_1.jpg' file_path,question from sor_meta.synonym_question")
                .outputDir("/data/2/HUMANA_FORM_1_1.JPG - 348907/preprocess/autorotation/auto-rotation/5238f1aa-8e74-11ed-8a9d-0242ac130005/")
                .resourceConn("intics_agadia_db_conn")
                .responseAs("result")
                .build();


        final ActionExecutionAudit action = ActionExecutionAudit.builder()
                .build();
        action.setRootPipelineId(11011L);
        action.getContext().put(DonutDocQaAction.ATTRIBUTION_URL,"http://localhost:10189/copro/attribution/kvp-docnet");
        final DonutDocQaAction donutDocQaAction = new DonutDocQaAction(action, log, build);
        donutDocQaAction.execute();

    }
}