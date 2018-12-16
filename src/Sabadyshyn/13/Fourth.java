import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Graph;

public class Fourth {
    Digraph digraph;

    public Fourth(Digraph digraph){//fourth
        this.digraph=digraph;
    }

    public DEC getEulerD(){
        DEC eu = new DEC(digraph);
        if(eu.isEulerian())
            return eu;
        else return null;
    }

    public HC getHamiltonD(){
        HC hc = new HC(digraph);
        if(hc.isHamilton())
            return hc;
        else return null;
    }
}