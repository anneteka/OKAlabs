import java.util.Comparator;

/**
 * сортування за алгоритмом шела
 *
 */
public class ShellSort {
	private static boolean less(Point v, Point w, Comparator<Point> comparator) {
		return comparator.compare(v, w) < 0;
	}

	private static boolean less(Point v, Point w) {
		return v.compareTo(w) < 0;
	}

	private static void exch(Point[] a, int i, int j) {
		Point swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

	/**
	 * сортування, яке використовує компаратор
	 */
	public static void sort(Point[] a, Comparator<Point> sLOPE_ORDER) {
		int n = a.length;

		int h = 1;
		while (h < n / 3)
			h = 3 * h + 1; // 1, 4, 13, 40, 121, 364, ...

		while (h >= 1) {
			for (int i = h; i < n; i++) {
				for (int j = i; j >= h; j -= h)
					if (less(a[j], a[j - h], sLOPE_ORDER))
						exch(a, j, j - h);
					else
						break;
			}
			h = h / 3;
		}
	}

	public static void sort(Point[] a) {
		int n = a.length;

		int h = 1;
		while (h < n / 3)
			h = 3 * h + 1; // 1, 4, 13, 40, 121, 364, ...

		while (h >= 1) {
			for (int i = h; i < n; i++) {
				for (int j = i; j >= h; j -= h)
					if (less(a[j], a[j - h]))
						exch(a, j, j - h);
					else
						break;
			}
			h = h / 3;
		}
	}
}
