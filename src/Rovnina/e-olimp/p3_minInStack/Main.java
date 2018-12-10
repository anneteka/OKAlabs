package minInStack;

import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Scanner;

/**
 * пошук мінімального елемента у стеку
 * 
 * @author Rovnina Tetiana
 *
 */
public class Main{

	public static void main(String[] args) {
		new Main().exploreStack();
	}

	private void exploreStack() {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out, true);
		ArrayDeque<Integer> st = new ArrayDeque<>();

		while (sc.hasNextLong()) {
			int last = 0;
			long sum = 0;
			int min = Integer.MAX_VALUE;

			// зчитуємо введені данні
			int n = sc.nextInt();
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			long x0 = sc.nextLong();

			if (n >= 1 && n <= 1000000 && a <= 10000 && b <= 10000 && c <= 10000 && x0 <= 10000) {
				for (int i = 0; i < n; i++) {
					x0 = ((a * x0 + b) * x0 + c) / 100 % 1000000;

					if (x0 % 5 < 2) {
						if (!st.isEmpty()) {
							last = st.pop();
							if (last == min) {// якщо елемент, який видаляємо дорівнює мінімальному, тоді шукаємо знову
												// мінімальний
								if (!st.isEmpty()) {
									min = minInStack(st);
									sum += min;
								} else
									min = Integer.MAX_VALUE;
							} else
								sum += min;
						}
					} else {
						st.push((int) x0);
						if (x0 < min) // перевірка на мінімальний елемент
							min = (int) x0;
						sum += min;
					}

				}
				out.printf("%d\n", sum);
			}
		}
	}

	/**
	 * метод, який шукає мінімальній елемент у стеку
	 * 
	 * @param st
	 *            стек
	 * @return мінімальний елемент у стеку
	 */
	private int minInStack(ArrayDeque<Integer> st) {
		int min = st.peek();
		for (int s : st) {
			if (s < min)
				min = s;
		}
		return min;
	}

	
}