package help;

public enum WeekDays {
    SUNDAY("Sunday"),
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday");

    private final String dayOfWeek;


    WeekDays(final String aDayOfWeek) {
        dayOfWeek = aDayOfWeek;
    }

    public String toString() {
        return dayOfWeek;
    }
}