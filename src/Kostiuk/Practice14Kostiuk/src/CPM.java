package Kostiuk.Practice14Kostiuk.src;

/**
 *  The {@code CPM} class provides a client that solves the
 *  parallel precedence-constrained job scheduling problem
 *  via the <em>critical path method</em>. It reduces the problem
 *  to the longest-paths problem in edge-weighted DAGs.
 *  It builds an edge-weighted digraph (which must be a DAG)
 *  from the job-scheduling problem specification,
 *  finds the longest-paths tree, and computes the longest-paths
 *  lengths (which are precisely the start times for each job).
 *  <p>
 *  This implementation uses {@link AcyclicLP} to find a longest
 *  path in a DAG.
 *  The running time is proportional to <em>V</em> + <em>E</em>,
 *  where <em>V</em> is the number of jobs and <em>E</em> is the
 *  number of precedence constraints.
 *  <p>
 *  For additional documentation,
 *  see <a href="https://algs4.cs.princeton.edu/44sp">Section 4.4</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class CPM {

    // this class cannot be instantiated
    private CPM() { }

    /**
     *  ����� ��������� �� ������������ �� ������������ �����
     *  � ����� �������� ������ �� ������������ ������.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

        // number of jobs
        int n = StdIn.readInt();

        // source and sink
        int source = 2*n;
        int sink   = 2*n + 1;

        // build network
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(2*n + 2);
        for (int i = 0; i < n; i++) {
            double duration = StdIn.readDouble();
            G.addEdge(new DirectedEdge(source, i, 0.0));
            G.addEdge(new DirectedEdge(i+n, sink, 0.0));
            G.addEdge(new DirectedEdge(i, i+n,    duration));

            // precedence constraints
            int m = StdIn.readInt();
            for (int j = 0; j < m; j++) {
                int precedent = StdIn.readInt();
                G.addEdge(new DirectedEdge(n+i, precedent, 0.0));
            }
        }

        // compute longest path
        AcyclicLP lp = new AcyclicLP(G, source);

        // print results
        StdOut.println(" job   start  finish");
        StdOut.println("--------------------");
        for (int i = 0; i < n; i++) {
            StdOut.printf("%4d %7.1f %7.1f\n", i, lp.distTo(i), lp.distTo(i+n));
        }
        StdOut.printf("Finish time: %7.1f\n", lp.distTo(sink));
    }

}