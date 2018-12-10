import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author Maria Skyrta. E-olymp Banket of Doctor Who. Check if all the guest
 *         can communicate with the exact amount of other guests. Algorithm: add
 *         all guests to Max priority Queue, remove guest with the biggest
 *         number, check if he can communicate with this number of guests by
 *         subtracting 1 from every next guest in queue. Continue till no guests
 *         are left in queue or guest's requirement can not be fulfilled.
 *
 */
public class Banket {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		MaxPQ<Integer> guests;
		while (true) {
			String s = br.readLine();
			StringTokenizer st = new StringTokenizer(s);
			guests = new MaxPQ<Integer>(st.countTokens());
			while (st.hasMoreTokens()) {
				guests.insert(Integer.parseInt(st.nextToken()));
			}
			boolean happy = true;
			while (!guests.isEmpty()) {
				int n = guests.delMax();
				int[] arr = new int[n];
				if (n > guests.size()) {
					happy = false;
					break;
				}
				for (int i = 0; i < n; i++) {
					arr[i] = guests.delMax() - 1;
				}
				for (int i = 0; i < n; i++) {
					if (arr[i] < 0) {
						happy = false;
						break;
					} else
						guests.insert(arr[i]);
				}
			}
			if (guests.isEmpty() && happy)
				System.out.println("ok\n");
			else
				System.out.println("fail\n");
		}
	}
}

class MaxPQ<Key extends Comparable<Key>> {

	private Key[] pq;
	private int n;

	public MaxPQ(int capacity) {
		pq = (Key[]) new Comparable[capacity + 1];
	}

	public boolean isEmpty() {
		return n == 0;
	}

	public int size() {
		return n;
	}

	public void insert(Key key) {
		pq[++n] = key;
		swim(n);
	}

	public Key delMax() {
		Key max = pq[1];
		exch(1, n--);
		sink(1);
		pq[n + 1] = null;
		return max;
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
		return pq[i].compareTo(pq[j]) < 0;
	}

	private void exch(int i, int j) {
		Key t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;
	}

}
