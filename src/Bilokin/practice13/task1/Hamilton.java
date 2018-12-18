package Task1;

import java.util.Stack;

import Helping.DepthFirstPaths;
import Helping.Graph;
import ua.princeton.lib.In;

public class Hamilton {
	private static String printcycl ="";
	public static Graph G;
	public static final String file = "test.txt";
	public static int degreeArray[];
	private boolean[] mark;
	private Stack<Integer> reverse;
	
	public Hamilton(Graph G){
		reverse = new Stack<Integer>();
		mark = new boolean[G.V()];
		for (int v = 0; v < G.V(); v++)
			if (!mark[v]) dfs(G, v);
	}
	
	private void dfs(Graph G, int v){
		mark[v] = true;
		for (int w : G.adj(v))
			if (!mark[w]) dfs(G, w);
				reverse.push(v);
	}
	
	public Iterable<Integer> reversePost(){ 
		return reverse;
	}	
	
	
	boolean isHamilton(){
		int w= reverse.pop();
		printcycl +=w+" => ";
		while(!reverse.isEmpty()){
			int v = reverse.pop();
			printcycl +=v+" => ";
		    @SuppressWarnings("rawtypes")
			DepthFirstPaths dfs = new DepthFirstPaths(G, w, v);
		    if (!dfs.hasPathTo(v))
		    	return false;
			w=v;
		}
		printcycl +=0;
		return true;
	}
	
	public static void main(String[] args) {
		In in = new In(file);
		G = new Graph(in);
		Hamilton hc = new Hamilton(G);
		boolean flag = hc.isHamilton();
		System.out.println("Hamilton cycle: "+flag);
		if(flag)
			System.out.println(printcycl);
	}


}
