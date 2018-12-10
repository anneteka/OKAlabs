package problem3nPlusn;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * 3n+1 problem
 * 
 * @author Rovnina Tetiana
 *
 */
public class Main {

	public static void main(String[] argv) {
		new Main().findMax();
	}

	/**
	 * знаходимо максимальну довжину числа з проміжку
	 */
	private void findMax() {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out, true);

		while (in.hasNextInt()) {// зчитуємо значення початку і кінця проміжку
			int i = in.nextInt();
			int j = in.nextInt();

			int from = Math.min(i, j); // визначаємо початок і кінець проміжку
			int to = Math.max(i, j);

			if (from > 0 && from < 1000000 && to > 0 && to < 1000000) {
				int max = 0;

				for (int n = from; n <= to; n++) {
					max = Math.max(max, countLength(n)); // перевірка на максимальне
				}

				out.printf("%d %d %d\n", i, j, max); // вивід результату
			}

		}
	}

	/**
	 * метод, який рахує довжину послідовності
	 * 
	 * @param n
	 *            число
	 * @return довжина послідовністі цього числа
	 */
	private int countLength(long n) {
		int length = 1;

		while (n != 1) { // перебираємо всі числа послідовності
			if (n % 2 != 0) // якщо n - непарне
				n = (3 * n) + 1;
			else // якщо n - парне
				n /= 2;

			length++;
		}
		return length;
	}
}
