import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
	OrderComparator comparator = new OrderComparator();

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int ammount = Integer.parseInt(in.readLine());
		Main main = new Main();
		in.readLine();
		while (ammount != 0) {
			String b = in.readLine();
			int K = Integer.parseInt(b.split(" ")[1]);
			int N = Integer.parseInt(b.split(" ")[0]);
			char[] arr[] = new char[K][N];
			char[] copy;
			for (int j = 0; j < K; j++) {
				copy = in.readLine().toCharArray();
				arr[j] = copy;
			}
			Arrays.sort(arr, main.comparator);
			for (int i = 0; i < K; i++) {
				System.out.println(arr[i]);
			}
			ammount--;
			System.out.println();
		}
	}
	
	public static int getInvCount(int n, char[] arr) {
		int inv_count = 0;
		for (int i = 0; i < n - 1; i++)
			for (int j = i + 1; j < n; j++)
				if (arr[i] > arr[j])
					inv_count++;

		return inv_count;
	}

	private class OrderComparator implements Comparator<char[]> {
		public int compare(char[] first, char[] second) {
			if (Main.getInvCount(first.length, first) == Main.getInvCount(second.length, second))
				return 0;
			else if (Main.getInvCount(first.length, first) < Main.getInvCount(second.length, second))
				return -1;
			else
				return 1;
		}
	}

}
