import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Graph {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int [][] graph=new int[n][n];
		int oriented=-1;
		for(int i=0;i<n;i++){
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++){
				graph[i][j]=Integer.parseInt(st.nextToken());
				if(i==j&&graph[i][j]!=0){
					System.out.println("NO");
					oriented=0;
					break;
				}
			}
			if(oriented==0)break;
		}
		if(oriented!=0){
			for(int i=0;i<n;i++){
				for(int j=0;j<n;j++){
					if(graph[i][j]!=graph[j][i]){
						System.out.println("NO");
						oriented=0;
						break;
					}
				}
				if(oriented==0)break;
			}
			if(oriented==-1)System.out.println("YES");
		}
	
	}
}
