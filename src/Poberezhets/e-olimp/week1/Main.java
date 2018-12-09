import java.util.Scanner;

import lib.In;
import lib.StdIn;

/**
 * Вхідні дані
Кожний тест задається в окремому та містить пару цілых чисел i та j. 
Вхідні числа меньші 1000000та більші 0. Вважайте, що для обчислень достатньо використовувати 32 бітний 
цілочисельний тип.

Вихідні дані
Для кожної пари чисел i та j виведіть числа i та j у тому ж порядку, в якому вони надійшли на вхід. Після чого виведіть максимальну довжину циклу серед усіх цілих чисел між i та j включно.
 Для кожного тесту три числа слід виводити в окремому рядку, розділяючи одним пропуском.
 * 
 * @author Богдана
 */
public class Main {
	
	
	public static void main(String[] args) {
		
		while(true) {
			System.out.println("Input i: ");
			String string1=StdIn.readLine();
			System.out.println("Input j: ");
			String string2=StdIn.readLine();
		try{
			int i=Integer.parseInt(string1);
			int j=Integer.parseInt(string2);
			System.out.println(i+" "+j+" = "+countIJ(i, j));
			
		}catch (Exception e) {
			System.out.println("Bad input");
		}
		}
		
		

		 

	}
	private static int parseInteger(String string) {
		while(true) {
			string=StdIn.readLine();
		try{
			int q=Integer.parseInt(string);
			return q;
		}catch (Exception e) {
			System.out.println("Bad input");
		}
		}
		
	}
	
	private static int program1(int n)
	{
		int counter=1;
		while(n!=1) 
		{
			if(n%2!=0)
			{
				n=3*n+1;
				counter++;
			}
			else
			{
				n=n/2;
				counter++;
			}	
		}
		return counter;
		
	}
	
	private static int countIJ(int min , int max) 
	{
		int maxDigit=0;
		for(int i=min; i<=max;i++) 
		{
			int temp=program1(i);
			maxDigit=Math.max(temp, maxDigit);
			
		}
		return maxDigit;
		
	}

}
