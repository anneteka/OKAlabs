import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BFS {
	private boolean[] marked;
	private int[] edgeTo;
	private int[] distTo;
	private int[] shortestPath;
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("Graph.txt"));
		int V = Integer.parseInt(reader.readLine());
		int start = Integer.parseInt(reader.readLine());
		int exit = Integer.parseInt(reader.readLine());
		System.out.println("Size : "+ V + "\nStart : "+ start + "\nExit : "+ exit);
		Graph g = new Graph(V);
		for(String line = reader.readLine(); line != null; line = reader.readLine()) {
			String[] vertices = line.split(" ");
			
			int vertex = Integer.parseInt(vertices[0]);
			int e = 0;
			for(int i = 1 ; i <  vertices.length ; i ++) {
				e = Integer.parseInt( vertices[i]);
				g.addEdge(vertex, e);
			}
		}		
		reader.close();
		
		BFS b = new BFS(V);
		b.bfs(g,start,exit);
		
		b.shortestPath();
	}
	
	BFS(int V){
		marked = new boolean[V];
		edgeTo = new int[V];
		distTo = new int[V];
	}
	
	private void shortestPath() {
		for(int i = 0 ; i < shortestPath.length-1 ; i++) {
			System.out.print(shortestPath[i] + "-");
		}
		System.out.print(shortestPath[shortestPath.length-1]);
	}
	
	private void bfs(Graph G, int s , int exit) {
		Queue<Integer> q = new Queue<Integer>();
		q.enqueue(s);
		marked[s] = true;
		distTo[s] = 0;
		
		while (!q.isEmpty()) {
			int v = q.dequeue();
			for (int w : G.adj(v)) {
				if (!marked[w]) {
					q.enqueue(w);
					marked[w] = true;
					edgeTo[w] = v;
					distTo[w] = distTo[v] + 1;
				}
			}
		}
		shortestPath = new int[distTo[exit]];
		shortestPath[0] = s;
		int last = exit;
		for(int i = shortestPath.length-1 ; last != s ; i--){
			shortestPath[i] = last;
			last = edgeTo[last];
		}
	}
}