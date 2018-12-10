/**
 * сортування вибором
 */
public class SelectionSort {

	/**
	 * повертає чи перший об'єкт менший за другий
	 */
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
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
	private static boolean isSorted(Comparable[] a) {
		for (int i = 1; i < a.length; i++)
			if (less(a[i], a[i - 1]))
				return false;
		return true;
	}

	/**
	 * сортування вибором
	 */
	public static void sort(Comparable[] a) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int min = i;
			for (int j = i + 1; j < n; j++)
				if (less(a[j], a[min]))
					min = j;
			exch(a, i, min);
		}
	}

}
