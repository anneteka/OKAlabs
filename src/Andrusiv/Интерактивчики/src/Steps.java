import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Steps {
	public static void main(String args[]) {
		String file = new String("input.txt");
		Scanner in;
		try {
			in = new Scanner(new FileReader("input.txt"));
		} catch (FileNotFoundException fnf) {
			System.out.println("File not found");
			return;
		}

		while (in.hasNext()) {
			System.out.println(fib(in.nextInt()));
		}

	}

	private static int fib(int n) {
		if(n<0) return 0;
			if(n==1||n==0) return 1;
			return fib(n-1)+fib(n-2);
	}

	
}
