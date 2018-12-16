package Kostiuk.Practice13Kostiuk.src;

public class EulerHamiltoncycle {
    Graph grph;
    public EulerHamiltoncycle(Graph graph){//first
        grph=graph;
    }
    public EulerHamiltoncycle(Digraph digraph){//fourth
        grph=digraph.DigraphToGraph();
    }
    public EulerCycle getEuler(){
        EulerCycle euler = new EulerCycle(grph);
        if(euler.hasEulerianCycle())
        return euler;
        else return null;
    }
    public Cycle getHamilton(){
        Cycle hamilton = new Cycle(grph);
        if(hamilton.hasCycle())
        return hamilton;
        else return null;
    }
}