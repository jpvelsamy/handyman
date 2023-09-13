package in.handyman.raven.lib;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.HwDetection;
import in.handyman.raven.lib.model.hwDectection.HwDetectionAction;
import org.junit.jupiter.api.Test;

import static org.reflections.Reflections.log;
class HwDetectionActionTest {
    @Test
    void execute() throws Exception {
        HwDetection hwDetection=HwDetection.builder()
                .name("paper classification macro")
                .condition(true)
                .resourceConn("intics_agadia_db_conn")
                .directoryPath("/home/sanjeeya.v@zucisystems.com/workspace/updated_jupyter/output")
                .modelPath("/home/sanjeeya.v@zucisystems.com/Documents/agadia/pr2/v1/hw_detection_v2.0.pth")
                .querySet("SELECT 'INT-1' as originId, '1234567' as preprocessedFileId, 1 as paperNo,'/home/sanjeeya.v@zucisystems.com/workspace/updated_jupyter/Humana_Form_1_1.jpg'  as filePath, '123456' as createdUserId, '123456y' as lastUpdatedUserId, '12345643' as tenantId,-1 as templateId, 90.00 as modelScore, 123 as modelRegistryId;")
                .build();
        final ActionExecutionAudit action = ActionExecutionAudit.builder()
                .build();
        action.setRootPipelineId(11011L);
        action.getContext().put("copro.hw-detection.url", "http://localhost:10189/copro/paper-classification/hw-detection");
        action.getContext().put("read.batch.size", "1");
        action.getContext().put("consumer.API.count", "1");
        action.getContext().put("write.batch.size", "1");

        HwDetectionAction hwDetectionAction=new HwDetectionAction(action,log,hwDetection);
        hwDetectionAction.execute();
    }
}