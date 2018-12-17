import edu.princeton.cs.algs4.Digraph;

import java.util.Stack;

public class Degrees {
    private Digraph digraph;

    Degrees(Digraph G) {
        this.digraph = G;
    }

    int indegree(int v) {
        int count = 0;
        for (int i = 0; i < digraph.V(); i++) {
            for (int a : digraph.adj(i)) {
                if (a == v) count++;
            }
        }
        return count;
    }

    int outdegree(int v) {
        int count = 0;
        for (int a : digraph.adj(v)) {
            count++;
        }
        return count;
    }

    Iterable<Integer> sources() {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < digraph.V(); i++) {
            for (int a : digraph.adj(i)) {
                if (indegree(a) == 0) stack.push(a);
            }
        }
        return stack;
    }

    Iterable<Integer> sinks() {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < digraph.V(); i++) {
            for (int a : digraph.adj(i)) {
                if (outdegree(a) == 0) stack.push(a);
            }
        }
        return stack;
    }

    /**
     * ˜Ë ∫ ‚≥‰Ó·‡ÊÂÌÌˇÏ
     */
    boolean isMap() {
        for (int i = 0; i < digraph.V(); i++) {
            for (int a : digraph.adj(i)) {
                if (outdegree(a) != 1) return false;
            }
        }
        return true;
    }
}
