package ConvexHull;

import java.util.Comparator;

/**
 * Merge Sort with comparator for Point2D
 *
 */
public class MergeSort {

	private static void merge(Point2D[] array, Point2D[] aux, Comparator comp, int lo, int mid, int hi) {
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

	private static void merge(Point2D[] array, Point2D[] aux, int lo, int mid, int hi) {
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

	private static void sort(Point2D[] array, Point2D[] aux, Comparator comp, int lo, int hi) {
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

	private static void sort(Point2D[] array, Point2D[] aux, int lo, int hi) {
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

	public static void sort(Point2D[] array, Comparator comp) {
		Point2D[] aux = new Point2D[array.length];
		sort(array, aux, comp, 0, array.length - 1);
	}

	public static void sort(Point2D[] array) {
		Point2D[] aux = new Point2D[array.length];
		sort(array, aux, 0, array.length - 1);
	}

	private static boolean isSorted(Point2D[] array, Comparator comp, int l, int m) {
		for (int i = l; i <= m; i++)
			if (less(array[i], array[i - 1], comp))
				return false;
		return true;
	}

	private static boolean less(Point2D a, Point2D a2, Comparator comp) {
		return comp.compare(a, a2) < 0;
	}

	private static boolean less(Point2D array, Point2D array2) {
		return array.compareTo(array2) < 0;
	}
}
