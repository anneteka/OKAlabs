import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;


public class DEC{
    private Digraph d;
    private boolean[] marked;
    private DirectedCycle directedCycle;
    public DEC( Digraph G ){
        d = G;
        marked = new boolean[G.V()];
        DirectedCycle dc = new DirectedCycle(G);
    }
    public boolean isEulerian(){
        if( d.V() != d.E()) {
            return false;
        }
        if(!directedCycle.hasCycle()){
            return false;
        }

        for(int a : directedCycle.cycle() ){
            marked[a] = true;
        }

        for( int i = 0; i < d.V(); i++ )
            if(!marked[i]){
                return false;
            }

        return true;
    }
}
