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
//				if(j==i){
//					continue;
//				}
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
		
//		while (point < k) {
//			double max = 0;
//			double equalT=0;
//			int index = 0;
//			for (int i = 0; i < n; i++) {
//				if (time[i] != -10) {
//					
//					//System.out.println(points[i] / time[i]);
//					//System.out.println(equalT+"   "+time[i]);
//					
//					if (max <= points[i] / time[i]) {
//						if(points[i] / time[i]==max&&equalT>time[i]&&equalT!=0){
//							index = i;
//							max = points[i] / time[i];
//							equalT=time[i];
//						}
//						else if(points[i] / time[i]>max){
//							index = i;
//						max = points[i] / time[i];
//						equalT=time[i];
//						}
//						
//					}
//				}
//			}
//			point += points[index];
//			times += time[index];
//			time[index] = -10;
//			points[index] = -10;
//		}
		System.out.println(times);
	}
	
}
