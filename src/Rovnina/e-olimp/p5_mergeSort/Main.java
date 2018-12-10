package mergeSort;

import java.io.*;
import java.util.StringTokenizer;

/**
 * ���������� �������, ���������� ������ ������
 * 
 * @author Rovnina Tetiana
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		new Main().sortRobots();

	}

	/**
	 * �����, ���� ����� ���� � ����� �� ����� ������
	 */
	private void sortRobots() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("src/mergeSort/input.txt"));
		PrintWriter out = new PrintWriter(new File("src/mergeSort/output.txt"));

		int n = Integer.parseInt(br.readLine());// ������� ������

		if (n >= 1 && n <= 100000) {
			int[][] robots = new int[n][2];
			StringTokenizer st = null;

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());// ������� ������ � ������� ������
				robots[i][0] = Integer.parseInt(st.nextToken());
				robots[i][1] = Integer.parseInt(st.nextToken());
			}

			sort(robots); // �������

			for (int[] r : robots) {// �������� ���������
				out.println(r[0] + " " + r[1]);
			}
		}
		br.close();
		out.close();
	}

	/**
	 * ������
	 */
	private void merge(int[][] a, int[][] aux, int lo, int mid, int hi) {
		for (int k = lo; k <= hi; k++) {
			aux[k][0] = a[k][0];
			aux[k][1] = a[k][1];
		}
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid) {
				a[k][0] = aux[j][0];
				a[k][1] = aux[j][1];

				j++;
			} else if (j > hi) {
				a[k][0] = aux[i][0];
				a[k][1] = aux[i][1];

				i++;
			} else if (aux[j][0] < aux[i][0]) {
				a[k][0] = aux[j][0];
				a[k][1] = aux[j][1];

				j++;
			} else {
				a[k][0] = aux[i][0];
				a[k][1] = aux[i][1];

				i++;
			}
		}
	}

	/**
	 * ����������
	 */
	private void sort(int[][] a, int[][] aux, int lo, int hi) {
		if (hi <= lo)
			return;
		int mid = lo + (hi - lo) / 2;
		sort(a, aux, lo, mid);
		sort(a, aux, mid + 1, hi);
		if (a[mid + 1][0] >= a[mid][0])
			return;
		merge(a, aux, lo, mid, hi);
	}

	/**
	 * ����� ����������, ���� ������� ���������� �����
	 */
	private void sort(int[][] a) {
		int[][] aux = new int[a.length][2];
		sort(a, aux, 0, a.length - 1);
	}

}
