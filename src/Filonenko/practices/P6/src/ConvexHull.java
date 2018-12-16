import javafx.util.Pair;
import lib.StdDraw;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ConvexHull {

    private Pair<Point, Point> line;
    private ArrayList<Point> hull;

    public Point[] getPoints(String filename) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(filename));
            int length = scanner.nextInt();
            Point[] points = new Point[length];
            for(int i=0;i<length;i++) {
                points[i] = new Point(scanner.nextInt(),scanner.nextInt());
            }
            return points;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void getLines(Point[] points) {
        ArrayList<Point> hull = new ArrayList<Point>();
        int l = getLowestPoints(points);
        int n = points.length;
        int p = l, q;
        do
        {
            hull.add(points[p]);
            q = (p + 1) % n;
            for (int i = 0; i < n; i++)
            {
                if (Point.ccw(points[p], points[i], points[q]) < 0)
                    q = i;
            }
            p = q;
        } while (p != l);
        Collections.sort(hull, points[l].BY_POLAR_ORDER);
        for(int i=0;i<hull.size()-1;i++) {
            hull.get(i).drawTo(hull.get(i+1));
        }
        hull.get(0).drawTo(hull.get(hull.size()-1));
    }

    public Point[] deletePoint(Point[] points, int index) {
        Point[] tmp = new Point[points.length-1];
        for(int i=0;i< points.length;i++) {
            if(i < index) {
                tmp[i] = points[i];
            } else if(i > index) {
                tmp[i-1] = points[i];
            }
        }
        return tmp;
    }
    void drawAllLines(Point[] points) {
        for(int i=10;i<points.length;i++) {
            points[0].drawTo(points[i]);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void drawLines(Line[] lines) {
        for(Line line: lines) {
            line.firstPoint.drawTo(line.secondPoint);
        }
    }
    public void drawPoints(Point[] points) {
        for(Point p: points) {
            p.draw();
        }
    }
    private Point[] replacePoints(Point[] points, Point lowestPoint) {
        int length = points.length;
        Point[] tmp = new Point[length];
        tmp[0] = lowestPoint;
        int insertIndex = 1;
        for(int i=0;i<length;i++) {
            if(points[i].equals(lowestPoint)) {
                i++;
            }
            tmp[insertIndex] = points[i];
            insertIndex++;
        }
        return tmp;
    }
    public static void main(String[] args) {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        ConvexHull test = new ConvexHull();
        String filename = "/Users/michaelfilonenko/Downloads/pr4_5_data/rs1423.txt";
        Point[] points = test.getPoints(filename);
        test.drawPoints(points);
        test.getLines(points);
    }
    private int getLowestPoints(Point[] points) {
        int minIndex = 0;
        for(int i=1, length = points.length;i<length;i++) {
            if(points[i].x < points[minIndex].x) {
                minIndex = i;
            }
        }
        return minIndex;
}
    public Point[] reverse(Point[] points) {
        for(int i = 0; i < points.length / 2; i++)
        {
            Point temp = points[i];
            points[i] = points[points.length - i - 1];
            points[points.length - i - 1] = temp;
        }
        return points;
    }
}
class Line {
    Point firstPoint;
    Point secondPoint;
    public Line(Point firstPoint, Point secondPoint) {
        this.firstPoint  = firstPoint;
        this.secondPoint = secondPoint;
    }
}

