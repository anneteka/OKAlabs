public class Edge {

    private Vertex vertex1;

    private Vertex vertex2;

    public Edge(Vertex v1, Vertex v2) {
        vertex1 = v1;
        vertex2 = v2;
    }

    public Vertex getFirst() {
        return vertex1;
    }

    public Vertex getSecond() {
        return vertex2;
    }
}