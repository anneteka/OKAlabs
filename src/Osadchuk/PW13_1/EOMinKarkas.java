package PW13_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;

public class EOMinKarkas {
    static int[][] arr;

    public static void main(String[] args) throws IOException {

	BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	PrintWriter pw = new PrintWriter(System.out);

	String[] st0 = bf.readLine().split(" ");
	int n = Integer.parseInt(st0[0]);
	int m = Integer.parseInt(st0[1]);

	arr = new int[m][3];

	for (int i = 0; i < m; ++i) {
	    String[] st = bf.readLine().split(" ");
	    arr[i][0] = Integer.parseInt(st[0]);
	    arr[i][1] = Integer.parseInt(st[1]);
	    arr[i][2] = Integer.parseInt(st[2]);
	}

	Arrays.sort(arr, new Comparator<int[]>() {
	    public int compare(int[] o1, int[] o2) {
		if (o1[2] < o2[2]) {
		    return -1;
		}
		if (o1[2] > o2[2]) {
		    return 1;
		}
		return 0;
	    }
	});

	int[] vert = new int[n + 1];

	int sum = 0;
	for (int i = 0; i < m; ++i) {
	    if (vert[arr[i][0]] == 0 || vert[arr[i][0]] != vert[arr[i][1]]) {
		sum += arr[i][2];

		if (vert[arr[i][0]] == 0 && vert[arr[i][1]] == 0) {
		    int min = arr[i][0];
		    if (min > arr[i][1]) {
			min = arr[i][1];
		    }
		    vert[arr[i][0]] = min;
		    vert[arr[i][1]] = min;
		} else if (vert[arr[i][0]] == 0) {
		    vert[arr[i][0]] = vert[arr[i][1]];
		} else if (vert[arr[i][1]] == 0) {
		    vert[arr[i][1]] = vert[arr[i][0]];
		} else {
		    if (vert[arr[i][0]] > vert[arr[i][1]]) {
			int kk = vert[arr[i][0]];
			for (int j = 1; j < n + 1; ++j) {
			    if (vert[j] == kk) {
				vert[j] = vert[arr[i][1]];
			    }
			}
		    } else {
			int kk = vert[arr[i][1]];
			for (int j = 1; j < n + 1; ++j) {
			    if (vert[j] == kk) {
				vert[j] = vert[arr[i][0]];
			    }
			}
		    }
		}
	    }

	}

	pw.println(sum);
	pw.flush();
    }
}