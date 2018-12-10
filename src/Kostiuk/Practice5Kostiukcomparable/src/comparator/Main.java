package comparator;
import java.util.Arrays;

import help.Student;

public class Main {

    public static void main(String[] args) {
        final Student karl = createStudent("Karl", 16, "kpi");
        final Student alabama = createStudent("Alabama", 17, "ukma");
        final Student mercedes = createStudent("Mercedes", 25, "knu");
        final Student victoria = createStudent("Victoria", 18, "knu");
        final Student jkob = createStudent("Jkob", 20, "kpi");
        final Student grizman = createStudent("Grizman", 33, "ukma");
        final Student azar = createStudent("Azar", 31, "ukma");

        Student[] students = new Student[] { karl, alabama, mercedes, victoria, jkob, grizman, azar };
        Arrays.sort(students, Student.BY_NAME);
        printStudents(students);
        Arrays.sort(students, Student.BY_AGE);
        printStudents(students);
        Arrays.sort(students, Student.BY_UNIVERSITY);
        printStudents(students);
    }


    private static void printStudents(final Student[] aStudents) {
        System.out.println();
        for (Student student : aStudents) {
            System.out.print(" " + student.getName());
        }
    }


    private static Student createStudent(final String aName, final int aAge, final String aSchool) {
        return new Student(aName, aAge, aSchool);
    }

}