import java.lang.reflect.Array;
import java.util.Iterator;

import princetonlib.StdIn;
import princetonlib.StdOut;

class Node<Key, Value> {
	Key key;
	Value val;
}

/**
 * Реалізація Symbol table
 * 
 * @author Пивовар Олена, 4 група, ІПЗ
 *
 * @param <Key>
 * @param <Value>
 */
public class ST<Key extends Comparable<Key>, Value> {

	private Node<Key, Value>[] map;
	private int n;

	ST() throws ClassNotFoundException {
		Class<?> c = Class.forName("Node");
		map = (Node[]) Array.newInstance(c, 1);
	}

	/**
	 * покласти пару ключ значення в таблицю і видалити ключ якщо значення null
	 * 
	 * @param key
	 * @param val
	 */
	void put(Key key, Value val) {
		if (key == null)
			return;
		int i = rank(key);
		if (isEmpty()) {
			Node t = new Node();
			t.key = key;
			t.val = val;
			map[n++] = t;
			return;
		}
		if (i < n && map[i].key.compareTo(key) == 0)
			map[i].val = val;
		else {
			if (n == map.length)
				resize(2 * map.length);
			for (int j = n; j > i; j--) {
				map[j] = map[j - 1];
			}
			map[i] = new Node();
			map[i].key = key;
			map[i].val = val;
			n++;
		}
	}

	/**
	 * повернути значення, що асоціюється з ключем і null якщо ключ відсутній
	 * 
	 * @param key
	 * @return
	 */
	Value get(Key key) {
		if (isEmpty())
			return null;
		int i = rank(key);
		if (i < n && map[i].key.compareTo(key) == 0)
			return map[i].val;
		else
			return null;
	}

	/**
	 * видалити ключ і значення з таблиці
	 * 
	 * @param key
	 */
	void delete(Key key) {
		if (isEmpty())
			return;
		if (get(key) == null)
			return;
		int i = rank(key);
		for (int j = i; j < n - 1; j++) {
			map[j] = map[j + 1];
		}
		n--;
	}

	/**
	 * чи є значення, що асоціюється з ключем +
	 * 
	 * @param key
	 * @return
	 */
	boolean contains(Key key) {
		return get(key) != null;
	}

	/**
	 * @return перевіряємо чи порожня таблиця
	 */
	boolean isEmpty() {
		return n == 0;
	}

	/**
	 * @return кількість пар key-value в таблиці
	 */
	int size() {
		return n;
	}

	/**
	 * @return найменший ключ
	 */
	Key min() {
		if (isEmpty())
			return null;
		return map[0].key;
	}

	/**
	 * @return найбільший ключ
	 */
	Key max() {
		if (isEmpty())
			return null;
		return map[n - 1].key;
	}

	/**
	 * @param key
	 * @return найбільший ключ менший або рівний key
	 */
	Key floor(Key key) {
		if (isEmpty())
			return null;
		int i = rank(key);
		if (i < n && map[i].key.compareTo(key) == 0)
			return map[i].key;
		if (i > 0 && i < n)
			return map[i - 1].key;
		return null;
	}

	/**
	 * @param key
	 * @return найменший ключ більший або рівний key
	 */
	Key ceiling(Key key) {
		if (isEmpty())
			return null;
		int i = rank(key);
		if (i < n)
			return map[i].key;
		return null;
	}

