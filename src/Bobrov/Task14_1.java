import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;


public class Task14_1 {
    static int INF = Integer.MAX_VALUE;
    static int[][] cap; // graph matrix
    static int[][] res; // additional matrix
    static boolean[] used; // array for marking using vertex
    static int n; // number of vertex

    public static void main(String[] args) throws IOException {
        new Task14_1().run();
    }

    private void run() throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter("src/destroyRoads/output.txt"));
        BufferedReader br = new BufferedReader(new FileReader("src/destroyRoads/input.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            n = Integer.parseInt(line);
            cap = new int[n][n];
            res = new int[n][n];
            used = new boolean[n];
            for (int i = 0; i < n; i++) {// create new graph as matrix
                line = br.readLine();
                for (int j = 0; j < n; j++)
                    cap[i][j] = line.charAt(j) - '0';
            }
            out.printf("%d\n", requiredCost());
        }

        br.close();
        out.close();

    }

    private static int requiredCost() {
        int best = INF;
        // result is minimum among max flow (minimal cut) between node 0 and other
        for (int s = 1; s < n; s++)
            best = Math.min(best, mincut(0, s));
        return best;
    }

    // return max flow (minimal cut) between s and t
    private static int mincut(int s, int t) {
        // copy graph matrix to matrix res
        for (int i = 0; i < n; i++)
            System.arraycopy(cap[i], 0, res[i], 0, n);
        int x, flow = 0;
        do {
            // initilize used;
            Arrays.fill(used, false);
        } while ((x = findCapacity(s, t, INF)) != 0 && (flow += x) != 0);
        return flow;
    }

    // find capacity constraints for complementary paths from x to t,
    // CurFlow - capacity constraints from 0 to x
    static int findCapacity(int x, int t, int CurFlow) {
        if (x == t)
            return CurFlow;
        if (used[x])
            return 0;
        used[x] = true;
        for (int Flow, y = 0; y < n; y++) {
            if (res[x][y] > 0 && (Flow = findCapacity(y, t, Math.min(CurFlow, res[x][y]))) > 0) {
                res[x][y] -= Flow;
                res[y][x] += Flow;
                return Flow;
            }
        }
        return 0;
    }

}