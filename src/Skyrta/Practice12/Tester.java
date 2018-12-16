import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Tester {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File("C:/Practice12/digraph1.txt")));
		String s = br.readLine();
		int v = Integer.parseInt(s);
		Digraph d = new Digraph(v);
		s = br.readLine();
		int n = Integer.parseInt(s);
		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			s = br.readLine();
			st = new StringTokenizer(s);
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			d.addEdge(v1, v2);
		}
		// Testing SAP
		SAP sap = new SAP(d);
		System.out.println(sap.ancestor(3, 1));
		System.out.println(sap.length(3, 1));
		WordNet w = new WordNet("C:/Practice12/synsets.txt", "C:/Practice12/hypernyms.txt");
		// Testing Outcast
		Outcast oc = new Outcast(w);
		String[] test = { "horse", "zebra", "cat", "bear", "table" };
		// String[] test2 = { "water", "soda", "bed", "orange_juice", "milk",
		// "apple_juice", "tea coffee" };
		System.out.println(oc.outcast(test));
		// System.out.println(oc.outcast(test2));

	}

}
