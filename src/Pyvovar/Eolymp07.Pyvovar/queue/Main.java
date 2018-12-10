package queue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * У цивілізованих країнах на залізничному вокзалі працює k кас, проте черга до
 * них всього одна. Обслуговування відбувається наступним чином. Спочатку, коли
 * усі каси вільні, перші k чоловік з черги підходять до кас. Інші чекають своєї
 * черги. Як тільки кого-небудь буде обслужено і відповідна каса звільниться,
 * наступна людина з черги підходить до цієї каси. Так продовжується до тих пір,
 * доки не буде обслужено усіх клієнтів.
 * 
 * Визначте час, за який буде обслужено усіх клієнтів.
 * 
 * @author Пивовар Олена, 4 група, ІПЗ
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		new Main().run();
	}

	/**
	 * зчитування з файлу та запис, виклик інших функцій
	 * 
	 * @throws IOException
	 */
	private void run() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File("src\\queue\\input.txt")));
		PrintWriter pw = new PrintWriter(new File("src\\queue\\output.txt"));
		while (true) {
			String s1 = br.readLine();
			if (s1 == null)
				break;
			StringTokenizer token = new StringTokenizer(s1);
			int n = Integer.parseInt(token.nextToken());
			int k = Integer.parseInt(token.nextToken());
			long[] array = new long[n];
			s1 = br.readLine();
			token = new StringTokenizer(s1);
			for (int i = 0; i < n; i++) {
				array[i] = Integer.parseInt(token.nextToken());
			}
			if (k > n)
				k = n;
			long res = timeQueue(k, array);
			pw.println(res);
		}
		pw.close();
		br.close();
	}

	/**
	 * @param k     кількість кас
	 * @param array масив з годин обслуговування кожного клієнта
	 * @return час обслуговування клієнтів
	 */
	private long timeQueue(int k, long[] array) {
		for (int i = k; i < array.length; i++) {
			array[findIndexMin(k, array)] += array[i];
		}

		return array[findIndexMax(k, array)];
	}

	/**
	 * @param k     кількість кас
	 * @param array масив з годин обслуговування кожного клієнта
	 * @return максимальний час на одній із кас
	 */
	private int findIndexMax(int k, long[] array) {
		int resIndex = 0;
		for (int i = 1; i < k; i++) {
			if (array[i] > array[resIndex])
				resIndex = i;
		}
		return resIndex;
	}

	/**
	 * @param k кількість кас
	 * @param array масив з годин обслуговування кожного клієнта
	 * @return мінімальний час на одній із кас
	 */
	private int findIndexMin(int k, long[] array) {
		int resIndex = 0;
		for (int i = 1; i < k; i++) {
			if (array[i] < array[resIndex])
				resIndex = i;
		}
		return resIndex;
	}

}
