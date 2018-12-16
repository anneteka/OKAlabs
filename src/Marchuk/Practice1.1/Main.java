package Practice1;

import java.io.IOException;
import java.util.*;

public class Main {
	private final int maxval = 1000000;

	public static void main(String[] args) {
		Main function = new Main();
		function.doTask();

	}

	private void doTask() {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextInt()) {
		int i = sc.nextInt();
		int j = sc.nextInt();
		
		int ijmin = Math.min(i, j);
		int ijmax = Math.max(i, j);
		
		if(ijmin>0 && ijmin<maxval && ijmax>0 && ijmax<maxval) {
			int res = 0;
			for(int k = ijmin; k<=ijmax; k++) {
				res = Math.max(res, taskFunction(k, 1));
			}
			System.out.println(i + " " + j + " " + res);
		}
		}
	}

	private int taskFunction(int k , int count) {
		if(k==1) {
		return count;
		} else {
			if(k%2==0) {
				k=k/2;
			} else {
				k = (3*k) + 1;
			}
			count++;
			return taskFunction(k, count);
		}
	}

}
