package Stakhurskyi.Eolimp12;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

	public static void main(String[] args) throws IOException {
		new Main().run();
	}

	private void run() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File("src/TurnMe/input.txt")));
		PrintWriter pw = new PrintWriter(new File("src/TurnMe/output.txt"));

		String str = br.readLine();
		int n = Integer.parseInt(str);

		StringBuilder[] res = new StringBuilder[n + 1];

		for (int i = 1; i <= n; i++) {
			str = br.readLine();
			if (str.length() == 0)
				continue;
			String line[] = str.split(" ");
			for (int j = 0; j < line.length; j++) {
				int k = Integer.parseInt(line[j]);
				if (res[k] == null)
					res[k] = new StringBuilder(String.valueOf(i));
				else
					res[k].append(" ").append(i);
			}
		}

		pw.println(n);
		for (int i = 1; i <= n; i++) {
			if (res[i] != null)
				pw.print(res[i]);
			pw.println();
		}

		pw.close();
		br.close();
	}
}