
import java.util.Stack;

import princeton.lib.Graph;
import princeton.lib.In;


public class HamiltonianCycle {

    private int[][] A;
    private Stack<Integer> cycle = new Stack<Integer>();
    private boolean[] visited;
    private int n;

    public HamiltonianCycle(Graph gr) {
        this.A = gr.asMatrix();
        this.n = gr.V();
        visited = new boolean[n];
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }
    /**
     * ��������, �� � ������������ ����
     * @param curr
     * @return
     */
    public boolean hasHamiltonCycle(int curr) {
        cycle.add(curr);
        if (cycle.size() == n) {
            if (A[cycle.get(0)][cycle.get(cycle.size() - 1)] == 1) {
                cycle.add(0);
                return true;
            }

            cycle.remove(cycle.size() - 1);
            return false;
        }
        visited[curr] = true;
        for (int nxt = 0; nxt < n; ++nxt)

            if (A[curr][nxt] == 1 && !visited[nxt])
                if (hasHamiltonCycle(nxt))
                    return true;

        visited[curr] = false;
        cycle.remove(cycle.size() - 1);
        return false;
    }

    public static void main(String[] args) {
        Graph g = new Graph(new In("src/hamiltonYes.txt"));
        HamiltonianCycle hm = new HamiltonianCycle(g);
        EulerianGraph eg = new EulerianGraph(g, new In("src/coordinates.txt"));
        if (hm.hasHamiltonCycle(0)) {
            System.out.println(hm.cycle());
            eg.showPath(hm.cycle());
        } else {
            System.out.println("No");
            eg.showGraph();
        }
    }
}