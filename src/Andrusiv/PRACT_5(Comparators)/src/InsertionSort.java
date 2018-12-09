import java.util.Comparator;

//insertion sort for students alphabet
public class InsertionSort {
	private static boolean less(Student v, Student w,Comparator<Student> d){
		return d.compare(v,w)<0;
	}
	
	private static void exch(Student[] a, int i, int j){
		Student swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
	
	private static boolean isSorted(Student[] a,Comparator<Student> d){
		for (int i=1;i<a.length;i++)
			if (less(a[i],a[i-1],d)) return false;
		return true;
	}

	public static void sort(Student[] a,Comparator<Student> d){
		int n = a.length;
		for (int i=0;i<n;i++){
			for (int j = i;j>0;j--)
				if (less(a[j],a[j-1],d))
					exch(a,j,j-1);
				else break;
		}
	}
	
	public static void sort(Student[] a, int l, int h,Comparator<Student> d){
		for (int i=l;i<=h;i++){
			for (int j = i;j>0;j--)
				if (less(a[j],a[j-1],d))
					exch(a,j,j-1);
				else break;
		}
	}
}
