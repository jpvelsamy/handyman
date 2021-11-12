package in.handyman.raven.lib;

import in.handyman.raven.compiler.RavenParser;
import in.handyman.raven.lambda.doa.Action;
import in.handyman.raven.lambda.process.LambdaEngine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

@Slf4j
@RequiredArgsConstructor
public class ActionCallable implements Runnable {

    private final RavenParser.ActionContext actionContext;
    private final Action action;
    private final CountDownLatch countDownLatch;

    @Override
    public void run() {
        try {
            LambdaEngine.doAction(action, actionContext);
        } finally {
            if (countDownLatch != null) {
                countDownLatch.countDown();
            }
        }
    }
}
