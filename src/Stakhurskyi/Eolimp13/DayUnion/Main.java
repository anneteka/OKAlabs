package Stakhurskyi.Eolimp13.DayUnion;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		new Main().run();
	}

	private void run() throws IOException {
		Scanner sc = new Scanner(new File("src/DayUnion/input.txt"));
		PrintWriter pw = new PrintWriter(new File("src/DayUnion/output.txt"));

		int n = sc.nextInt();

		Point[] points = new Point[n];

		for (int i = 0; i < n; i++) {
			Point point = new Point(sc.nextInt(), sc.nextInt());
			points[i] = point;
		}

		pw.println(findDist(n, points));
		sc.close();
		pw.close();
	}

	private double findDist(int n, Point[] points) {
		int[][] graph = new int[n][];

		for (int i = 0; i < n; i++) {
			graph[i] = new int[i + 1];
			graph[i][i] = 0;
			for (int j = 0; j < i; j++) {
				int dx = points[i].x - points[j].x;
				int dy = points[i].y - points[j].y;
				int w = (int) Math.pow(dx, 2) + (int) Math.pow(dy, 2);
				graph[i][j] = w;
			}
		}

		boolean[] used = new boolean[n];
		int[] dist = new int[n];
		int end_e[] = new int[n];

		for (int i = 0; i < used.length; i++)
			used[i] = false;

		dist[0] = 0;

		double distance = 0;
		for (int i = 1; i < dist.length; i++)
			dist[i] = Integer.MAX_VALUE;

		for (int i = 0; i < end_e.length; i++)
			end_e[i] = -1;

		for (int i = 0; i < n; i++) {
			int v = -1;
			for (int j = 0; j < n; j++)
				if (!used[j] && (v == -1 || dist[j] < dist[v]))
					v = j;

			used[v] = true;
			if (end_e[v] != -1)
				distance += Math.sqrt(distTo(points, v, end_e[v]));

			for (int to = 0; to < n; to++) {
				int fromVtoTO = (int) distTo(points, v, to);
				if (!used[to] && (fromVtoTO < dist[to])) {
					dist[to] = fromVtoTO;
					end_e[to] = v;
				}
			}
		}

		return distance;
	}

	private double distTo(Point[] points, int a, int b) {
		int dx = points[a].x - points[b].x;
		int dy = points[a].y - points[b].y;
		return Math.pow(dx, 2) + (int) Math.pow(dy, 2);
	}

}