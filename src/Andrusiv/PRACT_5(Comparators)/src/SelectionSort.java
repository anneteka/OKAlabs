import java.util.Comparator;

//selection sort by sorting students grades
public class SelectionSort {

	private static boolean less(Student v, Student w,Comparator<Student> d){
		return d.compare(v,w)<0;
	}
	
	private static void exch(Student[] a, int i, int j){
		Student swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
	
	/*private static boolean isSorted(Student[] a){
		for (int i=1;i<a.length;i++)
			if (less(a[i],a[i-1])) return false;
		return true;
	}
*/
	public static void sort(Student[] a,Comparator<Student> d){
		int n = a.length;
		for (int i=0;i<n;i++){
			int min = i;
			for (int j = i+1;j<n;j++)
				if (less(a[j],a[min],d))
					min = j;
			exch(a,i,min);
		}
	}
	
}
