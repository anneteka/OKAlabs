import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;

public class Evolution {
	public static void main(String[] args) throws IOException {
		 Evolution s= new Evolution();
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
				 n=Integer.valueOf(br.readLine());
				 BigInteger n1=new BigInteger(br.readLine());
				 BigInteger n2=new BigInteger(br.readLine());
				BigInteger two=new BigInteger("2");
				 if(n1.compareTo(n2)==-1) {
					 while(n1.compareTo(n2)==-1) n2=n2.divide(two);
				 } else if(n1.compareTo(n2)==1){
					 while(n1.compareTo(n2)==1) n1=n1.divide(two);
					 BigInteger z=n1;
					 n1=n2;
					 n2=z;
				 }
				 while(n1.compareTo(n2)!=0) {
					 n1=n1.divide(two);
					 if(n1.compareTo(n2)==0) break;
					 n2=n2.divide(two);
				 }
				 out.println(n1);
			
			out.close();
	}
	
}
