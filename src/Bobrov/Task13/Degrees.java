package Task13;

import java.util.Collection;
import java.util.Vector;



public class Degrees {
    Digraph G, R;

    Degrees(Digraph G) {
        this.G = G;
        R = new Digraph(G.V());
        // �������� ����
        for (int i = 0; i < R.V(); i++) {
            for (int v : G.adj(i))
                R.addEdge(v, i);
        }
    }


    int indegree(int v) {
        Collection<Integer> c = (Collection<Integer>) R.adj(v);
        return c.size();

    }


    int outdegree(int v) {
        Collection<Integer> c = (Collection<Integer>) G.adj(v);
        return c.size();

    }


    Iterable<Integer> sources() {
        Vector<Integer> bag = new Vector<>();
        for (int i = 0; i < G.V(); i++) {
            if (indegree(i) == 0)
                bag.add(i);
        }
        return bag;

    }


    Iterable<Integer> sinks() {
        Vector<Integer> bag = new Vector<>();
        for (int i = 0; i < G.V(); i++) {
            if (outdegree(i) == 0)
                bag.add(i);
        }
        return bag;

    }


    boolean isMap() {
        for (int i = 0; i < G.V(); i++)
            for (int v : G.adj(i))
                if (outdegree(v) != 1)
                    return false;

        return true;
    }


    public Digraph getR() {
        return R;
    }
}