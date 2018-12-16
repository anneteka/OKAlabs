package PW7_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;

public class EOQueue {

	public static void main(String[] args) throws IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		String[] st1 = bf.readLine().split(" ");
		int n = Integer.parseInt(st1[0]);
		int k = Integer.parseInt(st1[1]);
		String[] st2 = bf.readLine().split(" ");

		long[] times = new long[n];
		for (int i = 0; i < n; ++i)
			times[i] = Integer.parseInt(st2[i]);
		
		PriorityQueue<Long> pi = new PriorityQueue<Long>();
		
		for (int i = 0; i < k; ++i)
			pi.add((long) 0);
		
		long res = 0;
		for (int i = 0; i < n; ++i){
			long j = pi.poll() + times[i];
			res = j>res?j:res;
			pi.add(j);
		}
		
		pw.println(res);
		pw.flush();
		
	}

}