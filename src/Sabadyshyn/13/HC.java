import edu.princeton.cs.algs4.Digraph;

import java.util.Stack;

public class HC {

    public Digraph G;

    private boolean[] marked;
    private Stack<Integer> reversePost;

    public HC(Digraph G){
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

    public Iterable<Integer> reversePost(){
        return reversePost;
    }

    boolean isHamilton(){
        int w=reversePost.pop();
        while(!reversePost.isEmpty()){
            int v = reversePost.pop();
            DFDP dfdp = new DFDP(G, w,v);
            if (!dfdp.hasPathTo(v)) {
                return false;
            }
            w=v;
        }
        return true;
    }

}
