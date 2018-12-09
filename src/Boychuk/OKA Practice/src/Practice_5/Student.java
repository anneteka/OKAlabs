package Practice_5;

import java.util.Comparator;

public class Student implements Comparable<Student> {

    private int age;
    private int course;
    private String name;
    private int averegeGrade;

    public Student(int age, int course, String name, int averegeGrade) {
        this.age = age;
        this.course = course;
        this.name = name;
        this.averegeGrade = averegeGrade;
    }

    @Override
    public int compareTo(Student o) {
        return name.compareTo(o.name);
    }

    public static final Comparator<Student> byAge = Comparator.comparingInt(o -> o.age);

    public static final Comparator<Student> byAverage = Comparator.comparingDouble(o -> o.averegeGrade);

    public static final Comparator<Student> byCourse = Comparator.comparingInt(o -> o.course);

    public static final Comparator<Student> byCourseAge = byCourse.thenComparing(byAge);

}
