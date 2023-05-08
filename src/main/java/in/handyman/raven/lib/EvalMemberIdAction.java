package in.handyman.raven.lib;

import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.action.ActionExecution;
import in.handyman.raven.lambda.action.IActionExecution;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.agadia.adapters.MemberIdAdapter;
import in.handyman.raven.lib.interfaces.ScalarEvaluationInterface;
import in.handyman.raven.lib.model.AgadiaAdapter;
import in.handyman.raven.lib.model.EvalMemberId;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * Auto Generated By Raven
 */
@ActionExecution(
        actionName = "EvalMemberId"
)
public class EvalMemberIdAction implements IActionExecution {
    private final ActionExecutionAudit action;

    private final Logger log;

    private final EvalMemberId evalMemberId;

    private final Marker aMarker;

    public EvalMemberIdAction(final ActionExecutionAudit action, final Logger log,
                              final Object evalMemberId) {
        this.evalMemberId = (EvalMemberId) evalMemberId;
        this.action = action;
        this.log = log;
        this.aMarker = MarkerFactory.getMarker(" EvalMemberId:" + this.evalMemberId.getName());
    }

    @Override
    public void execute() throws Exception {
        try {
            ScalarEvaluationInterface memberAdapter = new MemberIdAdapter();
            AgadiaAdapter adapter = AgadiaAdapter.builder()
                    .wordCountLimit(evalMemberId.getWordCountLimit())
                    .wordCountThreshold(evalMemberId.getWordCountThreshold())
                    .charCountLimit(evalMemberId.getCharCountLimit())
                    .charCountThreshold(evalMemberId.getCharCountThreshold())
                    .validatorDetail(evalMemberId.getSpecialCharacter())
                    .validatorThreshold(evalMemberId.getValidatorThreshold()).build();
            int score = memberAdapter.getConfidenceScore(evalMemberId.getMemberID(), adapter);
            action.getContext().put(evalMemberId.getName().concat(".score"), String.valueOf(score));
        } catch (Exception ex) {
            action.getContext().put(evalMemberId.getName().concat(".error"), "true");
            log.info(aMarker, "The Exception occurred ", ex);
            throw new HandymanException("Failed to execute", ex, action);
        }
    }

    @Override
    public boolean executeIf() throws Exception {
        return evalMemberId.getCondition();
    }
}