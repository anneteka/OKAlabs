import java.util.Scanner;

public class hw1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextInt()) {

			int i = sc.nextInt();
			int j = sc.nextInt();
			int finalI = i;
			int finalJ = j;
			int arifmet = 0;
			if (i <= j) {
				arifmet = 1;
			} else {
				arifmet = -1;
			}
			int max = 0;
			int premax = 0;
			for (; i != j; i = i + arifmet) {
				int n = i;
				if (0 < n && n < 1000000) {
					while (true) {
						// System.out.println(n);
						premax++;
						if (n == 1)
							break;
						if (n % 2 != 0)
							n = 3 * n + 1;
						else
							n = n / 2;
					}
				}
				if (premax > max)
					max = premax;
				premax = 0;
			}
			System.out.println(finalI + " " + finalJ + " " + max);
		}
	}
}
