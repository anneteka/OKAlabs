import java.util.Comparator;

public class Worker implements Comparable<Worker>{
    private final int age;
    private final int bestGradeInCollege;
    private final int yearsOfExperience;

    public static final Comparator<Worker> BY_AGE = new By_Age();
    public static final Comparator<Worker> BY_GRADE = new By_Grade();
    public static final Comparator<Worker> BY_EXPERIENCE = new By_Experience();


    public Worker(int age, int bestGradeInCollege, int yearsOfExperience){
        this.age = age;
        this.bestGradeInCollege = bestGradeInCollege;
        this.yearsOfExperience = yearsOfExperience;
    }

        @Override
    public int compareTo(Worker that) { //by age
        if (this.age > that.age)
            return 1;
        else if (this.age < that.age)
            return -1;
        return 0;
    }

    public int compareToByGrade(Worker that)
    {
        if (this.bestGradeInCollege > that.bestGradeInCollege)
            return 1;
        else if (this.bestGradeInCollege < that.bestGradeInCollege)
            return -1;
        return 0;
    }

    public int compareToByExperience(Worker that)
    {
        if (this.yearsOfExperience > that.yearsOfExperience)
            return 1;
        else if (this.yearsOfExperience < that.yearsOfExperience)
            return -1;
        return 0;
    }

    @Override
    public String toString() {
        return this.age + ", " + this.bestGradeInCollege + ", " + this.yearsOfExperience;
    }
}
