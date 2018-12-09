import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Tester {
	private static File file0 = new File("src/first.txt");
	private static File file1 = new File("src/second.txt");
	private static File file2 = new File("src/third.txt");
	
  public static void main(String[] args) throws FileNotFoundException {
	  Scanner sc = new Scanner(file0);
	  int lamp = sc.nextInt();
	  int v = sc.nextInt();
	  Graph graph = new Graph(v);
	  int edg = sc.nextInt();
	  for(int i =0; i<edg; i++) {
		  int u = sc.nextInt();
		  int w = sc.nextInt();
		  graph.addEdge(u, w);
	  }
	  
	  System.out.println();
	  DepthFirstPaths df = new DepthFirstPaths(graph, lamp);
	  if(df.hasPathTo(0)) {
		  int number = 0;
	  for(int step: df.pathTo(0)) {
		  System.out.print(step+"-->");
		  number++;
	  }
	  System.out.println("FINISH");
	  System.out.println("Вершини що пройдені: "+number);
	  System.out.println("Довжина шляху: "+(number-1));
	  }
	  else {
		  System.out.println("No path :(");
	  }
     BreadthFirstPaths bf = new BreadthFirstPaths(graph, lamp);
     System.out.println("BFS:");
     if(bf.hasPathTo(0)) {
		  int number = 0;
	  for(int step: bf.pathTo(0)) {
		  System.out.print(step+"-->");
		  number++;
	  }
	  System.out.println("FINISH");
	  System.out.println("Вершини що пройдені: "+number);
	  System.out.println("Довжина шляху: "+(number-1));
	  }
	  else {
		  System.out.println("No path :(");
	  }
  }
	
}
