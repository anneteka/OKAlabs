package Task12;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SAP {
    private Digraph graph;

    private Map<String, SAPProcessor> cache;

    public SAP(Digraph g) {
        cache = new HashMap<>();
        graph = new Digraph(g);
    }

    private boolean validIndex(int i) {
        if (i < 0 || i >= graph.V()) {
            return false;
        }
        return true;
    }

    private boolean validIndex(Iterable<Integer> vertices) {
        for (Integer vertex : vertices) {
            if (!validIndex(vertex)) {
                return false;
            }
        }
        return true;
    }


    public int length(int v, int w) {
        if (!validIndex(v) || !validIndex(w)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return cachedResult(v, w).distance;
    }


    public int ancestor(int v, int w) {
        if (!validIndex(v) || !validIndex(w)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return cachedResult(v, w).ancestor;
    }


    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        if (!validIndex(v) || !validIndex(w)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return cachedResult(v, w).distance;
    }


    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if (!validIndex(v) || !validIndex(w)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return cachedResult(v, w).ancestor;
    }


    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph graph = new Digraph(in);
        SAP sap = new SAP(graph);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }

    private SAPProcessor cachedResult(int v, int w) {
        String key = v + "_" + w;
        if (cache.containsKey(key)) {
            SAPProcessor p = cache.get(key);
            cache.remove(key);
            return p;
        }
        SAPProcessor p = new SAPProcessor(v, w);
        cache.put(key, p);
        return p;
    }

    private SAPProcessor cachedResult(Iterable<Integer> v, Iterable<Integer> w) {
        String key = v.toString() + "_" + w.toString();
        if (cache.containsKey(key)) {
            SAPProcessor p = cache.get(key);
            // we need to cache only for 2 consecutive calls, therefore we delete the result after the second call
            cache.remove(key);
            return p;
        }

        SAPProcessor p = new SAPProcessor(v, w);
        cache.put(key, p);
        return p;
    }

    private class SAPProcessor {
        int ancestor;
        int distance;

        public SAPProcessor(int v, int w) {
            BreadthFirstDirectedPaths a = new BreadthFirstDirectedPaths(graph, v);
            BreadthFirstDirectedPaths b = new BreadthFirstDirectedPaths(graph, w);

            process(a, b);
        }

        public SAPProcessor(Iterable<Integer> v, Iterable<Integer> w) {
            BreadthFirstDirectedPaths a = new BreadthFirstDirectedPaths(graph, v);
            BreadthFirstDirectedPaths b = new BreadthFirstDirectedPaths(graph, w);

            process(a, b);
        }

        private void process(BreadthFirstDirectedPaths a, BreadthFirstDirectedPaths b) {
            List<Integer> ancestors = new ArrayList<>();
            for (int i = 0; i < graph.V(); i++) {
                if (a.hasPathTo(i) && b.hasPathTo(i)) {
                    ancestors.add(i);
                }
            }

            int shortestAncestor = -1;
            int minDistance = Integer.MAX_VALUE;
            for (int ancestor : ancestors) {
                int dist = a.distTo(ancestor) + b.distTo(ancestor);
                if (dist < minDistance) {
                    minDistance = dist;
                    shortestAncestor = ancestor;
                }
            }
            if (Integer.MAX_VALUE == minDistance) {
                distance = -1;
            } else {
                distance = minDistance;

            }
            ancestor = shortestAncestor;
        }
    }
}