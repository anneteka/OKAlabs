package Practice10;

import java.math.BigInteger;
import java.util.Scanner;

public class Evolution {

	public static void main(String[] args) {
		Evolution obj = new Evolution();
		obj.doTask();

	}

	private void doTask() {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()) {
		int stage = Integer.parseInt(sc.nextLine());
		BigInteger first = new BigInteger(sc.nextLine());
		BigInteger firstcopy = first;
		BigInteger second = new BigInteger(sc.nextLine());
		BigInteger secondcopy = second;
		int firstgen = 0, secondgen = 0;
		boolean loop = true;
		while(loop) {
			if(firstcopy.compareTo(BigInteger.ONE) != 0) {
			firstcopy = firstcopy.divide(new BigInteger("2"));
				firstgen = firstgen + 1;
			}
			if(secondcopy.compareTo(BigInteger.ONE) != 0) {
				secondcopy = secondcopy.divide(new BigInteger("2"));
				secondgen = secondgen + 1;
			}
			if(secondcopy.compareTo(BigInteger.ONE) == 0 && firstcopy.compareTo(BigInteger.ONE) == 0) loop = false;
		}
		String temp = "", temp1 = "";
		if(firstgen>secondgen) {
			firstgen = firstgen - secondgen;
			temp = temp + (int)Math.pow(2, firstgen);
			first = first.divide(new BigInteger(temp));
		}else if(secondgen>firstgen) {
			secondgen = secondgen - firstgen;
			temp1 = temp1 + (int)Math.pow(2, secondgen);
			second = second.divide(new BigInteger(temp1));
		}
		while(first.compareTo(second)!=0) {
			first = first.divide(new BigInteger("2"));
			second = second.divide(new BigInteger("2"));
		}
		System.out.println(first.toString());
		}
	}

}
