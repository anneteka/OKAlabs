package rabbits;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * count rabbits
 * 
 * @author Rovnina Tetiana
 *
 */
public class Main {

	public static void main(String[] args) {
		new Main().countRabbits();
	}

	private void countRabbits() {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out, true);
		while (sc.hasNextInt()) {
			int n = sc.nextInt(); // number of months
			int k = sc.nextInt(); // number of rabbits for eating
			int rabbits = 1; // number of rabbits

			if (n >= 0 && n <= 100 && k >= 0 && k <= 10000) {
				for (int i = 0; i < n; i++) {
					if (rabbits > k) // monster eat k rabbits
						rabbits -= k;
					rabbits *= 2; // born new rabbits
				}
				out.printf("%d\n", rabbits);
			}
		}
	}

}
