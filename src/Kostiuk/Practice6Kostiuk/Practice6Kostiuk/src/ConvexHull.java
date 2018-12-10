import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.ArrayList;

class Point implements Comparable<Point> {
	int x, y;

	public int compareTo(Point1 p) {
		if (this.x == p.x) {
			return this.y - p.y;
		} else {
			return this.x - p.x;
		}
	}

	public String toString() {
		return "("+x + "," + y+")";
	}

	@Override
	public int compareTo(Point o) {
		// TODO Auto-generated method stub
		return 0;
	}

}

public class ConvexHull {

	public static long cross(Point O, Point A, Point B) {
		return (A.x - O.x) * (B.y - O.y) - (A.y - O.y) * (B.x - O.x);
	}

	public static Point[] convex_hull(Point[] P) {

		if (P.length > 1) {
			int n = P.length, k = 0;
			Point[] H = new Point[2 * n];

			Arrays.sort(P);

			// Build lower hull
			for (int i = 0; i < n; ++i) {
				while (k >= 2 && cross(H[k - 2], H[k - 1], P[i]) <= 0)
					k--;
				H[k++] = P[i];
			}

			// Build upper hull
			for (int i = n - 2, t = k + 1; i >= 0; i--) {
				while (k >= t && cross(H[k - 2], H[k - 1], P[i]) <= 0)
					k--;
				H[k++] = P[i];
			}
			if (k > 1) {
				H = Arrays.copyOfRange(H, 0, k - 1); // remove non-hull vertices after k; remove k - 1 which is a duplicate
			}
			return H;
		} else if (P.length <= 1) {
			return P;
		} else{
			return null;
		}
	}

	private double x;
	private double y;
	  public void drawTo(Point[] hull) {
	        StdDraw.line(this.x, this.y, hull.length, hull.length);
	    }

	


	public static void main(String[] args) throws IOException {

		BufferedReader f = new BufferedReader(new FileReader("hull2.in")); 	// "hull.in"  Input Sample => size x y x y x y x y
		StringTokenizer st = new StringTokenizer(f.readLine());
		Point[] p = new Point[Integer.parseInt(st.nextToken())];
		for (int i = 0; i < p.length; i++) {
			p[i] = new Point();
			p[i].x = Integer.parseInt(st.nextToken()); // Read X coordinate 
			p[i].y = Integer.parseInt(st.nextToken()); // Read y coordinate
		}
		
		Point[] hull = convex_hull(p).clone();
		double x0=hull[0].x;
		double y0=hull[0].y;
		double x1, y1;
		for (int i = 0; i < hull.length; i++) {
			if (hull[i] != null) 
				System.out.print(hull[i]);
			x1=hull[i].x;
			y1=hull[i].y;
			StdDraw.line(x0, y0, x1,y1);	
			x0=hull[i].x;
			y0=hull[i].y;
		}
		
			
		 
	}
}
		
		
	

