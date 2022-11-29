package in.handyman.raven.lib;

import in.handyman.raven.lambda.action.ActionExecution;
import in.handyman.raven.lambda.action.IActionExecution;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.SetValue;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * Auto Generated By Raven
 */
@ActionExecution(
    actionName = "SetValue"
)
public class SetActionValueAction implements IActionExecution {
  private final ActionExecutionAudit action;

  private final Logger log;

  private final SetValue setValue;

  private final Marker aMarker;

  public SetActionValueAction(final ActionExecutionAudit action, final Logger log,
      final Object setValue) {
    this.setValue = (SetValue) setValue;
    this.action = action;
    this.log = log;
    this.aMarker = MarkerFactory.getMarker(" SetValue:"+this.setValue.getName());
  }

  @Override
  public void execute() throws Exception {
    action.getContext().put(setValue.getContextKey(), setValue.getContextValue());
  }

  @Override
  public boolean executeIf() throws Exception {
    return setValue.getCondition();
  }
}
