import lib.In;
import lib.StdDraw;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ConvexHull {

    private Point2D[] points;
    private LinkedList<Point2D> pointsForConvexHull;

    public ConvexHull(Point2D[] points) {

        this.points = points;
        pointsForConvexHull = getPointsForConvexHull(points);
    }

    private void paint() {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.setPenRadius(0.005);
        for (Point2D point : points) {
            point.draw();
        }
        for (int i = 0; i < pointsForConvexHull.size(); i++) {
            int nextIndex = i == pointsForConvexHull.size() - 1 ? 0 : i + 1;
            pointsForConvexHull.get(i).drawTo(pointsForConvexHull.get(nextIndex));
        }
    }

    private LinkedList<Point2D> getPointsForConvexHull(Point2D[] points) {
        LinkedList<Point2D> pointsForConvexHull = new LinkedList<>();
        if (points.length == 0) return pointsForConvexHull;
        Arrays.sort(points);
        Arrays.sort(points , points[0].POLAR_ORDER);
        pointsForConvexHull.add(points[0]);
        pointsForConvexHull.add(points[1]);
        for (int i = 2; i < points.length; i++) {
            pointsForConvexHull.add(points[i]);
            int ccw = ccwOfThreeLast(pointsForConvexHull);
            if (ccw >= 0) {
                removeExtraPoints(pointsForConvexHull);
            }
        }
        return pointsForConvexHull;
    }

    private void removeExtraPoints(LinkedList<Point2D> points) {
        points.remove(points.size() - 2);
        if (points.size() < 3) return;
        if (ccwOfThreeLast(points) < 0)
            return;
        removeExtraPoints(points);
    }

    private int ccwOfThreeLast(LinkedList<Point2D> points) {
        if (points.size() < 3)
            throw new ArrayIndexOutOfBoundsException("Array must contain 3 or more elements");
        int indexOfLast = points.size() - 1;
        return Point2D.ccw(points.get(indexOfLast), points.get(indexOfLast - 1), points.get(indexOfLast - 2));
    }

    public static void main(String[] args) {
        Point2D[] points = readToArrayListFromFile();
        ConvexHull convexHull = new ConvexHull(points);
        convexHull.paint();
    }

    private static Point2D[] readToArrayListFromFile() {
        In in = new In("horizontal100.txt");
        int n = in.readInt();
        Point2D[] points = new Point2D[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point2D(x, y);
        }
        return points;
    }
}
