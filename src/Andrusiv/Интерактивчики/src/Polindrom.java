import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Polindrom {

	public static void main(String args[]) {
		String file = new String("file.txt");
		Scanner in;
		try {
			in = new Scanner(new FileReader("input.txt"));
		} catch (FileNotFoundException fnf) {
			System.out.println("File not found");
			return;
		}

		while (in.hasNext()) {
			int n = in.nextInt();
		//	System.out.println(n);
		//	n=Math.abs(n);
			
			String copy = n + "";
			int count = copy.length();
			
		
			if(check(copy, count)) System.out.println("true");
			else System.out.println("false");

		}
	}

	private static boolean check(String str, int count) {
		for(int i=0,j=count-1;i<count;i++,j--) 
			if(str.charAt(i)!=str.charAt(j)) return false;
		return true;
	}
}
