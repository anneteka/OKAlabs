package Kostiuk.Practice14Kostiuk.src;

import java.util.Stack;

public class Topological {
    private Iterable<Integer> order;

    public Topological(EdgeWeightedDigraph G) {
        EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(G);
        if (!finder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    public boolean hasOrder() {
        return order != null;
    }

    class DepthFirstOrder {
        private boolean[] marked;
        private int[] pre;
        private int[] post;
        private Queue<Integer> preorder;
        private Queue<Integer> postorder;
        private int preCounter;
        private int postCounter;

        public DepthFirstOrder(EdgeWeightedDigraph G) {
            pre = new int[G.V()];
            post = new int[G.V()];
            postorder = new Queue<Integer>();
            preorder = new Queue<Integer>();
            marked = new boolean[G.V()];
            for (int v = 0; v < G.V(); v++)
                if (!marked[v]) dfs(G, v);
        }

        private void dfs(EdgeWeightedDigraph G, int v) {
            marked[v] = true;
            pre[v] = preCounter++;
            preorder.enqueue(v);
            for (DirectedEdge e : G.adj(v)) {
                int w = e.to();
                if (!marked[w]) {
                    dfs(G, w);
                }
            }
            postorder.enqueue(v);
            post[v] = postCounter++;
        }


        public Iterable<Integer> reversePost() {
            Stack<Integer> reverse = new Stack<Integer>();
            for (int v : postorder)
                reverse.push(v);
            return reverse;
        }

    }

}