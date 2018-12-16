import java.util.Scanner;

import princeton.lib.Digraph;
import princeton.lib.StdIn;
import princeton.lib.StdOut;

public class OutCast {
    public OutCast(WordNet wordNet) {
    }

    public String outcast(String[] nouns) {
        return null;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int in = sc.nextInt();
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