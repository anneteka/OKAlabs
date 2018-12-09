import java.util.Scanner;

/** Done
 * Задано послідовність, що складається з n натуральних чисел. Визначити, чи є
 * вона перестановкою перших n натуральних чисел.
 * 
 * Вхідні дані
 * 
 * В єдиному рядку задано спочатку число n (n ≤10000), а потім n натуральних
 * чисел, розділених пропуском. Відомо, що кожне з натуральних чисел менше за
 * 2000000.
 * 
 * Вихідні дані
 * 
 * Вивести 0, якщо послідовність є перестановкою. Інакше слід вивести мінімальне
 * число, що не входить в цю послідовність.
 * 
 * @author Богдана
 *
 */
public class Main {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int i = 0;
		int n = in.nextInt();
		int m[] = new int[n + 1];
		for (i = 0; i < n; i++) {
			int a = in.nextInt();
			if (a <= n)
				m[a]++;
		}
		for (i = 1; i <= n; i++)
			if (m[i] == 0)
				break;
		if (i <= n)
			System.out.println(i);
		else
			System.out.println(0);
		in.close();

	}

}