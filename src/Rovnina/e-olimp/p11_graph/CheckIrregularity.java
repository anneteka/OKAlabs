package graph;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CheckIrregularity {

	public static void main(String[] args) throws IOException {
		new CheckIrregularity().run();

	}

	private void run() throws IOException {
		Scanner sc = new Scanner(new File("src/graph/input.txt"));
		PrintWriter pw = new PrintWriter(new File("src/graph/output.txt"));
		int n = sc.nextInt();
		int[][] array = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				array[i][j] = sc.nextInt();
			}
		}
		if (simpleIrregular(array))
			pw.println("YES");
		else
			pw.println("NO");
		pw.close();
	}

	/**
	 * @return true if graph simple and irregular
	 */
	private boolean simpleIrregular(int[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				if ((i == j && array[i][j] != 0) || array[i][j] != array[j][i])
					return false;
			}
		}
		return true;
	}

}
