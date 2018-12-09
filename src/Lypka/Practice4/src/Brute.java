import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Brute {

	public static void main(String[] args) {
		
		try {
			File file = new File("input\\grid6x6.txt");
			Scanner in = new Scanner(file);
			
			int n = in.nextInt();
			Point[] points = new Point[n];
			
			StdDraw.setXscale(0, 32768);
			StdDraw.setYscale(0, 32768);
			
			for(int i = 0; i < n; i++) {				
				points[i] = new Point(in.nextLong(), in.nextLong());
				points[i].draw();
			}

			for(int i = 0; i < n - 3; i++)
				for(int j = i + 1; j < n - 2; j++)
					for(int k = j + 1; k < n - 1; k++)
						for(int l = k + 1; l < n; l++) {
							if(points[i].slopeTo(points[j]) == points[i].slopeTo(points[k]) && points[i].slopeTo(points[j]) == points[i].slopeTo(points[l])) {
								System.out.println(points[i] + " -> " + points[j] + " -> " + points[k] + " -> " + points[l]);
								Point[] temp = new Point[4];
								temp[0] = points[i]; temp[1] = points[j]; temp[2] = points[k]; temp[3] = points[l];
								drawLine(temp);
							}
						}
			
			in.close();
		}catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
	}
	
	public static void drawLine(Point[] p) {
		Point from = p[0], to = p[1];
		
		for(int i = 0; i < p.length; i++) {
			boolean min = true, max = true;
			for(int j = 0; j < p.length; j++) {
				if(i == j) continue;
				if(p[i].compareTo(p[j]) < 0) max = false;
				if(p[i].compareTo(p[j]) > 0) min = false;
			}
			if(min) from = p[i];
			if(max) to = p[i];
		}
		
		from.drawTo(to);
	}
}
