package queue;

import ua.princeton.lib.In;
import ua.princeton.lib.StdIn;

public class Tester {

	public static void main(String[] args) {

		int n = 0;
		int k = 0;
		do {
			n = StdIn.readInt();
			k = StdIn.readInt();
		} while (k < 1 || k > 10000 || n < 1 || n > 10000);
		int[] people = new int[n];
		for (int i = 0; i < n; i++)
			do {
				people[i] = StdIn.readInt();
			} while (people[i] < 1 || people[i] > 100000);
		Queue r = new Queue(n, k, people);
		System.out.println(r.Station());

	}
}
