import princeton.lib.*;

public class DirectedGraph {
    public void checkEulerCycle(Digraph g) {
        Iterable<Integer> path = new EulerianCycle(g).cycle();

        if (path != null) {
            StdOut.print("Eulerian cycle:  ");
            for (int v : path)
                StdOut.print(v + " ");
        } else {
            StdOut.println("Eulerian cycle:   none");
        }
    }


    public static void main(String[] args) {
        String fileName = "src/directedEulerYes.txt";
        Digraph g = new Digraph(new In(fileName));
        new DirectedGraph().checkEulerCycle(g);
        StdOut.println();

    }

}