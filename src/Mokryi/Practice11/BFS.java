import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BFS {
    
    private boolean[] isMarked;
    private int[] edge;
    private int[] dist;
    private int[] shortWay;

    BFS(int v){
        isMarked = new boolean[v];
        edge = new int[v];
        dist = new int[v];
    }

    private void shortWay() {
        for(int i = 0 ; i < shortWay.length-1 ; i++) {
            System.out.print(shortWay[i] + " -> ");
        }
        System.out.print(shortWay[shortWay.length-1]);
    }

    private void bfs(Graph G, int s , int finish) {
        Queue<Integer> q = new Queue<>();
        q.enqueue(s);
        
        isMarked[s] = true;
        dist[s] = 0;

        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if (!isMarked[w]) {
                    q.enqueue(w);
                    isMarked[w] = true;
                    edge[w] = v;
                    dist[w] = dist[v] + 1;
                }
            }
        }
        shortWay = new int[dist[finish]];
        shortWay[0] = s;
        int last = finish;
        for(int i = shortWay.length-1 ; last != s ; i--){
            shortWay[i] = last;
            last = edge[last];
        }
    }
    
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("Graph.txt"));

        int v = Integer.parseInt(reader.readLine());
        int start = Integer.parseInt(reader.readLine());
        int finish = Integer.parseInt(reader.readLine());

        System.out.println("----Maze----");
        System.out.println(" size: "+ v + "\n start : "+ start + "\n finish : "+ finish+"\n");

        Graph g = new Graph(v);
        
        for(String line = reader.readLine(); line != null; line = reader.readLine()) {
            String[] vertices = line.split(" ");

            int vertex = Integer.parseInt(vertices[0]);
            int e = 0;
            for(int i = 1 ; i <  vertices.length ; i ++) {
                e = Integer.parseInt( vertices[i]);
                g.addEdge(vertex, e);
            }
        }
        reader.close();

        BFS b = new BFS(v);

        b.bfs(g,start,finish);
        b.shortWay();
    }
}