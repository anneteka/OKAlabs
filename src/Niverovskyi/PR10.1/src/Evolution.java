import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Evolution {

	public static void main(String[] args) throws FileNotFoundException {
		String filename = "evolution.txt";
		Scanner s = new Scanner(new FileInputStream(filename));
		while(s.hasNext()){
		int n = s.nextInt();
		int a = s.nextInt();
		int b = s.nextInt();
		
		while(a!=b){
			if(a>b)
				a/=2;
			else
				b/=2;
		}
		System.out.println(a);

	}

}
}
