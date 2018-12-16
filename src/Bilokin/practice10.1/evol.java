import java.math.BigInteger;
import java.util.Scanner;

public class evol {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		
		
		BigInteger par1=sc.nextBigInteger();
		
		BigInteger par2=sc.nextBigInteger();
		BigInteger div=BigInteger.valueOf(2);
		
		int k=0;

		
		while(!par1.equals(par2)){
			par1=par1.divide(div);
			par2=par2.divide(div);
			if(par1.compareTo(par2)==1&&k==0){
				k++;
				if(check(par1, par2,div).compareTo(par2)==0){
					par1=par2;
					break;
				}
			}
			else if(par1.compareTo(par2)==-1&&k==0) {
				k++;
				if(check(par2, par1,div).compareTo(par1)==0){
					par1=par2;
					break;
				}
			}
		}
		
		System.out.println(par1);
	}
	public static BigInteger check(BigInteger value,BigInteger dontTouch,BigInteger div){
		while(value.compareTo(dontTouch)==1){
			value=value.divide(div);
		}
		if(value.compareTo(dontTouch)==0)return value;
		else return BigInteger.valueOf(-1);
	}
}
