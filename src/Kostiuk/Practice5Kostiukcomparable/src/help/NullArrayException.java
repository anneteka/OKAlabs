package help;

import help.NullArrayException;
public class NullArrayException extends RuntimeException {

    public NullArrayException(final String message) {
        super(message);
    }
}