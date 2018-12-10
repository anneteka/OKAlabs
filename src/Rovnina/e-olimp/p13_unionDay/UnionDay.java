package unionDay;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Print the minimum total length of the roads
 * 
 * @author Rovnina Tetiana
 *
 */
public class UnionDay {

	public static void main(String[] args) throws IOException {
		PrintWriter out = new PrintWriter(new File("src/unionDay/output.txt"));
		Scanner sc = new Scanner(new File("src/unionDay/input.txt"));
		int n = sc.nextInt();// number of cities

		int[] xr = new int[n];// coordinates of X
		int[] yr = new int[n];// coordinates of Y

		for (int i = 0; i < n; i++) {
			xr[i] = sc.nextInt();
			yr[i] = sc.nextInt();
		}

		int INF = Integer.MAX_VALUE;
		int[][] graph = new int[n][];

		for (int u = 0; u < n; u++) {
			graph[u] = new int[u + 1];
			graph[u][u] = 0;
			for (int v = 0; v < u; v++) {
				int dx = xr[u] - xr[v];// distance X between cities
				int dy = yr[u] - yr[v];// distance Y between cities
				int w = dx * dx + dy * dy;// square of distance
				graph[u][v] = w;
			}
		}
		boolean[] used = new boolean[n];// if this vertex is used
		int[] dist = new int[n];

		Arrays.fill(dist, INF);// initialization array with very big numbers
		dist[0] = 0;

		// looking for the vertex with the minimum value dist[v] among the vertices
		// not yet included in the minimal skeleton (for which used [v] == false)
		while (true) {
			int v = -1;
			for (int nv = 0; nv < n; nv++)
				if (!used[nv] && dist[nv] < INF && (v == -1 || dist[v] > dist[nv]))
					v = nv;
			if (v == -1)
				break;
			used[v] = true;
			for (int nv = 0; nv < n; nv++)
				if (!used[nv] && get(graph, v, nv) < INF)
					dist[nv] = Math.min(dist[nv], get(graph, v, nv));
		}

		double w = 0;
		for (int d : dist) {
			w += Math.sqrt(d);
		}
		out.printf("%f\n", w);
		out.close();
	}

	// get element from triangle graph
	static int get(int[][] ar, int v, int u) {
		if (u > v)
			return ar[u][v];
		return ar[v][u];

	}
}
