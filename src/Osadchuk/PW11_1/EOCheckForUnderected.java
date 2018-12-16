package PW11_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class EOCheckForUnderected {

    public static void main(String[] args) throws IOException {

	BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	PrintWriter pw = new PrintWriter(System.out);

	int n = Integer.parseInt(bf.readLine());

	int[][] arr = new int[n][n];

	for (int j = 0; j < n; ++j) {
	    String[] sts = bf.readLine().split(" ");
	    for (int i = 0; i < n; ++i) {
		arr[i][j] = Integer.parseInt(sts[i]);
	    }
	}

	boolean bl = true;
	cikl: for (int j = 0; j < n; ++j)
	    for (int i = 0; i < n; ++i) {
		if (i == j && arr[i][j] == 1) {
		    bl = false;
		    break cikl;
		}
		if (arr[i][j] != arr[j][i]) {
		    bl = false;
		    break cikl;
		}
	    }
	if (bl) {
	    pw.println("YES");
	} else {
	    pw.println("NO");
	}
	pw.flush();
    }

}