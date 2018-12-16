package graphs;

import java.util.Stack;

public class DepthFirstOrder {

	private boolean[] marked;
	private Stack<Integer> reversePost;

	public DepthFirstOrder(EdgeWeightedDigraph G) {
		reversePost = new Stack<Integer>();
		marked = new boolean[G.V()];
		for (int v = 0; v < G.V(); v++)
			if (!marked[v])
				dfs(G, v);
	}

	private void dfs(EdgeWeightedDigraph G, int v) {
		marked[v] = true;
		for (DirectedEdge w : G.adj(v))
			if (!marked[w.from()])
				dfs(G, w.from());
		reversePost.push(v);
	}
	

	public Iterable<Integer> reversePost() {
		return reversePost;
	}
}
