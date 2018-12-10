package lin;

import ua.princeton.lib.In;
import ua.princeton.lib.StdDraw;

public class Painting {
	public static void main(String[] args) {

        
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show();

        
        String filename = "rs1423.txt";
        In in = new In(filename);
        int N = in.readInt();
        Point prev = null;
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            p.draw();
            if (prev != null)
                prev.drawTo(p);
            prev = p;
        }

        
        StdDraw.show();
    }

}
