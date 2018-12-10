import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

import com.sun.javafx.geom.Line2D;
import com.sun.prism.Graphics;

import java.util.ArrayList;

class Point1 implements Comparable<Point1> {
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

}

public class ConvexHull22 {

	public static long cross(Point1 O, Point1 A, Point1 B) {
		return (A.x - O.x) * (B.y - O.y) - (A.y - O.y) * (B.x - O.x);
	}

	public static Point1[] convex_hull(Point1[] P) {

		if (P.length > 1) {
			int n = P.length, k = 0;
			Point1[] H = new Point1[2 * n];

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
	  public void drawTo(Point1[] hull) {
	        StdDraw.line(this.x, this.y, hull.length, hull.length);
	    }
	  
	 
	@SuppressWarnings("null")
	public static void main(String[] args) throws IOException {
		StdDraw.setCanvasSize(600, 600);
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.setPenRadius(0.02);

		BufferedReader f = new BufferedReader(new FileReader("hull3.in")); 	// "hull.in"  Input Sample => size x y x y x y x y
		StringTokenizer st = new StringTokenizer(f.readLine());
		Point1[] p = new Point1[Integer.parseInt(st.nextToken())];
		for (int i = 0; i < p.length; i++) {
			p[i] = new Point1();
			p[i].x = Integer.parseInt(st.nextToken()); // Read X coordinate 
			p[i].y = Integer.parseInt(st.nextToken()); // Read y coordinate
		//	StdDraw.point(p[i].x, p[i].y);
		
		}
		double xx[]=null, yy[]=null;
		Point1[] hull = convex_hull(p).clone();
		for (int i = 0; i < hull.length; i++) {
			if (hull[i] != null) {
		System.out.print(hull[i]);
			
		}
		}

	
			//

	
		double x0=hull[0].x;
		double y0=hull[0].y;
		double x1, y1;
		for (int n = 1; n <hull.length; n++) {
			x1=hull[n].x;
			y1=hull[n].y;
			StdDraw.line(x0, y0, x1,y1);	
			x0=hull[n].x;
			y0=hull[n].y;
	}
	
		
	
}
	}

		
		
	

