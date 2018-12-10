import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Tester {

	public static void main(String[] args) throws FileNotFoundException {
		String filename = "orgraph.txt";
		Scanner s = new Scanner(new FileInputStream(filename));
		int size = s.nextInt();
		int matrix[][] = new int[size][size];
		String number = "";
		String line;
		s.nextLine();
		for (int i = 0; i < size; i++) {
			line = s.nextLine();
			for (int j = 0; j <= line.length(); j++) {
				if (j != line.length())
					if ((line.charAt(j) == ' ') && (number != " ")) {
						int k = Integer.parseInt(number) - 1;
						matrix[i][k] = 1;
						number = "";
					} else
						number += line.charAt(j);
				else {
					if (number != "") {
						int k = Integer.parseInt(number) - 1;
						matrix[i][k] = 1;
						number = "";
					}
				}
			}
		}
		int new_matrix[][] = orientedGraph(matrix, size);
		System.out.println(toString(new_matrix, size));

	}

	private static int[][] orientedGraph(int matrix[][], int size) {
		int new_matrix[][] = new int[size][size];
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				if (matrix[i][j] == 1)
					new_matrix[j][i] = 1;
		return new_matrix;
	}

	private static String toString(int matrix[][], int size) {
		String res = "";
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++)
				if (matrix[i][j] == 1)
					res += j + 1 + " ";
			res += "\n";
		}
		return res;
	}
}
