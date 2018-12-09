import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class BFS {
    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;
    private int[] visited; //number of visited nodes
       private int counter = 0;

    BFS(Digraph G, int s) {
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        visited = new int[G.V()];
        bfs(G, s);
    }

    BFS(Digraph G, Iterable<Integer> sources) {
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        visited = new int[G.V()];
        bfs(G, sources);
    }

    private void bfs(Digraph G, int s) {
        Queue<Integer> q = new Queue<Integer>();
        q.enqueue(s);
        marked[s] = true;
        distTo[s] = 0;
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    q.enqueue(w);
                    marked[w] = true;
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    visited[w] = ++counter;
                }
            }
        }
    }

    private void bfs(Digraph G, Iterable<Integer> sources ) {
        Queue<Integer> q = new Queue<Integer>();
        for (int s: sources){
            q.enqueue(s);
            marked[s] = true;
            distTo[s] = 0;
        }
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    q.enqueue(w);
                    marked[w] = true;
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    visited[w] = ++counter;
                }
            }
        }
    }

    public int getCounter(int v){
        return visited[v];
    }


    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public int getDistTo(int v) {
        return distTo[v];
    }


    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        int x;
        for (x = v; x != 0; x = edgeTo[x])
            path.push(x);
        path.push(x);
        return path;
    }
}
