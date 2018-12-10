package lin;

public class InsertionSort {
	private static boolean less(Comparable v, Comparable w){
		return v.compareTo(w)<0;
	}
	
	private static void exch(Point[] a, int i, int j){
		Point swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
	
	private static boolean isSorted(Point[] a){
		for (int i=1;i<a.length;i++)
			if (less(a[i],a[i-1])) return false;
		return true;
	}

	public static void sort(Point[] a){
		int n = a.length;
		for (int i=0;i<n;i++){
			for (int j = i;j>0;j--)
				if (less(a[j],a[j-1]))
					exch(a,j,j-1);
				else break;
		}
	}
	public static void sort(Point[] a, int l, int h){
		for (int i=l;i<=h;i++){
			for (int j = i;j>0;j--)
				if (less(a[j],a[j-1]))
					exch(a,j,j-1);
				else break;
		}
	}
}
