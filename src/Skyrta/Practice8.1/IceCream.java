import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author Maria Skyrta. E-olymp Ice-cream. Find the smallest distance between
 *         certain amount of shops, when the distance between them is the
 *         biggest possible. Algorithm: using binary search find the optimal
 *         distance.
 *
 */
public class IceCream {
	private int[] dist;
	private int m;
	private int res;

	IceCream(int[] dist, int m) {
		this.dist = dist;
		this.m = m;
		int val = 1000000000;
		res = rank(val);
	}

	private boolean check(int val) {
		int length = 0;
		int sallers = 1;
		for (int i = 1; i < dist.length; i++) {
			length += dist[i] - dist[i - 1];
			if (length >= val) {
				length = 0;
				sallers++;
			}
		}
		return sallers >= m;
	}

	private int rank(int val) {
		int lo = 0, hi = val;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (!check(mid))
				hi = mid - 1;
			else
				lo = mid + 1;
		}
		return lo - 1;
	}

	public int result() {
		return res;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (true) {
			s = br.readLine();
			StringTokenizer st = new StringTokenizer(s);
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int[] dist = new int[n];
			s = br.readLine();
			st = new StringTokenizer(s);
			for (int i = 0; i < n; i++) {
				dist[i] = Integer.parseInt(st.nextToken());
			}
			IceCream ic = new IceCream(dist, m);
			System.out.println(ic.result());
		}
	}

}
