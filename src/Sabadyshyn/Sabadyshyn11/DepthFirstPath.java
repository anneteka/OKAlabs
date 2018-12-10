import edu.princeton.cs.algs4.*;

public class DepthFirstPath {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;
    public static int pathLength = 0;

    public DepthFirstPath(Graph G, int s) {
        this.s = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        validateVertex(s);
        dfs(G, s);
    }

    private void dfs(Graph G, int v) { //метод рекурсивного пошуку
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
            pathLength++;
        }
        path.push(s);
        return path;
    }

    private void validateVertex(int v) { //перевірка вершини на правильність вводу
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("Вершина " + v + " не входитть в діапазон від 0 до " + (V - 1)+"!!!");
    }
}
