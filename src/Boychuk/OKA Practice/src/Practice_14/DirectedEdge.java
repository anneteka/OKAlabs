public class DirectedEdge {
    private final int v;
    private final int w;
    private final double weight;

    public DirectedEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }
 public double weight() {
        return weight;
    }



    public int to() {
        return w;
    }

    public int from() {
        return v;
    }


   

    public String toString() {
        return v + "->" + w + " " + String.format("%5.2f", weight);
    }

}
