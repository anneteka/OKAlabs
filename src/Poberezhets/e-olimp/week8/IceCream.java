package pr9;

import java.util.Scanner;

/**
 * 
 * Вздовж моря вузькою полоскою тягнеться пляж. У деяких точках пляжу розміщено
 * кіоски з морозивом. В один прекрасний день не усі морозивщики вийшли на
 * роботу. Розподіліть морозивщиків по кіоскам так, щоб мінімальна відстань між
 * морозивщиками було якомога більшою. Так вони менше будуть заважати один
 * одному. Вхідні дані
 * 
 * У першому рядку вводяться кількість кіосків n (2 < n < 10001) та кількість
 * морозивщиків k (1 < k < n), які вийшли на роботу. У другому рядку задано n
 * натуральних чисел у порядку зростання - координати кіосків (координати не
 * перевищують 10^9).
 * 
 * Вихідні дані
 * 
 * Виведіть одне число - мінімальну відстань між сусідніми кіосками в
 * оптимальному розміщенні.
 */

public class IceCream {
	static int n = 0;
	static int k = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();// shops
		k = sc.nextInt();// assictants
		int m[] = new int[n];
		for (int i = 0; i < m.length; i++) {
			m[i] = sc.nextInt();
		}

		// for (Long s : arr)
		// System.out.println(s);
		int Left = 0, Right = 1000000000;
		while (Left <= Right) {
			int Middle = (Left + Right) / 2;
			if (Check(m, Middle))
				Left = Middle + 1;
			else
				Right = Middle - 1;
		}
		System.out.println(Left - 1);

	}

	static boolean Check(int[] m, int Value) {
		int i, stall = 1, len = 0;
		for (i = 1; i < n; i++) {
			len += m[i] - m[i - 1];
			if (len >= Value) {
				len = 0;
				stall++;
			}
		}
		return stall >= k;
	}
}
