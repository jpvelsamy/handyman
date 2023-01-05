package in.handyman.raven.lib;

import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lambda.process.LambdaEngine;
import in.handyman.raven.lib.model.FtpsUpload;
import org.junit.jupiter.api.Test;

class FtpsUploadActionTest {

    private final FtpsUploadAction ftpsUploadAction;

    {
        var actionExecutionAudit = ActionExecutionAudit.builder().actionName("FTP UPLOAD").build();
        ftpsUploadAction = new FtpsUploadAction(actionExecutionAudit,
                LambdaEngine.getLogger(actionExecutionAudit),
                FtpsUpload.builder()
                        .host("")
                        .userName("")
                        .password("")
                        .port("21")
                        .sessionTimeOut("1200")
                        .sourceFile("/home/manikandan.tm@zucisystems.com/Workspace/ftp_check/upload/source")
                        .destDir("/home/ubuntu/zftp1/ftp_test/abcd")
                        .name("FTP file upload")
                        .condition(true)
                        .build());
    }

    @Test
    void execute() throws Exception {
        ftpsUploadAction.execute();
    }

    @Test
    void uploadDirectory() {
    }

    @Test
    void uploadSingleFile() {
    }
}