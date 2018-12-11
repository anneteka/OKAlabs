import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.*;

public class ConvexHull {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("File Name: ");
        BufferedReader bufferedReader = new BufferedReader(new FileReader("obolonka_data/" + scanner.next() + ".txt"));
        StreamTokenizer streamTokenizer = new StreamTokenizer(bufferedReader);
        streamTokenizer.nextToken();
        Point2D[] points = new Point2D[(int) streamTokenizer.nval];

        for (int i = 0; i < points.length; i++) {
            streamTokenizer.nextToken();
            int x = (int) streamTokenizer.nval;
            streamTokenizer.nextToken();
            int y = (int) streamTokenizer.nval;
            points[i] = new Point2D(x, y);
            points[i].draw();
        }

        Arrays.sort(points);
        Arrays.sort(points, points[0].POLAR_ORDER);
        Stack<Point2D> stack = new Stack<>();
        stack.push(points[0]);

        int counter;
        for (counter = 2; counter < points.length; counter++) {
            if (Point2D.ccw(points[0], points[1], points[counter]) != 0) {
                break;
            }
        }
        stack.push(points[counter - 1]);
        for (int i = counter; i < points.length; i++) {
            Point2D centerPoint = stack.pop();
            while (Point2D.ccw(stack.peek(), centerPoint, points[i]) >= 0) {
                centerPoint = stack.pop();
            }
            stack.push(centerPoint);
            stack.push(points[i]);
        }

        ArrayList<Point2D> drawing = new ArrayList<>();
        for (Point2D point : stack)
            drawing.add(point);

        for (int i = 0; i < drawing.size() - 1; i++) {
            System.out.print(drawing.get(i).toString() + " --> ");
            drawing.get(i).drawTo(drawing.get(i + 1));
        }
        System.out.print(drawing.get(drawing.size() - 1).toString());
        drawing.get(drawing.size() - 1).drawTo(drawing.get(0));
    }
}

