import java.util.Arrays;
import java.util.Stack;

public class Pchk {
    private Stack<Point2D> hull = new Stack<Point2D>();
    public Pchk(Point2D[] points){

        int n = points.length;
        Point2D[] a = new Point2D[n];
        for (int i = 0; i < n; i++) {
            if (points[i] == null)
                throw new IllegalArgumentException("points[" + i + "] is null");
            a[i] = points[i];
        }

        Arrays.sort(a, 1, n, a[0].polarOrder());

        hull.push(a[0]);

        int f;
        int s;

        for (f = 1; f < n; f++) {
            if (!a[0].equals(a[f]))
                break;
        }

        if (f == n)
            return;


        for (s = f+1; s < n; s++) {
            if (Point2D.ccw(a[0], a[f], a[s]) != 0)
                break;
        }

        hull.push(a[s-1]);

        for (int i = s; i < n; i++) {
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
        for (Point2D p : hull) s.push(p);
        return s;
    }
}
