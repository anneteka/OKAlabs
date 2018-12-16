package PW12_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Iterator;

public class EOPoverniMene {

    public static void main(String[] args) throws IOException {

	BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	PrintWriter pw = new PrintWriter(System.out);

	int n = Integer.parseInt(bf.readLine());

	ArrayDeque<Integer>[] answers = new ArrayDeque[n];
	for (int i = 0; i < n; ++i) {
	    answers[i] = new ArrayDeque<Integer>();
	}
	for (int j = 1; j <= n; ++j) {
	    String st = bf.readLine();
	    if (st.length() == 0)
		continue;
	    String[] sts = st.split(" ");
	    for (int i = 0; i < sts.length; ++i) {
		answers[Integer.parseInt(sts[i])-1].add(j);
	    }
	}

	pw.println(n);
	for (int i = 0; i < n; ++i) {
	    Iterator<Integer> it = answers[i].iterator();
	    while (it.hasNext()) {
		pw.print(it.next());
		if(it.hasNext()) {
		    pw.print(" ");
		}
	    }
	    pw.println();
	}

	pw.flush();
    }

}