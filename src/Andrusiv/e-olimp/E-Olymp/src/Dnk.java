import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Dnk {
	
	
	
	static class Key implements Comparable<Key> {
		int j;
		char[] s;

		Key(char[] line, int length) {
			this.s = line;
			this.j = getInvCount(length,line);
		}

		  static int getInvCount(int n,char[] arr) 
		    { 
		        int inv_count = 0; 
		        for (int i = 0; i < n - 1; i++) 
		            for (int j = i + 1; j < n; j++) 
		                if (arr[i] > arr[j]) 
		                    inv_count++; 
		  
		        return inv_count; 
		    } 
		  public int getInv() {
			  return j;
		  }
		  
		  @Override
			public int compareTo(Key o) {
				return Integer.compare(this.j, o.j);
			}
		  public String toString() {
			  return String.copyValueOf(s);
		  }
	}

	public static void main(String[] args) {
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File("input.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		PrintWriter bw = null;
		try {
			bw = new PrintWriter(new File("output.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			String s = br.readLine();
			int tests = Integer.parseInt(s);
			
			
			for(int k=0;k<tests;k++) {
				
			s=br.readLine();
			s=br.readLine();
			String[] x = s.split(" ");
			int length=Integer.parseInt(x[0]);
			int  n=Integer.parseInt(x[1]);
				Key[] ccg=new Key[n];
				for(int i=0;i<n;i++) {
					s=br.readLine();
					char[] ch=new char[length];
					for(int l=0;l<length;l++) 
						ch[l]=s.charAt(l);
					
					ccg[i]=new Key(ch,length);
			}
			Arrays.sort(ccg);
			for(int t=0;t<n;t++)
				bw.write(ccg[t].toString()+"\n");
			
			
			bw.write("\n");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		bw.close();
		

	}

	

}