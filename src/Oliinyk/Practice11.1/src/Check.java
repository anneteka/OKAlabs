import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Check {
	public static void main(String[] args) throws IOException {
		Check s= new Check();
		s.inputNumber();

	}

	private void inputNumber() throws IOException {
		//Scanner scann=new Scanner(System.in);
		//BufferedReader br= new BufferedReader(new FileReader("input.txt"));
		//BufferedReader br= new BufferedReader(new FileReader(new File(testFile)));
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		 //PrintWriter out = new PrintWriter("output.txt");
		 PrintWriter out = new PrintWriter(System.out, true);
	        int n=0;
	        String [] a;
	        int [] [] first;
	        boolean right=true;
		//	while(br.ready()) {
				 n=Integer.valueOf(br.readLine());
				first=new int[n] [n];
				int countZero=0;
				int curN=0;
				for(int i=0; i<n; i++) {
					 a = br.readLine().split(" ");
					 for(int j=0; j<n; j++) {
						 if(!right) break;
						 curN=Integer.valueOf(a[j]);
						 if(curN!=0 && curN!=1) {
							 right=false;
							  break;
						 }
						 if(countZero==(n+1)) {
							 countZero=0;
							 if(curN!=0) {
							  right=false;
							  break;
							 }
						 }
						 first[i][j]=curN;
						 countZero++;
					 }
					 
				}
				if(right) {
					for(int i=0; i<n; i++) {
						for(int j=0; j<n; j++) {
							if(first[i][j] !=first[j][i]) {
								right=false;
								break;
							}
						}
						if(!right) break;
					}
				}
				if(right) out.println("YES");
				else out.println("NO");
				
			// }
			out.close();
	}
} 
