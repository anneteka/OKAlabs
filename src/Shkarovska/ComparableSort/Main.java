import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args0){
        Scanner scn;
        try {
            scn = new Scanner(new FileReader("file.txt"));
        } catch (FileNotFoundException fnf) {
            System.out.println("File not found");
            return;
        }
        int n = scn.nextInt();
        Smth[] people = new Smth[n];
        for(int i=0; i<n; i++) {
        	String str = scn.nextLine();
        	int k = scn.nextInt();
        	people[i] = new Smth(str,k);
        }
        ShellSort ss= new ShellSort<>();
        Comparator<Smth> cmp = new SmthStrComporator().thenComparing(new SmthIntComporator());
       // Comparator<Smth> cmp = new SmthIntComporator().thenComparing(new SmthStrComporator());
        ss.sort(people,cmp);

        for(Smth  p : people){
            System.out.println(p.getStr() + " " + p.getI());
        }

    }
}
