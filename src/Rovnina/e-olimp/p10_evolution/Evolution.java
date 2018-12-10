package evolution;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * Перший рядок містить кількість етапів еволюції n (1 ≤ n ≤ 100), що відбулися
 * на планеті Олімпія до теперішнього часу. Другий та третій рядки містять по
 * одному натуральному числу, що представляють номери видів, для яких потрібно
 * знайти номер їх найближчого спільного предка.
 * 
 * @author Rovnina Tetiana
 *
 */
public class Evolution {

	public static void main(String[] args) throws IOException {
		new Evolution().evolution();
	}

	
	/**
	 * метод, який шукає спільного предка
	 */
	private void evolution() throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out, true);

		int n = sc.nextInt();
		BigInteger x = new BigInteger(sc.next());
		BigInteger y = new BigInteger(sc.next());
		BigInteger two = new BigInteger("2");
		
		do {
			if (x.compareTo(y) > 0)
				x = x.divide(two);
			if (x.compareTo(y) < 0)
				y = y.divide(two);
		} while (x.compareTo(y) != 0);

		pw.println(x);
	}

}
