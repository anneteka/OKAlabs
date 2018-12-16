import java.util.Arrays;
import java.util.Stack;

public class PointCheck {
    private Stack<Point2D> hull = new Stack<Point2D>();
    public PointCheck(Point2D[] points){
        if (points == null) throw new IllegalArgumentException("argument is null");
        if (points.length == 0) throw new IllegalArgumentException("array is of length 0");

        int n = points.length;
        Point2D[] a = new Point2D[n];
        for (int i = 0; i < n; i++) {
            if (points[i] == null)
                throw new IllegalArgumentException("points[" + i + "] is null");
            a[i] = points[i];
        }

        //Arrays.sort(a);
        Arrays.sort(a, 1, n, a[0].polarOrder());

        hull.push(a[0]);

        int first;
        for (first = 1; first < n; first++) {
            if (!a[0].equals(a[first]))
                break;
        }

        if (first == n)
            return;

        int second;
        for (second = first+1; second < n; second++) {
            if (Point2D.ccw(a[0], a[first], a[second]) != 0)
                break;
        }

        hull.push(a[second-1]);

        for (int i = second; i < n; i++) {
            Point2D top = hull.pop();
            while (Point2D.ccw(hull.peek(), top, a[i]) <= 0) {
                top = hull.pop();
            }
            hull.push(top);
            hull.push(a[i]);
        }
    }

    public Iterable<Point2D> hull() {
        Stack<Point2D> s = new Stack<Point2D>();
        for (Point2D p : hull)
            s.push(p);
        return s;
    }
}
