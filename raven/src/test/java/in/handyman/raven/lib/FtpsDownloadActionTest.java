package in.handyman.raven.lib;

import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lambda.process.LambdaEngine;
import in.handyman.raven.lib.model.FtpsDownload;
import org.junit.jupiter.api.Test;

class FtpsDownloadActionTest {

    private final FtpsDownloadAction ftpsDownloadAction;

    {
        var actionExecutionAudit = ActionExecutionAudit.builder().actionName("FTP UPLOAD").build();
        ftpsDownloadAction = new FtpsDownloadAction(actionExecutionAudit,
                LambdaEngine.getLogger(actionExecutionAudit),
                FtpsDownload.builder()
                        .host("18.117.16.124")
                        .userName("zftpuser")
                        .password("Test123!@#")
                        .port("21")
                        .sessionTimeOut("1200")
                        .sourceFile("/home/ubuntu/zftp1/ftp_test/abcd")
                        .destDir("/home/manikandan.tm@zucisystems.com/Workspace/ftp_check/download/dest")
                        .name("FTP file Download")
                        .condition(true)
                        .build());
    }

    @Test
    void execute() throws Exception {
        ftpsDownloadAction.execute();
    }

    @Test
    void ftpDownloadDirectory() {
    }

    @Test
    void downloadSingleFile() {
    }
}