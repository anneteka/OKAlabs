import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author Maria Skyrta. E-olymp 2470: check if the graph is simple. Algorithm:
 *         check if the matrix is symmetric, any value is not bigger than 1 and
 *         if the values on the main diagonal are zeroes.
 *
 */
public class GraphCheck {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		while (true) {
			String s = br.readLine();
			int n = Integer.parseInt(s);
			int matrix[][] = new int[n][n];
			for (int i = 0; i < n; i++) {
				s = br.readLine();
				st = new StringTokenizer(s);
				for (int j = 0; j < n; j++) {
					String num = st.nextToken();
					matrix[i][j] = Integer.parseInt(num);
				}
			}
			boolean isSimple = true;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (matrix[i][j] != matrix[j][i] || matrix[i][j] > 1) {
						isSimple = false;
						break;
					} else if (i == j && matrix[i][j] != 0) {
						isSimple = false;
						break;
					}

				}
				if (!isSimple)
					break;
			}
			if (isSimple)
				System.out.println("YES");
			else
				System.out.println("NO");
		}
	}

}
