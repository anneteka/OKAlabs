package eolymp;

import java.util.Scanner;

public class IceCream {

	static int n;
	static int k;
	static int[] arr;
	static int left;
	static int right;
	
	
	static boolean Check(int Value)
	{
		int i, stall = 1, len = 0;
		for (i = 1; i < n; i++)
		{
			len += arr[i] - arr[i - 1];
			if (len >= Value) {
				len = 0;
				stall++;
			}
		}
		return stall >= k;
	}
	
	
	public static void main(String[] args) {
		System.out.println(" ");
		Scanner scanner = new Scanner(System.in);
		
		
		n = scanner.nextInt();
		k = scanner.nextInt();
		arr = new int[n];
		
		for(int i = 0;i < n;i++) {
			arr[i] = scanner.nextInt();
		}
		left = 0;
		right = 1000000000;
		
		while (left <= right)
		{
			int Middle = (left + right) / 2;
			if (Check(Middle)) left = Middle + 1;
			else right = Middle - 1;
		}
		
		System.out.println(left - 1);
		
	}

}
