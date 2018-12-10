package sortdna;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Відсортувати послідовність ДНК рядків (вони містять лише чотири літери A, C,
 * G, Т). Проте сортування слід проводити не у алфавітному порядку, а у порядку
 * "впорядкованості", від "самих відсортованих" до "найменш відсортованих". Усі
 * рядки мають однакову довжину.
 * 
 * @author Пивовар Олена, 4 група, ІПЗ
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		new Main().run();
	}

	/**
	 * зчитування з файлу даних та запис результату у новий файл
	 * @throws IOException
	 */
	private void run() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File("src/Sortdna/input.txt")));
		PrintWriter pw = new PrintWriter(new File("src/Sortdna/output.txt"));
		int k = Integer.parseInt(br.readLine());
		for (int i = 0; i < k; i++) {
			String str = br.readLine();
			str = br.readLine();
			StringTokenizer token = new StringTokenizer(str);
			int n = Integer.parseInt(token.nextToken());
			int m = Integer.parseInt(token.nextToken());
			if (n > 0 && n <= 50 && m > 0 && m <= 100) {
				String[] arrayStr = new String[m];
				int[] arrayInt = new int[m];
				for (int j = 0; j < m; j++) {
					arrayStr[j] = br.readLine();
				}
				findDisorderlyNumb(arrayInt, arrayStr, n);
				for (int j = 0; j < arrayStr.length; j++) {
					pw.println(arrayStr[j]);
				}
				pw.println();
			}
		}
		br.close();
		pw.close();
	}

	/**
	 * знаходження кількості невпорядкованих елементів в заданій ДНК
	 * @param arrayInt
	 * @param arrayStr
	 * @param n
	 */
	private void findDisorderlyNumb(int[] arrayInt, String[] arrayStr, int n) {
		char[] arrayChar;
		for (int i = 0; i < arrayStr.length; i++) {
			arrayChar = arrayStr[i].toCharArray();
			for (int j = 0; j < arrayChar.length - 1; j++) {
				for (int k = j + 1; k < arrayChar.length; k++) {
					if (arrayChar[j] > arrayChar[k]) {
						arrayInt[i]++;
					}
				}
			}
		}
		sortDNA(arrayInt, arrayStr);
	}

	/**
	 * сортування вставкою
	 * 
	 * @param arrayInt
	 * @param arrayStr
	 */
	private void sortDNA(int[] arrayInt, String[] arrayStr) {
		int n = arrayInt.length;
		for (int i = 0; i < n; i++) {
			for (int j = i; j > 0; j--)
				if (arrayInt[j] < arrayInt[j - 1]) {
					String tS = arrayStr[j];
					int tI = arrayInt[j];
					arrayStr[j] = arrayStr[j - 1];
					arrayInt[j] = arrayInt[j - 1];
					arrayStr[j - 1] = tS;
					arrayInt[j - 1] = tI;
				} else
					break;
		}
	}

}
