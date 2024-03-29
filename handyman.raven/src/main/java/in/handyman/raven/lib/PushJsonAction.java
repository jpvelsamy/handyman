package in.handyman.raven.lib;

import in.handyman.raven.lambda.action.ActionExecution;
import in.handyman.raven.lambda.action.IActionExecution;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.PushJson;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * Auto Generated By Raven
 */
@ActionExecution(
        actionName = "PushJson"
)
public class PushJsonAction implements IActionExecution {
    private final ActionExecutionAudit actionExecutionAudit;

    private final Logger log;

    private final PushJson pushJson;

    private final Marker aMarker;


    public PushJsonAction(final ActionExecutionAudit actionExecutionAudit, final Logger log, final Object pushJson) {
        this.pushJson = (PushJson) pushJson;
        this.actionExecutionAudit = actionExecutionAudit;
        this.log = log;
        this.aMarker = MarkerFactory.getMarker(" PushJson:" + this.pushJson.getName());
    }

    @Override
    public void execute() throws Exception {
        if (pushJson.getValue() != null) {
            log.info(aMarker, "{}", pushJson);
            actionExecutionAudit.getContext().put(pushJson.getKey(), pushJson.getValue().toString());
        }
    }

    @Override
    public boolean executeIf() throws Exception {
        return pushJson.getCondition();
    }
}
