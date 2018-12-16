import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
	
  public static void main(String[] args) throws FileNotFoundException {
	  FileReader fw = new FileReader("src/file2.txt");
	  Scanner sc = new Scanner(fw);
	  int lamp = 6;
	  int v = 13;
	  Graph graph = new Graph(v);
	  
	  int edg = 13;
	  for(int i =0; i<edg; i++) {
		  int u = sc.nextInt();
		  int w = sc.nextInt();
		  graph.addEdge(u, w);
	  }
	  
	  System.out.println();
	  System.out.println("DFS: ");
	  DFS df = new DFS(graph, lamp);
	  if(df.hasPathTo(0)) {
		  int number = 0;
	  for(int step: df.pathTo(0)) {
		  System.out.print(step+" -> ");
		  number++;
	  }
	  System.out.println("Find");
	  System.out.println("steps: "+number);
	  }
	  else {
		  System.out.println("No path :(");
	  }
	  
	  System.out.println();
	  System.out.println("BFS: ");
     BFS bf = new BFS(graph, lamp,v);
     if(bf.hasPathTo(0)) {
		  int number = 0;
	  for(int step: bf.pathTo(0)) {
		  System.out.print(step+" -> ");
		  number++;
	  }
	  System.out.println("Find");
	  System.out.println("steps: "+number);
	  }
	  else {
		  System.out.println("No path :(");
	  }
  }
	
}