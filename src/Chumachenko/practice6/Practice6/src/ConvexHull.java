import java.awt.color.CMMException;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;

public class ConvexHull {

	Point2D[] array;
	Point2D[] newArray;
	ArrayList<Point2D> list = new ArrayList<Point2D>();
	int size;
	
	ConvexHull(File file) throws FileNotFoundException{
		reading(file);
		rewrite();
		draw();
		drawing();
	}
	
	private void drawing() {
	
	   for(int i = 0; i<list.size()-1; i++) {
		   list.get(i).drawTo(list.get(i+1));

	   }
	   list.get(0).drawTo(list.get(list.size()-1));
	}
	private void draw() {
	     MergeSort.sort(array, array[0].POLAR_ORDER);
	    Point2D a;
	    Point2D b;
	    Point2D c;
	
	  
	 
	     for(Point2D f:array) {  System.out.println(f+ " ");
	     list.add(f);}
	    int i = 0;
	    while(i+2!=list.size()) {
	    	a=list.get(i);
	    	b=list.get(i+1);
	    	if((i+2)==list.size())
	    		c=list.get(0);
	    	else
	    		c=list.get(i+2);
	    	if(Point2D.ccw(a, b, c)<0)
	    		i++;
	    	else {
	    		list.remove(i+1);
	    	if(i!=0) i--;
	    }
	    }
	     System.out.println(list.toString());
	
	}

	private void rewrite() {
	    Arrays.sort(array );
	    newArray = new Point2D[size];
	 
	}

	private void reading(File file) throws FileNotFoundException{
		Scanner sc = new Scanner(file);
	    size = sc.nextInt();
	    array = new Point2D[size];
	    for(int i =0 ; i<size; i++) {
	    Point2D point =	 new Point2D(sc.nextInt(),sc.nextInt());
		   array[i] = point;
		   point.draw();
	    }
	}
	
}
