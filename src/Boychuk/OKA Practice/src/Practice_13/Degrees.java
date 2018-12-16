public class Degrees{

    private int sz;
    private Digraph d;
    private Digraph rev;
    public Degrees(Digraph G){
        sz = G.V();
        d = G;
        rev = G.reverse();
    }


    

   

    public Iterable<Integer> sources(){
        Bag<Integer> sources = new Bag<Integer>();
        for( int i = 0; i < sz ; i++ )
            if ( indegree(i) == 0 ) sources.add(i);
        return sources;
    }

 public int outdegree(int v){
        return d.outdegree(v);
    }


    public Iterable<Integer> sinks(){
        Bag<Integer> sinks = new Bag<Integer>();
        for( int i = 0; i < sz ; i++ )
            if (outdegree(i) == 0) sinks.add(i);
        return sinks;
    }
public int indegree(int v){
        return rev.indegree(v);
    }
}