public class SAP {
    // конструктор приймає орграф (не обов’язково DAG)

    public SAP(Digraph G) {

    }

// довжина найкоротшого шляху до спільного батька v та w

//-1 якщо такого шляху немає

    public int length(int v, int w) {
        return 0;
    }

// спільний батько v та w,  з найкоротшого шляху

//-1 якщо такого шляху немає

    public int ancestor(int v, int w) {
        return 0;
    }

// довжина найкоротшого шляху між будь-якою вершиною з v та з w;

//-1 якщо такого шляху немає

    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        return 0;
    }

// спільний батько з найкоротшого шляху …;

//-1 якщо такого шляху немає

    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        return 0;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);

        Digraph G = new Digraph(in);

        SAP sap = new SAP(G);

        while (!StdIn.isEmpty()) {

            int v = StdIn.readInt();

            int w = StdIn.readInt();

            int length = sap.length(v, w);

            int ancestor = sap.ancestor(v, w);

            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}
