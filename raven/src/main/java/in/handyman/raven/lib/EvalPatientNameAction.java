package in.handyman.raven.lib;

import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.action.ActionExecution;
import in.handyman.raven.lambda.action.IActionExecution;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.agadia.adapters.PatientNameAdapter;
import in.handyman.raven.lib.interfaces.ScalarEvaluationInterface;
import in.handyman.raven.lib.model.AgadiaAdapter;
import in.handyman.raven.lib.model.EvalPatientName;

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
        actionName = "EvalPatientName"
)
public class EvalPatientNameAction implements IActionExecution {
  private final ActionExecutionAudit action;

  private final Logger log;

  private final EvalPatientName evalPatientName;

  private final Marker aMarker;

  public EvalPatientNameAction(final ActionExecutionAudit action, final Logger log,
                               final Object evalPatientName) {
    this.evalPatientName = (EvalPatientName) evalPatientName;
    this.action = action;
    this.log = log;
    this.aMarker = MarkerFactory.getMarker(" EvalPatientName:" + this.evalPatientName.getName());
  }

  @Override
  public void execute() throws Exception {
    try {
      ScalarEvaluationInterface patientAdapter = new PatientNameAdapter();
      AgadiaAdapter adapter = AgadiaAdapter.builder()
              .wordCountLimit(evalPatientName.getWordCountLimit())
              .wordCountThreshold(evalPatientName.getWordCountThreshold())
              .charCountLimit(evalPatientName.getCharCountLimit())
              .charCountThreshold(evalPatientName.getCharCountThreshold())
              .validatorDetail(evalPatientName.getNerCoproApi())
              .validatorThreshold(evalPatientName.getNerApiThreshold()).build();
      int score = patientAdapter.getConfidenceScore(evalPatientName.getPatientName(), adapter);
      action.getContext().put(evalPatientName.getName().concat(".score"), String.valueOf(score));
    } catch (Exception ex) {
      action.getContext().put(evalPatientName.getName().concat(".error"), "true");
      log.info(aMarker, "The Exception occurred ", ex);
      throw new HandymanException("Failed to execute", ex);
    }
  }

  @Override
  public boolean executeIf() throws Exception {
    return evalPatientName.getCondition();
  }
}
