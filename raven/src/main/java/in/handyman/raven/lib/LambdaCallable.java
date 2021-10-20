package in.handyman.raven.lib;

import in.handyman.raven.process.Process;
import in.handyman.raven.process.ProcessEngine;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

@Log4j2
@RequiredArgsConstructor
public class LambdaCallable implements Callable<Process> {

    private final String fileRelativePath;
    private final String targetProcess;
    private final Long parentProcessId;
    private final Map<String, String> context;

    private final CountDownLatch countDownLatch;


    @Override
    public Process call() throws Exception {
        final Process process;
        try {
            process = ProcessEngine.start(fileRelativePath, targetProcess,
                    parentProcessId, context);
            log.info(process);
        } finally {
            if (countDownLatch != null) {
                countDownLatch.countDown();
            }
        }
        return process;
    }
}
