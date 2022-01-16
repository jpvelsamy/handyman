package in.handyman.raven.lib;

import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.action.ActionExecution;
import in.handyman.raven.lambda.action.IActionExecution;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lambda.process.LambdaEngine;
import in.handyman.raven.lib.model.Consumer;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Auto Generated By Raven
 */
@ActionExecution(
        actionName = "Consumer"
)
public class ConsumerAction implements IActionExecution {

    private final ActionExecutionAudit actionExecutionAudit;
    private final Logger log;
    private final Consumer consumer;
    private final Marker aMarker;

    public ConsumerAction(final ActionExecutionAudit actionExecutionAudit, final Logger log, final Object consumer) {
        this.consumer = (Consumer) consumer;
        this.actionExecutionAudit = actionExecutionAudit;
        this.log = log;
        this.aMarker = MarkerFactory.getMarker(" Consumer:" + this.consumer.getName());
    }

    @Override
    public void execute() {
        final String event = consumer.getEvent();
        while (true) {

            final List<Map<String, Object>> maps = consumer.getSource().get()
                    .withHandle(handle -> handle.createQuery(event)
                            .mapToMap().list());
            final List<List<ActionCallable>> actionCallableList = new ArrayList<>();

            if (maps.isEmpty() && consumer.isCompleted()) {
                break;
            }

            if (maps.isEmpty() && consumer.isStandalone()) {
                break;
            }

            for (var node : maps) {
                try {
                    final Map<String, String> context = new HashMap<>(actionExecutionAudit.getContext());
                    var map = new HashMap<String, String>();

                    node.forEach((s, o) -> map.put("payload." + s, String.valueOf(o)));
                    context.putAll(map);
                    Optional.ofNullable(consumer.getActions()).filter(x -> !x.isEmpty())
                            .ifPresent(actionContexts -> {
                                final List<ActionCallable> collect = actionContexts.stream()
                                        .map(actionContext -> {
                                            var vAction = LambdaEngine.getAction(consumer.getName(), actionExecutionAudit);
                                            vAction.setContext(context);
                                            return new ActionCallable(actionContext, vAction, null);
                                        }).collect(Collectors.toList());
                                actionCallableList.add(collect);
                            });
                } catch (Exception e) {
                    log.error("Error at payload implementation", e);
                }
            }


            if (!actionCallableList.isEmpty()) {
                final Integer count = Optional.ofNullable(consumer.getThreadCount()).map(Integer::valueOf).orElse(1);
                var executor = Executors.newWorkStealingPool(count);
                final CountDownLatch downLatch = new CountDownLatch(actionCallableList.size());
                actionCallableList.forEach(actionCallables -> executor.submit(() -> {
                    actionCallables.forEach(ActionCallable::run);
                    downLatch.countDown();
                }));
                log.info("submitted all task to executors");
                try {
                    downLatch.await();
                } catch (InterruptedException e) {
                    throw new HandymanException("Failed to execute the consumer", e);
                }
                log.info("completed all task to executors");
            }


        }

    }

    public ActionExecutionAudit getAction() {
        return actionExecutionAudit;
    }

    @Override
    public boolean executeIf() {
        return consumer.getCondition();
    }
}
