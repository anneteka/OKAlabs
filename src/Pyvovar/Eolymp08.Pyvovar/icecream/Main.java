package icecream;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Вздовж моря вузькою полоскою тягнеться пляж. У деяких точках пляжу розміщено
 * кіоски з морозивом. В один прекрасний день не усі морозивщики вийшли на
 * роботу. Розподіліть морозивщиків по кіоскам так, щоб мінімальна відстань між
 * морозивщиками було якомога більшою. Так вони менше будуть заважати один
 * одному.
 * 
 * @author Пивовар Олена, 4 група, ІПЗ
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		new Main().run();
	}

	private void run() throws IOException {
		Scanner sc = new Scanner(new File("src/icecream/input.txt"));
		PrintWriter pw = new PrintWriter(new File("src/icecream/output.txt"));
		while (sc.hasNext()) {
			int n = sc.nextInt();
			int k = sc.nextInt();
			int koord[] = new int[n];
			for (int i = 0; i < n; i++) {
				koord[i] = sc.nextInt();
			}
			int firstK = 0;
			int lastK = 1000000000;
			while (firstK <= lastK) {
				int middle = (firstK + lastK) / 2;
				if (canSeat(middle, n, k, koord))
					firstK = middle + 1;
				else
					lastK = middle - 1;
			}
			pw.println(firstK - 1);
		}
		pw.close();
	}

	private boolean canSeat(int middle, int n, int k, int[] koord) {
		int countL = 1;
		int distance = 0;
		for (int i = 1; i < n; i++) {
			distance += koord[i] - koord[i - 1];
			if (distance >= middle) {
				distance = 0;
				countL++;
			}
		}
		return countL >= k;
	}

}
