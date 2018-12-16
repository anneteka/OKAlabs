import java.util.ArrayList;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Graph;

public class HamiltonianCycle {


	private ArrayList<Integer> cycle = new ArrayList<Integer>();
	private int[][] A;
	private boolean[] visited;
	private int n;

	public HamiltonianCycle(Graph gr) {
		this.A = new int[n][n];
		this.n = gr.V(); 
		visited = new boolean[n];
	}
	

	public Iterable<Integer> cycle() {
		return cycle;
	}
	public boolean hasHamiltonCycle(int curr) {
		cycle.add(curr);
		if (cycle.size() == n) {
			if (A[cycle.get(0)][cycle.get(cycle.size() - 1)] == 1) {
				cycle.add(0);
				return true;
			}

			cycle.remove(cycle.size() - 1);
			return false;
		}
		visited[curr] = true;
		for (int nxt = 0; nxt < n; ++nxt)

			if (A[curr][nxt] == 1 && !visited[nxt])
				if (hasHamiltonCycle(nxt))
					return true;

		visited[curr] = false;
		cycle.remove(cycle.size() - 1);
		return false;
	}
	

		 class DepthFirstOrder {

			private boolean[] marked;
			private ArrayList<Integer> reversePost;
			
			public DepthFirstOrder(Digraph G){
				reversePost = new ArrayList<Integer>();
				marked = new boolean[G.V()];
				for (int v = 0; v < G.V(); v++)
					if (!marked[v]) dfs(G, v);
			}
			
			private void dfs(Digraph G, int v){
				marked[v] = true;
				for (int w : G.adj(v))
					if (!marked[w]) dfs(G, w);
						reversePost.add(v);
			}
			
			public Iterable<Integer> reversePost()	{ 
				return reversePost; 
			}
		}

}