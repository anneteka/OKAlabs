package Practice_11;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Graph {
	private final int V; // amount of vertexes
	private int E; // amount of edges
	private Bag<Integer>[]adj; // amount of adjective vertexes
	
	public Graph(int V) {
		this.V = V;
		this.E = 0;
		adj = (Bag<Integer>[]) new Bag[V];
	}
	
	public Graph(String path) throws FileNotFoundException {
		File f = new File(path);
		Scanner sc = new Scanner(f);
		V = sc.nextInt();
		adj = (Bag<Integer>[]) new Bag[V];
		for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
		int E = sc.nextInt();
		for (int i = 0; i < E; i++) {
			int v = sc.nextInt();
			int w = sc.nextInt();
			addEdge(v,w);
		}
	}
	
	public int V() {
		return V;
	}
	
	public int E() {
		return E;
	}
	
	public void addEdge(int v, int w) {
		adj[v].add(w); // Adding w in vertex list v.
		adj[w].add(v); // Adding v in vertex list w.
		E++;
	}
	
	public Iterable<Integer> adj(int v){
		return adj[v];
	}
	
	public String toString() {
		String s ="Graph has: "+ V + " vertexes, "+E+" edges\n";
		for (int i = 0; i < V; i++) {
			s+= i+" connected with: ";
			for (int w: this.adj(i)) {
				s+= w+ " ";
			}
			s+= "\n";
		}
		return s;
	}
}
