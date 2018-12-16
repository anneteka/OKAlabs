package Karlysheva.practice13;

import ua.princeton.lib.Digraph;
import ua.princeton.lib.Stack;

public class Degrees {

    private Digraph digraph;

    Degrees(Digraph G) {
        digraph = G;
    }

    int indegree(int v) {
       return digraph.indegree(v);
    }

    int outdegree(int v) {
        return digraph.outdegree(v);
    }

    // -джерела
    Iterable<Integer> sources() {
        Stack<Integer> temp = new Stack<>();
        for (int i = 0; i < digraph.V(); i++) {
            if (indegree(i)==0) temp.push(i);
        }
        return temp;
    }

    // - стоки
    Iterable<Integer> sinks() {
        Stack<Integer> temp = new Stack<>();
        for (int i = 0; i < digraph.V(); i++) {
            if (outdegree(i)==0) temp.push(i);
        }
        return temp;
    }

    // - чи є G відображенням
    boolean isMap() {
        for (int i = 0; i < digraph.V(); i++) {
            if (outdegree(i)!=1&&indegree(i)!=1) return false;
        }
        return true;
    }
}
