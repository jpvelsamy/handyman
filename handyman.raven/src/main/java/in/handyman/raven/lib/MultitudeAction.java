package in.handyman.raven.lib;

import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.action.ActionExecution;
import in.handyman.raven.lambda.action.IActionExecution;
import in.handyman.raven.lambda.doa.Action;
import in.handyman.raven.lambda.doa.ExecutionGroup;
import in.handyman.raven.lambda.process.LambdaEngine;
import in.handyman.raven.lib.model.Multitude;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Auto Generated By Raven
 */
@ActionExecution(
        actionName = MultitudeAction.MULTITUDE
)
public class MultitudeAction implements IActionExecution {

    protected static final String MULTITUDE = "Multitude";
    private final Action action;
    private final Logger log;
    private final Multitude multitude;
    private final Marker aMarker;

    public MultitudeAction(final Action action, final Logger log, final Object multitude) {
        this.multitude = (Multitude) multitude;
        this.action = action;
        this.log = log;
        this.aMarker = MarkerFactory.getMarker(MULTITUDE);
    }

    @Override
    public void execute() throws Exception {
        log.info(aMarker, "given {}", multitude);
        Optional.ofNullable(multitude.getActions()).filter(x -> !x.isEmpty())
                .ifPresent(actionContexts -> {

                    final boolean isParallel = Optional.ofNullable(multitude.getOn()).filter(s -> Objects.equals("PARALLEL", s)).isPresent();
                    final int threadCount = Optional.ofNullable(multitude.getWriteThreadCount()).map(Integer::parseInt).orElse(0);
                    var countDown = new CountDownLatch(actionContexts.size());
                    log.info("Multitude has been initialized in a {} mode with threadcount of {} and countdown of {}", isParallel, threadCount, countDown);
                    final Set<ActionCallable> collect = actionContexts.stream().map(actionContext -> {
                        var vAction = Action.builder()
                                .pipelineId(action.getPipelineId())
                                .executionGroupId(ExecutionGroup.ACTION.getId())
                                .actionName(multitude.getName())
                                .build();
                        vAction.setContext(action.getContext());
                        LambdaEngine.toAction(vAction, action);
                        return new ActionCallable(actionContext, vAction, countDown);
                    }).collect(Collectors.toSet());

                    if (isParallel) {
                        log.info("Execution has been started in a Parallel with thread count of {}", threadCount);
                        var executor = threadCount != 0 ? Executors.newWorkStealingPool(threadCount) : Executors.newWorkStealingPool();
                        collect.forEach(executor::submit);
                        log.info("Completed Execution of multitude");
                    } else {
                        log.info("Execution started in a sequential manner");
                        collect.forEach(ActionCallable::run);
                        log.info("Completed execution of multitude");
                    }

                    try {
                        countDown.await();
                    } catch (InterruptedException e) {
                        throw new HandymanException("Failed to execute", e);
                    }


                });

    }

    @Override
    public boolean executeIf() throws Exception {
        return multitude.getCondition();
    }
}
