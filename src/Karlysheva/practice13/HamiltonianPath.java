package Karlysheva.practice13;

import ua.princeton.lib.*;

import java.util.Iterator;

public class HamiltonianPath {
    private Topological topological;
    private Digraph digraph;

    public HamiltonianPath (Graph graph){
        digraph=toDigraph(graph);
        topological=new Topological(digraph);
    }
    public HamiltonianPath (Digraph digraph){
        this.digraph=digraph;
        topological = new Topological(digraph);
    }
    private Digraph toDigraph(Graph graph){
        Digraph temp = new Digraph(graph.V());
        for (int i = 0; i < graph.V(); i++) {
            int help = i;
            Iterable<Integer> tempAdj = graph.adj(i);
           tempAdj.forEach(j -> temp.addEdge(help, j));
        }
        return temp;
    }
    public boolean hasHamiltonian(){
        Iterable<Integer> order = topological.order();
        if (order==null) return false;

        return true;
    }
}
