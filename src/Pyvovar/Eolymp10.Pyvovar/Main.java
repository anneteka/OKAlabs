package evolution;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * програа, яка за номерами двох видів обчислить номер виду їх найближчого
 * спільного предка у дереві еволюції.
 * 
 * @author Пивовар Олена, 4 група, ІПЗ
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		new Main().run();
	}

	/**
	 * зчитування з файлу та виведення результату у файл
	 * 
	 * @throws IOException
	 */
	private void run() throws IOException {
		Scanner sc = new Scanner(new File("src\\evolution\\input.txt"));
		PrintWriter pw = new PrintWriter("src\\evolution\\output.txt");
		while (sc.hasNext()) {
			int n = sc.nextInt();
			BigInteger a = new BigInteger(sc.next());
			BigInteger b = new BigInteger(sc.next());

			BigInteger res = findAncestor(n, a, b);
			pw.println(res);
		}
		pw.close();
	}

	/**
	 * @param n кількість етапів еволюції
	 * @param a номер першого виду
	 * @param b номер другого виду
	 * @return номер найближчого предка
	 */
	private BigInteger findAncestor(int n, BigInteger a, BigInteger b) {
		BigInteger res = new BigInteger("0");

		boolean together = false;

		for (int i = 0; i < n; i++) {
			BigInteger step1 = new BigInteger("2");
			step1 = step1.pow(i);

			BigInteger step2 = new BigInteger("2");
			step2 = step2.pow(i + 1);

			// перевірка, чи види на одному етапі еволюції
			if ((a.compareTo(step1) >= 0) && a.compareTo(step2) == -1 && (b.compareTo(step1) >= 0)
					&& b.compareTo(step2) == -1) {
				together = true;
				break;
			}
		}

		if (together == true)
			res = findIfTogether(a, b);
		else
			res = findIfNotTogether(n, a, b);

		return res;
	}

	/**
	 * якщо номери знаходяться не на одному етапі еволюції, то щнаходимо предка для
	 * того номеру, який у дереві еволюції знаходиться нижче
	 * 
	 * @param n кількість етапів еволюції
	 * @param a номер першого виду
	 * @param b номер другого виду
	 * @return предок для номерів видів
	 */
	private BigInteger findIfNotTogether(int n, BigInteger a, BigInteger b) {
		BigInteger a1 = a;
		BigInteger b1 = b;
		int firstN = n;
		int secondN = n;

		while (true) {
			if (a1.compareTo(new BigInteger("2").pow(firstN)) >= 0)
				break;
			firstN--;
		}
		while (true) {
			if (b1.compareTo(new BigInteger("2").pow(secondN)) >= 0)
				break;
			secondN--;
		}

		if (firstN < secondN) {
			while (firstN != secondN) {
				b1 = b1.divide(new BigInteger("2"));
				secondN--;
			}
		} else {
			while (firstN != secondN) {
				a1 = a1.divide(new BigInteger("2"));
				firstN--;
			}
		}

		BigInteger res = findIfTogether(a1, b1);
		return res;
	}

	/**
	 * @param a номер першого виду
	 * @param b номер другого виду
	 * @return предок для номерів видів, які на однаковому етапі еволюції
	 */
	private BigInteger findIfTogether(BigInteger a, BigInteger b) {
		BigInteger a1 = a;
		BigInteger b1 = b;
		while (true) {
			if (a1.equals(b1))
				break;
			a1 = a1.divide(new BigInteger("2"));
			b1 = b1.divide(new BigInteger("2"));
		}
		return a1;
	}

}
