package PW9_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class EOPhoneNumbers {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		int n1 = Integer.parseInt(bf.readLine());
		for(int i1=0; i1<n1; ++i1) {
			int n2 = Integer.parseInt(bf.readLine());
			
			String[] sts = new String[n2];
			
			for(int i=0; i<n2; ++i)
				sts[i] = bf.readLine();
			
			Arrays.sort(sts);
			
			boolean bl = false;
			for(int i=1; i<n2; ++i) {
				if(sts[i].startsWith(sts[i-1])) {
					bl = true;
					break;
				}
			}
			
			if(bl) {
				pw.println("NO");
			} else {
				pw.println("YES");
			}
		}
		
		pw.flush();
	}
}