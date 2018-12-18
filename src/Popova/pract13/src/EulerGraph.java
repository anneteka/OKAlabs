import princeton.lib.Graph;
import princeton.lib.In;
import princeton.lib.StdDraw;

import java.awt.*;
import java.util.Stack;

public class EulerGraph {

    private Graph g;
    private Point points[];

    public EulerGraph(Graph g, In in) {
        StdDraw.setScale(0, 100);
        this.g = g;
        points = new Point[g.V()];

        for (int i = 0; i < g.V(); i++)
            points[i] = new Point(in.readInt(), in.readInt());
    }


    public void showGraph() {
        for (int i = 0; i < g.V(); i++) {
            for (int v : g.adj(i)) {
                double x1 = points[i].x, x2 = points[v].x, y1 = points[i].y, y2 = points[v].y;
                double a = Math.atan2(y2 - y1, x2 - x1);
                double lenCut = 3;

                x1 += lenCut * Math.cos(a);
                y1 += lenCut * Math.sin(a);
                x2 -= lenCut * Math.cos(a);
                y2 -= lenCut * Math.sin(a);
                StdDraw.line(x1, y1, x2, y2);
            }
            StdDraw.circle(points[i].x, points[i].y, 3);
            StdDraw.text(points[i].x, points[i].y, String.valueOf(i));
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
                StdDraw.text(points[from].x, points[from].y, String.valueOf(from));
                StdDraw.circle(points[from].x, points[from].y, 3);
                arrow(points[from].x, points[from].y, points[v].x, points[v].y, String.valueOf(++count));
                from = v;
            }
        }

    }

    private static void arrow(double x1, double y1, double x2, double y2, String str) {
        double a = Math.atan2(y2 - y1, x2 - x1);
        double lenCut = 3;
        double dx = lenCut * Math.cos(a);
        double dy = lenCut * Math.sin(a);
        x2 -= dx;
        y2 -= dy;
        x1 += dx;
        y1 += dy;

        StdDraw.line(x1, y1, x2, y2);

        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.text((x1 + x2) / 2 + 2, (y1 + y2) / 2 + 2, str);
        StdDraw.setPenColor(StdDraw.BLACK);
    }

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 4);
        graph.addEdge(1, 2);
        graph.addEdge(3, 2);
        graph.addEdge(2, 3);
        graph.addEdge(4, 3);
        EulerGraph eg = new EulerGraph(graph, new In("graph"));
        eg.showGraph();
    }

}