package Practice4;
public class ShellSort1 {

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

	public static void sort(Comparable[] a,Comparable[] b){
		int n = a.length;
		for (int i=0;i<n;i++){
			int min = i;
			for (int j = i+1;j<n;j++)
				if (less(a[j],a[min]))
					min = j;
			exch(a,i,min);
			exch(b,i,min);
		}
	}
	
}
