import java.util.Scanner;

import edu.princeton.cs.algs4.Digraph;
import ua.princeton.lib.In;
import ua.princeton.lib.StdIn;
import ua.princeton.lib.StdOut;

public class Outcast {

	public Outcast(WordNet wordnet) {

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