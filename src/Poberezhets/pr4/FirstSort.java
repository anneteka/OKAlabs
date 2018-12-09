package Practice4;
public class FirstSort {
	
	private static boolean less(Comparable v, Comparable w){
		return v.compareTo(w)<0;
	}
	
	private static void exch(Comparable[] a, int i, int j){
		Comparable swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
	
	private static boolean isSorted(Comparable[] a){
		for (int i=1;i<a.length;i++)
			if (less(a[i],a[i-1])) return false;
		return true;
	}

	public static void sort(Comparable[] a){
		int n = a.length;
		for (int i=0;i<n;i++)
			for (int j = i;j>0;j--)
				if (a[j].compareTo(a[j-1])<0)
					exch(a,j,j-1);
				else break;
	}
}
