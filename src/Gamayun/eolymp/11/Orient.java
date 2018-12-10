package eolymp;

import java.io.IOException;
import java.util.Scanner;

public class Orient {

	
	static int i, j, n;
	static int[][] m = new int[101][101];
	
	public static void main(String[] args) throws IOException {  	
	    	
		System.out.println(" ");
	    Scanner scanner = new Scanner(System.in);
	    
	    n = scanner.nextInt();
	    
	    for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++)
			{
				m[i][j] = scanner.nextInt();
				if (i == j && m[i][j] > 0)
				{
					System.out.println("NO\n");
					return;
				}
			}
		}
	    
	    for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++)
				if (m[i][j] != m[j][i])
				{
					System.out.println("NO\n");
					return;
				}
		}
	    System.out.println("YES\n");
	    
	    
	}
		
}
