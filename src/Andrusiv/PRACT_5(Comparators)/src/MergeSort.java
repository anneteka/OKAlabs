import java.util.Comparator;

//merge sort by sorting students alphabet
public class MergeSort {


	private static final int CUTOFF =7;
	
	private static void merge(Student[] a, Student[] aux, int lo, int mid, int hi,Comparator<Student> d){
		assert isSorted(a, lo, mid,d); // precondition: a[lo..mid] sorted
		assert isSorted(a, mid+1, hi,d); // precondition: a[mid+1..hi] sorted
		for (int k = lo; k <= hi; k++)
			aux[k] = a[k];
		int i = lo, j = mid+1;
		for (int k = lo; k <= hi; k++){
			if (i > mid) a[k] = aux[j++];
			else if (j > hi) a[k] = aux[i++];
			else if (less(aux[j], aux[i],d)) a[k] = aux[j++];
			else a[k] = aux[i++];
		}
		assert isSorted(a, lo, hi,d); // postcondition: a[lo..hi] sorted
	}
	
	private static void sort(Student[] a, Student[] aux, int lo, int hi,Comparator<Student> d){
		if (hi <= lo) return;
		if (hi <= lo + CUTOFF - 1) InsertionSort.sort(a,lo,hi,d);
		else {int mid = lo + (hi - lo) / 2;
			sort(a, aux, lo, mid,d);
			sort(a, aux, mid+1, hi,d);
			if (!less(a[mid+1],a[mid],d)) return;
			merge(a, aux, lo, mid, hi,d);
		}
	}
	
	public static void sort(Student[] a,Comparator<Student> d)	{
	
		Student[] aux = new Student[a.length];
		sort(a, aux, 0, a.length - 1,d);
	}
	
	private static boolean isSorted(Student[] a, int l, int m, Comparator<Student> d){
		for (int i=l;i<=m;i++)
			if (less(a[i],a[i-1],d)) return false;
		return true;
	}
	
	private static boolean less(Student v, Student w, Comparator<Student> d){
		return d.compare(v,w)<0;
	}
}
