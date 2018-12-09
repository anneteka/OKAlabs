import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class UnionDay {
    static class MinPQ<Key extends Comparable<Key>> {
        private Key[] pq;
        private int n;

        public MinPQ() {
            this(1);
        }

        public MinPQ(int capacity) {
            pq = (Key[]) new Comparable[capacity + 1];
        }

        public boolean isEmpty() {
            return n == 0;
        }

        public void insert(Key key) {
            if (key != null) {
                if ((n + 1) == pq.length) resize(2 * pq.length);
                pq[++n] = key;
                swim(n);
            }
        }

        public Key delMin() { //look
            Key max = pq[1];
            exch(1, n--);
            sink(1);
            pq[n + 1] = null;
            if (n > 0 && n <= pq.length / 4) resize(pq.length / 2);
            return max;
        }

        private void swim(int k) {
            while (k > 1 && more(k / 2, k)) {
                exch(k, k / 2);
                k = k / 2;
            }
        }

        private void sink(int k) {
            while (2 * k <= n) {
                int j = 2 * k;
                if (j < n && more(j, j + 1)) j++;
                if (!more(k, j)) break;
                exch(k, j);
                k = j;
            }
        }

        private boolean more(int i, int j) {
            if (pq[j] == null || pq[i] == null) return false;
            return pq[j].compareTo(pq[i]) < 0;
        }

        private void exch(int i, int j) {
            Key t = pq[i];
            pq[i] = pq[j];
            pq[j] = t;
        }

        private void resize(int capacity) {
            Key[] copy = (Key[]) new Comparable[capacity];
            for (int i = 0; i < Math.min(pq.length, capacity); i++)
                copy[i] = pq[i];
            pq = copy;
        }

        @Override
        public String toString() {
            return Arrays.toString(pq);
        }

    }

    static class Point{
        private int x;
        private int y;
        private int vertex;

        Point(int x, int y, int vertex){
            this.x = x;
            this.y = y;
            this.vertex = vertex;
        }
    }

    static class Edge implements Comparable<Edge> {

        private final Point v, w;
        private final double weight;

        public Edge(Point v, Point w, double weight) {
            this.v = v;
            this.w = w;
            this.weight = weight;
        }

        public Point either() {
            return v;
        }

        public Point other(Point vertex) {
            if (vertex == v) return w;
            else return v;
        }

        public int compareTo(Edge that) {
            if (this.weight < that.weight) return -1;
            else if (this.weight > that.weight) return 1;
            else return 0;
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

    public static double length(Point a, Point b){
        return Math.sqrt(Math.pow((a.x - b.x),2) + Math.pow((a.y - b.y),2));
    }

    private boolean[] marked; // MST vertices
    private MinPQ<Edge> pq; // PQ of edges
    private int counter = 0;
    private double sum = 0;

    UnionDay(EdgeWeightedGraph G, Point zero){
        pq = new MinPQ<Edge>();
        marked = new boolean[G.V()];
        visit(G, zero);

        while (!pq.isEmpty() && (counter < (G.V() - 1))) {
            Edge e = pq.delMin();
            Point v = e.either(), w = e.other(v);
            if (marked[v.vertex] && marked[w.vertex]) continue;
            counter++;
            sum += e.weight;
            if (!marked[v.vertex])
                visit(G, v);
            if (!marked[w.vertex])
                visit(G, w);
        }
    }

    private void visit(EdgeWeightedGraph G, Point v) {
        marked[v.vertex] = true;
        for (Edge e : G.adj(v.vertex))
            if (!marked[e.other(v).vertex])
                pq.insert(e);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int vertexes = sc.nextInt();
        EdgeWeightedGraph ewg = new EdgeWeightedGraph(vertexes + 1);

        Point points[] = new Point[vertexes];
        for(int i = 0; i < vertexes; i++){
            points[i] = new Point(sc.nextInt(), sc.nextInt(), i);
        }

        for (int i = 0; i < vertexes; i++){
            for(int j = i+1; j < vertexes; j++){
                ewg.addEdge(new Edge(points[i],points[j], length(points[i],points[j])));
            }
        }

        UnionDay mr = new UnionDay(ewg, points[0]);
        System.out.println(mr.sum);


    }
}