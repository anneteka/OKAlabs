import java.util.Stack;

public class BFS {
	private boolean[] marked;
	private int[] edgeTo;
	private int[] distTo;
	private static final int INFINITY = Integer.MAX_VALUE;

	public BFS(Graph g, int v) {
		marked = new boolean[g.vertices()];
		distTo = new int[g.vertices()];
		edgeTo = new int[g.vertices()];
		if (v >= 0 && v < g.vertices()) {
			bfs(g, v);
		}
	}

	private void bfs(Graph g, int v) {
		Queue<Integer> q = new Queue<Integer>();
		for (int i = 0; i < g.vertices(); i++)
			distTo[i] = INFINITY;
		distTo[v] = 0;
		marked[v] = true;
		q.enqueue(v);
		while (!q.isEmpty()) {
			int w = q.dequeue();
			for (int k : g.adj(w)) {
				if (!marked[k]) {
					marked[k] = true;
					edgeTo[k] = w;
					distTo[k] = distTo[w] + 1;
					q.enqueue(k);
				}
			}
		}
	}

	public boolean hasPathTo(int v) {
		if (v >= 0 && v < marked.length) {
			return marked[v];
		}
		return false;
	}

	// shortest path to entered point
	public int distTo(int v) {
		if (v >= 0 && v < marked.length) {
			return distTo[v];
		}
		return INFINITY;
	}

	public Iterable<Integer> pathTo(int v) {
		if (v >= 0 && v < marked.length) {
			if (!hasPathTo(v)) {
				return null;
			}
			Stack<Integer> path = new Stack<Integer>();
			int i;
			for (i = v; distTo[i] != 0; i = edgeTo[i])
				path.push(i);
			path.push(i);
			return path;
		}
		return null;
	}

}
