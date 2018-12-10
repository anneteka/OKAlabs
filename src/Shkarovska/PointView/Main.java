import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scn;
        try {
            scn = new Scanner(new FileReader("rs1423.txt"));
        } catch (FileNotFoundException fnf) {
            System.out.println("File not found");
            return;
        }


        StdDraw.setYscale(0, 32768);
        StdDraw.setXscale(0, 32768);
        StdDraw.show();

        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(Color.green);

        int count = scn.nextInt();
        Point[] points = new Point[count];
        for (int i = 0; i < count; i++) {
            points[i] = new Point(scn.nextInt(), scn.nextInt());
            points[i].draw();
        }

        StdDraw.setYscale(0, 32768);
        StdDraw.setXscale(0, 32768);
        StdDraw.show();

        ArrayList<Line> lines = new ArrayList<Line>();

        while (points.length > 1) {
            Point.setComparingPoint(points[0]);
            Point[] newpoints = new Point[--count];
            System.arraycopy(points, 1, newpoints, 0, count);
            points = newpoints;
            ShellSort<Point> sorter = new ShellSort<Point>();
            sorter.sort(points, Point.SLOPE_ORDER);

            double[] slopes = new double[count];
            for (int i = 0; i < count; i++) {
                slopes[i] = Point.getSlopeFromComparingPoint(points[i]);
            }

            ArrayList<Point> line = new ArrayList<Point>();
            line.add(Point.getComparingPoint());
            line.add(points[0]);
            double currSlope = slopes[0];
            for (int i = 1; i < count; i++) {
                if (currSlope == slopes[i] || (currSlope == -0d && slopes[i] == 0d) || (currSlope == 0d && slopes[i] == -0d)) {
                    line.add(points[i]);
                } else {
                    if (line.size() >= 4) {
                        boolean cont = false;
                        for (Line l : lines) {
                            if (l.sameLine(line.get(0), line.get(1))) {
                                cont = true;
                                break;
                            }
                        }
                        if (!cont) {
                            outLine(line);
                            lines.add(new Line(line.get(0).slopeTo(line.get(1)), line.get(0)));
                        }
                    }
                    line = new ArrayList<Point>();
                    line.add(Point.getComparingPoint());
                    line.add(points[i]);
                    currSlope = slopes[i];
                }
            }

            if (line.size() >= 4) {
                for (Line l : lines)
                    if(l.sameLine(line.get(0), line.get(1)))
                        return;

                outLine(line);
                lines.add(new Line(line.get(0).slopeTo(line.get(1)), line.get(0)));
            }
        }
    }

    private static void outLine(ArrayList<Point> line) {
        for (int j = 0; j < line.size() - 1; j++) {
            System.out.print(line.get(j));
            System.out.print(" -> ");
        }
        System.out.println(line.get(line.size() - 1));

        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setPenRadius(0.005);
        for(int i = 0; i < line.size() - 1; i++) {
            line.get(i).drawTo(line.get(i + 1));
        }

        StdDraw.setPenRadius(0.002);
        StdDraw.setPenColor(Color.blue);
        for(Point p : line) {
            p.draw();
        }
    }
}
