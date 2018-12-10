import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	private static ArrayList<Point2D> sphere = new ArrayList<>();

	public static void main(String[] args) {
		Scanner scn;
		try {
			scn = new Scanner(new FileReader("rs1423.txt"));
		} catch (FileNotFoundException fnf) {
			System.out.println("File not found");
			return;
		}

		StdDraw.setYscale(0, 32685);
        StdDraw.setXscale(0,32685);
		StdDraw.show();
		StdDraw.setPenRadius(0.01);
		StdDraw.setPenColor(Color.blue);

		int n = scn.nextInt();
		Point2D[] points = new Point2D[n];
		int numberMinY = 0;
		Point2D minX = new Point2D(32680,32680);
		for (int i = 0; i < n; i++) {
			points[i] = new Point2D(scn.nextInt(), scn.nextInt());
			points[i].draw();
			if ((points[i].getY() <= points[numberMinY].getY()) || 
					(points[i].getY() == points[numberMinY].getY() && points[i].getX() >= points[numberMinY].getX())) {
				numberMinY = i;
			}
			if((minX.getX()>points[i].getX()) || 
					(minX.getX()==points[i].getX() && minX.getY()>points[i].getY())){
				minX = points[i];
			}
		}
		Point2D now = points[0];
		points[0] = points[numberMinY];
		points[numberMinY] = now;
		
		sphere.add(points[0]);
		Point2D.setComparableOrder(points[0]);
		Point2D[] p = new Point2D[n-2];
		
		Point2D min = points[0], max = points[0];
		for (int i = 0, k=1; k < n-1; i++, k++) {
				p[i] = points[k];
		}
		points = p;
		
		QuickSort<Point2D> quick = new QuickSort<Point2D>();
		quick.sort(points, Point2D.POLAR_ORDER);
		sphere.add(points[0]);
		for(int i=1; i<n-2; i++) {
			sphere.add(points[i]);
				while(Point2D.ccw(sphere.get(sphere.size()-3), sphere.get(sphere.size()-2), sphere.get(sphere.size()-1))<=0) {
					sphere.remove(sphere.size()-2);
					if(sphere.size()<=2) break;
				}
		}
		//sphere.add(minX);
		
		StdDraw.setPenRadius(0.005);
		StdDraw.setPenColor(Color.green);
		for (int i = 0; i < sphere.size()-1; i++) {
			sphere.get(i).drawTo(sphere.get(i+1));
		}
		sphere.get(0).drawTo(sphere.get(sphere.size()-1));
	}

}
