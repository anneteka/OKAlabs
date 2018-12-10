import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author Maria Skyrta. E-olymp Railway Queue. Get the time when all the
 *         customers from queue will be serviced. Algorithm: insert k first
 *         customers to MinPriorityQueue, remove the one with smallest time,
 *         insert next customer with time of previous+his own time to be
 *         serviced. Continue till the last customer is inserted, return the
 *         biggest number from queue.
 * 
 *
 */
public class Railway {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		StringTokenizer st;
		MinPriorityQ<Long> queue;
		while (true) {
			s = br.readLine();
			st = new StringTokenizer(s);
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			s = br.readLine();
			st = new StringTokenizer(s);
			if (n > k) {
				queue = new MinPriorityQ<Long>(k);
				for (int i = 0; i < k; i++) {
					long m = Long.parseLong(st.nextToken());
					queue.insert(m);
				}
				for (int j = k; j < n; j++) {
					long m = Long.parseLong(st.nextToken());
					long prev = queue.delMin();
					queue.insert(m + prev);
				}
			} else {
				queue = new MinPriorityQ<Long>(n);
				for (int i = 0; i < n; i++) {
					long m = Long.parseLong(st.nextToken());
					queue.insert(m);
				}
			}
			while (queue.size() > 1)
				queue.delMin();
			System.out.println(queue.delMin());
		}
	}

}

class MinPriorityQ<Key extends Comparable<Key>> {
	private Key[] pq;
	private int n;

	public MinPriorityQ(int capacity) {
		pq = (Key[]) new Comparable[capacity + 1];
	}

	public boolean isEmty() {
		return n == 0;
	}

	public int size() {
		return n;
	}

	public void insert(Key key) {
		pq[++n] = key;
		swim(n);
	}

	public Key delMin() {
		Key min = pq[1];
		exch(1, n--);
		sink(1);
		pq[n + 1] = null;
		return min;
	}

	private void swim(int k) {
		while (k > 1 && less(k / 2, k)) {
			exch(k, k / 2);
			k = k / 2;
		}
	}

	private void sink(int k) {
		while (2 * k <= n) {
			int j = 2 * k;
			if (j < n && less(j, j + 1))
				j++;
			if (!less(k, j))
				break;
			exch(k, j);
			k = j;
		}
	}

	private boolean less(int i, int j) {
		return pq[i].compareTo(pq[j]) > 0;
	}

	private void exch(int i, int j) {
		Key t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;
	}
}
