package lin;

import ua.princeton.lib.In;
import ua.princeton.lib.StdOut;

public class Brute {
    public static void main(String[] args) {
        In in = new In("point.txt");      
        Point[] points = new Point[in.readInt()];
        
        int x, y, i = 0;
        while (!in.isEmpty()) {
            x = in.readInt();
            y = in.readInt();
            points[i++] = new Point(x, y);
        }
        MergeSort.sort(points);
        findPointsOnLine(points);
    }
    
    private static void findPointsOnLine(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[k])) {
                        for (int l = k + 1; l < points.length; l++) {
                            if (points[i].slopeTo(points[k]) == points[i].slopeTo(points[l])) {
                                String p = points[i].toString();
                                String q = points[j].toString();
                                String r = points[k].toString();
                                String s = points[l].toString();
                                StdOut.println(p + " -> " + q + " -> " + r + " -> " + s);
                            }
                        }
                    }
                }
            }
        }
    }
}