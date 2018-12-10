package practice5;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Student[] students = new Student[5];
        students[0] = new Student(19, 100, "Аня");
        students[1] = new Student(19, 99, "Юлічка");
        students[2] = new Student(18, 100, "Катюха");
        students[3] = new Student(20, 94, "Владочка");
        students[4] = new Student(17, 10, "Алекс");

        print(students);
        Arrays.sort(students);
        print(students);
        Arrays.sort(students, Student.AGE());
        print(students);
        Arrays.sort(students, Student.NAME());
        print(students);
        Arrays.sort(students, Student.RATING());
        print(students);
        Arrays.sort(students, Student.WORST_RATING());
        print(students);
    }

    private static void print(Student[] students) {
        for (Student s : students)
            System.out.println(s);
        System.out.println();
    }
}
