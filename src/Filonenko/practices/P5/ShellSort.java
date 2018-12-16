import java.util.Comparator;

public class ShellSort {
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

        int h = 1;
        while (h<n/3) h = 3*h+1; // 1, 4, 13, 40, 121, 364, ...

        while(h>=1){
            for (int i = h; i<n;i++){
                for (int j=i; j>=h;j-=h)
                    if (less(a[j],a[j-h], comparator))
                        exch(a,j,j-h);
                    else break;
            }
            h=h/3;
        }
    }
}
