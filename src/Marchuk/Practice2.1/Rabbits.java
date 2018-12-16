package Practice2;

import java.util.Scanner;

import Practice1.Main;

public class Rabbits {

	public static void main(String[] args) {
		Rabbits t = new Rabbits();
		t.executeOrder66();

	}

	private void executeOrder66() {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextInt()) {
			int n = sc.nextInt();
			int k = sc.nextInt();
			if (n >= 0 && n <= 100 && k >= 0 && k <= 10000) {
				int quant = 1;
				for (int i = 0; i < n; i++) {
					if (quant > k) {
						quant -= k;
					}
					quant += quant;
				}
			System.out.println(quant);
			}
		}

	}

}
