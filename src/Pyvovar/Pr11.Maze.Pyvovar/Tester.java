import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Ви потрапили в лабіринт. Десь в лабіринті знаходиться чарівна лампа, що
 * дозволить Вам повернутися додому. Іншого виходу з лабіринту, крім як знайти
 * лампу, не існує. Ви з'євляєтеся в лабіринті завжди в одній і тій же точці,
 * лабіринт задається з файлу, чарівна лампа з'являється випадково. Ваша задача
 * знайти шлях до лампи використовуючи метод DFS і BFS, як результат вивести
 * шлях, довжину шляху, та кількість відвіданих вузлів. Як ви зрозуміли лабіринт
 * задається у вигляді графа. При реалізації DFS і BFS вузли додаються в чергу
 * або стек в порядку зростання їх нумерів.
 * 
 * @author Пивовар Олена, 4 група
 *
 */
public class Tester {

	public static void main(String[] args) throws IOException {
		new Tester().readFile();
	}

	/**
	 * зчитування з файлу та знаходження шляху
	 * 
	 * @throws IOException
	 */
	private void readFile() throws IOException {
		Scanner sc = new Scanner(new File("input1.txt"));
		PrintWriter pw = new PrintWriter(new File("result.txt"));
		int s = sc.nextInt();
		int v = sc.nextInt();
		int edg = sc.nextInt();

		Graph graph = new Graph(v);

		for (int i = 0; i < edg; i++) {
			int v1 = sc.nextInt();
			int v2 = sc.nextInt();
			graph.addEdge(v1, v2);
		}

		DepthFirstPaths dfp = new DepthFirstPaths(graph, s);
		pw.println("DepthFirstPaths: ");
		if (dfp.hasPathTo(0)) {
			int numberV = 0;
			pw.print("Шлях: ");
			for (int step : dfp.pathTo(0)) {
				pw.print(step + " ");
				numberV++;
			}
			pw.println();
			pw.println("Кількість пройдених вершин: " + numberV);
			int len = numberV-1;
			pw.println("Довжина шляху: " + len);
		} else
			pw.println("Шляху немає");
		pw.println();

		BreadthFirstPaths bfp = new BreadthFirstPaths(graph, s);
		pw.println("BreadthFirstPaths: ");
		if (bfp.hasPathTo(0)) {
			int numberV = 0;
			pw.print("Шлях: ");
			for (int step : bfp.pathTo(0)) {
				pw.print(step + " ");
				numberV++;
			}
			pw.println();
			pw.println("Кількість пройдених вершин: " + numberV);
			int len = numberV-1;
			pw.println("Довжина шляху: " + len);
		} else
			pw.println("Шляху немає");
		pw.close();
	}

}
