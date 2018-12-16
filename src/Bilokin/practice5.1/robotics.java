import java.util.Scanner;

public class robotics {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int[][] arr = new int[n][2];
		for (int i = 0; i < n; i++) {
			arr[i][0] = scan.nextInt();
			arr[i][1] = scan.nextInt();
		}
		robotics r = new robotics();
		r.sortix(arr);

		for (int i = 0; i < n; i++) {
			System.out.println(arr[i][0] + "  " + arr[i][1]);
		}
	}

	public void sortix(int[][] values) {
		int number = values.length;
		int[][] helper = new int[number][2];
		msortix(values, helper, 0, number - 1);
	}

	private void msortix(int[][] numbers, int[][] helper, int low, int high) {
		if (low < high) {
			int middle = low + (high - low) / 2;
			msortix(numbers, helper, low, middle);
			msortix(numbers, helper, middle + 1, high);
			mm(numbers, helper, low, middle, high);
		}
	}

	private void mm(int[][] numbers, int[][] helper, int low, int middle, int high) {
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
