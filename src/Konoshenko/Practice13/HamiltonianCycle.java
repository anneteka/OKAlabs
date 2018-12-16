import java.util.Stack;


public class HamiltonianCycle {

	private int[][] A;
	private Stack<Integer> cycle = new Stack<Integer>();
	private boolean[] visited;
	private int n;

	public HamiltonianCycle(Graph gr) {
		this.A = gr.makeMatrix();
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
			private Stack<Integer> reversePost;
			
			public DepthFirstOrder(Digraph G){
				reversePost = new Stack<Integer>();
				marked = new boolean[G.V()];
				for (int v = 0; v < G.V(); v++)
					if (!marked[v]) dfs(G, v);
			}
			
			private void dfs(Digraph G, int v){
				marked[v] = true;
				for (int w : G.adj(v))
					if (!marked[w]) dfs(G, w);
						reversePost.push(v);
			}
			
			public Iterable<Integer> reversePost()	{ 
				return reversePost; 
			}
		}

}