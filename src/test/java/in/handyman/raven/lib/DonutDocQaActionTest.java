package in.handyman.raven.lib;

import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.DonutDocQa;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class DonutDocQaActionTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    void execute() throws Exception {

        final DonutDocQa build = DonutDocQa.builder()
                .condition(true)
                .name("Test Donut DocQa")
                .forkBatchSize("3")
                .inputFilePath("/data/2/HUMANA_FORM_1_1.JPG - 348907/preprocess/autorotation/auto-rotation/5238f1aa-8e74-11ed-8a9d-0242ac130005/Humana_Form_1_1.jpg")
                .questionSql("select question from sor_meta.synonym_question")
                .outputDir("/data/2/HUMANA_FORM_1_1.JPG - 348907/preprocess/autorotation/auto-rotation/5238f1aa-8e74-11ed-8a9d-0242ac130005/")
                .resourceConn("intics_agadia_db_conn")
                .build();


        final ActionExecutionAudit action = ActionExecutionAudit.builder()
                .build();
        action.setRootPipelineId(11011L);
        action.getContext().put(DonutDocQaAction.ATTRIBUTION_URL,"http://localhost:10189/copro/attribution/kvp-docnet");
        final DonutDocQaAction donutDocQaAction = new DonutDocQaAction(action, logger, build);
        donutDocQaAction.execute();

    }
}