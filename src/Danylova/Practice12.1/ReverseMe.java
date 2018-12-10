import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ReverseMe {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String[] arr= new String[Integer.parseInt(st.nextToken())+1];
		for(int i=1;i<arr.length;i++){
			st=new StringTokenizer(br.readLine());
			//if(!st.hasMoreTokens())i--;
			while(st.hasMoreTokens()){
				int n=Integer.parseInt(st.nextToken());
				if(arr[n]==null){
					arr[n]=String.valueOf(i)+' ';
				}
				else arr[n]+=String.valueOf(i)+' ';
			}
		}
		System.out.println(arr.length-1);
		for(int i=1;i<arr.length;i++){
			if(arr[i]!=null)System.out.println(arr[i]);
			else System.out.println();
		}
		
	}
}
