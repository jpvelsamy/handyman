package in.handyman.raven.lib;

import in.handyman.raven.core.context.ActionContext;
import in.handyman.raven.exception.AbortException;
import in.handyman.raven.lambda.LambdaAutowire;
import in.handyman.raven.lambda.LambdaExecution;
import in.handyman.raven.lib.model.Abort;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.MarkerManager;

/**
 * Auto Generated By Raven
 */
@LambdaAutowire(lambdaName = "Abort")
@Log4j2
public class AbortAction implements LambdaExecution {

    private final ActionContext actionContext;
    private final Abort context;
    private final MarkerManager.Log4jMarker aMarker;

    public AbortAction(final ActionContext actionContext, final Object context) {
        this.context = (Abort) context;
        this.actionContext = actionContext;
        this.aMarker = new MarkerManager.Log4jMarker("Abort");
        this.actionContext.getDetailMap().putPOJO("context", context);
    }

    @Override
    public void execute() {
        log.info(aMarker, "Abort action id#{}, name#{}, calledProcess#{}, message#{}", actionContext.getLambdaId(), actionContext.getName(),
                actionContext.getProcessName(), context.getValue());
        throw new AbortException(context.getValue());
    }

    @Override
    public boolean executeIf() {
        final Boolean condition = context.getCondition();
        log.info(aMarker, "Completed evaluation to execute id#{} actionId#{}, name#{}, expression#{}",
                actionContext.getProcessId(), actionContext.getLambdaId(),
                actionContext.getName(), condition);
        actionContext.getDetailMap().put("condition-output", condition);
        return condition;
    }
}