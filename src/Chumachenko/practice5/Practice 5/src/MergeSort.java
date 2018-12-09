import java.util.Comparator;

public class MergeSort {

	private static final int CUTOFF =7;
	
	private static void merge(Dog[] a, Dog[] aux, int lo, int mid, int hi, Comparator<Dog> t){
		assert isSorted(a, lo, mid, t); // precondition: a[lo..mid] sorted
		assert isSorted(a, mid+1, hi, t); // precondition: a[mid+1..hi] sorted
		for (int k = lo; k <= hi; k++)
			aux[k] = a[k];
		int i = lo, j = mid+1;
		for (int k = lo; k <= hi; k++){
			if (i > mid) a[k] = aux[j++];
			else if (j > hi) a[k] = aux[i++];
			else if (less(aux[j], aux[i], t)) a[k] = aux[j++];
			else a[k] = aux[i++];
		}
		assert isSorted(a, lo, hi, t); // postcondition: a[lo..hi] sorted
	}
	
	private static void sort(Dog[] a, Dog[] aux, int lo, int hi, Comparator<Dog> t){
		if (hi <= lo) return;
		if (hi <= lo + CUTOFF - 1) InsertionSort.sort(a,lo,hi,t);
		else {int mid = lo + (hi - lo) / 2;
			sort(a, aux, lo, mid, t);
			sort(a, aux, mid+1, hi, t);
			if (!less(a[mid+1],a[mid],t)) return;
			merge(a, aux, lo, mid, hi, t);
		}
	}
	
	public static void sort(Dog[] a, Comparator<Dog> t)	{
		Dog[] aux = new Dog[a.length];
		sort(a, aux, 0, a.length - 1, t);
	}
	
	private static boolean isSorted(Dog[] a, int l, int m, Comparator<Dog> t){
		for (int i=l;i<=m;i++)
			if (less(a[i],a[i-1], t)) return false;
		return true;
	}
	
	private static boolean less(Dog v, Dog w, Comparator<Dog>t){
		return t.compare(v,w)<0;
	}
}
