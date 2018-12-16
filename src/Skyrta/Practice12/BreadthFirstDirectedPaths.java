import java.util.Stack;

public class BreadthFirstDirectedPaths {
	private static final int INFINITY = Integer.MAX_VALUE;
	private boolean[] marked; // check if the path exists
	private int[] edgeTo;
	private int[] distTo;

	public BreadthFirstDirectedPaths(Digraph G, int s) {
		marked = new boolean[G.V()];
		distTo = new int[G.V()];
		edgeTo = new int[G.V()];
		for (int v = 0; v < G.V(); v++)
			distTo[v] = INFINITY;
		bfs(G, s);
	}

	public BreadthFirstDirectedPaths(Digraph G, Iterable<Integer> sources) {
		marked = new boolean[G.V()];
		distTo = new int[G.V()];
		edgeTo = new int[G.V()];
		for (int v = 0; v < G.V(); v++)
			distTo[v] = INFINITY;
		bfs(G, sources);
	}

	private void bfs(Digraph G, int s) {
		MyQueue<Integer> q = new MyQueue<Integer>();
		marked[s] = true;
		distTo[s] = 0;
		q.enqueue(s);
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

	private void bfs(Digraph G, Iterable<Integer> sources) {
		MyQueue<Integer> q = new MyQueue<Integer>();
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

	public int distTo(int v) {
		return distTo[v];
	}

	public boolean hasPathTo(int v) {
		return marked[v];
	}

	public Iterable<Integer> pathTo(int v) {
		if (!hasPathTo(v))
			return null;
		Stack<Integer> path = new Stack<Integer>();
		int x;
		for (x = v; distTo[x] != 0; x = edgeTo[x])
			path.push(x);
		path.push(x);
		return path;
	}

}
