import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

/**
 * 
 * @author Maria Skyrta. E-olymp Wormix. Find the smallest time in which entered
 *         amount of points can be achieved. Algorithm: fill the array with best
 *         time for each amount of points, get the best for entered amount of
 *         points.
 *
 */
public class Wormix {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		StringTokenizer st;
		BigInteger sum;
		while (true) {
			sum = BigInteger.valueOf(0);
			int time = 0;
			s = br.readLine();
			st = new StringTokenizer(s);
			int n = Integer.parseInt(st.nextToken());
			int minPoints = Integer.parseInt(st.nextToken());
			int[] points = new int[n];
			int[] times = new int[n];
			for (int i = 0; i < n; i++) {
				s = br.readLine();
				st = new StringTokenizer(s);
				int p = Integer.parseInt(st.nextToken());
				points[i] = p;
				sum = sum.add(BigInteger.valueOf(p));
				int t = Integer.parseInt(st.nextToken());
				times[i] = t;
				time += t;
			}
			if (sum.compareTo(BigInteger.valueOf(minPoints)) < 0) {
				System.out.println("-1");
			} else if (sum.compareTo(BigInteger.valueOf(minPoints)) == 0) {
				System.out.println(time);
			} else {
				int m[][] = new int[n][minPoints + 1];
				for (int i = 0; i < n; i++)
					for (int j = 1; j <= minPoints; j++) {
						if (points[i] >= j)
							m[i][j] = times[i];
						else {
							if (i > 0) {
								if (m[i - 1][j - points[i]] != -1)
									m[i][j] = m[i - 1][j - points[i]] + times[i];
								else
									m[i][j] = -1;
							} else {
								m[i][j] = -1;
							}
						}
						if (i > 0) {
							if (m[i - 1][j] != -1 && (m[i - 1][j] < m[i][j] || m[i][j] == -1))
								m[i][j] = m[i - 1][j];
						}
					}

				System.out.println(m[n - 1][minPoints]);
			}
		}
	}
}
