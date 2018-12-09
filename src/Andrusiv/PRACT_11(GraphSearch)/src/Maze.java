import edu.princeton.cs.algs4.StdRandom;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Maze {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("maze.txt"));
        int vertexes = sc.nextInt();
        int edges = sc.nextInt();

        Graph graph = new Graph(vertexes);
        for (int i = 0; i < edges; i++) {
            graph.addEdge(sc.nextInt(), sc.nextInt());
        }

        // System.out.println(graph.toString());

        int lamp = StdRandom.uniform(1, vertexes);
        System.out.println("Lamp " + lamp);

        drawMaze(lamp);

        System.out.println("DFS");
        DFS dfs = new DFS(graph, 0);
        for (int k : dfs.pathTo(lamp)) {
            if (k == lamp) {
                System.out.println("LAMP");
                break;
            }
            System.out.print(k + " -> ");
        }
        System.out.println("Path length = " + dfs.getDistTo(lamp));
        System.out.println("Visited vertexes = " + dfs.getCounter(lamp));

        System.out.println("BFS");
        BFS bfs = new BFS(graph, 0);
        for (int k : bfs.pathTo(lamp)) {
            if (k == lamp) {
                System.out.println("LAMP");
                break;
            }
            System.out.print(k + " -> ");
        }
        System.out.println("Path length = " + bfs.getDistTo(lamp));
        System.out.println("Visited vertexes = " + bfs.getCounter(lamp));
    }

    /**
     * hard test code
     *
     * @param lamp
     */
    public static void drawMaze(int lamp) {
        String[][] maze = new String[9][9];
        int k = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (i == 0 || i == 8) maze[i][j] = " - ";
                else if (j == 0 || j == 8) maze[i][j] = " | ";
                else if (j % 2 == 1 && i % 2 == 1 && k < 10) maze[i][j] = " " + (k++) + " ";
                else if (j % 2 == 1 && i % 2 == 1 && k >= 10) maze[i][j] = " " + (k++);
                else maze[i][j] = "   ";
            }
        }

        maze[2][5] = " - ";
        maze[4][3] = " - ";
        maze[6][3] = " - ";
        maze[3][2] = " | ";
        maze[5][4] = " | ";
        maze[7][6] = " | ";
        maze[(lamp / 4) * 2 + 1][(lamp % 4) * 2 + 1] = " * ";

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }
}
