import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Search {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("test.txt"));
        int N = Integer.parseInt(in.readLine());
        int start = 0;
        int exit = 18;
        System.out.println("Size : "+ N + " Start : "+ start + " Exit : "+ exit);
        Graph graph = new Graph(N);
        for(String line = in.readLine(); line != null; line = in.readLine()) {
            String[] vertices = line.split(" ");
            int vertex = Integer.parseInt(vertices[0]);
            for(int i = 1 ; i <  vertices.length ; i ++) {
                int a = Integer.parseInt( vertices[i]);
                graph.addEdge(vertex, a);
            }
        }
        //return -1 if you cannot move from start to exit
        BFS bfs = new BFS(N);
        bfs.bfs(graph,start,exit);
        bfs.shortestPath();
    }
}