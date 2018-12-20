package Stakhurskyi.Eolimp3;

import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		new Main().count();
	}

	/**
	 * c?eooaaiiy ?ioi?iao?? c eiinie? oa ciaoia?aiiy noie i?i?iaeuieo cia?aiu o
	 * noao? i?a ?an ei?iiai aiaaaaiiy aai a?ai?iaiiy aeaiaioa
	 */
	private void count() {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out, true);
		ArrayDeque<Integer> ad = new ArrayDeque<>();
		long res = 0;
		int min = Integer.MAX_VALUE;
		int removeEl;

		while (in.hasNextInt()) {
			int n = in.nextInt();
			int a = in.nextInt();
			int b = in.nextInt();
			int c = in.nextInt();
			long x0 = in.nextLong();
			if (n >= 1 && n <= 1000000 && a >= 0 && b >= 0 && c >= 0 && x0 >= 0 && a <= 10000 && b <= 10000
					&& c <= 10000 && x0 <= 10000) {
				for (int i = 0; i < n; i++) {
					x0 = (a * x0 * x0 + b * x0 + c) / 100 % 1000000;
					if (x0 % 5 < 2) {
						if (!ad.isEmpty()) {
							removeEl = ad.pop();
							if (removeEl == min) {
								if (!ad.isEmpty()) {
									min = minInSteck(ad);
									res += min;
								} else
									min = Integer.MAX_VALUE;
							} else {
								res += min;
							}
						}
					} else {
						ad.push((int) x0);
						if (x0 < min)
							min = (int) x0;
						res += min;
					}
				}
				out.printf("%d\n", res);
			}
		}
	}

	/**
	 * @param ad noae, o yeeio iio??aii ciaeoe i?i?ioi
	 * @return i?i?ioi o noao?
	 */
	private int minInSteck(ArrayDeque<Integer> ad) {
		int min = ad.peek();
		for (Integer i : ad) {
			if (i < min)
				min = i;
		}
		return min;
	}

}