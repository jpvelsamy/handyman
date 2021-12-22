package in.handyman.raven.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
