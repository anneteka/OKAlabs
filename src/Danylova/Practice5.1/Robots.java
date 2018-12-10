import java.util.Scanner;

public class Robots {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] array = new int[n][2];
		for (int i = 0; i < n; i++) {
			array[i][0] = sc.nextInt();
			array[i][1] = sc.nextInt();
		}
		Robots r = new Robots();
		r.sort(array);

		for (int i = 0; i < n; i++) {
			System.out.println(array[i][0] + "  " + array[i][1]);
		}
	}

	public void sort(int[][] values) {
		int number = values.length;
		int[][] helper = new int[number][2];
		mergesort(values, helper, 0, number - 1);
	}

	private void mergesort(int[][] numbers, int[][] helper, int low, int high) {
		if (low < high) {
			int middle = low + (high - low) / 2;
			mergesort(numbers, helper, low, middle);
			mergesort(numbers, helper, middle + 1, high);
			merge(numbers, helper, low, middle, high);
		}
	}

	private void merge(int[][] numbers, int[][] helper, int low, int middle, int high) {
		for (int i = low; i <= high; i++) {
			helper[i][0] = numbers[i][0];
			helper[i][1] = numbers[i][1];
		}

		int i = low;
		int j = middle + 1;
		int k = low;
		while (i <= middle && j <= high) {
			if (helper[i][0] <= helper[j][0]) {
				numbers[k][0] = helper[i][0];
				numbers[k][1] = helper[i][1];
				i++;
			} else {
				numbers[k][0] = helper[j][0];
				numbers[k][1] = helper[j][1];
				j++;
			}
			k++;
		}
		while (i <= middle) {
			numbers[k][0] = helper[i][0];
			numbers[k][1] = helper[i][1];
			k++;
			i++;
		}

	}
}
