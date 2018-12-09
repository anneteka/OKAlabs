public class EHC {
    Graph g;


    public EHC(Graph graph){//1
        g=graph;
    }

    public EHC(Digraph digraph){//4
        g=digraph.DigraphToGraph();
    }

    public EulerCycle getEuler(){
        EulerCycle eu = new EulerCycle(g);
        if(eu.hasEulerianCycle())
        return eu;
        else return null;
    }

    public Cycle getHamilton(){
        Cycle hc = new Cycle(g);
        if(hc.hasCycle())
        return hc;
        else return null;
    }
}
