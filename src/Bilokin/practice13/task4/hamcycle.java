package Task4;



import java.util.Stack;

import Helping.DirectedDepthFirstPaths;
import Helping.Digraph;
import ua.princeton.lib.In;

public class hamcycle {
	public static String printC ="";
	public static Digraph G;
	public static final String file = "test4.txt";
	private boolean[] mark;
	private Stack<Integer> reverse;
	
	public hamcycle(Digraph G){
		reverse = new Stack<Integer>();
		mark = new boolean[G.V()];
		for (int v = 0; v < G.V(); v++)
			if (!mark[v]) dfs(G, v);
	}
	
	private void dfs(Digraph G, int v){
		mark[v] = true;
		for (int w : G.adj(v))
			if (!mark[w]) dfs(G, w);
				reverse.push(v);
	}
	
	public Iterable<Integer> reversePost(){ 
		return reverse;
	}	
	
	boolean isHam(){
		int w= reverse.pop();
		printC +=w+" => ";
		while(!reverse.isEmpty()){
			int v = reverse.pop();
			printC +=v+" => ";
		    DirectedDepthFirstPaths dfs = new DirectedDepthFirstPaths(G, w,v);
		    if (!dfs.hasPathTo(v)) {
	        	return false;
	        }
			w=v;
		}
		printC +=0;
		return true;
	}
	
	public static void main(String[] args){
		In in = new In(file);
		G = new Digraph(in);
		hamcycle hc = new hamcycle(G);
		boolean flag= hc.isHam();
		System.out.println("Hamilton cycle: "+flag);
		if(flag)
			System.out.println(printC);
	}

}