	/**
	 * @param key
	 * @return кількість ключів менших за key
	 */
	int rank(Key key) {
		int lo = 0, hi = n - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int cmp = key.compareTo(map[mid].key);
			if (cmp < 0)
				hi = mid - 1;
			else if (cmp > 0)
				lo = mid + 1;
			else if (cmp == 0)
				return mid;
		}
		return lo;
	}

	/**
	 * @param k
	 * @return key k
	 */
	Key select(int k) {
		if (isEmpty())
			return null;
		if (k >= 0 && k < n)
			return map[k].key;
		return null;
	}

	/**
	 * видалити мінімальний ключ
	 */
	void deleteMin() {
		delete(min());
	}

	/**
	 * видалити максимальний ключ
	 */
	void deleteMax() {
		delete(max());
	}

	/**
	 * @param lo
	 * @param hi
	 * @return кількість ключів в [lo..hi]
	 */
	int size(Key lo, Key hi) {
		if (isEmpty())
			return 0;
		if (lo.compareTo(hi) == 1) {
			Key d = lo;
			lo = hi;
			hi = d;
		}
		int i = rank(lo);
		if (contains(lo))
			i++;
		int j = rank(hi);
		if (contains(hi))
			j++;
		int res = Math.abs(i - j);
		if (contains(lo) && contains(hi) && res != 0)
			res++;
		return res;
	}

	/**
	 * @return повертає ітератор по ключам
	 */
	Iterable<Key> keys() {
		return new KeyIterator();
	}

	/**
	 * @param lo
	 * @param hi
	 * @return повертає ітератор по ключам в межах [hi, lo]
	 */
	Iterable<Key> keys(Key lo, Key hi) {
		if(lo == null || hi == null)
			return null;
		if (lo.compareTo(hi) == 1) {
			Key d = lo;
			lo = hi;
			hi = d;
		}
		KeyIteratorLoHi it = new KeyIteratorLoHi();
		it.f = rank(lo);
		it.l = rank(hi);
		if (contains(hi))
			it.l++;
		return it;
	}

	private class KeyIteratorLoHi implements Iterator<Key>, Iterable<Key> {
		private int f;
		private int l;

		@Override
		public Iterator<Key> iterator() {
			return this;
		}

		@Override
		public boolean hasNext() {
			return f < l;
		}

		@Override
		public Key next() {
			return map[f++].key;
		}

	}

	private class KeyIterator implements Iterator<Key>, Iterable<Key> {
		private int i = 0;

		@Override
		public boolean hasNext() {
			return i < n;
		}

		@Override
		public Key next() {
			return map[i++].key;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Iterator<Key> iterator() {
			return this;
		}
	}

	/**
	 * зміна розміру масиву
	 * 
	 * @param capacity
	 */
	private void resize(int capacity) {
		Node<Key, Value>[] copy = (Node[]) Array.newInstance(Node.class, capacity);
		// (Node<Key,Value>[])new Object[capacity];
		for (int i = 0; i < n; i++)
			copy[i] = map[i];
		map = copy;
	}

	public static void main(String[] args) throws ClassNotFoundException {

		ST<Integer, Integer> st = new ST<Integer, Integer>();
		st.put(4, 2);
		st.put(2, 2);
		st.put(6, 6);
		st.put(2, 3);
		st.put(8, 4);

		Iterator<Integer> iner = (Iterator<Integer>) st.keys(1, 7);
		while (iner.hasNext()) {
			System.out.print(iner.next() + " ");
		}

		System.out.println();
		System.out.println("- - -");
		iner = (Iterator<Integer>) st.keys(3, 7);
		while (iner.hasNext()) {
			System.out.print(iner.next() + " ");
		}

		System.out.println();
		System.out.println("- - -");
		iner = (Iterator<Integer>) st.keys(2, 6);
		while (iner.hasNext()) {
			System.out.print(iner.next() + " ");
		}

		System.out.println();
		System.out.println("- - -");
		iner = (Iterator<Integer>) st.keys(8, 8);
		while (iner.hasNext()) {
			System.out.print(iner.next() + " ");
		}

		System.out.println();
		System.out.println("- - -");
		iner = (Iterator<Integer>) st.keys(8, 3);
		while (iner.hasNext()) {
			System.out.print(iner.next() + " ");
		}
		System.out.println();
		System.out.println("- - -");

		for (int s : st.keys())
			System.out.println(s + " - " + st.get(s));
		System.out.println("key size 2 - 8 = " + st.size(2, 8));
		System.out.println("key size 1 - 2 = " + st.size(1, 2));
		System.out.println("key size 0 - 1 = " + st.size(0, 1));
		System.out.println("key size 6 - 8 = " + st.size(6, 8));
		System.out.println("key size 7 - 10 = " + st.size(7, 10));
		System.out.println("key size 9 - 10 = " + st.size(9, 10));
		System.out.println("key size 5 - 10 = " + st.size(5, 10));

		System.out.println("min = " + st.min());
		System.out.println("max = " + st.max());
		System.out.println("floor(3) = " + st.floor(3));
		System.out.println("ceiling(1) = " + st.ceiling(1));
		System.out.println("select(4) = " + st.select(4));
		System.out.println("select(2) = " + st.select(2));
		System.out.println("size = " + st.size());

		st.delete(4);
		System.out.println("- - - delete 4");
		for (int s : st.keys())
			System.out.println(s + " - " + st.get(s));
		System.out.println("select(2) = " + st.select(2));

		st.deleteMin();
		System.out.println("- - - delete min");
		for (int s : st.keys())
			System.out.println(s + " - " + st.get(s));

		st.deleteMax();
		System.out.println("- - - delete max");
		for (int s : st.keys())
			System.out.println(s + " - " + st.get(s));
	}
}
