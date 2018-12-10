import edu.princeton.cs.algs4.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MazeBFS {
    public static void main(String[] args) throws FileNotFoundException {
        Random random = new Random();
        File file = new File("example/input.txt");
        Scanner sc = new Scanner(file);

        StringTokenizer tokenizer = new StringTokenizer(sc.nextLine());
        int numOfVert = Integer.parseInt(tokenizer.nextToken());

        tokenizer = new StringTokenizer(sc.nextLine());
        int numOfEdges = Integer.parseInt(tokenizer.nextToken());

        Graph gr = new Graph(numOfVert);
        for (int i = 1; i <= numOfEdges; i++) {
            tokenizer = new StringTokenizer(sc.nextLine());
            gr.addEdge(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()));
        }


        int lampaLocation = random.nextInt(numOfVert - 1) + 1;

        DepthFirstPath dfs = new DepthFirstPath(gr, 0);
        BreadthFirstPaths bfs = new BreadthFirstPaths(gr, 0);

        System.out.println("Інфо по графу:\nЛокація цілі: " + lampaLocation);
        System.out.println(gr.toString());
        System.out.println("Результати знаходження через пошук в глибину: " + dfs.pathTo(lampaLocation).toString() + "\nдовжина шляху: " + dfs.pathLength);
        System.out.println("Результати знаходження через пошук в ширину: " + bfs.pathTo(lampaLocation).toString() + "\nдовжина шляху: " + bfs.distTo(lampaLocation));

        System.out.println("\n\nВисновок: знаходження цілі в лабіринті більш підходить для знаходження якомога коротшого шляху, якщо ставиться така задача");

    }
}

