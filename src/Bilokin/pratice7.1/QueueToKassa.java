import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class QueueToKassa {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int queueSize=Integer.parseInt(st.nextToken());
		int howManyKass=Integer.parseInt(st.nextToken());
		st=new StringTokenizer(br.readLine()); 
		int[] kassa=new int[howManyKass];
		for(int i=0;i<howManyKass;i++){
			if(st.hasMoreTokens())kassa[i]+=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(kassa);
		while(st.hasMoreTokens()){
			kassa[0]+=Integer.parseInt(st.nextToken());
			Arrays.sort(kassa);
		}
		System.out.println(kassa[howManyKass-1]);
	}
}
