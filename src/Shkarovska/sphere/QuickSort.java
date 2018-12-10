import java.util.Comparator;

public class QuickSort<T extends Comparable> {
	private Comparator<T> comparator;
	
	 public void sort(T[] a, Comparator<T> comparator) {
		 this.comparator = comparator;
		 StdRandom.shuffle(a);
		 sort(a, 0, a.length - 1);
	    }
	 
	 public void sort(T[] a){
		 StdRandom.shuffle(a);
		 sort(a, 0, a.length - 1);
		 }
		 private void sort(T[] a, int lo, int hi){
		 if (hi <= lo) return;
		 int j = partition(a, lo, hi);
		 sort(a, lo, j-1);
		 sort(a, j+1, hi);
		 }

	 private int partition(T[] a, int lo, int hi){
		 int i = lo, j = hi+1;
		 while (true){
		 while (less(a[++i], a[lo]))
		 if (i == hi) break;
		 while (less(a[lo], a[--j]))
		 if (j == lo) break;
		 if (i >= j) break;
		 exch(a, i, j);
		 }
		 exch(a, lo, j);
		 return j;
		 }
	 private boolean less(T a, T b) {
		 return comparator.compare(a, b)<0;
	 }
	 private void exch(T[] a, int lo, int j) {
		 T now = a[lo];
		 a[lo] = a[j];
		 a[j] = now;
	 }
}
