import java.util.*;
import java.io.*;

public class Main{
	public static void main(String[] args) throws IOException{
//		Scanner con = new Scanner(new FileReader ("first.txt"));
//		Scanner con = new Scanner(new FileReader ("second.txt"));
		Scanner con = new Scanner(System.in);
		int n = con.nextInt();
		int m[][] = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) {
				m[i][j] = con.nextInt();
				if ((i == j) && (m[i][j] == 1)) {
					System.out.println("NO");
					return;
				}
			}
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (m[i][j] != m[j][i]) {
					System.out.println("NO");
					return;
				}
		System.out.println("YES");
		con.close();

	}

}