package practice11;

import ua.princeton.lib.In;
import ua.princeton.lib.StdOut;

import java.util.Random;
import java.util.Stack;


public class DFS {

    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public DFS(Graph G, int s) {
        this.s = s;
        edgeTo = new int[G.V];
        marked = new boolean[G.V];
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }

    private static final String testFile = "tinyCG.txt";

    public static void main(String[] args) {
        In in = new In(testFile);
        Graph G = new Graph(in);
        DFS dfs = new DFS(G, 0);
        Random random = new Random();
        int lamp = random.nextInt(G.V);

        System.out.println("Lamp is: " + lamp);
        if (dfs.hasPathTo(lamp)) {
            Iterable<Integer> pathToLamp = dfs.pathTo(lamp);
            int lenght = 0;
            System.out.print("Path to lamp: ");
            for (int x : pathToLamp) {
                System.out.print("-" + x);
                lenght++;
            }
            System.out.println();
            System.out.println("Path length: " + lenght);
            System.out.println("Nodes number in path: " + (lenght + 1));
        } else {
            System.out.println("0 to lamp:  not connected");
        }
    }
}

