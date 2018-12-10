import java.io.PrintWriter;
import java.util.Scanner;

/**
 * задача 3n + 1
 * 
 * @author Пивовар Олена, 4 група
 *
 */
public class Main {

	private static final int MAX = 1000000;
	private static final int MIN = 0;

	public static void main(String[] args) {
		Main m = new Main();
		m.findMax();
	}

	/**
	 * знаходження максимальної довжини циклу серед двох заданих чисел
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
	 * @param n число, довжину циклу якого потрібно знайти
	 * @return довжина циклу
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
