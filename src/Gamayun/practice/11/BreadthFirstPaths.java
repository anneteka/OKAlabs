import java.util.ArrayList;

//import java.util.Queue;

public class BreadthFirstPaths {
	private final int start; //start position
	private boolean[] marked;
	private int[] edgeTo;
	private int[] distTo;
	
	public BreadthFirstPaths(Graph G, int start, int V) {
		this.start = start;
		marked = new boolean[V];
		edgeTo = new int[V];
		distTo = new int[V];
		bfs(G);
	}

	private void bfs(Graph G) {
		Queue<Integer> q = new Queue<Integer>();
		q.enqueue(start);
		marked[start] = true;
		distTo[start] = 0;
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
	
	public boolean hasPathTo(int v) {
        return marked[v];
    }
	
	@SuppressWarnings("unchecked")
	public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        ArrayList<Integer> path = new ArrayList<Integer>(getDistance(v));
        for (int x = v; x != start; x = edgeTo[x]) {
            path.add(x);
//            System.out.println(x);
        }
        path.add(start);
        return path;
    }
	
	public int getDistance(int v) {
		return distTo[v];
	}
}
