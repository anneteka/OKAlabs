package week12.practice;
import edu.princeton.cs.algs4.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SAP {
    private Digraph graph;

    private Map<String, SAPhelp> map;

    public SAP(Digraph g) {
        map = new HashMap<>();
        graph = new Digraph(g);
    }

    //довжина найкоротшого шляху до спільного батька v та w
    //-1 якщо такого шляху немає
    public int length(int v, int w) {
        if (!validIndex(v) || !validIndex(w)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        String key = v + "_" + w;
        if (map.containsKey(key)) {
            SAPhelp p = map.get(key);
            map.remove(key);
            return p.distance;
        } else {
            SAPhelp p = new SAPhelp(v, w);
            map.put(key, p);
            return p.distance;
        }
    }

    //спільний батько v та w,  з найкоротшого шляху
    //-1 якщо такого шляху немає
    public int ancestor(int v, int w) {
        if (!validIndex(v) || !validIndex(w)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        String key = v + "_" + w;
        if (map.containsKey(key)) {
            SAPhelp p = map.get(key);
            map.remove(key);
            return p.ancestor;
        } else {
            SAPhelp p = new SAPhelp(v, w);
            map.put(key, p);
            return p.ancestor;
        }
    }

    //довжина найкоротшого шляху між будь-якою вершиною з v та з w;
    //-1 якщо такого шляху немає
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        if (!validIndex(v) || !validIndex(w)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        String key = v + "_" + w;
        if (map.containsKey(key)) {
            SAPhelp p = map.get(key);
            map.remove(key);
            return p.distance;
        } else {
            SAPhelp p = new SAPhelp(v, w);
            map.put(key, p);
            return p.distance;
        }
    }

    //спільний батько з найкоротшого шляху
    //-1 якщо такого шляху немає
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if (!validIndex(v) || !validIndex(w)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        String key = v + "_" + w;
        if (map.containsKey(key)) {
            SAPhelp p = map.get(key);
            map.remove(key);
            return p.ancestor;
        } else {
            SAPhelp p = new SAPhelp(v, w);
            map.put(key, p);
            return p.ancestor;
        }
    }

    //перевірка на валідний уведений індекс
    private boolean validIndex(int i) {
        if (i < 0 || i >= graph.V()) {
            return false;
        }
        return true;
    }

    //перевірка на валідний уведений індекс
    private boolean validIndex(Iterable<Integer> vertices) {
        for (Integer vertex : vertices) {
            if (!validIndex(vertex)) {
                return false;
            }
        }
        return true;
    }

    private class SAPhelp {
        int ancestor;
        int distance;

        public SAPhelp(int v, int w) {
            BreadthFirstDirectedPaths a = new BreadthFirstDirectedPaths(graph, v);
            BreadthFirstDirectedPaths b = new BreadthFirstDirectedPaths(graph, w);

            help(a, b);
        }

        public SAPhelp(Iterable<Integer> v, Iterable<Integer> w) {
            BreadthFirstDirectedPaths a = new BreadthFirstDirectedPaths(graph, v);
            BreadthFirstDirectedPaths b = new BreadthFirstDirectedPaths(graph, w);

            help(a, b);
        }

        private void help(BreadthFirstDirectedPaths a, BreadthFirstDirectedPaths b) {
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