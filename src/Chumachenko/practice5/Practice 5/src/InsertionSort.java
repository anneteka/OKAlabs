import java.util.Comparator;

public class InsertionSort {
	private static boolean less(Dog v, Dog w, Comparator<Dog> t){
		return t.compare(v, w)<0;
	}
	
	private static void exch(Dog[] a, int i, int j){
		Dog swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
	
	private static boolean isSorted(Dog[] a, Comparator<Dog> t){
		for (int i=1;i<a.length;i++)
			if (less(a[i],a[i-1],t)) return false;
		return true;
	}

	public static void sort(Dog[] a, Comparator<Dog> t){
		int n = a.length;
		for (int i=0;i<n;i++){
			for (int j = i;j>0;j--)
				if (less(a[j],a[j-1], t))
					exch(a,j,j-1);
				else break;
		}
	}
	public static void sort(Dog[] a, int l, int h, Comparator<Dog> t){
		for (int i=l;i<=h;i++){
			for (int j = i;j>0;j--)
				if (less(a[j],a[j-1],t))
					exch(a,j,j-1);
				else break;
		}
	}
}
