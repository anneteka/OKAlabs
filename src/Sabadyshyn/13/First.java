import edu.princeton.cs.algs4.Cycle;
import edu.princeton.cs.algs4.Graph;

public class First {
    Graph graph;


    public First(Graph graph){
        this.graph=graph;
    }

    public EC EulerMethod(){
        EC euler = new EC(graph);
        if(euler.containsEulerianCycle()){
            return euler;
        }
        return null;
    }
    public Cycle HamiltonMethod(){
        Cycle hamilton = new Cycle(graph);
        if(hamilton.hasCycle()){
            return hamilton;
        }

        return null;
    }
}