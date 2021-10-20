package in.handyman.raven.lib;

import in.handyman.raven.action.ActionExecution;
import in.handyman.raven.action.IActionExecution;
import in.handyman.raven.process.Context;
import in.handyman.raven.lib.model.DogLeg;
import in.handyman.raven.process.Process;
import in.handyman.raven.process.ProcessEngine;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.MarkerManager;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

/**
 * Auto Generated By Raven
 */
@ActionExecution(
        actionName = "DogLeg"
)
@Log4j2
public class DogLegAction implements IActionExecution {
    private final Context context;

    private final DogLeg dogLeg;

    private final MarkerManager.Log4jMarker aMarker;

    public DogLegAction(final Context context, final Object dogLeg) {
        this.dogLeg = (DogLeg) dogLeg;
        this.context = context;
        this.aMarker = new MarkerManager.Log4jMarker("DogLeg");
        this.context.getDetailMap().putPOJO("context", dogLeg);
    }

    @Override
    public void execute() throws Exception {
        var processList = dogLeg.getProcessList();
        log.info(aMarker," id: {}, name: {}", context.getLambdaId(), dogLeg.getName());
        var countDownLatch = new CountDownLatch(processList.size());
        var inheritContext = Objects.equals(dogLeg.getInheritContext(), "true");
        var executor = Executors.newWorkStealingPool();
        final Map<String, String> config = inheritContext ? context.getContext() : Collections.emptyMap();
        processList.forEach(startProcess -> {
            var processName = startProcess.getName();
            var fileRelativePath = startProcess.getTarget();
            final Process process = ProcessEngine.newInstanceProcess(fileRelativePath, processName, context.getProcessId(), config);
            var processWorker = new LambdaCallable(process, countDownLatch);
            executor.submit(processWorker);
        });
        countDownLatch.await();
    }

    @Override
    public boolean executeIf() throws Exception {
        return dogLeg.getCondition();
    }
}
