import java.util.Scanner;

public class Main {
	public static int getAnswer(int n) {
		int currentSum = 1;
		int currentMember = 2;
		for(int i=0;i<n;i++) {
			currentSum += currentMember;
			currentMember += 2;
		}
		return currentSum;
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNextInt()) {
			int n = scanner.nextInt();
			System.out.println(getAnswer(n));
		}
	}
}
