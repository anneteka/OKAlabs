import java.util.Comparator;

/**
 * сортування Шела (частинами)
 *
 */
public class ShellSort {
	/**
	 * повертаємо чи перший об'єкт менший за другий
	 */
	private static boolean less(Comparable v, Comparable w, Comparator comp) {
		return comp.compare(v, w) < 0;
	}

	/**
	 * міняємо об'єкти місцями
	 */
	private static void exch(Comparable[] a, int i, int j) {
		Comparable swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

	/**
	 * чи відсортован масив
	 */
	private static boolean isSorted(Comparable[] a, Comparator comp) {
		for (int i = 1; i < a.length; i++)
			if (less(a[i], a[i - 1], comp))
				return false;
		return true;
	}

	/**
	 * сортування частинами
	 */
	public static void sort(Comparable[] a, Comparator comp) {
		int n = a.length;

		int h = 1;
		while (h < n / 3)
			h = 3 * h + 1; // 1, 4, 13, 40, 121, 364, ...

		while (h >= 1) {
			for (int i = h; i < n; i++) {
				for (int j = i; j >= h; j -= h)
					if (less(a[j], a[j - h], comp))
						exch(a, j, j - h);
					else
						break;
			}
			h = h / 3;
		}
	}
}
