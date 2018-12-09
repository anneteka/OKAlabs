package Practice8;

import java.util.Scanner;
import java.util.Stack;

public class Wormix {

	private int[][] A;
	private Stack<Integer> ans = new Stack<Integer>();

	public static void main(String[] args) {
		Wormix obj = new Wormix();
		obj.doTask();
	}

	private void doTask() {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			int n = sc.nextInt();
			int k = sc.nextInt();
			int[] t = new int[n];
			int[] pts = new int[n];
			for (int i = 0; i < n; i++) {
				pts[i] = sc.nextInt();
				t[i] = sc.nextInt();
			}
			A = new int[n][n];
			for (int i = 0; i < n; i++) {
				A[0][i] = 0;
			}
			for (int i = 0; i < n; i++) {
				A[i][0] = 0;
			}
			for (int m = 1; m < n; m++) {
				for (int s = 1; s < n; s++) {
					if (s >= pts[m])
						A[m][s] = Math.max(A[m - 1][s], A[m - 1][s - pts[m]] + t[m]);
					else
						A[m][s] = A[m - 1][s];
				}
			}
			for(int m = 1; m<n; m++) {
				for(int s = 1; s < n; s++) {
					findAns(m,s,pts);
				}
			}
			String res = "";
			for(int f = 0; f<ans.capacity(); f++) {
				res += ans.pop();
			}
			System.out.println(res);
		}
	}

	private void findAns(int k, int s, int[] pts) {
		if (A[k][s] == 0)
			return;
		if (A[k - 1][s] == A[k][s])
			findAns(k - 1, s, pts);
		else {
			ans.push(k);
			findAns(k - 1, s - pts[k], pts);
		}
	}
}
