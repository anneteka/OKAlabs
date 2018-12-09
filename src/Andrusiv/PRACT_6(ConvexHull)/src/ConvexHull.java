import edu.princeton.cs.algs4.StdDraw;

import java.awt.Color;
import java.io.*;
import java.util.*;


public class ConvexHull {


    public static String convexHull(Point2D[] points) {

        if (points.length < 2) return "";
        //sorting points
        Arrays.sort(points);
        String res = points[0].toString();
        StdDraw.setPenColor(Color.red);
        StdDraw.point(points[0].x,points[0].y);
        Arrays.sort(points,1,points.length,points[0].POLAR_ORDER);
        if (points.length == 2) {
            points[0].drawTo(points[1]);
            res += " => " + points[1].toString() + " => " + points[0].toString();
            return res;
        }

        Stack<Point2D> stack = new Stack<>();
        stack.push(points[0]);
        stack.push(points[1]);

        //algorythm for finding Convex Hull
        for (int i = 2; i < points.length; i++) {
            Point2D c = points[i],
                    b = stack.pop(),
                    a = stack.peek();
            if (Point2D.ccw(a, b, c) > 0) {
                stack.push(b);
                stack.push(c);
            } else if (Point2D.ccw(a, b, c) == 0)
                stack.push(c);
            else i--;
            if (stack.size()==1) stack.push(c);
        }
        stack.push(points[0]);

        // Resulting array printing and drawing
        ArrayList<Point2D> hull=new ArrayList<>(stack);
        for (int i = 0; i < hull.size() - 1; i++) {
            hull.get(i).drawTo(hull.get(i + 1));
            res += " => " + hull.get(i + 1).toString();
        }
        return res;
    }

    public static void main(String[] args) {
    	StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(-5, 100);            
    StdDraw.setYscale(-5, 100);
     
    	
    	
        Scanner in = null ;
        try {
            in = new Scanner(new FileReader(new File("rs1423.txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StdDraw.setYscale(0, 32768);
        StdDraw.setXscale(0, 32768);
        int n = in.nextInt();
        Point2D[] points = new Point2D[n];
        for (int i = 0; i < n; i++) {
            points[i] = new Point2D(in.nextInt(), in.nextInt());
        }
        
       System.out.println(convexHull(points));
       
    }
}