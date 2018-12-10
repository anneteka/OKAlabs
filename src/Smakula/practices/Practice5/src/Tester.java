import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Tester {

    public static void main(String[] args) throws IOException {
        ArrayList<Student> students = new ArrayList<>();
        readFromFile(students);
        printSorted(students);
    }

    private static void readFromFile(ArrayList<Student> students) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(""));
        String[] lineArray;
        while (true) {
            String line = reader.readLine();
            if (line == null) break;
            lineArray = line.split(" ");
            students.add(new Student(Integer.parseInt(lineArray[0]), Integer.parseInt(lineArray[1]), lineArray[2], Integer.parseInt(lineArray[3])));
        }
    }

    private static void printSorted(ArrayList<Student> students) {
        Collections.sort(students);
        System.out.println(students);
        Collections.sort(students, Student.byAge);
        System.out.println(students);
        Collections.sort(students, Student.byAverage);
        System.out.println(students);
        Collections.sort(students, Student.byCourse);
        System.out.println(students);
        Collections.sort(students, Student.byCourseAge);
        System.out.println(students);
    }
}
