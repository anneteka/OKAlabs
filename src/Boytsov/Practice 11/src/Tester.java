import java.util.Random;

public class Tester {
	public static void main(String[] args) {
		Random rnd = new Random();
		In in = new In("mediumG.txt");
		Graph G = new Graph(in);
		int v = rnd.nextInt(G.V());
		int start = 0;
		DFS dfs = new DFS(G, start, v);
		BFS bfs = new BFS(G, start, v);

		// if (dfs.hasPathTo(v)) {
		StdOut.printf("%d to %d:  ", start, v);
		int length1 = 0;
		try {
			for (int x : dfs.pathTo(v)) {
				if (x == start)
					StdOut.print(x);
				else {
					StdOut.print("-" + x);
					length1++;
				}
			}
		} catch (Exception e) {
			System.out.println("Lamp is not reachable");
		}
		StdOut.println();
		StdOut.println("Length:" + length1);
		//
		// }
		//
		// else {
		// StdOut.printf("%d to %d: not connected\n", start, v);
		// }

		// if (bfs.hasPathTo(v)) {
		StdOut.printf("%d to %d:  ", start, v);
		int length2 = 0;
		try {
			for (int x : bfs.pathTo(v)) {
				if (x == start)
					StdOut.print(x);
				else {
					StdOut.print("-" + x);
					length2++;
				}
			}
		} catch (Exception e) {
			System.out.println("Lamp is not reachable");
		}
		StdOut.println();
		StdOut.println("Length:" + length2);
	}

	// else {
	// StdOut.printf("%d to %d (-): not connected\n", start, v);
	// }

	// }
}
