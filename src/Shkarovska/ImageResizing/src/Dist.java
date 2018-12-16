import java.util.Stack;

public class Dist {
    private double[] distTo;         // distTo[v] = distance  of shortest s->v path
    private Edge[] edgeTo;   // edgeTo[v] = last edge on shortest s->v path



    public Dist(EDigraph G, int s) {
        distTo = new double[G.V()];
        edgeTo = new Edge[G.V()];

        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;

       DFS topological = new DFS(G);
        for (int v : topological.reversePost()) {
            for (Edge e : G.adj(v))
                reduce(e);
        }
    }
    private void reduce(Edge e) {
        int v = e.from(), w = e.to();
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
        }       
    }

 
    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }


    public Iterable<Edge> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Edge> path = new Stack<Edge>();
        for (Edge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }
    

    class DFS {

    	private boolean[] marked;
    	private Stack<Integer> reversePost;
    	
    	public DFS(EDigraph G){
    		reversePost = new Stack<Integer>();
    		marked = new boolean[G.V()];
    		for (int v = 0; v < G.V(); v++)
    			if (!marked[v]) dfs(G, v);
    	}
    	
    	private void dfs(EDigraph G, int v){
    		marked[v] = true;
    		for (Edge w : G.adj(v))
    			if (!marked[w.from()]) dfs(G, w.from());
    				reversePost.push(v);
    	}

    	public Iterable<Integer> reversePost()	{ 
    		return reversePost; 
    	}
    }
}