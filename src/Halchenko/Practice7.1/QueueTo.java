import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class QueueTo {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int queueSize=Integer.parseInt(st.nextToken());
		int howManyKass=Integer.parseInt(st.nextToken());
		st=new StringTokenizer(br.readLine());
		PriorityQueue<Integer> pq=new PriorityQueue<>();
		for(int i=0;i<howManyKass;i++){
			if(st.hasMoreTokens()){
				pq.add(Integer.parseInt(st.nextToken()));
			}
		}
		
		while(st.hasMoreTokens()){
			int k=pq.poll();
			k+=Integer.parseInt(st.nextToken());
			pq.add(k);
		}
		int max=-1;
		while(!pq.isEmpty()){
			if(max<pq.peek())max=pq.poll();
			else pq.poll();
		}
		System.out.println(max);
	}
	
}
