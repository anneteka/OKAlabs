import java.util.Comparator;

public class SelectionSort {
    private static boolean less(Comparable v, Comparable w, Comparator comparator){
        return comparator.compare(v,w) <0;
    }

    private static void exch(Comparable[] a, int i, int j){
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void sort(Comparable[] a, Comparator comparator){
        int n = a.length;
        for (int i=0;i<n;i++){
            int min = i;
            for (int j = i+1;j<n;j++)
                if (less(a[j],a[min], comparator))
                    min = j;
            exch(a,i,min);
        }
}
}
