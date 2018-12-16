package project;
import java.util.Stack;

public class BreadthFirstPaths {
	private boolean[] marked;
	private int[] edgeTo;
	int[] distTo;
 
	public BreadthFirstPaths(Digraph G, int s) {
        
        edgeTo = new int[G.V()];
        distTo = new int[G.V()];
        marked = new boolean[G.V()];
        bfs(G, s);
    }
	 public BreadthFirstPaths(Digraph G, Iterable<Integer> sources) {
		 marked = new boolean[G.V()];
	        distTo = new int[G.V()];
	        edgeTo = new int[G.V()];   
	        bfs(G, sources);

	}

	/**
     * Чи присутній шлях з v в s, що задана конструктором
     * @param v - вершина до чкої шукаємо шлях
     * @return true якщо є шлях, false якщо немає
     */
    public boolean hasPathTo(int v) {
        return marked[v];
    }
    
    /**
     * повертає шлях між s та v; null якщо шляху немає
     */
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        int x;
        for (x = v; distTo[x] != 0; x = edgeTo[x])
            path.push(x);
        path.push(x);
        return path;
    }
	private void bfs(Digraph G, Iterable<Integer> sources) {
		 Queue<Integer> q = new Queue<Integer>();
	        for (int s : sources) {
	            marked[s] = true;
	            distTo[s] = 0;
	            q.enqueue(s);
	        }
	        while (!q.isEmpty()) {
	            int v = q.dequeue();
	            for (int w : G.adj(v)) {
	                if (!marked[w]) {
	                    edgeTo[w] = v;
	                    distTo[w] = distTo[v] + 1;
	                    marked[w] = true;
	                    q.enqueue(w);
	                }
	            }
	        }

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
				}
			}
		}
	}
}