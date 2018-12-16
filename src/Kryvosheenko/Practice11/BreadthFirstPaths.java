import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.LinkedQueue;
import edu.princeton.cs.algs4.StdRandom;

public class BreadthFirstPaths {
	private boolean[] visited;
	private int[] edgeTo;
	private int[] distTo;
	private static int steps;

	public BreadthFirstPaths(Graph G, int s) {
		this.visited = new boolean[G.V()];
		this.distTo = new int[G.V()];
		this.edgeTo = new int[G.V()];
		this.steps = 0;
		bfs(G, s);
	}

	private void bfs(Graph G, int s) {
		LinkedQueue<Integer> path = new LinkedQueue<Integer>();
		path.enqueue(s);
		distTo[s] = 0;
		visited[s] = true;
		while (!path.isEmpty()) {
			int v = path.dequeue();
			for (int w : G.adj(v)) {
				if (!visited[w]) {
					path.enqueue(w);
					visited[w] = true;
					edgeTo[w] = v;
					distTo[w] = distTo[v] + 1;
				}
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
		int x;
		for (x = v; distTo[x] != 0; x = edgeTo[x]) {
			path.add(x);
			steps++;
		}
		path.add(x);
		return path;
	}

	public static void main(String[] args) {
		Scanner in = null;
		try {
			in = new Scanner(new File("tinyG.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int ourLocation = 0;
		Graph G = new Graph(in.nextInt());
		BreadthFirstPaths bfs = new BreadthFirstPaths(G, ourLocation);
		int goal = StdRandom.uniform(G.V());
		for (int v = 0; v < G.V(); v++) {
			Iterable<Integer> path = bfs.pathTo(v);
			if (v == goal) {
				System.out.println("Our location is " + ourLocation);
				System.out.println("Our goal is " + goal);
				if (bfs.hasPathTo(v)) {
					int res = 0;
					ArrayList<Integer> print = new ArrayList<>();
					for (int x : path) {
						print.add(x);
					}
					System.out.print(print.get(print.size() - 1));
					for (int i = 1; i < print.size(); i++) {
						System.out.print(" - " + print.get(print.size() - 1 - i));

					}

					System.out.println();
					System.out.println("The length of path is " + bfs.distTo[goal]);
					System.out.println("The number of steps is " + steps);
				}

				else {
					System.out.println(ourLocation + " to " + v + " not connected. \n");
				}
				break;
			}

		}

	}
}