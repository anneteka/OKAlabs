import lib.StdDraw;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

public class Fast {
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
    private HashSet<HashSet<Point>> fast(Point[] points) {
        HashSet<HashSet<Point>> combinations = new HashSet<HashSet<Point>>();
        int length = points.length;
        for(int i=0;i<length;i++) {
            Point p = points[i];
            double[] slopes = new double[length];
            Point[] newPoints = new Point[points.length];
            for(int j=0;j<length;j++) {
                newPoints[j] = points[j];
            }
            for(int j=0;j<length;j++) {
                slopes[j] = p.slopeTo(points[j]);
            }
            Arrays.sort(slopes);
            Arrays.sort(newPoints, p.SLOPE_ORDER);
            double baseNumber = slopes[0];
            HashSet<Point> combination = new HashSet<Point>();
            combination.add(p);
            for(int j=1;j<length;j++) {
                if(baseNumber == slopes[j]) {
                    combination.add(newPoints[j]);
                } else {
                    if(combination.size() >= 2) {
                        combination.add(p);
                        combinations.add(combination);
                    }
                    combination = new HashSet<Point>();
                    baseNumber = slopes[j];
                }
                if(j==length-1) {
                    if(combination.size() >=23) {
                        combination.add(p);
                        combinations.add(combination);
                    }
                }
            }
        }
        return combinations;
    }

    public void drawPoints(HashSet<HashSet<Point>> combinations) {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for(HashSet hs: combinations) {
            TreeSet<Point> ts = new TreeSet<Point>(hs);
            System.out.println(ts);;
            ts.first().drawTo(ts.last());
        }
    }
    public static void main(String[] args) {
        Fast brute = new Fast();
        brute.drawPoints(brute.fast(brute.readPoints("/Users/michaelfilonenko/LocalDocs/KMA/ОКА/P4/pr4_5_data/rs1423.txt")));
    }

}