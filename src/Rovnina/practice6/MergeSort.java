import java.util.Comparator;

/**
 * сортування злиттям
 *
 */
public class MergeSort {

	/**
	 * злиття
	 */
	private static void merge(Comparable[] array, Comparable[] aux, Comparator comp, int lo, int mid, int hi) {
		assert isSorted(array, comp, lo, mid); // precondition: a[lo..mid] sorted
		assert isSorted(array, comp, mid + 1, hi); // precondition: a[mid+1..hi] sorted
		for (int k = lo; k <= hi; k++)
			aux[k] = array[k];
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid)
				array[k] = aux[j++];
			else if (j > hi)
				array[k] = aux[i++];
			else if (less(aux[j], aux[i], comp))
				array[k] = aux[j++];
			else
				array[k] = aux[i++];
		}
		assert isSorted(array, comp, lo, hi); // postcondition: a[lo..hi] sorted
	}
	
	/**
	 * злиття без компаратора
	 */
	private static void merge(Comparable[] array, Comparable[] aux, int lo, int mid, int hi) {
		for (int k = lo; k <= hi; k++)
			aux[k] = array[k];
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid)
				array[k] = aux[j++];
			else if (j > hi)
				array[k] = aux[i++];
			else if (less(aux[j], aux[i]))
				array[k] = aux[j++];
			else
				array[k] = aux[i++];
		}
		
	}

	/**
	 * сортування
	 */
	private static void sort(Comparable[] array, Comparable[] aux, Comparator comp, int lo, int hi) {
		if (hi <= lo)
			return;
		else {
			int mid = lo + (hi - lo) / 2;
			sort(array, aux, comp, lo, mid);
			sort(array, aux, comp, mid + 1, hi);
			if (!less(array[mid + 1], array[mid], comp))
				return;
			merge(array, aux, comp, lo, mid, hi);
		}
	}
	
	/**
	 * сортування без компаратора
	 */
	private static void sort(Comparable[] array, Comparable[] aux, int lo, int hi) {
		if (hi <= lo)
			return;
		else {
			int mid = lo + (hi - lo) / 2;
			sort(array, aux, lo, mid);
			sort(array, aux, mid + 1, hi);
			if (!less(array[mid + 1], array[mid]))
				return;
			merge(array, aux, lo, mid, hi);
		}
	}

	/**
	 * виклик сортування. створення додаткового масива
	 */
	public static void sort(Comparable[] array, Comparator comp) {
		Comparable[] aux = new Comparable[array.length];
		sort(array, aux, comp, 0, array.length - 1);
	}
	
	/**
	 * виклик сортування без компаратора. створення додаткового масива
	 */
	public static void sort(Comparable[] array) {
		Comparable[] aux = new Comparable[array.length];
		sort(array, aux, 0, array.length - 1);
	}

	/**
	 * чи відсортован масив
	 */
	private static boolean isSorted(Comparable[] array, Comparator comp, int l, int m) {
		for (int i = l; i <= m; i++)
			if (less(array[i], array[i - 1], comp))
				return false;
		return true;
	}

	/**
	 * повертає чи менше перший об'єкт
	 */
	private static boolean less(Comparable a, Comparable a2, Comparator comp) {
		return comp.compare(a, a2) < 0;
	}
	
	/**
	 * повертає чи менше перший об'єкт
	 */
	private static boolean less(Comparable array, Comparable array2) {
		return array.compareTo(array2) < 0;
	}
}
