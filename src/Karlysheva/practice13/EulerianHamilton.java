package Karlysheva.practice13;

import ua.princeton.lib.Graph;

import java.util.NoSuchElementException;

public class EulerianHamilton {
    private EulerianCycle eulerian;
    private Cycle hamilton;
    private Graph graph;

    public EulerianHamilton(Graph graph) {
        eulerian = new EulerianCycle(graph);
        hamilton = new Cycle(graph);
        this.graph = graph;
    }

    public boolean hasEulerianCycle() {
        return eulerian.hasEulerianCycle();
    }

    public boolean hasHamiltonCycle() {
        return graph.V() == hamilton.size();
    }

    public EulerianCycle getEulerian() {
        if (hasEulerianCycle())
            return eulerian;
        else
            throw new NoSuchElementException();
    }

    public Cycle getHamilton() {
        if (hasHamiltonCycle())
            return hamilton;
        else
            throw new NoSuchElementException();
    }
}
