package week11;

import edu.princeton.cs.algs4.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;
import java.util.StringTokenizer;

public class MazeBFS {
    public static void main(String[] args) throws NumberFormatException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/week11/graphs/output.txt"));
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            int vertexes = Integer.parseInt(tokenizer.nextToken());
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int edges = Integer.parseInt(tokenizer.nextToken());
            Graph graph = new Graph(vertexes);
            for (int i = 0; i < edges; i++) {
                tokenizer = new StringTokenizer(bufferedReader.readLine());
                graph.addEdge(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()));
            }
            Random random = new Random();
            int goal = random.nextInt(vertexes-1) + 1;
//            int goal = 5;
            DepthFirstPath dfs = new DepthFirstPath(graph, 0);
            BreadthFirstPaths bfs = new BreadthFirstPaths(graph, 0);
            System.out.println(graph.toString());
            System.out.println("Start vertex - 0");
            System.out.println("Goal vertex - " + goal);
            System.out.println("DFS: "+dfs.pathTo(goal).toString());
            System.out.println("DFS length = "+dfs.pathLength);
            System.out.println("BFS: "+bfs.pathTo(goal).toString());
            System.out.println("BFS length = "+bfs.distTo(goal));
        } catch (NullPointerException e) {
            System.out.println("No path existing");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

