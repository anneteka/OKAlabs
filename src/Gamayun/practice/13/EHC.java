public class EHC {
    Graph g;
    Digraph d;

    public EHC(Graph graph){//1
        g=graph;
    }

    public EHC(Digraph digraph){//4
        d=digraph;
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


    public EulerCycle1 getEulerD(){
        EulerCycle1 eu = new EulerCycle1(d);
        if(eu.isEulerian())
            return eu;
        else return null;
    }

    public HamiltonCycle getHamiltonD(){
        HamiltonCycle hc = new HamiltonCycle(d);
        if(hc.isHamilton())
            return hc;
        else return null;
    }
}
