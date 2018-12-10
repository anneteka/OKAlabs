package turnMe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * turn graph
 * 
 * @author Rovnina Tetiana
 *
 */
public class TurnMe {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("src/turnMe/input.txt"));
		PrintWriter out = new PrintWriter(new File("src/turnMe/output.txt"));

		int n = Integer.parseInt(br.readLine()); // number of vertex
		StringBuilder[] res = new StringBuilder[n + 1]; // for quicker result. vertex begin from 1. so our line also
														// begin from 1. res[0] is empty

		for (int i = 1; i <= n; i++) {
			String line = br.readLine();
			if (line.length() != 0) {
				String[] row = line.split(" "); // vertex which are connected with current vertex (number of line)

				for (int j = 0; j < row.length; j++) {
					int v = Integer.parseInt(row[j]);
					if (res[v] == null)// create new builder for this vertex
						res[v] = new StringBuilder(String.valueOf(i));
					else
						res[v].append(" ").append(i);// add new vertex with space
				}
			}
		}

		out.printf("%d\n", n);
		for (int i = 1; i <= n; i++) {// print new graph
			if (res[i] != null)
				out.printf("%s", res[i].toString());
			out.printf("\n");
		}

		br.close();
		out.close();
	}

}
