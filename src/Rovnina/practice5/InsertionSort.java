import java.util.Comparator;

/**
 * сортування вставкою
 */
public class InsertionSort {
	/**
	 * повертає чи перший об'єкт менший за другий
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
	 * сортування вставкою
	 */
	public static void sort(Comparable[] a, Comparator comp) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			for (int j = i; j > 0; j--)
				if (less(a[j], a[j - 1], comp))
					exch(a, j, j - 1);
				else
					break;
		}
	}
}
