package youngGardener;

import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out, true);
		while (sc.hasNextInt()) {
			int n = sc.nextInt(); // number of floors
			if (n >= 0 && n <= 1000)
				out.printf("%d\n", 1 + n + n * n);// top leave + sum of arithmetic progression : a1 = 2; d = 2
		}
	}

}
