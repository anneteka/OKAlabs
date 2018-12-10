package Vormix;

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
		BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
		PrintWriter pw = new PrintWriter(new File("output.txt"));
		while (true) {
			String s = br.readLine();
			if(s == null)
				break;
			StringTokenizer token = new StringTokenizer(s);
			int n = Integer.parseInt(token.nextToken());
			int k = Integer.parseInt(token.nextToken());
			int[] arrayk = new int[n];
			int[] arrayt = new int[n];
			for (int i = 0; i < n; i++) {
				s = br.readLine();
				token = new StringTokenizer(s);
				arrayk[i] = Integer.parseInt(token.nextToken());
				arrayt[i] = Integer.parseInt(token.nextToken());
			}
			sort(arrayt, arrayk, new int[n], new int[n], 0, n-1);
			int res1 = minTime(k, arrayk, arrayt);
			sortob(arrayt, arrayk, new int[n], new int[n], 0, n-1);
			int res2 = minTime(k, arrayk, arrayt);
			int res = 0;
			res = Math.min(res1, res2);
			if(res1==-1)
				res = res2;
			if(res2==-1)
				res = res1;
			pw.println(res1);
		}
		br.close();
		pw.close();
	}

	private int minTime(int k, int[] arrayk, int[] arrayt) {
		int rest = Integer.MAX_VALUE;
		int t = 0;
		int s = 0;
		int counter;
		for (int i = 0; i < arrayk.length; i++) {
			if(correctS(arrayk[i], k) && arrayt[i]<rest)
				rest = arrayt[i];
		}
		for (int i = 0; i < arrayk.length-1; i++) {
			for (int j = i+1; j < arrayk.length; j++) {
				counter = 0;
				s = arrayk[i] + arrayk[j];
				t = arrayt[i] + arrayt[j];
				if(correctS(s, k)) {
					if(t < rest) rest = t;
					continue;
				}
				while(j+counter < arrayk.length) {
					do {
						counter++;
						if(j+counter == arrayk.length)
							break;
						s += arrayk[j+counter];
						t += arrayt[j+counter];
					} while (!correctS(s, k));
					if(j+counter == arrayk.length)
						break;
					if(t < rest) rest = t;
					s -= arrayk[j+counter];
					t -= arrayt[j+counter];
				}
			}
		}
		if(rest == Integer.MAX_VALUE)
			return -1;
		return rest;
	}

	private boolean correctS(int s, int k) {
		return s>=k;
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
	}
	
	private void sortob(int[] a, int[] a2, int[] aux, int[] aux2, int lo, int hi) {
		if (hi <= lo)
			return;
		else {
			int mid = lo + (hi - lo) / 2;
			sortob(a, a2, aux, aux2, lo, mid);
			sortob(a, a2, aux, aux2, mid + 1, hi);
			if (a[mid + 1] <= a[mid])
				return;
			mergeob(a, a2, aux, aux2, lo, mid, hi);
		}
	}

	private void mergeob(int[] a, int[] a2, int[] aux, int[] aux2, int lo, int mid, int hi) {
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
			} else if (aux[j] > aux[i]) {
				a[k] = aux[j];
				a2[k] = aux2[j];
				j++;
			} else {
				a[k] = aux[i];
				a2[k] = aux2[i];
				i++;
			}
		}
	}

	

}