import java.util.Arrays;
import java.util.Stack;

public class ConvexHull {
    public static void main(String[]args){
        String fileName = ".\\rs1423.txt";
        ua.princeton.lib.In in = new ua.princeton.lib.In(fileName);

        int N = in.readInt();
        Point2D[] arr = new Point2D[N];

        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        int i=0;
        while (!in.isEmpty()) {
            int x = in.readInt();
            int y = in.readInt();

            Point2D p = new Point2D(x, y);
            p.draw();
            arr[i++] = p;
        }

        Stack<Point2D> stack = new Stack<Point2D>();
        Arrays.sort(arr, arr[0].POLAR_ORDER);
        int first;
        for (first = 1; first < N; first++)
            if (!arr[0].equals(arr[first]))
                break;
        if (first == N)
            return;
        stack.push(arr[0]);

        int second;
        for (second = first + 1; second < N; second++)
            if (Point2D.ccw(arr[0], arr[first], arr[second]) != 0)
                break;
        stack.push(arr[second - 1]);

        for (int j = second; j< N; j++) {
            Point2D top = stack.pop();
            while (Point2D.ccw(stack.peek(), top, arr[j]) <= 0) {
                top = stack.pop();
            }
            stack.push(top);
            stack.push(arr[j]);
        }

        int count = 0;
        Point2D p = null;
        Point2D f = null;
        for (Point2D j : stack) {
            if(count > 0) {
                p.drawTo(j);
            }
            if(count == 0) f = j;
            p = j;
            count++;
        }
        p.drawTo(f);
    }
}