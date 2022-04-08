package in.handyman.process.onethread;

import in.handyman.server.ProcessAST;
import in.handyman.server.ProcessExecutor;

import static org.junit.jupiter.api.Assertions.fail;

class SendMailActionTest {

    @org.junit.jupiter.api.Test
    void execute() {
        String instanceName = "sendmail.process#1";
        var runtimeContext = ProcessAST.loadProcessAST(instanceName, "{}");
        try {
            ProcessExecutor.execute("in.handyman.process.onethread", runtimeContext);
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
}
