import java.util.Comparator;

public class Point implements Comparable<Point> {
	
    public final Comparator<Point> SLOPE_ORDER = new SOrder();

    private final long x;
    private final long y;

    public Point(long x, long y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }
    
    public long getX() {
    	return x;
    }
    
    public long getY() {
    	return y;
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
    	if(this.y == that.y && this.x == that.x) return Double.POSITIVE_INFINITY;
    	if(this.y == that.y) return 0;
    	if(this.x == that.x) return Double.NEGATIVE_INFINITY;
		return (double)(that.y - this.y) / (that.x - this.x);
    }

    public int compareTo(Point that) {
    	if(this.y < that.y) return -1;
    	if(this.y > that.y) return 1;
    	if(this.x < that.x) return -1;
    	if(this.x > that.x) return 1;
		return 0;
    }

	private class SOrder implements Comparator<Point> {
		public int compare(Point p, Point q) {
			if(Point.this.slopeTo(p) < Point.this.slopeTo(q)) return -1;
			if(Point.this.slopeTo(p) > Point.this.slopeTo(q)) return 1;
			return 0;
		}
	}

	public String toString() {
		/* DO NOT MODIFY */
		return "(" + x + ", " + y + ")";
	}
}