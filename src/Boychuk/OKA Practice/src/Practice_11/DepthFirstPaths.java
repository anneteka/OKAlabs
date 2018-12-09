package Practice_11;
import java.util.Stack;

public class DepthFirstPaths {
	private static final int INFINITY = Integer.MAX_VALUE;
	private boolean[] marked; 
	private int[] edgeTo; 
	private final int s; 
	private int[] distTo;
	private int visitedVertexes;
	public DepthFirstPaths(Graph G, int s) {
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		distTo = new int[G.V()];
		visitedVertexes = 0;
		this.s = s;
		dfs(G, s);
	}

	private void dfs(Graph G, int v) {
		marked[v] = true;
		visitedVertexes++;
		distTo[v] = 0;
		for (int w : G.adj(v))
			if (!marked[w]) {
				edgeTo[w] = v;
				distTo[v] += 1;
				dfs(G, w);
			}
	}
	
	public boolean hasPathTo (int v) {
		return marked[v];
	}
	
	public int distTo(int v) {
		return distTo[v];
	}
	
	public Iterable<Integer> pathTo(int v){
		if (!hasPathTo(v))
			return null;
		Stack<Integer> path = new Stack<Integer>();
		for (int x = v; x != s; x = edgeTo[x])
			path.push(x);
		path.push(s);
		return path;
	}
	public int visitedVertexes() {
		return visitedVertexes;
	}
}
