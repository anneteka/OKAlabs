package PW10_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;

public class EOEvolution {

	public static void main(String[] args) throws IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		bf.readLine();
		BigInteger first = new BigInteger(bf.readLine());
		BigInteger second = new BigInteger(bf.readLine());
		BigInteger TWO = new BigInteger("2");
		BigInteger max;

		while(!first.equals(second)) {
			max = first.max(second);
			if(max.equals(first))
				first = first.divide(TWO);
			else
				second = second.divide(TWO);
		}
		
		pw.println(first);
		pw.flush();
		
	}
}