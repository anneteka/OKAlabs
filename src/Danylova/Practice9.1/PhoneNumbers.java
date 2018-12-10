import java.util.Arrays;
import java.util.Scanner;

public class PhoneNumbers {
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		int numberOfTests=sc.nextInt();
		for(int i=0;i<numberOfTests;i++){
			int breakFlag=0;
			int numberOfPhoneNumbers=sc.nextInt();
			int[] phones=new int[numberOfPhoneNumbers];
			for(int j=0;j<numberOfPhoneNumbers;j++){
				phones[j]=sc.nextInt();
			}
			//Arrays.sort(phones);
			for(int j=0;j<numberOfPhoneNumbers;j++){
				String firstVal=String.valueOf(phones[j]);
				for(int k=j+1;k<numberOfPhoneNumbers-1;k++){
					String secondVal=String.valueOf(phones[k]);
					if(secondVal.substring(0, firstVal.length()).matches(firstVal)){
						breakFlag=1;
						break;
					}
				}
				if(breakFlag==1)break;
			}
			if(breakFlag==1){
				System.out.println("No");
			}
			else System.out.println("Yes");
		}
	}
}
