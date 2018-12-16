package Practice13;

import java.util.Arrays;
import java.util.Scanner;

public class UniteDay {
	public static void main(String[] args) {
		UniteDay obj = new UniteDay();
		obj.doTask();
	}
		
		private final int MAX = 5001;

		int i, j, n, v, to;
		int x[] = new int[MAX], y[] = new int[MAX], used[] = new int[MAX], min_e[] = new int[MAX], end_e[] = new int[MAX];

		private int sqr(int x) { return x*x; }

		private int mesafe(int i, int j) { return sqr(x[j] - x[i]) + sqr(y[j] - y[i]); }

		private double doTask() {
			Scanner sc = new Scanner(System.in);
			n = sc.nextInt();
			for (i = 0; i < n; i++) {
				x[i] = sc.nextInt();
				y[i] = sc.nextInt();
			}
			Arrays.fill(min_e, 63);
			Arrays.fill(end_e, -1);
			Arrays.fill(used, 0);
			double dist = min_e[1] = 0;

			for (i = 0; i < n; i++)
			{
				v = -1;
				for (j = 0; j < n; j++)
					if (used[j]==0 && (v == -1 || min_e[j] < min_e[v])) v = j;

				used[v] = 1;
				if (end_e[v] != -1) dist += Math.sqrt(mesafe(v, end_e[v]));

				for (to = 0; to < n; to++)
				{
					int dV_TO = mesafe(v, to);
					if (used[to]==0 && dV_TO < min_e[to])
					{
						min_e[to] = dV_TO;
						end_e[to] = v;
					}
				}
			}
			System.out.println(dist);
			return 0;
		}
		 
}
	
