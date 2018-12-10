package help;

public class EmptyQueueException extends RuntimeException {

    public EmptyQueueException(final String message) {
        super(message);
    }
}