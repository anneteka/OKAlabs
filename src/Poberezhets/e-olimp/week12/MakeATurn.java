package pr13;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Хлопчик Вася дуже любить розвертати орієнтовні графи. Допоможіть йому в
 * цьому.
 * 
 * Вхідні дані
 * 
 * Першим записано число N (1 ≤ N ≤ 50000) - кількість вершин у графі. У
 * наступних N рядках записано граф у вигляді списків суміжності: в i-ому рядку,
 * у порядку зростання, записано номери вершин, у які йдуть ребра з i-ої
 * вершини. Нумерація починається з одиниці. Гарантується, що ребер у графі не
 * більше 50000.
 * 
 * Вихідні дані
 * 
 * Виведіть розвернутий граф у тому ж форматі, що й заданий.
 * 
 * @author Богдана
 *
 */

public class MakeATurn {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(new File("output.txt"));

		int n = Integer.parseInt(br.readLine());
		StringBuilder[] res = new StringBuilder[n + 1];

		for (int i = 1; i <= n; i++) {
			String line = br.readLine();
			if (line.length() != 0) {
				String[] row = line.split(" ");
				for (int j = 0; j < row.length; j++) {
					int v = Integer.parseInt(row[j]);
					if (res[v] == null)
						res[v] = new StringBuilder(String.valueOf(i));
					else
						res[v].append(" ").append(i);
				}
			}
		}

		out.printf("%d\n", n);
		for (int i = 1; i <= n; i++) {
			if (res[i] != null)
				out.printf("%s", res[i].toString());
			out.printf("\n");
		}

		br.close();
		out.close();
	}

}
