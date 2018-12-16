import java.util.Comparator;

public class MergeSort {


    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi, Comparator comparator){
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++){
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i], comparator)) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi, Comparator comparator){
        if (hi <= lo) return;
        else {int mid = lo + (hi - lo) / 2;
            sort(a, aux, lo, mid, comparator);
            sort(a, aux, mid+1, hi, comparator);
            if (!less(a[mid+1],a[mid], comparator)) return;
            merge(a, aux, lo, mid, hi, comparator);
        }
    }

    public static void sort(Comparable[] a, Comparator comparator)	{
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1, comparator);
    }


    private static boolean less(Comparable v, Comparable w, Comparator comparator){
        return comparator.compare(v,w)<0;
    }
}
