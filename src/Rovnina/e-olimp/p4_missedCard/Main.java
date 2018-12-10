package missedCard;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * �������� ������. ��� ��������� ��� ���������������� ������ � �������� �� 1
 * �� n (���������� ����� n �� �������� 106). ���� ������ ����������. �������
 * ��
 * 
 * @author Rovnina Tetiana
 *
 */
public class Main {
	public static void main(String[] args) {
		new Main().findCard();
	}

	private void findCard() {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out, true);

		int n = sc.nextInt();
		if (n > 1 && n <= 1000000) {
			int[] array = new int[n - 1];
			for (int i = 0; i < n - 1; i++)// ���������� �����
				array[i] = sc.nextInt();

			array = sort(array); // ������� �����

			out.printf("%d\n", binarySearch(array));
		} else if (n == 1)
			out.printf("%d\n", 1);
	}

	/**
	 * ���������� ������� ����
	 */
	private int[] sort(int[] array) {
		int n = array.length;
		int swap = 0;

		int h = 1;
		while (h < n / 3)
			h = 3 * h + 1;

		while (h >= 1) {
			for (int i = h; i < n; i++) {
				for (int j = i; j >= h; j -= h)
					if (array[j] < array[j - h]) {
						swap = array[j];
						array[j] = array[j - h];
						array[j - h] = swap;
					} else
						break;
			}
			h = h / 3;
		}
		return array;
	}

	/**
	 * ������� �����. ��������� �������� ������, ��� �� �������
	 */
	private int binarySearch(int[] array) {
		int lo = 0, hi = array.length - 1, mid = 0;
		while (lo <= hi) {
			mid = (hi + lo) / 2;
			if (array[mid] == mid + 1)
				lo = mid + 1;
			else
				hi = mid - 1;
		}
		if (array[mid] == mid + 1)
			return mid + 2;
		else
			return mid + 1;
	}
}
