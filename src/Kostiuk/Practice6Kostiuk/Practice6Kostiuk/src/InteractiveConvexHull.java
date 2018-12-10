public class InteractiveConvexHull {

    @SuppressWarnings("deprecation")
	public static void main(String[] args) {
        StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(0, 10000);
        StdDraw.setYscale(0, 10000);
        StdDraw.enableDoubleBuffering();

        Bag<Point2D> bag = new Bag<Point2D>();

        while (true) {
            if (StdDraw.mousePressed()) {
                // mouse pressed so add point to list of points
                int x = (int) (Math.round(StdDraw.mouseX()));
                int y = (int) (Math.round(StdDraw.mouseY()));
                bag.add(new Point2D(x, y));

                // extract array of points
                int N = bag.size();
                Point2D[] points = new Point2D[N];
                int n = 0;
                for (Point2D p : bag) {
                    points[n++] = p;
                }

                // compute convex hull
                GrahamScan graham = new GrahamScan(points);


                StdDraw.clear();

                // draw the points in black
                StdDraw.setPenRadius(.01);
                StdDraw.setPenColor(StdDraw.BLACK);
                for (int i = 0; i < N; i++)
                    points[i].draw();

                // draw the hull points in red
                StdDraw.setPenColor(StdDraw.RED);
                for (Point2D p : graham.hull())
                    p.draw();

                // draw the hull line segments in blue
                StdDraw.setPenRadius();
                StdDraw.setPenColor(StdDraw.BLUE);
                Point2D prev = null;
                for (Point2D p : graham.hull()) {
                    if (prev != null) prev.drawTo(p);
                    prev = p;
                }
                // hack to connect first and last points
                for (Point2D p : graham.hull()) {
                    prev.drawTo(p);
                    break;
                }
            }
            StdDraw.show();
            StdDraw.pause(20);
        }

    }

}
