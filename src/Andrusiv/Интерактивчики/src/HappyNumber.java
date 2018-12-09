import java.util.HashSet;
import java.util.Scanner;

public class HappyNumber {

	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		int n=in.nextInt();
		HashSet<Integer> set = new HashSet<Integer>();
		String numb=n+"";
		int sum=0;
		
		while(Integer.parseInt(numb)!=1) {
			
		for(int i=0;i<numb.length();i++) 
		sum+=Math.pow(numb.charAt(i)-48, 2);
		System.out.println(sum);
		numb=sum+"";
		sum=0;
		
		if(!set.add(Integer.parseInt(numb))) {
			System.out.println("unlucky!");
			break;
		}
		
		}
		if(Integer.parseInt(numb)==1) System.out.println("lucky!");
		
		
		
	}

	

}
