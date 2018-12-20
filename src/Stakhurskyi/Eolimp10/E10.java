package Stakhurskyi.Eolimp10;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) throws IOException {
		new Main().run();
	}

	/**
	 * c?eooaaiiy c oaeeo oa aeaaaaiiy ?acoeuoaoo o oaee
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
	 * @param n e?eue?nou aoai?a aaie?o??
	 * @param a iiia? ia?oiai aeao
	 * @param b iiia? a?oaiai aeao
	 * @return iiia? iaeaee??iai i?aaea
	 */
	private BigInteger findAncestor(int n, BigInteger a, BigInteger b) {
		BigInteger res = new BigInteger("0");

		boolean together = false;

		for (int i = 0; i < n; i++) {
			BigInteger step1 = new BigInteger("2");
			step1 = step1.pow(i);

			BigInteger step2 = new BigInteger("2");
			step2 = step2.pow(i + 1);

			// ia?aa??ea, ?e aeae ia iaiiio aoai? aaie?o??
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
	 * yeui iiia?e ciaoiayouny ia ia iaiiio aoai? aaie?o??, oi uiaoiaeii i?aaea aey
	 * oiai iiia?o, yeee o aa?aa? aaie?o?? ciaoiaeouny ie??a
	 * 
	 * @param n e?eue?nou aoai?a aaie?o??
	 * @param a iiia? ia?oiai aeao
	 * @param b iiia? a?oaiai aeao
	 * @return i?aaie aey iiia??a aea?a
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
	 * @param a iiia? ia?oiai aeao
	 * @param b iiia? a?oaiai aeao
	 * @return i?aaie aey iiia??a aea?a, ye? ia iaiaeiaiio aoai? aaie?o??
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