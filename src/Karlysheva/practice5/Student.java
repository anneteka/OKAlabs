package practice5;

import java.util.Comparator;

public class Student implements Comparable<Student> {
    private int age, rating;
    private String name;

    public static Comparator<Student> NAME() {
        return new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.name.compareTo(o2.name);
            }
        };
    }

    public static Comparator<Student> AGE() {
        return new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if (o1.age < o2.age) return -1;
                if (o1.age > o2.age) return 1;
                return 0;
            }
        };
    }

    public static Comparator<Student> RATING() {
        return new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if (o1.rating > o2.rating) return -1;
                if (o1.rating < o2.rating) return 1;
                return 0;
            }
        };
    }

    public static Comparator<Student> WORST_RATING() {
        return new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if (o1.rating < o2.rating) return -1;
                if (o1.rating > o2.rating) return 1;
                return 0;
            }
        };
    }

    public Student(int age, int rating, String name) {
        this.age = age;
        this.rating = rating;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Student o)
    {
        return name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return name+",  "+ age +" років, "+ rating +" балів";
    }
}
