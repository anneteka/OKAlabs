package icecream;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * У першому рядку вводяться кількість кіосків n (2 < n < 10001) та кількість
 * морозивщиків k (1 < k < n), які вийшли на роботу. У другому рядку задано n
 * натуральних чисел у порядку зростання - координати кіосків (координати не
 * перевищують 109). Виведіть одне число - мінімальну відстань між сусідніми
 * кіосками в оптимальному розміщенні.
 * 
 * @author Rovnina Tetiana
 *
 */
public class Icecream {

	public static void main(String[] args) throws IOException {
		new Icecream().run();
	}

	private void run() throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out, true);

		int n = sc.nextInt();
		int k = sc.nextInt();
		int[] coordinates = new int[n];

		for (int i = 0; i < n; i++)
			coordinates[i] = sc.nextInt();

		int first = 0;
		int last = coordinates[n - 1];

		while (first <= last) {
			int middle = (first + last) / 2;
			if (check(middle, coordinates, k))
				first = middle + 1;
			else
				last = middle - 1;

		}
		pw.println(first - 1);

	}

	/**
	 * метод, що повертає true, якщо дистанція задовольяє всіх працівників
	 */
	private boolean check(int dist, int[] coord, int k) {
		int stall = 1, len = 0;

		for (int i = 1; i < coord.length; i++) {

			len += coord[i] - coord[i - 1];

			if (len >= dist) {
				len = 0;
				stall++;
			}

		}

		return stall >= k;
	}

}
