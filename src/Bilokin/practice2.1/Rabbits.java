import java.util.Scanner;

import ua.princeton.lib.StdIn;

public class Rabbits {
	public int makeMoreRabbits(int rabbits){
		return rabbits*2;
	}
	public int eatRabbits(int rabbits,int k){
		if(rabbits>k)
			rabbits-=k;
		return rabbits;
	}
	
	
	public static void main(String[] args) {
		Rabbits rab=new Rabbits();
		Scanner sc=new Scanner(System.in);
		while(sc.hasNextInt()){
			int n=sc.nextInt();
			int k=sc.nextInt();
			int rabbits=1;
			for(int i=0;i<n;i++){
				rabbits=rab.eatRabbits(rabbits, k);
				rabbits=rab.makeMoreRabbits(rabbits);
			}
			System.out.println(rabbits);
		}
	}
}