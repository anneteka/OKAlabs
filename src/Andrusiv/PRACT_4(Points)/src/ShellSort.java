import java.util.Comparator;

public class ShellSort {
	private static boolean less(Point v, Point w,Comparator<Point> d){
		return d.compare(v,w)<0;
	}
	
	private static void exch(Point[] a, int i, int j){
		Point swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
	
	private static boolean isSorted(Point[] a,Comparator<Point> d){
		for (int i=1;i<a.length;i++)
			if (less(a[i],a[i-1],d)) return false;
		return true;
	}

	public static void sort(Point[] a,Comparator<Point> d){
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
