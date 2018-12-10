import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

public class Solver {
	
	private int V = 0;
	private int E = 0;
	private final int start; //starter position
	private Graph G;
	private int target;
	
	private static final String testFile = "data\\tinyG.txt";

	public Solver() {
		start = 0;
	}
	
	public static void main(String[] args) throws Exception {
		Solver s = new Solver();
		s.findPath();
	}
	
	private void findPath() throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(testFile));
		try {
			V = Integer.parseInt(br.readLine());
			G = new Graph(V);
		}catch (Exception e) {
			System.out.println("Error in line 1");
		}
		try {
			E = Integer.parseInt(br.readLine());
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error in line 2");
		}
		try {
			for(int i = 0; i < E; i++) {
				String[] edge = br.readLine().split(" ");
				G.addEdge(Integer.parseInt(edge[0]), Integer.parseInt(edge[1]));
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("wrong input format");
		}
		
		Random rand = new Random(System.currentTimeMillis());
		target = rand.nextInt(V);
		
		BreadthFirstPaths bfs = new BreadthFirstPaths(G, start, V);
		int length = -2;
		
		System.out.println("The lamp is in the " + target + "-th room");
		if(bfs.hasPathTo(target)) {
			for(int i : bfs.pathTo(target)) {
				System.out.println(i);
				length ++;
			}
			if(length == -1)
				length++;
			System.out.println("The distance is: " + bfs.getDistance(target));
			System.out.println("The length of the path:" + (length));
		}
		else {
			System.out.println("There is no path to that room");
		}
		
//		for(int i: (G.adj(4))){
//			System.out.println(i);
//		}
	
		br.close();
	}
}
