package Stakhurskyi.Eolimp5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		new Main().run();
	}

	private void run() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("src\\Margesort\\input.txt"));
		PrintWriter pw = new PrintWriter(new File("src\\Margesort\\output.txt"));
		while (true) {
			String s1 = br.readLine();
			if (s1 == null)
				break;
			int n = Integer.parseInt(s1);
			if (n >= 1 && n <= 100000) {
				int[] array1 = new int[n];
				int[] array2 = new int[n];
				StringTokenizer token;
				for (int i = 0; i < n; i++) {
					s1 = br.readLine();
					token = new StringTokenizer(s1);
					array1[i] = Integer.parseInt(token.nextToken());
					array2[i] = Integer.parseInt(token.nextToken());
				}
				int[] aux1 = new int[n];
				int[] aux2 = new int[n];
				sort(array1, array2, aux1, aux2, 0, array1.length - 1);
				for (int i = 0; i < n; i++) {
					pw.println(array1[i] + " " + array2[i]);
				}
			}
		}
		pw.close();
		br.close();
	}

	private void sort(int[] a, int[] a2, int[] aux, int[] aux2, int lo, int hi) {
		if (hi <= lo)
			return;
		else {
			int mid = lo + (hi - lo) / 2;
			sort(a, a2, aux, aux2, lo, mid);
			sort(a, a2, aux, aux2, mid + 1, hi);
			if (a[mid + 1] >= a[mid])
				return;
			merge(a, a2, aux, aux2, lo, mid, hi);
		}
	}

	private void merge(int[] a, int[] a2, int[] aux, int[] aux2, int lo, int mid, int hi) {
		assert isSorted(a, lo, mid); // precondition: a[lo..mid] sorted
		assert isSorted(a, mid + 1, hi); // precondition: a[mid+1..hi] sorted
		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k];
			aux2[k] = a2[k];
		}
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid) {
				a[k] = aux[j];
				a2[k] = aux2[j];
				j++;
			} else if (j > hi) {
				a[k] = aux[i];
				a2[k] = aux2[i];
				i++;
			} else if (aux[j] < aux[i]) {
				a[k] = aux[j];
				a2[k] = aux2[j];
				j++;
			} else {
				a[k] = aux[i];
				a2[k] = aux2[i];
				i++;
			}
		}
		assert isSorted(a, lo, hi); // postcondition: a[lo..hi] sorted
	}

	private boolean isSorted(int[] a, int l, int m) {
		for (int i = l; i <= m; i++)
			if (a[i] < a[i - 1])
				return false;
		return true;
	}

}