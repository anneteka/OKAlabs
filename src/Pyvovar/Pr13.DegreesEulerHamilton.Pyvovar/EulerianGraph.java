package practice;

import java.awt.Point;
import java.util.Stack;

import princetonlib.In;
import princetonlib.StdDraw;

/**
 * Напишіть і реалізуйте EulerianGraph для графів, вершини яких подані у вигляді
 * точок на площині з вказаними координатами. Включіть метод show() для
 * малювання такого графа за допомогою StdDraw.
 */
public class EulerianGraph {
	private Graph g;
	private Point points[];

	public EulerianGraph(Graph g, In in) {
		StdDraw.setScale(0, 100);
		this.g = g;
		points = new Point[g.V()];

		for (int i = 0; i < g.V(); i++)
			points[i] = new Point(in.readInt(), in.readInt());
	}

	/**
	 * малюємо граф
	 */
	public void showGraph() {
		for (int i = 0; i < g.V(); i++) {
			for (int v : g.adj(i)) {
				double x1 = points[i].x, x2 = points[v].x, y1 = points[i].y, y2 = points[v].y;
				double a = Math.atan2(y2 - y1, x2 - x1);
				double lenCut = 3;

				x1 += lenCut * Math.cos(a);
				y1 += lenCut * Math.sin(a);
				x2 -= lenCut * Math.cos(a);
				y2 -= lenCut * Math.sin(a);
				StdDraw.line(x1, y1, x2, y2);
			}
			StdDraw.circle(points[i].x, points[i].y, 3);
			StdDraw.text(points[i].x, points[i].y, String.valueOf(i));
		}
	}

	/**
	 * малюємо граф зі шляхом Eйлера
	 * 
	 * @param шлях p
	 */
	public void showPath(Iterable<Integer> p) {
		Stack<Integer> path = (Stack<Integer>) p;

		if (path == null)
			return;

		int from = -1;
		int count = 0;
		for (int v : path) {
			if (from == -1)
				from = v;
			else {
				StdDraw.text(points[from].x, points[from].y, String.valueOf(from));
				StdDraw.circle(points[from].x, points[from].y, 3);
				arrow(points[from].x, points[from].y, points[v].x, points[v].y, String.valueOf(++count));
				from = v;
			}
		}

	}

	/**
	 * метод, що малює лінію з номером кроку на шляху
	 * 
	 * @param str шлях ейлера
	 */
	private static void arrow(double x1, double y1, double x2, double y2, String str) {
		double a = Math.atan2(y2 - y1, x2 - x1); // кут нахилу лінії
		double lenCut = 3;// скорочення лінії, щоб не вилазила на кружечки

		// скорочуємо координати для лінії
		double dx = lenCut * Math.cos(a);
		double dy = lenCut * Math.sin(a);
		x2 -= dx;
		y2 -= dy;
		x1 += dx;
		y1 += dy;

		StdDraw.line(x1, y1, x2, y2);

		// числа на лініях
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.text((x1 + x2) / 2 + 2, (y1 + y2) / 2 + 2, str);
		StdDraw.setPenColor(StdDraw.BLACK);
	}

	public static void main(String[] args) {
		Graph g = new Graph(new In("source3sinks2.txt"));
		EulerianGraph eg = new EulerianGraph(g, new In("coordinates.txt"));
		eg.showGraph();
	}

}
