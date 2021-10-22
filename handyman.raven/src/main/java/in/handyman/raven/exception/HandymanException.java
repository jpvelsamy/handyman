package in.handyman.raven.exception;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class HandymanException extends RuntimeException {

    public HandymanException(final String message) {
        super(message);
        log.warn(message);
    }

    public HandymanException(final String message, final Throwable t) {
        super(message, t);
        log.warn(message, t);
    }
}
