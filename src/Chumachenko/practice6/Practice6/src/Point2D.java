import java.util.Comparator;

public class Point2D implements Comparable<Point2D>{

	double x;
	double y;
	PolarOrder POLAR_ORDER = new PolarOrder();
	Point2D(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 *  
	 * @param a
	 * @param b
	 * @param c
	 * @return if a->b->->c == ccw return 1;
	 */
	public static int ccw(Point2D a, Point2D b, Point2D c) {
	    double res = (b.x-a.x)*(c.y-a.y)-(b.y-a.y)*(c.x-a.x);
	    if(res>0) return 1;
	    if(res<0) return -1;
		return 0;
		}
	
	public boolean equals(Object other) {
if((this.x == ((Point2D) other).x)&&(this.y == ((Point2D) other).y)) return true;
		return false;
		}
	
	public String toString() {
		
		return "("+this.x+"; "+this.y+");";
		}
	
	public void draw() {
		
		
	}
	
	public void drawTo(Point2D that) {
		   StdDraw.line(this.x, this.y, that.x, that.y);
	}
	
	
	public int compareTo(Point2D that) {
		if(this.y<that.y) return -1;
    	
    	if(this.y==that.y)return 0;
		return 1;
	}
	
	private class PolarOrder implements Comparator<Point2D>{

		@Override
		public int compare(Point2D o1, Point2D o2) {
			 if((o1.y>Point2D.this.y)&&(o2.y<Point2D.this.y))return -1;
			 if((Point2D.this.y>o1.y)&&(Point2D.this.y<o2.y))return 1;
			 return ccw(Point2D.this, o1, o2);
		}
		
	}
	
	
}
