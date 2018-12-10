public class BFS {
    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;
    private int[] path;

    BFS(int V){
        marked = new boolean[V];
        edgeTo = new int[V];
        distTo = new int[V];
    }

    public void shortestPath() {
        System.out.print("Shortest path: ");
        for(int i = 0 ; i <= path.length-1 ; i++) {

            System.out.print(path[i] + " ");
        }
    }

    public void bfs(Graph G, int s , int last) {
        Queue<Integer> q = new Queue<Integer>();
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
        path = new int[distTo[last]];
        if(path.length!=0){
            path[0] = s;
            int l = last;
            for(int i = path.length-1 ; l != s ; i--){
                path[i] = l;
                l = edgeTo[l];
            }
        }
        else{
            path = new int[1];
            path[0]=-1;
        }


    }
}
