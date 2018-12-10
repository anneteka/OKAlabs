package help;

import help.NullArrayException;
public class QueueOutOfBoundException extends RuntimeException {

    public QueueOutOfBoundException(final String message) {
        super(message);
    }
}