import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GraphExpander expander = new GraphExpander(scanner);
        expander.expand();;
        System.out.println(expander);
    }
}

class GraphExpander {
    private LinkedList<Integer>[] graphList;
    public GraphExpander(LinkedList<Integer>[] graphList) {
        this.graphList = graphList;
    }
    public GraphExpander(Scanner scanner) {
        int length = scanner.nextInt();
        scanner.nextLine();
        LinkedList<Integer>[] graphList = (LinkedList<Integer>[]) new LinkedList[length];
        for(int i=0;i<length;i++) {
            graphList[i] = new LinkedList<Integer>();
            String[] edges = scanner.nextLine().split(" ");
            for(int j=0;j<edges.length;j++) {
                if(edges[j].length() != 0) {
                    graphList[i].add(Integer.parseInt(edges[j]));
                }
            }
        }
        this.graphList = graphList;
    }
    public void expand() {
        LinkedList<Integer>[] expandedGraph = (LinkedList<Integer>[]) new LinkedList[graphList.length];
        for(int i=0;i<expandedGraph.length;i++) {
            expandedGraph[i] = new LinkedList<Integer>();
        }
        for(int i=0;i<graphList.length;i++) {
            for(int edge: graphList[i]) {
                expandedGraph[edge-1].add(i + 1);
            }
        }
        this.graphList = expandedGraph;
    }
    public LinkedList<Integer>[] get() {
        return graphList;
    }
    public String toString() {
        StringBuilder graphString = new StringBuilder();
        graphString.append(graphList.length);
        graphString.append("\n");
        for(LinkedList<Integer> vertex: graphList) {
            for(int edge: vertex) {
                graphString.append(edge);
                graphString.append(" ");
            }
            graphString.append("\n");
        }
        return graphString.toString();
    }
}