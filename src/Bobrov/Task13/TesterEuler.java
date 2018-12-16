package Task13;

import edu.princeton.cs.algs4.In;


public class TesterEuler {

    public static void main(String[] args) {


        Digraph e = new Digraph(new In("directedEulerYes.txt"));
        EulerianCycle ec = new EulerianCycle(e);
        if (ec.hasEulerianCycle()) {
            System.out.println("Has Eulerian cycle: " + ec.cycle());
        } else {
            System.out.println("No path found");
        }

    }


}