package practice6;

import ua.princeton.lib.StdDraw;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ConvexHull {
    private int n;
    private File input;
    private Point2D[] points;
    private ArrayList<Point2D> hull;
    Point2D point;

    public ConvexHull(String file) throws IOException {
        input = new File(file);
        points = readFile();
        hull = new ArrayList<>();
        //   Arrays.sort(points);
        //point=points[0];
        point = findLowest(points);


        StdDraw.setPenColor(Color.red);
        Arrays.sort(points, point.PolarOrder());
        System.out.println(point);
        hull.add(point);
        hull.add(points[1]);
        drawAll();
        convexHull();
        System.out.println("done");
    }

    private void convexHull() {
        for (int i = 2; i < n; i++) {
            //якщо значення <0, тоді a,b,c утворюють поворот за годинниковою стрілкою
            Point2D point1=hull.get(hull.size() - 2);
            Point2D point2=hull.get(hull.size() - 1);
            Point2D point3=points[i];
            int ccw = Point2D.ccw(point1, point2, points[i]);
            if (ccw >= 0){
                if (ccw!=0||point1.distance(point3)>point1.distance(point2))
                hull.add(points[i]);
            }
            else {
                hull.remove(hull.size() - 1);
                i--;
            }
            int a=0;
        }
        StdDraw.setPenColor(Color.black);
        StdDraw.setPenRadius(0.006);
        for (int i = 0; i < hull.size() - 1; i++) {
            hull.get(i).drawTo(hull.get(i + 1));
        }
        hull.get(0).drawTo(hull.get(hull.size() - 1));
    }

    Point2D[] readFile() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(
                new FileInputStream(input)));
        n = Integer.parseInt(bf.readLine().split(" ")[0]);
        Point2D[] temp = new Point2D[n];
        for (int i = 0; i < n; i++) {
            String[] line = bf.readLine().trim().split(" ");
            int a = Integer.parseInt(line[0]);
            int b = Integer.parseInt(line[line.length - 1]);
            temp[i] = new Point2D(a, b);
        }
        return temp;
    }

    public static void main(String[] args) throws IOException {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        ConvexHull convexHull = new ConvexHull("grid6x6.txt");
    }

    private void drawAll() {
        StdDraw.setPenColor(Color.red);
        StdDraw.setPenRadius(0.004);
        for (int i = 0; i < n; i++) {
            points[i].draw();

        }
    }

    private Point2D findLowest(Point2D[] points) {
        Point2D lowest = points[0];
        for (int i = 1; i < points.length; i++)
            if (points[i].getY() < lowest.getY())
                lowest = points[i];
        return lowest;
    }
}
