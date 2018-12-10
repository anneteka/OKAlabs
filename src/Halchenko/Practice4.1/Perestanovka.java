import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Perestanovka {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int[] array = new int[n];
		for (int i = 0; i < n; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(array);
		for (int i = 0; i < n; i++) {
			if (i + 1 >= n) {
				System.out.println("0");
				break;
			}

			if (array[i] + 1 != array[i + 1]) {
				System.out.println(array[i] + 1);
				break;
			}

		}
	}
}
