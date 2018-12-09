package Practice8;

import java.util.Scanner;

public class IceCream {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()) {
		int n, k;
		long left = 0, right = 1000000000;
		long m[] = new long[10001];
		n = sc.nextInt();
		k = sc.nextInt();
		for(int i=0; i<n; i++) {
			m[i] = sc.nextLong();
		}
		while (left <= right)
		{
			long Middle = (left + right) / 2;
			if (check(Middle, n, k, m)) left = Middle + 1;
			else right = Middle - 1;
		}
		System.out.println(left-1);
		}
	}

	private static boolean check(long Value, int n, int k, long m[])
	{
		int i, stall = 1;
		long len = 0;
		for (i = 1; i < n; i++)
		{
			len += m[i] - m[i - 1];
			if (len >= Value) {
				len = 0; 
				stall++;
			}
		}
		return stall >= k;
	}
	
}
