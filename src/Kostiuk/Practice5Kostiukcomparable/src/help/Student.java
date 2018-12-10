package help;

import java.util.Comparator;

public class Student {

    final private String name;
    final private int age;
    final private String school;

    public static final Comparator BY_NAME = new ByName();
    public static final Comparator BY_AGE = new ByAge();
    public static final Comparator BY_UNIVERSITY = new BySchool();


    public Student(final String aName, final int aAge, final String aSchool) {
        name = aName;
        age = aAge;
        school = aSchool;
    }


    public String getName() {
        return name;
    }


    public int getAge() {
        return age;
    }


    public String getSchool() {
        return school;
    }


    private static class ByName implements Comparator<Student> {

        @Override
        public int compare(final Student aThis, final Student aThat) {
            return aThis.getName().compareTo(aThat.getName());
        }

    }


    private static class ByAge implements Comparator<Student> {

        @Override
        public int compare(final Student aThis, final Student aThat) {
            if (aThis.getAge() < aThat.getAge()) {
                return -1;
            } else if (aThis.getAge() > aThat.getAge()) {
                return 1;
            }
            return 0;
        }
    }


    private static class BySchool implements Comparator<Student> {

        @Override
        public int compare(final Student aThis, final Student aThat) {
            return aThis.getSchool().compareTo(aThat.getSchool());
        }
    }
}