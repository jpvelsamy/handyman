package in.handyman.raven.exception;

import in.handyman.raven.lambda.access.repo.HandymanRepoImpl;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HandymanException extends RuntimeException {
    private static final HandymanRepoImpl handymanRepo = new HandymanRepoImpl();

    public HandymanException(final String message) {
        super(message);
    }

    public HandymanException(final String message, final Exception t) {
        super(message, t);
    }

    public HandymanException(final String message, final Exception exception, ActionExecutionAudit actionExecutionAudit) {
        super(message, exception);
        this.handymanRepo.insertExceptionLog(actionExecutionAudit, exception, message);
    }

    public HandymanException(Exception e) {
        super(e);
    }

    public static void insertException(final String message, final HandymanException exception,  final ActionExecutionAudit actionExecutionAudit){
        handymanRepo.insertExceptionLog(actionExecutionAudit, exception, message);
    }
}
