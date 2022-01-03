package in.handyman.raven.exception;

public class HandymanException extends RuntimeException {

    public HandymanException(final String message) {
        super(message);
    }

    public HandymanException(final String message, final Throwable t) {
        super(message, t);
    }
}
