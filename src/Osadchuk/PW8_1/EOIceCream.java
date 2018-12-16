package PW8_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class EOIceCream {

	public static void main(String[] args) throws IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		String st1[] = bf.readLine().split(" ");
		int n = Integer.parseInt(st1[0]);
		int k = Integer.parseInt(st1[1]);
		
		String st2[] = bf.readLine().split(" ");
		int[] arr = new int[n];
		for(int i=0; i<n; ++i) {
			arr[i] = Integer.parseInt(st2[i]);
		}
		
		int Left = 0, Right = 1000000000;
		while (Left <= Right) {
			int Middle = (Left + Right) / 2;
			
			if (check(Middle, n, k, arr)) {
				Left = Middle + 1;
			} else  {
				Right = Middle - 1;
			}
		}
		
		pw.println(Left - 1);
		pw.flush();
	}
	
	private static boolean check(int Value, int n, int k, int[]ar) {
		int stall = 1, len = 0;
		for (int i = 1; i < n; ++i) {
			len += ar[i] - ar[i - 1];
			if (len >= Value) {
				len = 0;
				++stall;
			}
		}
		return stall >= k;
	}
	
}