import java.util.Comparator;

import princeton.lib.StdDraw;

public class Point implements Comparable<Point> {

    
    public final Comparator<Point> SLOPE_ORDER = new SOrder();

    private final int x;
    private final int y;

    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    
    public void draw() {
        StdDraw.point(x, y);
    }

    
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

  
    public double slopeTo(Point that) {
    	if(this.compareTo(that)==0)return Double.NEGATIVE_INFINITY;
    	if(this.x==that.x)return Double.POSITIVE_INFINITY;
    	if(this.y==that.y)return 0;
    	return (that.y - this.y) / (double) (that.x - this.x);
    }

  
    public int compareTo(Point that) {
    	if(this.y<that.y)return -1;
    	if(this.y==this.y){
    		if(this.x<that.x)return -1;
    		if(this.x==that.x)return 0;
    	}
		return 1;
    }
    
    private class SOrder implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            double slope1 = Point.this.slopeTo(p1);
            double slope2 = Point.this.slopeTo(p2);
            if (slope1 == slope2) {
                return 0;
            }
            if (slope1 < slope2) {
                return -1;
            }
            return 1;
        }
    }

   
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
    
    public static void main(String[] args) {
    	StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
		Point point=new Point(2, 3);
		point.draw();
		Point point2=new Point(7000,500);
		point2.draw();
		point.drawTo(point2);
		System.out.println(point.compareTo(point2));
	}
    
    
}