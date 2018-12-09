package maze;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * тестування класів DepthFirstPaths та BreadthFirstPaths
 * 
 * @author Rovnina Tetiana
 *
 */
public class Tester {
	public static void main(String[] args) throws IOException {
		PrintWriter pw = new PrintWriter("files/result.txt");

		pw.println("Результат DFS");
		new Tester().runDFP(pw);

		pw.println("\nРезультат BFS");
		new Tester().runBFP(pw);
		pw.close();
	}

	
	/**
	 * пошук лампи за допомогою метода bfs
	 */
	private void runBFP(PrintWriter pw) throws IOException {
		Scanner sc = new Scanner(new File("files/maze1.txt"));
		int s = sc.nextInt();
		int v = sc.nextInt();
		int edges = sc.nextInt();

		Graph graph = new Graph(v);

		for (int i = 0; i < edges; i++) {
			int v1 = sc.nextInt();
			int v2 = sc.nextInt();
			graph.addEdge(v1, v2);
		}

		BreadthFirstPaths bfp = new BreadthFirstPaths(graph, s);
		if (bfp.hasPathTo(0)) {
			int steps = 0;

			for (int p : bfp.pathTo(0)) {
				pw.print(" --> " + p);
				steps++;
			}
			pw.println("\nкількість вузлів: " + steps);
			pw.println("довжина шляху: " + (steps-1));
		} else
			pw.println("немає шляху");

	}

	/**
	 * пошук лампи за допомогою метода dfs
	 */
	private void runDFP(PrintWriter pw) throws IOException {
		Scanner sc = new Scanner(new File("files/maze1.txt"));
		int s = sc.nextInt();
		int v = sc.nextInt();
		int edges = sc.nextInt();

		Graph graph = new Graph(v);

		for (int i = 0; i < edges; i++) {
			int v1 = sc.nextInt();
			int v2 = sc.nextInt();
			graph.addEdge(v1, v2);
		}

		DepthFirstPaths dfp = new DepthFirstPaths(graph, s);
		if (dfp.hasPathTo(0)) {
			int steps = 0;
			for (int p : dfp.pathTo(0)) {
				pw.print(" --> " + p);
				steps++;
			}
			pw.println("\nкількість вузлів: " + steps);
			pw.println("довжина шляху: " + (steps-1));
		} else
			pw.println("немає шляху");

	}
}
