package Practice_11;

public class DepthFirstSearch {
	private boolean[] marked; // Was vertex visited?
	private int count; // Number of vertexes passed

	public DepthFirstSearch(Graph G, int s) {
		marked = new boolean[G.V()];
		dfs(G, s);
	}

	private void dfs(Graph G, int v) {
		marked[v] = true;
		count++;
		for (int w : G.adj(v))
			if (!marked[w])
				dfs(G, w);
	}

	public boolean marked(int w) {
		return marked[w];
	}

	public int count() {
		return count;
	}
}
