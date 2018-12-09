import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class RealQueue {
	int  n=0, m=0;
	int[] time;
	public static void main(String[] args) throws IOException {
		RealQueue s= new RealQueue();
		s.inputNumber();

	}

	private void inputNumber() throws IOException {
		BufferedReader br= new BufferedReader(new FileReader("input.txt"));
		
		 PrintWriter out = new PrintWriter("output.txt");
	
			
			String[]a;
			while(br.ready()) {
				 a = br.readLine().split(" ");
				 n=Integer.valueOf(a[0]);
				 m=Integer.valueOf(a[1]);
				 time= new int[n];
				 a = br.readLine().split(" ");
				 for(int i=0; i<n; i++) {
					 time[i]=Integer.valueOf(a[i]);
				 }
				 out.write(count()+"");
			 }
			out.close();
	}
	
	
	private long count() {
		int min=0;
		long counter=0;
		int indexOfArr=m-1;
		int maxV=0;
		boolean toAdd=true;
		if(time.length<=m) {
			Arrays.sort(time);
			counter=time[time.length-1];
		} else {
			Arrays.sort(time, 0, m);
		while(indexOfArr<time.length) {
				if(toAdd) {
					maxV=time[m-1];
					counter+=maxV;
					toAdd=false;
				}
				min=time[0];
				if(min==0) {
					counter+=time[m-1]-maxV;
					break;
				}
				for(int i=0; i<m; i++) 	time[i]-=min;
			    maxV-=min;

			    	while(time[0]==0 && indexOfArr<time.length-1) {
			    			indexOfArr++;
			    			int in=time[indexOfArr];
			    			time[0]=in;
			    			moveToRightPos( in);
			    	}
			    if(maxV==0) toAdd=true;
			    
		}
		}
		return counter;
	}

	private void moveToRightPos( int in) {
		int curPos=0;
		for(int i=1; i<m; i++) {
			if(in>time[i]) {
				int z=time[i];
				time[i]=in;
				time[curPos]=z;
				curPos=i;
			} else break;
		}
		
	}
			
		
}
