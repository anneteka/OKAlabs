package balloons;

import java.util.*;
import java.io.*;

/**
 * порахувати мінімальну кількість кульок, яку потрібно віддати
 * 
 * @author Rovnina Tetiana
 *
 */
public class Main{

	public static void main(String[] args) throws IOException {
		new Main().countBalloons();
	}

	private void countBalloons() throws IOException {
		int[] balloons = new int[9];

		Scanner sc = new Scanner(new File("src/balloons/input.txt"));
		PrintWriter pw = new PrintWriter(new File("src/balloons/output.txt"));
		//зчитуємо данні
		int n = sc.nextInt();
		int max = 0;
		int x = 0;
		if (n >= 1 && n <= 100000) {
			for (int i = 0; i < n && sc.hasNextInt(); i++) {
				x = sc.nextInt(); // рахуэмо кульки одного кольору
				if (x >= 1 && x <= 9)
					balloons[x - 1]++;
			}

			for (int b : balloons)// пошук максимальної кількості кульок одного кольору
				max = Math.max(max, b);

			pw.println(n - max);
		}
		pw.close();
	}

}
