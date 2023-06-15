package in.handyman.raven.lib;

import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.action.ActionExecution;
import in.handyman.raven.lambda.action.IActionExecution;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.adapters.AlphaNumericAdapter;
import in.handyman.raven.lib.interfaces.AdapterInterface;
import in.handyman.raven.lib.model.Alphanumericvalidator;
import in.handyman.raven.lib.model.Validator;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * Auto Generated By Raven
 */
@ActionExecution(
        actionName = "Alphanumericvalidator"
)
public class AlphanumericvalidatorAction implements IActionExecution {
    private final ActionExecutionAudit action;

    private final Logger log;

    private final Alphanumericvalidator alphanumericvalidator;

    private final Marker aMarker;
    private final AdapterInterface alphaNumericAdapter;


    public AlphanumericvalidatorAction(final ActionExecutionAudit action, final Logger log,
                                       final Object alphanumericvalidator) {
        this.alphanumericvalidator = (Alphanumericvalidator) alphanumericvalidator;
        this.action = action;
        this.log = log;
        this.alphaNumericAdapter = new AlphaNumericAdapter();
        this.aMarker = MarkerFactory.getMarker(" Alphanumericvalidator:" + this.alphanumericvalidator.getName());
    }

    int getAlphaNumericScore(Validator adapter) {
        try {
            boolean validator = alphaNumericAdapter.getValidationModel(adapter.getInputValue(), adapter.getAllowedSpecialChar(), action);
            return validator ? adapter.getThreshold() : 0;
        } catch (Exception ex) {
            log.error("Exception occurred in getting alpha numeric score", ex);
            throw new HandymanException("Exception occurred in getting alpha numeric score", ex, action);
        }
    }

    @Override
    public void execute() throws Exception {

        try {
            log.info(aMarker, "Alpha Numeric Count Action for {} has been started" , alphanumericvalidator.getName());
            AdapterInterface alphaNumericAdapter = new AlphaNumericAdapter();
            boolean validator = alphaNumericAdapter.getValidationModel(alphanumericvalidator.getInputValue(), alphanumericvalidator.getAllowedSpecialCharacters(), action);
            int confidenceScore = validator ? Integer.parseInt(alphanumericvalidator.getThresholdValue()) : 0;
            action.getContext().put("validator.score", String.valueOf(confidenceScore));
            log.info(aMarker, "Alpha Numeric Action for {} has been completed" , alphanumericvalidator.getName());

        } catch (Exception ex) {
            action.getContext().put(alphanumericvalidator.getName().concat(".error"), "true");
            log.error(aMarker, "Exception occurred in alpha numeric validator action ", ex);
            throw new HandymanException("Exception occurred in alpha numeric validator action", ex, action);
        }
    }

    @Override
    public boolean executeIf() throws Exception {
        return alphanumericvalidator.getCondition();
    }
}
