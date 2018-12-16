
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.StdRandom;

public class DepthFirstPaths {

	private boolean[] visited;
	private int[] edgeTo;
	private final int s;
	private static int steps;

	public DepthFirstPaths(Graph G, int s) {
		this.s = s;
		this.edgeTo = new int[G.V()];
		this.visited = new boolean[G.V()];
		steps = 0;
		dfs(G, s);
	}

	private void dfs(Graph G, int v) {
		visited[v] = true;
		for (int w : G.adj(v)) {
			if (!visited[w]) {
				edgeTo[w] = v;
				dfs(G, w);
			}
		}
	}

	public boolean hasPathTo(int v) {
		return visited[v];
	}

	public Iterable<Integer> pathTo(int v) {
		if (!hasPathTo(v))
			return null;
		ArrayList<Integer> path = new ArrayList<Integer>();
		for (int x = v; x != s; x = edgeTo[x]) {
			path.add(x);
			steps++;
		}
		path.add(s);
		return path;
	}

	public static void main(String[] args) {
		Scanner in = null;
		try {
			in = new Scanner(new File("tinyG.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Graph G = new Graph(in.nextInt());
		int s = Integer.parseInt("0");
		DepthFirstPaths dfs = new DepthFirstPaths(G, s);
		int goal = StdRandom.uniform(G.V());
		for (int v = 0; v < G.V(); v++) {
			Iterable<Integer> path = dfs.pathTo(v);
			if (v == goal) {
				System.out.println("Our location is " + s);
				System.out.println("Our goal is " + goal);
				if (dfs.hasPathTo(v)) {
					int res = 0;
					ArrayList<Integer> print = new ArrayList<>();
					for (int x : path) {
						print.add(x);
					}
					System.out.print(print.get(print.size() - 1));
					for (int i = 1; i < print.size(); i++) {
						System.out.print(" - " + print.get(print.size() - 1 - i));
						res++;
					}

					System.out.println();
					System.out.println("The length of path is " + res);
					System.out.println("The number of steps is " + steps);
				}

				else {
					System.out.println(s + " to " + v + " not connected. \n");
				}
				break;
			}
		}
	}
}
