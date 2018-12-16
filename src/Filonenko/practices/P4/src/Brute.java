import lib.StdDraw;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Brute {
    public Point[] readPoints(String fileName) {
        Point[] points = null;
        try {
            Scanner scanner = new Scanner(new File(fileName));
            int capacity = scanner.nextInt();
            points = new Point[capacity];
            for(int i=0;i<capacity;i++) {
                points[i] = new Point(scanner.nextInt(), scanner.nextInt());
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
        return points;
    }
    private HashSet<HashSet<Point>> bruteForce(Point[] points) {
        int length = points.length;
        HashSet<HashSet<Point>> combinations = new HashSet<HashSet<Point>>();
        for(int p=0;p<length;p++) {
            for(int q=p;q<length;q++) {
                for(int r=q;r<length;r++) {
                    for(int s=r;s<length;s++) {
                        if(points[p].slopeTo(points[q]) == points[p].slopeTo(points[r])) {
                            if(points[p].slopeTo(points[q]) == points[p].slopeTo(points[s])) {
                                HashSet<Point> combination = new HashSet<Point>();
                                combination.add(points[p]);
                                combination.add(points[q]);
                                combination.add(points[r]);
                                combination.add(points[s]);
                                if(combination.size() == 4)
                                combinations.add(combination);
                            }
                        }
                    }
                }
            }
        }
        for(HashSet hs: combinations) {
            for (Object p : hs) {
                System.out.print(p + " -> ");
            }
            System.out.println("\n");
        }
        return combinations;
    }
    public void drawPoints(HashSet<HashSet<Point>> combinations) {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for(HashSet hs: combinations) {
            Point[] pointsArray = new Point[4];
            int i = 0;
            for (Object j : hs) {
                Point p = (Point) j;
                pointsArray[i] = p;
                i++;
            }
            ShellSort.sort(pointsArray);
            pointsArray[0].drawTo(pointsArray[pointsArray.length-1]);

        }
    }
    public static void main(String[] args) {
        Brute brute = new Brute();
        brute.drawPoints(brute.bruteForce(brute.readPoints("/Users/michaelfilonenko/LocalDocs/KMA/ОКА/P4/pr4_5_data/grid6x6.txt")));
    }
}

