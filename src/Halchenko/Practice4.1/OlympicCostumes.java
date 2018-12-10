import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class OlympicCostumes {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String dynamic = br.readLine();
		int n = Integer.parseInt(dynamic);
		dynamic = br.readLine();
		StringTokenizer st = new StringTokenizer(dynamic);
		dynamic = br.readLine();
		StringTokenizer st1 = new StringTokenizer(dynamic);
		int min = Integer.parseInt(st1.nextToken());
		int max = Integer.parseInt(st1.nextToken());
		int counter = 0;
		for (int i = 0; i < n; i++) {
			int k = Integer.parseInt(st.nextToken());
			if (k >= min && k <= max)
				counter++;
		}
		System.out.println(counter);
	}
}