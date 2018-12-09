import java.util.Comparator;

public class ShellSort {
	private static boolean less(Student v, Student w,Comparator<Student> d){
		return d.compare(v,w)<0;
	}
	
	private static void exch(Student[] a, int i, int j){
		Student swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
	
/*	private static boolean isSorted(Student[] a){
		for (int i=1;i<a.length;i++)
			if (less(a[i],a[i-1])) return false;
		return true;
	}*/

	public static void sort(Student[] a,Comparator<Student> d){
		int n = a.length;
		
		int h = 1;
		while (h<n/3) h = 3*h+1;
		
		while(h>=1){
			for (int i = h; i<n;i++){
				for (int j=i; j>=h;j-=h)
					if (less(a[j],a[j-h],d))
						exch(a,j,j-h);
					else break;
			}
			h=h/3;
		}
	}
}
