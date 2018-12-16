package Practice2;

import java.util.Scanner;

public class Gardener {
	
	public static void main(String[] args) {
		Gardener temp = new Gardener();
		temp.computeLeaves();

	}

	private void computeLeaves() {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextInt()) {
			int n = sc.nextInt();
			if(n>=0 && n<=1000) {
			int res = 1;
			for(int i = 1; i<=n; i++) {
				res += i*2;
			}
			System.out.println(res);
			}
		}
	}
	
}
