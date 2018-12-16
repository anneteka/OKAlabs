import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Warmix {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		double points[] = new double[n];
		double time[] = new double[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			points[i] = Double.parseDouble(st.nextToken());
			time[i] = Double.parseDouble(st.nextToken());
		
		}
		double point = 0;
		double times = 100000000;
		double dynamicPoint=0;
		double dynamicTime=0;
		for(int i=0;i<n;i++){
			dynamicPoint=0;
			dynamicTime=0;
			for(int j=0;j<n;j++){
				dynamicPoint+=points[j];
				dynamicTime+=time[j];
				
				if(dynamicPoint>=k){
					if(dynamicTime<times){
						point=dynamicPoint;
						times=dynamicTime;
						break;
					}
				}

			}
		}
		
		System.out.println(times);
	}
	
}
