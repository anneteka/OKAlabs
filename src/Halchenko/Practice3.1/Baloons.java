import java.util.Scanner;

public class Baloons {
	int[] array;
	static Scanner sc=new Scanner(System.in);
	
	
	public void enterColors(int n){
		array=new int[n];
		for(int i=0;i<n;i++){
			array[i]=sc.nextInt();
		}
	}
	
	
	public int countingBaloons(){
		int max=0;
		int premax=0;
		for(int i=1;i<=9;i++){
			for(int j=0;j<array.length;j++){
				if(array[j]==i){
					premax++;
				}
			}
			if(premax>max){
				max=premax;
				
			}
			premax=0;
		}
		return array.length-max;
	}
	
	
	public static void main(String[] args) {
		Baloons bl=new Baloons();
		while(sc.hasNextInt()){
			int n=sc.nextInt();
			bl.enterColors(n);
			System.out.println(bl.countingBaloons());
		}
	}
}
