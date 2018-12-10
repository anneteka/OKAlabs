package queue;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.Scanner;

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
 * @author Rovnina Tetiana
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		new Main().time();
	}

	private void time() throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out, true);

		PriorityQueue<Long> queue = new PriorityQueue<>();// черга кас
		int n = sc.nextInt();
		int k = sc.nextInt();
		long time = 0;

		if (n >= 1 && n <= 100000 && k >= 1 && k <= 10000) {

			for (int i = 0; i < k; i++) {// заповнили k - кас
				if (n-- > 0)
					queue.add((long) sc.nextInt());
			}

			while (!queue.isEmpty()) {
				time = queue.remove();// видаляэмо елемент з каси

				if (n-- > 0)// якщо ще є елементи, то додаємо їх до каси з часов який вони чекали
					queue.add(sc.nextInt() + time);
			}

			pw.println(time);
		}

	}

}
