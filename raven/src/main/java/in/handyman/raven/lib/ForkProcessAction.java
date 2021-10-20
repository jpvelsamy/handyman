package in.handyman.raven.lib;

import in.handyman.raven.action.Action;
import in.handyman.raven.action.IActionExecution;
import in.handyman.raven.context.ActionContext;
import in.handyman.raven.lib.model.ForkProcess;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.MarkerManager;

/**
 * Auto Generated By Raven
 */
@Action(
    actionName = "ForkProcess"
)
@Log4j2
public class ForkProcessAction implements IActionExecution {
  private final ActionContext actionContext;

  private final ForkProcess context;

  private final MarkerManager.Log4jMarker aMarker;

  public ForkProcessAction(final ActionContext actionContext, final Object context) {
    this.context = (ForkProcess) context;
    this.actionContext = actionContext;
    this.aMarker = new MarkerManager.Log4jMarker("ForkProcess");
    this.actionContext.getDetailMap().putPOJO("context", context);
  }

  @Override
  public void execute() throws Exception {
  }

  @Override
  public boolean executeIf() throws Exception {
    return false;
  }
}
