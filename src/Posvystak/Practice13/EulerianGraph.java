import java.util.Stack;

import princeton.lib.Graph;
import princeton.lib.In;
import princeton.lib.StdDraw;

public class EulerianGraph {
    private Graph g;
    private int[] x;
    private int[] y;

    public EulerianGraph(Graph g, In in) {
        StdDraw.setScale(0, 100);
        this.g = g;
        x = new int[g.V()];
        y = new int[g.V()];
        for (int i = 0; i < g.V(); i++) {
            x[i] = in.readInt();
            y[i] = in.readInt();
        }
    }


    public void showGraph() {
        for (int i = 0; i < g.V(); i++) {
            StdDraw.text(x[i], y[i], String.valueOf(i));
            StdDraw.circle(x[i], y[i], 3);

            for (int v : g.adj(i)) {
                double x1 = x[i], x2 = x[v], y1 = y[i], y2 = y[v];
                double a = Math.atan2(y2 - y1, x2 - x1);
                double lenCut = 3;
                double dx = lenCut * Math.cos(a);
                double dy = lenCut * Math.sin(a);

                x1 = x1 + dx;
                y1 = y1 + dy;
                x2 = x2 - dx;
                y2 = y2 - dy;
                StdDraw.line(x1, y1, x2, y2);
            }
        }
    }

    public void showPath(Iterable<Integer> p) {
        Stack<Integer> path = (Stack<Integer>) p;

        if (path == null)
            return;

        int from = -1;
        int count = 0;

        for (int v : path) {
            if (from == -1)
                from = v;
            else {
                StdDraw.text(x[from], y[from], String.valueOf(from));
                StdDraw.circle(x[from], y[from], 3);
                arrow(x[from], y[from], x[v], y[v], String.valueOf(++count));
                from = v;
            }
        }

    }

    private static void arrow(double x1, double y1, double x2, double y2, String txt) {
        double fi = Math.PI / 12;
        double a = Math.atan2(y2 - y1, x2 - x1);
        double lenArrow = 5;
        double lenCut = 3;

        double dx = lenCut * Math.cos(a);
        double dy = lenCut * Math.sin(a);
        x2 = x2 - dx;
        y2 = y2 - dy;
        x1 = x1 + dx;
        y1 = y1 + dy;

        StdDraw.line(x1, y1, x2, y2);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.text((x1 + x2) / 2 + 3, (y1 + y2) / 2 + 2, txt);
        StdDraw.setPenColor(StdDraw.BLACK);

    }

    public static void main(String[] args) {
        Graph g = new Graph(new In("src/source3sinks2.txt"));
        EulerianGraph eg = new EulerianGraph(g, new In("src/coordinates.txt"));
        eg.showGraph();
    }

}