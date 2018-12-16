
import java.util.*;
import java.io.*;

class Tester {
  public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out, true);
		
		while(in.hasNextInt()){
			int i = in.nextInt();
			int j = in.nextInt();
			
			int from = Math.min(i,	j);
			int to = Math.max(i, j);
			
			int max = 0;
			
			for (int ii = from;ii<=to;ii++){
				max = Math.max(max, computeCycleLength(ii));
			}
			
			out.printf("%d %d %d\n", i, j, max);
		}
			
	}

	private static int computeCycleLength(long n) {
		
		if (n==1)
				return 1;
		if (n<_MaxValue && memo[(int)n] != 0)
			return memo[(int)n];
		
		
		int len = 1 + computeCycleLength(nextCollatz(n));
		
		
		if (n<_MaxValue)
			memo[(int)n] = len;
		return len;
	}
	
	
	private static int _MaxValue = 1000000;
	public static int[] memo = new int[_MaxValue];
	
	public static long nextCollatz(long n){
		
		if (n%2==0)
			return n/2;
		else 
			return n*3+1;
	}
}