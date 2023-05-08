package in.handyman.raven.exception;

import in.handyman.raven.lambda.access.repo.HandymanRepoImpl;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HandymanException extends RuntimeException {
    private final HandymanRepoImpl handymanRepo = new HandymanRepoImpl();

    public HandymanException(final String message) {
        super(message);
    }

    public HandymanException(final String message, final Exception t) {
        super(message, t);
    }

    public HandymanException(final String message, final Exception exception, ActionExecutionAudit actionExecutionAudit) {
        super(message, exception);
        handymanRepo.insertExceptionLog(actionExecutionAudit, exception, message);
    }
}
