package Stakhurskyi.Eolimp1;

import java.io.PrintWriter;
import java.util.Scanner;


public class Main {

	private static final int MAX = 1000000;
	private static final int MIN = 0;

	public static void main(String[] args) {
		Main m = new Main();
		m.findMax();
	}

	/**
	 * ciaoia?aiiy iaeneiaeuii? aia?eie oeeeo na?aa aaio caaaieo ?enae
	 */
	private void findMax() {
		Scanner in = new Scanner(System.in);

		while (in.hasNextInt()) {
			int i = in.nextInt();
			int j = in.nextInt();

			int max = 0;

			int maxN = Math.max(i, j);
			int minN = Math.min(i, j);

			if (maxN > MIN && maxN < MAX && minN > MIN && minN < MAX) {
				for (int n = minN; n <= maxN; n++) {
					max = Math.max(max, findLeng(n));
				}
				System.out.println(i + " " + j + " " + max);
			}
		}
	}

	/**
	 * @param n ?enei, aia?eio oeeeo yeiai iio??aii ciaeoe
	 * @return aia?eia oeeeo
	 */
	private int findLeng(int n) {
		int step = 1;

		while (n != 1) {
			if (n % 2 != 0)
				n = 3 * n + 1;
			else
				n = n / 2;
			step++;
		}
		return step;
	}

}