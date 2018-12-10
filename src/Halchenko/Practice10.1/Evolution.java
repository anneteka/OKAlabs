import java.math.BigInteger;
import java.util.Scanner;

public class Evolution {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		
		
		BigInteger parent1=sc.nextBigInteger();
		
		BigInteger parent2=sc.nextBigInteger();
		BigInteger div=BigInteger.valueOf(2);
		
		int k=0;

		
		while(!parent1.equals(parent2)){
			parent1=parent1.divide(div);
			parent2=parent2.divide(div);
			if(parent1.compareTo(parent2)==1&&k==0){
				k++;
				if(check(parent1, parent2,div).compareTo(parent2)==0){
					parent1=parent2;
					break;
				}
			}
			else if(parent1.compareTo(parent2)==-1&&k==0) {
				k++;
				if(check(parent2, parent1,div).compareTo(parent1)==0){
					parent1=parent2;
					break;
				}
			}
		}
		
		System.out.println(parent1);
	}
	public static BigInteger check(BigInteger value,BigInteger dontTouch,BigInteger div){
		while(value.compareTo(dontTouch)==1){
			value=value.divide(div);
		}
		if(value.compareTo(dontTouch)==0)return value;
		else return BigInteger.valueOf(-1);
	}
}
