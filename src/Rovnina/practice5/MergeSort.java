import java.util.Comparator;

/**
 * сортування злиттям
 *
 */
public class MergeSort {

	/**
	 * злиття
	 */
	private static void merge(Comparable[] a, Comparable[] aux, Comparator comp, int lo, int mid, int hi) {
		assert isSorted(a, comp, lo, mid); // precondition: a[lo..mid] sorted
		assert isSorted(a, comp, mid + 1, hi); // precondition: a[mid+1..hi] sorted
		for (int k = lo; k <= hi; k++)
			aux[k] = a[k];
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid)
				a[k] = aux[j++];
			else if (j > hi)
				a[k] = aux[i++];
			else if (less(aux[j], aux[i], comp))
				a[k] = aux[j++];
			else
				a[k] = aux[i++];
		}
		assert isSorted(a, comp, lo, hi); // postcondition: a[lo..hi] sorted
	}

	/**
	 * сортування
	 */
	private static void sort(Comparable[] a, Comparable[] aux, Comparator comp, int lo, int hi) {
		if (hi <= lo)
			return;
		else {
			int mid = lo + (hi - lo) / 2;
			sort(a, aux, comp, lo, mid);
			sort(a, aux, comp, mid + 1, hi);
			if (!less(a[mid + 1], a[mid], comp))
				return;
			merge(a, aux, comp, lo, mid, hi);
		}
	}

	/**
	 * виклик сортування. створення додаткового масива
	 */
	public static void sort(Comparable[] a, Comparator comp) {
		Comparable[] aux = new Comparable[a.length];
		sort(a, aux, comp, 0, a.length - 1);
	}

	/**
	 * чи відсортован масив
	 */
	private static boolean isSorted(Comparable[] a, Comparator comp, int l, int m) {
		for (int i = l; i <= m; i++)
			if (less(a[i], a[i - 1], comp))
				return false;
		return true;
	}

	/**
	 * повертає чи менше перший об'єкт
	 */
	private static boolean less(Comparable v, Comparable w, Comparator comp) {
		return comp.compare(v, w) < 0;
	}
}
