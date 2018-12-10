import java.util.Iterator;

/**
 * Реалізація Binary Search Trees
 * 
 * @author Пивовар Олени, 4 група, ІПЗ
 *
 * @param <Key>
 * @param <Value>
 */
public class BST<Key extends Comparable<Key>, Value> {

	private Node root;

	private class Node {
		private Key key;
		private Value val;
		private Node left, right;
		private int count;

		public Node(Key key, Value val) {
			this.key = key;
			this.val = val;
		}
	}

	/**
	 * додавання елемента
	 * 
	 * @param key
	 * @param val
	 */
	public void put(Key key, Value val) {
		root = put(root, key, val);
	}

	private Node put(Node x, Key key, Value val) {
		if (x == null)
			return new Node(key, val);
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			x.left = put(x.left, key, val);
		else if (cmp > 0)
			x.right = put(x.right, key, val);
		else if (cmp == 0)
			x.val = val;
		x.count = 1 + size(x.left) + size(x.right);
		return x;
	}

	/**
	 * @param key
	 * @return елемент за ключем
	 */
	public Value get(Key key) {
		Node x = root;
		while (x != null) {
			int cmp = key.compareTo(x.key);
			if (cmp < 0)
				x = x.left;
			else if (cmp > 0)
				x = x.right;
			else if (cmp == 0)
				return x.val;
		}
		return null;
	}

	/**
	 * видалення елемента за ключем
	 * 
	 * @param key
	 */
	public void delete(Key key) {
		root = delete(root, key);
	}

	private Node delete(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			x.left = delete(x.left, key);
		else if (cmp > 0)
			x.right = delete(x.right, key);
		else {
			if (x.right == null)
				return x.left;
			if (x.left == null)
				return x.right;
			Node t = x;
			x = min(t.right);
			x.right = deleteMin(t.right);
			x.left = t.left;
		}
		x.count = size(x.left) + size(x.right) + 1;
		return x;
	}

	/**
	 * видалення мінімального
	 */
	public void deleteMin() {
		root = deleteMin(root);
	}

	private Node deleteMin(Node x) {
		if (x.left == null)
			return x.right;
		x.left = deleteMin(x.left);
		x.count = 1 + size(x.left) + size(x.right);
		return x;
	}

	/**
	 * @return ітератор
	 */
	public Iterable<Key> iterator() {
		Queue<Key> q = new Queue<Key>();
		inorder(root, q);
		return q;
	}

	private void inorder(Node x, Queue<Key> q) {
		if (x == null)
			return;
		inorder(x.left, q);
		q.enqueue(x.key);
		inorder(x.right, q);
	}

	/**
	 * @return мінімальний ключ в таблиці
	 */
	public Key min() {
		Node x = root;
		Key res = null;
		while (x != null) {
			res = x.key;
			x = x.left;
		}
		return res;
	}

	private Node min(Node node) {
		Node x = node;
		Node temp = x;
		Key res = null;
		while (x != null) {
			res = x.key;
			temp = x;
			x = x.left;
		}
		return temp;
	}

	/**
	 * @return максимальний ключ в таблиці
	 */
	public Key max() {
		Node x = root;
		Key res = null;
		while (x != null) {
			res = x.key;
			x = x.right;
		}
		return res;
	}

	/**
	 * @param key
	 * @return максимальний ключ в таблиці, що менше або дорівнює заданому
	 */
	public Key floor(Key key) {
		Node x = floor(root, key);
		if (x == null)
			return null;
		return x.key;
	}

	private Node floor(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0)
			return x;
		if (cmp < 0)
			return floor(x.left, key);
		Node t = floor(x.right, key);
		if (t != null)
			return t;
		else
			return x;
	}

	/**
	 * @param key
	 * @return найменший ключ в таблиці, що менше або дорівнює заданому
	 */
	public Key ceiling(Key key) {
		Node x = ceiling(root, key);
		if (x == null)
			return null;
		return x.key;
	}

	private Node ceiling(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0)
			return x;
		if (cmp < 0) {
			Node t = ceiling(x.left, key);
			if (t != null)
				return t;
			else
				return x;
		}
		return ceiling(x.right, key);
	}

	/**
	 * @param key
	 * @return кількість ключів менших за key
	 */
	public int rank(Key key) {
		return rank(key, root);
	}

	private int rank(Key key, Node x) {
		if (x == null)
			return 0;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			return rank(key, x.left);
		else if (cmp > 0)
			return 1 + size(x.left) + rank(key, x.right);
		else
			return size(x.left);
	}

	/**
	 * @return розмір дерева
	 */
	public int size() {
		return size(root);
	}

	private int size(Node x) {
		if (x == null)
			return 0;
		return x.count;
	}

	public static void main(String[] args) {
		BST<Integer, Integer> bst = new BST<>();
		bst.put(5, 5);
		bst.put(2, 2);
		bst.put(7, 7);
		bst.put(6, 6);
		bst.put(3, 3);

		Iterator<Integer> iterator = bst.iterator().iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + " ");
		}
		System.out.println();

		System.out.println("min = " + bst.min());
		System.out.println("max = " + bst.max());

		bst.put(-1, 6);
		bst.put(10, 3);
		System.out.println("min = " + bst.min());
		System.out.println("max = " + bst.max());

		System.out.println("ceiling(3) = " + bst.ceiling(3));
		System.out.println("ceiling(5) = " + bst.ceiling(5));
		System.out.println("ceiling(0) = " + bst.ceiling(0));
		System.out.println("ceiling(9) = " + bst.ceiling(9));

		System.out.println("floor(1) = " + bst.floor(1));
		System.out.println("floor(9) = " + bst.floor(9));

		System.out.println("- - -");
		System.out.println("before delete 5 = " + bst.get(5));
		bst.delete(5);
		System.out.println("after delete 5 = " + bst.get(5));

		System.out.println("- - -");
		System.out.println("before delete -1 = " + bst.get(-1));
		bst.delete(-1);
		System.out.println("after delete -1 = " + bst.get(-1));
		System.out.println("min = " + bst.min());
	}

}
