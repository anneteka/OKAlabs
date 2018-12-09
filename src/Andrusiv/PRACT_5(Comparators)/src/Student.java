import java.util.Comparator;

public class Student implements Comparable<Student> {
	String name;
	int grade;
	public static StudentNameComparator stn=new StudentNameComparator();
	public static StudentGradeComparator stg=new StudentGradeComparator();

	public Student(String n, int g) {
		this.name = n;
		this.grade = g;
	}

	public String getName() {
		return name;
	}

	public int getMark() {
		return grade;
	}

	public int compareTo(Student o) {
		return this.name.compareTo(o.name);
	}
	
	

	public String toString() {
		return " "+name+"-"+grade+" ";
	}
	
}

class StudentNameComparator implements Comparator<Student> {

	@Override
	public int compare(Student a, Student b) {
		return a.getName().compareTo(b.getName());
	}

}

class StudentGradeComparator implements Comparator<Student> {

	@Override
	public int compare(Student a, Student b) {

		return Integer.compare(a.getMark(),b.getMark());
	}

}