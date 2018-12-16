import ua.princeton.lib.In;
import ua.princeton.lib.StdDraw;

public class EulerianGraph {
	
	private final int V;
	private static String testFile="Test02.txt";
	Edge[] adj;
	
	 public EulerianGraph(In in){
	      	V = in.readInt();
		 	adj = new Edge[V];
	        int E = in.readInt();
	        for (int i = 0; i < E; i++){
	            int v = in.readInt();
	            int w = in.readInt();
	            Point p = new Point(v,w);
	            int v1 = in.readInt();
	            int w1 = in.readInt();
	            Point p1 = new Point(v1,w1);
	            Edge e = new Edge(p, p1);
	            adj[i]=e;
	        }
	 }
	
	public static void main(String[] args) {
		StdDraw.setXscale(0, 7);
		StdDraw.setYscale(0, 7);
		In in = new In(testFile);
		EulerianGraph eg = new EulerianGraph(in);
		for(int i = 0 ; i < eg.V;i++){
			Edge edg = eg.adj[i];
			edg.getOne().drawTo(edg.getTwo());
			
		}
	}
	
}
