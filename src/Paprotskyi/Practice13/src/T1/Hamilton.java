package Task1;

import java.util.Stack;

import Helping.DepthFirstPaths;
import Helping.Graph;
import lib.In;

public class Hamilton {
	private static String cyclePrint="";
	public static Graph G;
	public static final String testFile = "Test01.txt";
	public static int degreeArray[];
	private boolean[] marked;
	private Stack<Integer> reversePost;
	
	public Hamilton(Graph G){
		reversePost = new Stack<Integer>();
		marked = new boolean[G.V()];
		for (int v = 0; v < G.V(); v++)
			if (!marked[v]) dfs(G, v);
	}
	
	private void dfs(Graph G, int v){
		marked[v] = true;
		for (int w : G.adj(v))
			if (!marked[w]) dfs(G, w);
				reversePost.push(v);
	}
	
	public Iterable<Integer> reversePost(){ 
		return reversePost; 
	}	
	
	
	boolean isHam(){
		int w=reversePost.pop();
		cyclePrint+=w+" -> ";
		while(!reversePost.isEmpty()){
			int v = reversePost.pop();
			cyclePrint+=v+" -> ";
		    @SuppressWarnings("rawtypes")
			DepthFirstPaths dfs = new DepthFirstPaths(G, w, v);
		    if (!dfs.hasPathTo(v))
		    	return false;
			w=v;
		}
		cyclePrint+=0;
		return true;
	}
	
	public static void main(String[] args) {
		In in = new In(testFile);
		G = new Graph(in);
		Hamilton hc = new Hamilton(G);
		boolean flag = hc.isHam();
		System.out.println("Hamilton cycle: "+flag);
		if(flag)
			System.out.println(cyclePrint);
	}


}
