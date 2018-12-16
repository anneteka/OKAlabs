import java.util.Comparator;

public class InsertionSort {
    private static boolean less(Comparable v, Comparable w, Comparator comparator){
        return comparator.compare(v,w) <0;
    }

    private static void exch(Comparable[] a, int i, int j){
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static boolean isSorted(Comparable[] a, Comparator comparator){
        for (int i=1;i<a.length;i++)
            if (less(a[i],a[i-1], comparator)) return false;
        return true;
    }

    public static void sort(Comparable[] a, Comparator comparator){
        int n = a.length;
        for (int i=0;i<n;i++){
            for (int j = i;j>0;j--)
                if (less(a[j],a[j-1], comparator))
                    exch(a,j,j-1);
                else break;
        }
    }
}
