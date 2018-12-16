
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.ReadOnlyFileSystemException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class DNA {
	
	private class Comp implements Comparator<String> {
		
		@Override
		public int compare(String arg0, String arg1) {
			if(quantity(arg0) > quantity(arg1)) return 1;
			if(quantity(arg0) < quantity(arg1)) return -1;
			return 0;
		}
	}

	
	public final Comparator<String> t = new Comp();
	
	
	
	
	public static void main(String[] args)  throws IOException {
		
		DNA d = new DNA();
		
		System.out.println(" ");
		Scanner scanner = new Scanner(System.in);
		
		
		//OutputStream out = new BufferedOutputStream(System.out);
		
		//BufferedWriter log = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] dnas;
		
		
		int tests = scanner.nextInt();
		for(int i = 0;i < tests;i++)
		{
			int n = scanner.nextInt();
			int m = scanner.nextInt();
			
			dnas = new String[m];
			
			
			
			for(int j = 0;j < m;j++)
			{
				dnas[j] = scanner.next();
				
			}
			
			Arrays.sort(dnas, d.t);
			
			//PrintWriter out = new PrintWriter(System.out);
			
			//out.print(Arrays.toString(dnas));
			
			StringBuilder sb = new StringBuilder();
			for(String k : dnas)
			{
				sb.append(k + "\n");
			}
		
			System.out.println(sb.toString());
			
			//BufferedWriter log = new BufferedWriter(new OutputStreamWriter(System.out));
	        
			//log.write(Arrays.toString(dnas));
			
			
		}
	}
	public static int quantity(String str)
	{
		int count = 0;
		for(int i = 0;i < str.length() - 1;i++)
		{
			for(int j = i + 1;j < str.length();j++)
			{
				if(str.charAt(i) > str.charAt(j))
				{
					count++;
				}
			}
		}
		return count;
	}

	
}
