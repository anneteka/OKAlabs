import java.util.Comparator;

public class MergeSort {

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi, Comparator comp) {
        assert isSorted(a, lo, mid, comp); // precondition: a[lo..mid] sorted
        assert isSorted(a, mid + 1, hi, comp); // precondition: a[mid+1..hi] sorted
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(comp, aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
        assert isSorted(a, lo, hi, comp); // postcondition: a[lo..hi] sorted
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi, Comparator comp) {
        if (hi <= lo) return;
        else {
            int mid = lo + (hi - lo) / 2;
            sort(a, aux, lo, mid, comp);
            sort(a, aux, mid + 1, hi, comp);
            if (!less(comp, a[mid + 1], a[mid])) return;
            merge(a, aux, lo, mid, hi, comp);
        }
    }

    public static void sort(Comparable[] a, Comparator comp) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1, comp);
    }

    private static boolean isSorted(Comparable[] a, int l, int m, Comparator comp) {
        for (int i = l; i <= m; i++)
            if (less(comp, a[i], a[i - 1])) return false;
        return true;
    }

    private static boolean less(Comparator comp, Comparable v, Comparable w) {
        return comp.compare(v,w ) < 0;
    }
}
