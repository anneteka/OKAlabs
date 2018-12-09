package Practice_11;
import java.io.FileNotFoundException;
import java.util.Random;

public class Tester {
	public static void main(String[] args) throws FileNotFoundException {
		int startPoint = 0;
		Random rand = new Random();
		Graph G = new Graph("D:\\lecture11\\workspasse\\OKA Practice\\src\\Practice_11\\mediumG.txt");
		System.out.println(G);
		int trophyAt = rand.nextInt(G.V());
		BreadthFirstPaths bfs = new BreadthFirstPaths(G, startPoint);
		System.out.println("Breadth first search \n");
		//for (int v = 0; v < G.V(); v++) {
			if (bfs.hasPathTo(trophyAt)) {
				System.out.printf("%d to %d:  ", startPoint, trophyAt);
				for (int x : bfs.pathTo(trophyAt)) {
					if (x == startPoint)
						System.out.print(x);
					else
						System.out.print(x + "-");
				}
				System.out.println();
				System.out.println("Path length: " + bfs.distTo(trophyAt));
				System.out.println("Visited vertexes: " + bfs.visitedVertexes() + "\n");
			} else {
				System.out.printf("%d to %d:  not connected\n", startPoint, trophyAt);
			}
		//}
		System.out.println("______________________________");
		DepthFirstPaths dfs = new DepthFirstPaths(G, startPoint);
		System.out.println("Depth first search \n");
		//for (int v = 0; v < G.V(); v++) {
			if (dfs.hasPathTo(trophyAt)) {
				System.out.printf("%d to %d:  ", startPoint, trophyAt);
				int length = -1;
				for (int x : dfs.pathTo(trophyAt)) {
					length++;
					if (x == startPoint)
						System.out.print(x);
					else
						System.out.print(x + "-");
				}
				System.out.println();
				System.out.println("Path length: " + length);
				System.out.println("Visited vertexes: " + bfs.visitedVertexes() + "\n");
			}

			else {
				System.out.printf("%d to %d:  not connected\n", startPoint, trophyAt);
			}

		//}
	}
}
