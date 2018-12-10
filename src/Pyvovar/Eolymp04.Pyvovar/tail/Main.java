package tail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Задано кількість розмірів, самі розміри в заданій кількості та проміжок [a,
 * b]. Потрібно визначити, які розміри входять в заданий інтервал. Зчитування та
 * виведення у файл
 * 
 * @author Olena Pyvovar
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		new Main().run();
	}

	private void run() throws IOException {
		BufferedReader sc = new BufferedReader(new FileReader("src\\tail\\input.txt"));
		PrintWriter pw = new PrintWriter(new File("src\\tail\\output.txt"));
		while (true) {
			String s1 = (String) sc.readLine();
			if (s1 == null)
				break;
			String s2 = (String) sc.readLine();
			String s3 = (String) sc.readLine();

			int n = Integer.parseInt(s1);

			StringTokenizer st = new StringTokenizer(s3);
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(s2);
			int k;
			int s = 0;
			while (st.hasMoreTokens()) {
				k = Integer.parseInt(st.nextToken());
				if (k >= a && k <= b)
					s++;
			}
			pw.printf("%d\n", s);
		}
		pw.close();
		sc.close();
	}

}
