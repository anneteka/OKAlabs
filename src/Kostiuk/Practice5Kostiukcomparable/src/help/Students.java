package help;

public enum Students {
    DENIS("Denis", 25, "Causeni"),
    ANDREI("Andrei", 27, "Cahul"),
    ALEX("Alex", 26, "Cantemir"),
    VITALIE("Vitalie", 28, "Chisinau"),
    ALEXANDR("Alexandr", 24, "Edinet"),
    STAS("Stas", 23, "Causeni"),
    ALINA("Alina", 29, "Soroca");

    private final Student student;


    Students(final String aName, final int aAge, final String aSchool) {
        student = new Student(aName, aAge, aSchool);
    }


    public Student getStudent() {
        return student;
    }
}