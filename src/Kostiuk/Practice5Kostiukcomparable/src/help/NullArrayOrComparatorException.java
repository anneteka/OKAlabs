package help;

import help.NullArrayException;
public class NullArrayOrComparatorException extends RuntimeException {

    public NullArrayOrComparatorException(final String message) {
        super(message);
    }
}