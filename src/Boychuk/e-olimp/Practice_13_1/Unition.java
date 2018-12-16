import java.util.Arrays;
import java.util.Scanner;

public class Unition {

	static int i, j, n, v, to;
	
	static int[] x = new int[5001];
	
	static int[] y = new int[5001];

	static int[] used = new int[5001];
	
	static int[] min_e = new int[5001];
	
	static int[] end_e = new int[5001];
	
	static int sqr(int x) { return x*x; }

	static int mesafe(int i, int j) 
	{ 
	return sqr(x[j] - x[i]) + sqr(y[j] - y[i]); 
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		
		for(int i = 0;i < n;i++)
		{
			x[i] = sc.nextInt();
			
			y[i] = sc.nextInt();	
		}
		
		Arrays.fill(min_e, 0x3F);
		
		Arrays.fill(end_e, -1);
		
		Arrays.fill(used, 0);
		double dist = min_e[1] = 0;
		
		
		for (i = 0; i < n; i++)
		{
			v = -1;
			for (j = 0; j < n; j++)
				if (used[j] == 0 && (v == -1 || min_e[j] < min_e[v])) v = j;

			used[v] = 1;
			if (end_e[v] != -1) dist += Math.sqrt(mesafe(v, end_e[v]));

			for (to = 0; to < n; to++)
			{
				int dV_TO = mesafe(v, to);
				if (used[to] ==0 && dV_TO < min_e[to])
				{
					min_e[to] = dV_TO;
					
					end_e[to] = v;
				}
			}
		}
		
		System.out.println(dist);
	}

}