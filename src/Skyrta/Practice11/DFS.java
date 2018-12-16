import java.util.Stack;

public class DFS {
	private boolean[] marked;
	private int[] edgeTo;
	private int v;

	DFS(Graph g, int v) {
		this.v = v;
		edgeTo = new int[g.vertices()];
		marked = new boolean[g.vertices()];
		if (v >= 0 && v < g.vertices()) {
			dfs(g, v);
		}
	}

	// recursively mark vertices
	private void dfs(Graph g, int w) {
		marked[w] = true;
		for (int i : g.adj(w))
			if (!marked[i]) {
				edgeTo[i] = w;
				// System.out.println(v);
				dfs(g, i);
			}
	}

	// it there is a path from current vertex to entered
	public boolean hasPathTo(int w) {
		if (w >= 0 && w < marked.length)
			return marked[w];
		return false;
	}

	// return path to vertex w
	public Iterable<Integer> pathTo(int w) {
		if (!hasPathTo(w))
			return null;
		Stack<Integer> stack = new Stack<>();
		for (int i = w; i != v; i = edgeTo[i]) {
			stack.push(i);
			// System.out.println("Add " + i + " to path to " + w);
		}
		stack.push(v);
		return stack;
	}

}
