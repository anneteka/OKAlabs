import java.awt.*;
import java.util.Stack;

public class DrawGraph {
    static class Point {
        private int x;
        private int y;
        private int vertex;

        Point(int x, int y, int vertex) {
            this.x = x;
            this.y = y;
            this.vertex = vertex;
        }
    }

    static class Edge {

        private final Point v, w;

        public Edge(Point v, Point w) {
            this.v = v;
            this.w = w;
        }

        public Point either() {
            return v;
        }

        public Point other(Point vertex) {
            if (vertex == v) return w;
            else return v;
        }
    }

    static class EdgeWeightedGraph {

        private final int V;
        private final Stack<Edge>[] adj;

        public EdgeWeightedGraph(int V) {
            this.V = V;
            adj = (Stack<Edge>[]) new Stack[V];
            for (int v = 0; v < V; v++)
                adj[v] = new Stack<Edge>();
        }

        public void addEdge(Edge e) {
            Point v = e.either(), w = e.other(v);
            adj[v.vertex].add(e);
            adj[w.vertex].add(e);
        }

        public int V() {
            return V;
        }

        public Iterable<Edge> adj(int v) {
            return adj[v];
        }

    }

    private EdgeWeightedGraph graph;
    private Point[] points;

    DrawGraph(EdgeWeightedGraph graph, Point[] points) {
        this.graph = graph;
        this.points = points;
    }

    public void show() {
        StdDraw.setPenRadius(0.009);
        StdDraw.setPenColor(Color.GRAY);
        for (int i = 0; i < points.length; i++) {
            for(Edge e: graph.adj(i))
            StdDraw.line(e.v.x, e.v.y, e.w.x, e.w.y);
        }
        StdDraw.setPenRadius(0.05);
        StdDraw.setPenColor(Color.BLUE);
        for (int i = 0; i < points.length; i++) {
            StdDraw.point(points[i].x, points[i].y);
        }
    }

    public static void main(String[] args) {
        StdDraw.setXscale(0, 1000);
        StdDraw.setYscale(0, 1000);

        Point[] points = new Point[5];
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point(StdRandom.uniform(50, 500), StdRandom.uniform(20, 700), i);
        }

        EdgeWeightedGraph ewg = new EdgeWeightedGraph(points.length);

        ewg.addEdge(new Edge(points[0], points[1]));
        ewg.addEdge(new Edge(points[1], points[3]));
        ewg.addEdge(new Edge(points[2], points[0]));
        ewg.addEdge(new Edge(points[0], points[4]));

        DrawGraph dg = new DrawGraph(ewg, points);
        dg.show();
    }
}
