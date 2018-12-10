package permutation;

import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Задано послідовність, що складається з n натуральних чисел. Визначити, чи є
 * вона перестановкою перших n натуральних чисел.
 * 
 * @author Olena Pyvovar
 *
 */
public class Main {

	public static void main(String[] args) {
		Main m = new Main();
		m.run();
	}

	private void run() {
		Scanner in = new Scanner(System.in);
		while (in.hasNextInt()) {
			int n = in.nextInt();
			TreeSet<Integer> set = new TreeSet<>();
			for (int i = 0; i < n; i++) {
				set.add(in.nextInt());
			}
			Iterator<Integer> iterator = set.iterator();
			int res = 0;
			int s = 0;
			int i = 0;
			boolean equalEl = false;
			if (n != set.size()) //якщо в послідовності задано декілька однакових чисел
				equalEl = true;
			while (iterator.hasNext()) {
				s = iterator.next() - i;
				if (s != 1) {
					res = i + 1;
					break;
				}
				i++;
			}
			if (equalEl && res == 0)
				res = i + 1;
			System.out.println(res);
		}
	}

}
