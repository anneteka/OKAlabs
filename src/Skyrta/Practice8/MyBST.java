import java.util.ArrayList;

/**
 * 
 * @author Maria Skyrta. Implementation of BST Api.
 *
 * @param <Key>
 * @param <Value>
 */
public class MyBST<Key extends Comparable<Key>, Value> {
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

	public Iterable<Key> keys() {
		ArrayList<Key> q = new ArrayList();
		inorder(root, q);
		return q;
	}

	private void inorder(Node x, ArrayList<Key> q) {
		if (x == null)
			return;
		inorder(x.left, q);
		q.add(x.key);
		inorder(x.right, q);
	}

	public Key min() {
		Node x = root;
		Node prev = null;
		while (x != null) {
			prev = x;
			x = x.left;
		}
		if (prev == null)
			return null;
		return prev.key;
	} // мінімальний ключ в таблиці.

	public Key max() {
		Node x = root;
		Node prev = null;
		while (x != null) {
			prev = x;
			x = x.right;
		}
		if (prev == null)
			return null;
		return prev.key;
	} // максимальний ключ в таблиці.

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
	// максимальний ключ в таблиці, що менше або дорівнює заданому.

	public Key ceiling() {
		return null;
	} // найменший ключ в таблиці, що менше або дорівнює заданому.

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
		else if (cmp == 0)
			return size(x.left);
		return 0;
	}

	public int size() {
		return size(root);
	}

	private int size(Node x) {
		if (x == null)
			return 0;
		return x.count;
	}

	public static void main(String[] args) {
		MyBST<Integer, Integer> bst = new MyBST<>();
		bst.put(4, 5);
		bst.put(2, 3);
		bst.put(10, 7);
	}

}
