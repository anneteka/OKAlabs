import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Icecream {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] mass = new int[n];
		int[] totalmas=new int[k];
		int i = 0;
		double totalDist=0;
		while (st.hasMoreTokens()) {
			mass[i] = Integer.parseInt(st.nextToken());
			if(i>0)totalDist+=mass[i]-mass[i-1];
			i++;
		}
		totalDist/=k-1;
		int totalIndex=0;
		for(i=0;i<k;i++){
			
		}
		System.out.println(totalDist);
	}
}
