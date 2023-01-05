package in.handyman.raven.lib;

import in.handyman.raven.lambda.doa.audit.PipelineExecutionAudit;
import in.handyman.raven.lambda.process.LContext;
import in.handyman.raven.lambda.process.LambdaEngine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

@Slf4j
@RequiredArgsConstructor
public class LambdaCallable implements Callable<PipelineExecutionAudit> {

    //TODO move all the context generation part into a method
    private final LContext lContext;
    private final CountDownLatch countDownLatch;

    @Override
    public PipelineExecutionAudit call() {
        try {
            return LambdaEngine.start(lContext);
        } catch (Exception e) {
            log.trace(e.getMessage(), e);
            throw e;
        } finally {
            if (countDownLatch != null) {
                countDownLatch.countDown();
            }
        }
    }
}
