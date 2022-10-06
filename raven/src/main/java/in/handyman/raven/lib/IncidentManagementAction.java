package in.handyman.raven.lib;

import in.handyman.raven.lambda.action.ActionExecution;
import in.handyman.raven.lambda.action.IActionExecution;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.IncidentManagement;
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
    actionName = "IncidentManagement"
)
public class IncidentManagementAction implements IActionExecution {
  private final ActionExecutionAudit action;

  private final Logger log;

  private final IncidentManagement incidentManagement;

  private final Marker aMarker;

  public IncidentManagementAction(final ActionExecutionAudit action, final Logger log,
      final Object incidentManagement) {
    this.incidentManagement = (IncidentManagement) incidentManagement;
    this.action = action;
    this.log = log;
    this.aMarker = MarkerFactory.getMarker(" IncidentManagement:"+this.incidentManagement.getName());
  }

  @Override
  public void execute() throws Exception {
  }

  @Override
  public boolean executeIf() throws Exception {
    return incidentManagement.getCondition();
  }
}
