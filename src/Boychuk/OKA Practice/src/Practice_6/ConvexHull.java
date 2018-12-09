package Practice_6;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;


public class ConvexHull {
    public ConvexHull(String path) throws FileNotFoundException {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        Point2D[] points = readPoints(path);
        // Stack with all hull dots
        Stack<Point2D> hull = new Stack<Point2D>();
        // Sorting by Y
        Arrays.sort(points, Point2D.SORT_BY_Y);
        // Sorting by polar order
        Arrays.sort(points, points[0].POLAR_ORDER);
        // Printing numbers of dots
        if (points.length < 101) {
            for (int i = 0; i < points.length; i++) {
                StdDraw.setFont(new Font("TimesRoman", Font.PLAIN, 12));
                StdDraw.text(points[i].getX(), points[i].getY(), "     " + i);
            }
        }
        // Adding to stack 2 points
        hull.push(points[0]);
        hull.push(points[1]);
        // Checking if points are ccw or cw or collinear
        for (int i = 2; i < points.length; i++) {
            Point2D top = hull.pop();
            while (Point2D.ccw(hull.peek(), top, points[i]) > 0) {
                top = hull.pop();
            }
            hull.push(top);
            hull.push(points[i]);
        }
        // Saving last point to connect
        Point2D last = hull.peek();
        // Drawing connections
        while (hull.size() > 1) {
            hull.pop().drawTo(hull.peek());
        }
        // Last connection
        hull.pop().drawTo(last);
    }
    // Reading points from file
    private static Point2D[] readPoints(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner sc = new Scanner(file);
        int numberOfPts = sc.nextInt();
        Point2D[] points = new Point2D[numberOfPts];
        for (int i = 0; i < numberOfPts; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            points[i] = new Point2D(x, y);
            StdDraw.filledCircle(x, y, 100);
            points[i].draw();
        }
        return points;
    }

}

