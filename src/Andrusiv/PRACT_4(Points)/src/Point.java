import java.util.Comparator; 
import edu.princeton.cs.algs4.*; 

public class Point implements Comparable<Point> {

	
    public final Comparator<Point> SLOPE_ORDER ;
    
    private final int x;
    private final int y;

    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
        SLOPE_ORDER=new SOrder(this);
    }

    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

   
    
    public double slopeTo(Point that) {
    	/* YOUR CODE HERE*/
    	if(this.x==that.x&&this.y==that.y) return Double.NEGATIVE_INFINITY;
    	else if(this.x==that.x) return Double.POSITIVE_INFINITY;
    	else if(this.y==that.y) return 0;
    	
    	else {
    	//	System.out.println((double)(that.y-this.y)/(that.x-this.x));
    		return (double)(that.y-this.y)/(that.x-this.x);
    	}
    }

    public int compareTo(Point that) {
    	/* YOUR CODE HERE  Точка (x0,y0) менша за точку (x1,y1), тоді і тільки тоді, 
    	 * коли y0<y1 або y0=y1 і x0<x1. */
    	if(this.y<that.y) return -1;
    	else if(this.y==that.y&&this.x<that.x) return 0;
    	return 1;
    }
    
    
    
    private static class SOrder implements Comparator<Point>{
    	private Point point;
    	public SOrder(Point p) {
    		super();
    		this.point=p;
    	}
    	
    	public int compare(Point p, Point q){
    		/* YOUR CODE HERE 
    		 * Формально точка (x1,y1) менша за точку (x2,y2), тоді і тільки тоді,
    		 *  коли нахил (y1-y0)/(x1-x0) менше, ніж нахил (y2-y0)/(x2-x0). */
    		
    				return Double.compare(point.slopeTo(p), point.slopeTo(q));
    	}
    }

    
    
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }
}