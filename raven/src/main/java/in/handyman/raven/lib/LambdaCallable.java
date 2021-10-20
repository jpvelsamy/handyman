package in.handyman.raven.lib;

import in.handyman.raven.context.ProcessContext;
import in.handyman.raven.process.ProcessEngine;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

@Log4j2
@RequiredArgsConstructor
public class LambdaCallable implements Callable<ProcessContext> {

    private final String fileRelativePath;
    private final String targetProcess;
    private final Long parentProcessId;
    private final Map<String, String> config;

    private final CountDownLatch countDownLatch;


    @Override
    public ProcessContext call() throws Exception {
        final ProcessContext processContext;
        try {
            processContext = ProcessEngine.start(fileRelativePath, targetProcess,
                    parentProcessId, config);
            log.info(processContext);
        } finally {
            if (countDownLatch != null) {
                countDownLatch.countDown();
            }
        }
        return processContext;
    }
}
