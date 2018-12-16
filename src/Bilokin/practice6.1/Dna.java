import java.util.Scanner;

public class Dna {

	static int getiC(String s, int n) {
		int inv_count = 0;
		for (int i = 0; i < n - 1; i++)
			for (int j = i + 1; j < n; j++)
				if (s.charAt(i) > s.charAt(j))
					inv_count++;

		return inv_count;
	}

	static String[][] sortix(String[][] values) {
		int number = values.length;
		String[][] helper = new String[number][2];
		return msortix(values, helper, 0, number - 1);
	}

	private static String[][] msortix(String[][] num, String[][] help, int low, int high) {
		if (low < high) {
			int mid = low + (high - low) / 2;
			msortix(num, help, low, mid);
			msortix(num, help, mid + 1, high);
			return mm(num, help, low, mid, high);
		}
		return null;
	}

	private static String[][] mm(String[][] numbers, String[][] helper, int low, int middle, int high) {
		for (int i = low; i <= high; i++) {
			helper[i][0] = numbers[i][0];
			helper[i][1] = numbers[i][1];
		}

		int i = low;
		int j = middle + 1;
		int k = low;
		while (i <= middle && j <= high) {
			if (Integer.parseInt(helper[i][0]) <= Integer.parseInt(helper[j][0])) {
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
		return numbers;
	}

	// Driver method to test the above function
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Scanner ss = new Scanner(System.in);
		int m = sc.nextInt();
		for (int j = 0; j < m; j++) {
			int size = sc.nextInt();
			int n = sc.nextInt();
			String[][] str = new String[n][2];
			for (int i = 0; i < n; i++) {
				String s = ss.nextLine();
				str[i][0] = String.valueOf(getiC(s, size));
				str[i][1] = s;
			}
			str = sortix(str);
			for (int i = 0; i < n; i++) {
				System.out.println(str[i][1]+"  "+str[i][0]);

			}
		}

	}
}
