import edu.princeton.cs.algs4.Stack;

public class DFS {

    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;
    private int[] visited;
    private final int s;
    private int counter  = 0;

    public DFS(Graph G, int s) {
        this.s = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        visited = new int[G.V()];
        dfs(G, s);
    }

    /**
     * пошук в глибину
     *
     * @param G - граф
     * @param v - dfs з вершини v
     */
    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                distTo[w] = distTo[v] + 1;
                visited[w] = ++counter;
//                System.out.println("w "+ w + " counter " + counter);
                dfs(G, w);
            }
        }
    }

    /**
     * Чи присутній шлях з v в s, що задана конструктором
     *
     * @param v - вершина до чкої шукаємо шлях
     * @return true якщо є шлях, false якщо немає
     */
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public int getDistTo(int v) {
        return distTo[v];
    }

    public int getCounter(int v){
        return visited[v];
    }

    /**
     * повертає шлях між s та v; null якщо шляху немає
     */
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }
}
