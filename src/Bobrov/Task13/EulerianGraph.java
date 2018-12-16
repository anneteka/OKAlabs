package Task13;

import java.util.Stack;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;



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


    public static void main(String[] args) {
        Graph g = new Graph(new In("eulerYes.txt"));
        EulerianGraph eg = new EulerianGraph(g, new In("src/pr13/coordinates.txt"));
        eg.showGraph();

    }

}