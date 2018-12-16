import java.util.Stack;

public class DirectedDepthFirstPaths{

	private boolean[] marked;    
    private int[] edgeTo;        
    private final int s;  
    private final int l; 
    private boolean flag = false;
    
    public DirectedDepthFirstPaths(Digraph G, int s, int l){
        this.s = s;
        this.l =l;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        dfs(G, s);
    }
     public boolean hasPathTo(int v){
        return marked[v];
    }
    private void dfs(Digraph G, int v){
        marked[v] = true;
        if(flag) return;
        for (int w : G.adj(v)) {
        	 if(w==l) {
        		marked[w] = true;
             	flag = true; 
             	return;
             }
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }
    
   
    
    public Iterable<Integer> pathTo(int v){
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }
}
