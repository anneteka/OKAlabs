package Practice_11;
import java.util.Stack;

public class BreadthFirstPaths {
	public static final int INFINITY = Integer.MAX_VALUE;
	private boolean[] marked; 
	private int[] edgeTo;
	private int[] distTo; 
	private final int s; 
	private int visitedVertexes;
	
	public BreadthFirstPaths(Graph G, int s) {
		marked = new boolean[G.V()];
		distTo = new int[G.V()];
		edgeTo = new int[G.V()];
		visitedVertexes = 0;
		this.s = s;
		bfs(G, s);
	}

	private void bfs(Graph G, int s) {
		Queue<Integer> queue = new Queue<Integer>();
		for (int v = 0; v < G.V(); v++) {
			distTo[v] = INFINITY;
		}
		distTo[s] = 0;
		marked[s] = true; 
		visitedVertexes++;
		queue.enqueue(s);
		while (!queue.isEmpty()) {
			int v = queue.dequeue(); 
			for (int w : G.adj(v))
				if (!marked[w]) { 
					edgeTo[w] = v; 
					distTo[w] = distTo[v] + 1;
					marked[w] = true; 
					visitedVertexes++;
					queue.enqueue(w); 
				}
		}
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

	public int distTo(int v) {
		return distTo[v];
	}
	
	public int visitedVertexes() {
		return visitedVertexes;
	}
}
