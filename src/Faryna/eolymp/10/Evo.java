package eolymp;

import java.math.BigInteger;
import java.util.Scanner;

public class Evo 
{ 
	
	
	public static void main(String args[]) 
	{ 
		System.out.println(" ");
		Scanner sc = new Scanner(System.in);
		
		BigInteger n = sc.nextBigInteger();
		
		BigInteger a = sc.nextBigInteger();
		BigInteger b = sc.nextBigInteger();
		
		
		
		
		while(a.compareTo(b) != 0)
		{
			if(a.compareTo(b) > 0)
			{
				a = a.divide(BigInteger.valueOf(2));
			}
			if(b.compareTo(a) > 0)
			{
				b = b.divide(BigInteger.valueOf(2));
			}
		
		}
		System.out.println(a);
		
	} 
} 
