package in.handyman.raven.lib;

import in.handyman.raven.process.Process;
import in.handyman.raven.process.ProcessEngine;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

@Log4j2
@RequiredArgsConstructor
public class LambdaCallable implements Callable<Process> {

    private final Process process;
    private final CountDownLatch countDownLatch;


    @Override
    public Process call() throws Exception {
        try {
            ProcessEngine.run(process);
        } finally {
            if (countDownLatch != null) {
                countDownLatch.countDown();
            }
        }
        return process;
    }
}
