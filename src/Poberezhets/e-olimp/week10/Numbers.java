package pr10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Numbers {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader r=new BufferedReader(new FileReader("src/pr10/input.txt"));
		PrintWriter wr=new PrintWriter(new File("src/pr10/output.txt"));
		int t1=Integer.parseInt(r.readLine());
		while(t1>0) {
			int n1=Integer.parseInt(r.readLine());
			String[] s=new String[n1];
			for(int i=0; i<n1;i++) 
				s[i]=r.readLine();
			Arrays.sort(s);
			if(isAccumulable(s))
				wr.println("YES");
			else 
				wr.println("NO");
			t1--;
			}
		r.close();
		wr.close();
				
	}
	
	private static boolean isAccumulable(String[] a) {
		for(int i=0; i<a.length-1;i++) {
			int l1=a[i].length();
			int l2=a[i+1].length();
			if(l1 <= l2)
				if(a[i].equals(a[i+1].substring(0, l1))) 
					return false;
			}		
		return true;
		
	}

}
