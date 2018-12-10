package dnaSort;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		new Main().sortDNA();
	}

	private void sortDNA() throws IOException {
		Scanner sc = new Scanner(new File("src/dnaSort/input.txt"));
		PrintWriter pw = new PrintWriter(new File("src/dnaSort/output.txt"));

		int tests = sc.nextInt();

		for (int i = 0; i < tests; i++) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			DNA[] arr = new DNA[m];
			for (int j = 0; j < m; j++) {
				arr[j] = new DNA(sc.next());
			}
			sort(arr);
			for (DNA dna : arr) {
				pw.println(dna);
			}
			pw.println();

		}
		pw.close();
	}

	private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
		for (int k = lo; k <= hi; k++)
			aux[k] = a[k];
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid)
				a[k] = aux[j++];
			else if (j > hi)
				a[k] = aux[i++];
			else if (less(aux[j], aux[i]))
				a[k] = aux[j++];
			else
				a[k] = aux[i++];
		}
	}

	private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
		if (hi <= lo)
			return;
		else {
			int mid = lo + (hi - lo) / 2;
			sort(a, aux, lo, mid);
			sort(a, aux, mid + 1, hi);
			if (!less(a[mid + 1], a[mid]))
				return;
			merge(a, aux, lo, mid, hi);
		}
	}

	public static void sort(Comparable[] a) {
		Comparable[] aux = new Comparable[a.length];
		sort(a, aux, 0, a.length - 1);
	}

	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	/**
	 * class which create DNA and contain its number i=of inversions
	 *
	 */
	private static class DNA implements Comparable<DNA> {
		public String dna;
		public int inv;

		public DNA(String str) {
			dna = str;

			// create array from characters
			char[] chars = new char[str.length()];
			str.getChars(0, str.length(), chars, 0);

			inv = getInvCount(chars);
		}

		/**
		 * count inversitions
		 */
		private int getInvCount(char[] chars) {
			int inv_count = 0;
			for (int i = 0; i < chars.length - 1; i++)
				for (int j = i + 1; j < chars.length; j++)
					if (chars[i] > (chars[j]))
						inv_count++;

			return inv_count;
		}

		public String toString() {
			return dna;
		}

		public int compareTo(DNA d) {
			if (this.inv < d.inv)
				return -1;
			if (this.inv > d.inv)
				return 1;
			return 0;
		}
	}

}
