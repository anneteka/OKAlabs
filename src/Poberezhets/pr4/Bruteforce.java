package Practice4;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

import lib.StdDraw;

/**
 * (Done) підчистити стрілку Brute force. Напишіть програму Brute.java, що
 * досліджує 4 точки за один раз і перевіряє, чи всі вони лежать в одному
 * сегменті лінії, виводьте кожну таку лінію і малюйте використовуючи StdDraw.
 * 
 * Для перевірки того, що 4 точки p,q,r,s колінеарні, перевіряйте градієнт між p
 * і q, p і r, p і s. Порядок зростання часу роботи в найгіршому випадку має
 * бути N4 і використовувати простір пропорційний N.
 * 
 * @author Богдана
 *
 */

public class Bruteforce {
	private static final String file = "D://words3.txt";
	private static final String file1 = "D://grid6x6.txt";
	private static final String file2 = "D://horizontal100.txt";
	private static final String file3 = "D://input6.txt";
	private static final String file4 = "D://input8.txt";
	private static final String file5 = "D://input40.txt";
	private static final String file6 = "D://input50.txt";
	private static final String file7 = "D://input56.txt";
	private static final String file8 = "D://input100.txt";
	private static final String file9 = "D://input400.txt";
	private static final String file10 = "D://rs1423.txt";
	
	public static void main(String[] args) throws IOException {
		Bruteforce brute = new Bruteforce();
		brute.bruteForce(brute.readPoint(file5));

	}

	/**
	 * Для перевірки того, що 4 точки p,q,r,s колінеарні, перевіряйте градієнт між .
	 * Порядок зростання часу роботи в найгіршому випадку має бути N4 і
	 * використовувати простір пропорційний N.
	 * 
	 * @param points
	 */
	/**
	 * (14000, 10000) -> (18000, 10000) -> (19000, 10000) -> (21000, 10000)
	 * 
	 * (14000, 10000) -> (18000, 10000) -> (19000, 10000) -> (32000, 10000)
	 * 
	 * (14000, 10000) -> (18000, 10000) -> (21000, 10000) -> (32000, 10000)
	 * 
	 * (14000, 10000) -> (19000, 10000) -> (21000, 10000) -> (32000, 10000)
	 * 
	 * (18000, 10000) -> (19000, 10000) -> (21000, 10000) -> (32000, 10000)
	 * 
	 * @param points
	 */
	// private void bruteForce(Point[] points) {
	// StdDraw.setXscale(0, 32768);
	// StdDraw.setYscale(0, 32768);
	// for(int i=0; i<points.length;i++) {
	// points[i].draw();
	// }
	// int n = points.length;
	// for (int p = 0; p < n; p++) {
	//
	// for (int q = p; q < n; q++) {
	//
	// for (int r = q; r < n; r++) {
	//
	// for (int s = r; s < n; s++) {
	// if(points[p].slopeTo(points[q])==points[p].slopeTo(points[r]))
	// if(points[p].slopeTo(points[q])==points[p].slopeTo(points[s]))
	// if(points[p].slopeTo(points[r])==points[p].slopeTo(points[s])) {
	// System.out.println(points[p] + "->" + points[q] + "->" + points[r] + "->" +
	// points[s]);
	// }
	// }
	// }
	// }
	// }
	//
	// }
	private HashSet bruteForce(Point[] points) {
		int length = points.length;
		HashSet<HashSet<Point>> combinations = new HashSet<HashSet<Point>>();
		for (int p = 0; p < length; p++) {
			for (int q = p; q < length; q++) {
				for (int r = q; r < length; r++) {
					for (int s = r; s < length; s++) {
						if (points[p].slopeTo(points[q]) == points[p].slopeTo(points[r])) {
							if (points[p].slopeTo(points[q]) == points[p].slopeTo(points[s])) {
								HashSet<Point> combination = new HashSet<Point>();
								combination.add(points[p]);
								combination.add(points[q]);
								combination.add(points[r]);
								combination.add(points[s]);
								if (combination.size() == 4)
									combinations.add(combination);
							}
						}
					}
				}
			}
		}
		for (HashSet hs : combinations) {
			
			
			for (Object p : hs) {
				System.out.print(p + "->");
				
			}
			System.out.println();
		}
		drawPoints(combinations);
		return combinations;
	}

	public void drawPoints(HashSet<HashSet<Point>> combinations) {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for(HashSet hs: combinations) {
            Point[] pointsArray = new Point[hs.size()];
            int i = 0;
            for (Object j : hs) {
                Point p = (Point) j;
                pointsArray[i] = p;
                i++;
            }
            FirstSort.sort(pointsArray);
            pointsArray[0].drawTo(pointsArray[pointsArray.length-1]);

        }
    }
	private Point[] readPoint(String fileName) {
		Point[] points = null;
		try {
			Scanner in = new Scanner(new File(fileName));
			int n = in.nextInt();
			points = new Point[n];
			for (int i = 0; i < n; i++)
				points[i] = new Point(in.nextInt(), in.nextInt());

		} catch (IOException e) {
			System.out.println("No file found");
		}

		return points;
	}

}
