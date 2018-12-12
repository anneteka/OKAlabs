package Poberezhets.pr13;

import java.util.Stack;

/**
 * �������� �� ������ ������� ����
 * @author �������
 *
 */
public class KosarajuSharirSCC {
	private boolean marked[];
	private int[] id;
	private int count;
	
	public KosarajuSharirSCC(Digraph G){
		marked = new boolean[G.V()];
		id = new int[G.V()];
		DepthFirstOrder dfs = new DepthFirstOrder(G.reverse());
		for (int v : dfs.reversePost()){
			if (!marked[v]){
				dfs(G, v);
				count++;
			}
		}
	}
	
	private void dfs(Digraph G, int v){
		marked[v] = true;
		id[v] = count;
		for (int w : G.adj(v))
			if (!marked[w])
				dfs(G, w);
	}
	
	public boolean stronglyConnected(int v, int w){ 
		return id[v] == id[w]; 
		}
	public class DepthFirstOrder {

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
