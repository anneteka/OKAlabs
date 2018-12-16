import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * 
 * @author Maria Skyrta. Test DFS and BFS serch for magic lamp in maze
 *
 */
public class Tester {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File("C:/medium.txt")));
		String s = br.readLine();
		int n = Integer.parseInt(s);
		s = br.readLine();
		int edges = Integer.parseInt(s);
		Graph g = new Graph(n);
		StringTokenizer st;
		for (int i = 0; i < edges; i++) {
			s = br.readLine();
			st = new StringTokenizer(s);
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			g.addEdge(v, w);
		}
		// Vertex to search path to
		int lamp = 244;
		// DFS testing
		DFS dfs = new DFS(g, 0);
		if (dfs.hasPathTo(lamp)) {
			for (int i : dfs.pathTo(lamp)) {
				System.out.print(i + " - ");
			}
		} else
			System.out.println("No path to " + lamp);
		// BFS testing
		System.out.println("\n");
		BFS bfs = new BFS(g, 0);
		if (bfs.hasPathTo(lamp))
			for (int i : bfs.pathTo(lamp))
				System.out.print(i + " - ");
		else
			System.out.println("No path to " + lamp);
	}

}
