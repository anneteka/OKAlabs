import java.util.Iterator;

public class Sap {
    private final Digraph sap;
    private int ancestor;
    private int min;

    public Sap(Digraph G) {
        sap = new Digraph(G);
    }

    public int length(int v, int w) {
        bfdp(v, w);
        return min;
    }

    public int ancestor(int v, int w) {
        bfdp(v, w);
        return ancestor;
    }

//    private void checkBounds(int v, int w) {
//        if (v < 0 || v > sap.V() - 1)
//            throw new java.lang.IndexOutOfBoundsException();
//
//        if (w < 0 || w > sap.V() - 1)
//            throw new java.lang.IndexOutOfBoundsException();
//    }
//
//    private void checkBounds(Iterable<Integer> v, Iterable<Integer> w) {
//        Iterator<Integer> iter = v.iterator();
//        while (iter.hasNext()) {
//            int tmp = iter.next();
//            if (tmp < 0 || tmp > sap.V() - 1)
//                throw new java.lang.IndexOutOfBoundsException();
//        }
//
//        iter = w.iterator();
//        while (iter.hasNext()) {
//            int tmp = iter.next();
//            if (tmp < 0 || tmp > sap.V() - 1)
//                throw new java.lang.IndexOutOfBoundsException();
//        }
//    }

    private void bfdp(int v, int w) {
        BreadthFirstDirectedPaths bV = new BreadthFirstDirectedPaths(sap, v);
        BreadthFirstDirectedPaths bW = new BreadthFirstDirectedPaths(sap, w);
        min = -1;
        ancestor = -1;
        for (int i = 0; i < sap.V(); i++) {
            if (bV.hasPathTo(i) && bW.hasPathTo(i)) {
                int minDist = bV.distTo(i) + bW.distTo(i);
                if (min < 0 || minDist < min) {
                    min = minDist;
                    ancestor = i;
                }
            }
        }
    }

    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        bfdpIter(v, w);
        return min;
    }

    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        bfdpIter(v, w);
        return ancestor;
    }

    private void bfdpIter(Iterable<Integer> v, Iterable<Integer> w) {
        BreadthFirstDirectedPaths bV = new BreadthFirstDirectedPaths(sap, v);
        BreadthFirstDirectedPaths bW = new BreadthFirstDirectedPaths(sap, w);
        min = -1;
        ancestor = -1;
        for (int i = 0; i < sap.V(); i++) {
            if (bV.hasPathTo(i) && bW.hasPathTo(i)) {
                int minDist = bV.distTo(i) + bW.distTo(i);
                if (min < 0 || minDist < min) {
                    min = minDist;
                    ancestor = i;
                }
            }
        }
    }
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        Sap sap = new Sap(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            System.out.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }


}