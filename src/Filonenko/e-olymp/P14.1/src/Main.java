import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Solver solver = new Solver(scanner);
    }
}


class Solver {
    public Solver(Scanner scanner) {
        int length = scanner.nextInt();
        Graph country = new Graph(length);
        scanner.nextLine();
        for(int i=0;i<length;i++) {
            char[] tmp = scanner.nextLine().toCharArray();
            for(int j=0;j<length;j++) {
                country.addEdge(i, j, tmp[j] - '0');
            }
        }
        System.out.println(country);
        System.out.println(solve(country));
    }

    public int solve(Graph g) {
        int minCost = Integer.MAX_VALUE;
        for(int i=1;i<g.vertices.length;i++) {
            int firstVertex = 0;
            int secondVertex = i;
            minCost = Math.min(minCost, minCut(g, firstVertex, secondVertex));
        }
        return minCost;
    }

    private int minCut(Graph g, int firstIndex, int secondIndex) {
        int maxEdgeSum = 0;
        int[] connectedWithSet = new int[g.vertices.length];
        for(int i=0;i<g.vertices.length;i++) {
            connectedWithSet[i] = i;
            maxEdgeSum = Math.max(maxEdgeSum, maxConnected(g, firstIndex, connectedWithSet));
        }
        return maxEdgeSum;
    }

    private int maxConnected(Graph g, int vertex, int[] vertices) {
        int edgeSum = 0;
        for(int connectedVertex: vertices) {
            if(connectedVertex !=  0)
            edgeSum += weight(g, vertex, connectedVertex);
        }
        return edgeSum;
    }

    private int weight(Graph g, int firstVertex, int secondVertex) {
        return g.vertices[firstVertex][secondVertex];
    }
}
class Graph {
    int[][] vertices;

    public Graph(int numberOfVertices) {
        vertices = new int[numberOfVertices][numberOfVertices];
    }
    public void addEdge(int firstVertexIndex, int secondVertexIndex, int cost) {
       vertices[firstVertexIndex][secondVertexIndex] = cost;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<vertices.length;i++) {
            for(int j=0;j<vertices.length;j++) {
                builder.append(vertices[i][j]);
                builder.append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}

