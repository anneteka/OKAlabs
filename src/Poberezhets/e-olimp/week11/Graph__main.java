package pr11;

import java.util.Scanner;


public class Graph__main {
	public static void main(String args[]) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		Integer[][] arr=new Integer[n][n];
		
		for(int i=0; i<n;i++) {
			for(int j=0; j<n;j++) {
				arr[i][j]=sc.nextInt();
			}
		}
		
		for(int i=0; i<arr.length;i++) {
			for(int j=0; j<arr.length;j++) {
				if(arr[i][j]!=arr[j][i]||(i==j&&arr[i][j]==1)) {
					System.out.println("NO");
					return ;
				}
			}
		}
		System.out.println("YES");
	}
}
