package at.spengergasse.essity.service.exceptions;

public abstract class AbstractBusinessException extends RuntimeException {
    protected AbstractBusinessException(String message) {
        super(message);
    }

    public AbstractBusinessException(String message, Throwable rootCause) {
        super(message, rootCause);
    }
}
