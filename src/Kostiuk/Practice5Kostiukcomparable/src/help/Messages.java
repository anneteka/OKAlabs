package help;


public enum Messages {

    NULL_ARRAY_EXCEPTION_MESSAGE("Null Array"),
    NOT_EQUALS_NAMES_MESSAGE("Wrong name: Expected '%s' but was '%s'"),
    ARRAY_NOT_EMPTY_MESSAGE("Wrong array length: Expected 0 but was %d"),
    NOT_EQUALS_DAYS_MESSAGE("Wrong day: Expected %d but was %d"),
    NOT_EQUALS_CUSTOMERS_MESSAGE("Wrong customer: Expected '%s' but was '%s'"),
    ARRAYS_LENGTH_NOT_EQUALS_MESSAGE("Wrong array length: Expected %d, but was %d"),
    ARRAY_NOT_SHUFFLED_MESSAGE("Array is not shuffled"),
    NULL_ARRAY_OR_COMPARATOR_MESSAGE("Null array or comparator"),
    WRONG_STUDENT_MESSAGE("Wrong student: Expected '%s' but was '%s'"),
    QUEUE_OUT_OF_BOUND_EXCEPTION_MESSAGE("Queue Out of Bound Exception."),
    QUEUE_NOT_EMPTY_MESSAGE("Queue should be empty, but it is not."),
    QUEUE_EMPTY_MESSAGE("Queue should not be empty, but it is."),
    PRIORITY_QUEUE_WRONG_KEY_MESSAGE("Wrong Key: Expected '%s' but was '%s'"),
    EMPTY_QUEUE_EXCEPTION_MESSAGE("Empty Queue Exception");

    final String message;


    private Messages(final String aMessage) {
        message = aMessage;
    }

    public String toString() {
        return message;
    }
}