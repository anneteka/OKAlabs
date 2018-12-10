package helping;

import java.util.Queue;
import java.util.Stack;

public class BFS {
	private boolean[] marked;
	private int[] edgeTo;
	private int[] distTo;
	private final int s; 
	private int vertexCounter=0;
	private int pathLength=0;
	public BFS(Graph G, int s,int lamp) {
		this.s = s;
		edgeTo = new int[G.V()];
		marked = new boolean[G.V()];
		distTo = new int [G.V()];
		bfs(G, s,lamp);
	}
	private void bfs(Graph G, int s,int lamp) {
		LinkedQueue<Integer> q = new LinkedQueue<Integer>();
		q.enqueue(s);
		marked[s] = true;
		distTo[s] = 0;
		while (!q.isEmpty()) {
			int v = q.dequeue();
			for (int w : G.adj(v)) {
				if (!marked[w]) {
					q.enqueue(w);
					vertexCounter++;
					marked[w] = true;
					edgeTo[w] = v;
					distTo[w] = distTo[v] + 1;
					if(w==lamp) return;
				}
			}
		}
	}
	public int getVertexCounter(){
		return vertexCounter;
	}
	 public boolean hasPathTo(int v) {
	        return marked[v];
	    }
	 public int pathLength(){
		 return pathLength;
	 }
	 public Iterable<Integer> pathTo(int v) {
	        if (!hasPathTo(v)) return null;
	        Stack<Integer> path = new Stack<Integer>();
	        for (int x = v; x != s; x = edgeTo[x]){
	            path.push(x);
	            pathLength++;
	        }
	        path.push(s);
	        return path;
	    }

}
