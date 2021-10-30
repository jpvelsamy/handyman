package in.handyman.raven.lib;

import in.handyman.raven.lambda.process.LContext;
import in.handyman.raven.lambda.process.LambdaEngine;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.concurrent.CountDownLatch;

@Log4j2
@RequiredArgsConstructor
public class LambdaCallable implements Runnable {

    private final LContext lContext;
    private final CountDownLatch countDownLatch;

    @Override
    public void run() {
        try {
            LambdaEngine.start(lContext);
        } finally {
            if (countDownLatch != null) {
                countDownLatch.countDown();
            }
        }
    }
}
