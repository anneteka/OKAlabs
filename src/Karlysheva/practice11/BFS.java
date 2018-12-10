package practice11;

//import practice3.LinkedQueue;
import ua.princeton.lib.In;
import ua.princeton.lib.Queue;


import java.util.Random;

public class BFS {
    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;

    public BFS(Graph G, int vertex) {
        marked = new boolean[G.V];
        distTo = new int[G.V];
        edgeTo = new int[G.V];
        bfs(G, vertex);
    }

    private void bfs(Graph G, int s) {
        Queue<Integer> q = new Queue<>();
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
    }

    private static final String testFile = "tinyCG.txt";

    public static void main(String[] args) {
        In in = new In(testFile);
        Graph G = new Graph(in);
        BFS bfs = new BFS(G, 0);
        Random random = new Random();
        int lamp = random.nextInt(G.V);

        System.out.println("Lamp is: " + lamp);
        if (bfs.marked[lamp]) {
            System.out.print("Path to lamp: ");
            int temp = lamp;
            while (temp != 0) {
                System.out.print("-" + temp);
                temp = bfs.edgeTo[temp];
            }
            System.out.println("-" + temp);
            int lenght = bfs.distTo[lamp];
            System.out.println("Path length: " + lenght);
            System.out.println("Nodes number in path: " + (lenght + 1));
        } else {
            System.out.println("0 to lamp:  not connected");
        }
    }

}

