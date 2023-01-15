package in.handyman.raven.exception;

public class HandymanException extends RuntimeException {

    public HandymanException(final String message) {
        super(message);
    }

    public HandymanException(final String message, final Exception t) {
        super(message, t);
    }
}
