package Stakhurskyi.Eolimp11;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class Main {

	public static void main(String[] args) throws IOException {
		new Main().run();
	}

	private void run() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File("src/graf/input.txt")));
		PrintWriter pw = new PrintWriter(new File("src/graf/output.txt"));
		while (true) {
			String s = br.readLine();
			if (s == null)
				break;
			StringTokenizer tokens = new StringTokenizer(s);
			int n = Integer.parseInt(tokens.nextToken());
			int[][] array = new int[n][n];
			for (int i = 0; i < n; i++) {
				s = br.readLine();
				tokens = new StringTokenizer(s);
				for (int j = 0; j < n; j++) {
					array[i][j] = Integer.parseInt(tokens.nextToken());
				}
			}
			if (simpleNotOrient(array))
				pw.println("YES");
			else
				pw.println("NO");
		}
		pw.close();
		br.close();
	}

	private boolean simpleNotOrient(int[][] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i][i] != 0)
				return false;
			for (int j = i + 1; j < array.length; j++) {
				if (array[i][j] != array[j][i])
					return false;
			}
		}
		return true;
	}

}