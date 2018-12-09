package pr7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

import javax.print.attribute.standard.NumberUpSupported;

import lib.In;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		int n=0;
		long k=0;
			String numbers[] = r.readLine().split(" ");
			n = Integer.parseInt(numbers[0]);
			k = Integer.parseInt(numbers[1]);
			String numbers1[] = r.readLine().split(" ");
			long[] second=new long[numbers1.length];
			for(int i=0; i<second.length;i++)
				second[i]=Integer.parseInt(numbers1[i]);



		if (n > k) {
			for (long i = k; i < n; i++) {

				long min = returnMin1(second, k);
				second[(int) min] += second[(int) i];

			}
		}
		System.out.println(returnMax(second));

	}

	static long returnMax(long[] arr) {
		long max = Long.MIN_VALUE;
		for (int i = 0; i < arr.length; i++)
			if (max < arr[i])
				max = arr[i];
		return max;
	}

	static long returnMin1(long[] arr, long to) {
		long min = Long.MAX_VALUE;
		long index = 0;
		for (int i = 0; i < to; i++) {
			if (min > arr[i]) {
				min = arr[i];
				index = i;
			}
		}
		return index;
	}

}
