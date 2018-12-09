package pr8;

/**
 * Binary search tree
 * 
 * @author Ѕогдана
 *
 * @param <Key>
 * @param <Value>
 */
public class BST<Key extends Comparable<Key>, Value> {
	private Node root = null;

	private class Node {
		Key key;
		Value val;
		private Node left;
		private Node right;
		private int count;// amount of children (including itself)

		public Node(Key key, Value val, int n) {
			this.key = key;
			this.val = val;
			this.count = n;
		}
	}

	/**
	 * Insert a node of a key, and a value in the tree. It searches the tree
	 * recursively until it finds a null link, and all that we need to do is replace
	 * that link with a new node.
	 * 
	 * If the key already exists, it updates the value (avoid duplicates). It
	 * removes the node from the tree if the value is null.
	 * 
	 * @param key
	 * @param val
	 */
	public void put(Key key, Value val) {
		if (key == null)
			return;
		root=put(root, key, val);
	}

	private Node put(Node current, Key key, Value val) {
		if (current == null) {
			return new Node(key, val, 1);
		}
		int cmp=key.compareTo(current.key);
		if (cmp < 0)
			current.left = put(current.left, key, val);
		else if (cmp> 0)
			current.right = put(current.right, key, val);
		else current.val = val;
		
		current.count = 1+size(current.left) + size(current.right);

		return current;
	}

	/**
	 * It searches for a node using itТs key recursively, and returns itТs value.
	 * 
	 * It starts from the root node, if itТs less than, then go left, if itТs
	 * greater than, go right, and if itТs equal, we are done; search hit. On the
	 * other hand, If the tree is empty, or we reached null link, we have a search
	 * miss.
	 * 
	 * @param key
	 * @return
	 */
	public Value get(Key key) {
		while (root != null) {
			if (key.compareTo(root.key) < 0)
				root = root.left;
			else if (key.compareTo(root.key) > 0)
				root = root.right;
			else
				return root.val;
		}

		return null;
	}

	boolean contains(Key key) {
		return get(key) != null;
	}

	boolean isEmpty() {
		return root == null;
	}

	public void delete(Key key) {
		if (key == null)
			return;
		root = delete(root, key);
	}

	private Node delete(Node current, Key key) {
		if (current == null)
			return null;
		int cmp = key.compareTo(current.key);
		if (cmp < 0)
			current.left=delete(current.left, key);
		else if (cmp > 0)
			current.right=delete(current.right, key);
		else {
			if (current.right == null)
				return current.left;
			if (current.left == null)
				return current.right;
			else {
				Node t = current;
				current = min(t.right);
				current.right = delete(t.right, key);
				current.left = t.left;
			}
			current.count = size(current.left) + size(current.right) + 1;
		}
		return current;
	}

	public Iterable<Key> iterator() {
		
		return null;
	}

	// м≥н≥мальний ключ в таблиц≥.
	public Key min() {
		if (isEmpty())
			return null;
		return min(root).key;
	}

	// A helper method to get the smallest node starting from Node current
	private Node min(Node current) {
		if (current.left == null)
			return current;
		else return min(current.left);

	}

	// максимальний ключ в таблиц≥.
	public Key max() {
		if (root == null)
			return null;
		return max(root).key;
	}

	// A helper method to get the largest node starting from Node current
	private Node max(Node curr) {
		if (curr.right == null)
			return curr;
		return max(curr.right);
	}

	// максимальний ключ в таблиц≥, що менше або дор≥внюЇ заданому.
	/**
	 * For finding the floor, If current node is greater than key k, keep going to
	 * left. If current node is smaller than k, go right. Whenever we go right, the
	 * current node is the floor, until we go right again.
	 * 
	 * @param key
	 * @return
	 */
	public Key floor(Key key) {
		if(isEmpty()) return null;
		if (key == null)
			return null;
		Node current =floor(root, key);
		if(current==null)return null;
		return current.key;
	}

	// check!!!
	public Node floor(Node current, Key key) {
		if (current == null)
			return current;
		int cmp=key.compareTo(current.key);
		if (cmp==0)
				return current;
		if (cmp < 0)
				return floor(current.left, key);
		Node t=floor(current.right,key);
			if(t!=null) return t;
			else return current;
	}

	// найменший ключ в таблиц≥, що менше або дор≥внюЇ заданому.
	/**
	 * For finding the floor, If current node is greater than key k, keep going to
	 * left. If current node is smaller than k, go right. Whenever we go right, the
	 * current node is the floor, until we go right again
	 * 
	 * @param key
	 * @return
	 */
	public Key ceiling(Key key) {
		if (root == null)
			return null;
		root = ceileng(root, key);
		return root.key;
	}

	private Node ceileng(Node root, Key key) {
		if (root == null)
			return null;
		if (key.compareTo(root.key) == 0)
			return root;
		if (key.compareTo(root.key) > 0)
			root = ceileng(root.right, key);
		Node t = ceileng(root.left, key);
		if (t != null)
			return t;
		return root;
	}

	/**
	 * If current node is greater than k, keep going to left; It means the key we
	 * are searching for is before(less than) the current key.
	 * 
	 * If current node is smaller than k, go right; It means the key we are
	 * searching for is after(greater than) the current key, and so, we need to
	 * count the current node, and all of the nodes on itТs left subtree.
	 * 
	 * Remember, rank of key k the number of keys that are less than k. In other
	 * words, it returns the index of k in a sorted array, or an ordered tree
	 * 
	 * @param key
	 * @return
	 */
	public int rank(Key key) {
		if (key == null)
			return 0;
		return rank(root, key);

	}

	private int rank(Node root, Key key) {
		if (root == null)
			return 0;
		if (root.key.compareTo(key) < 0)
			rank(root.left, key);
		if (root.key.compareTo(key) > 0)
			return 1 + size(root.left) + rank(root.right, key);
		return root.left.count;

	}

	/**
	 * For select, suppose that we want the key at rank k(at index k in a BST) Е
	 * 
	 * If the number of keys t in the left subtree is larger than k, we go left, and
	 * if t is smaller than k, we go right, and update k = k-t-1(subtract the
	 * current node, and all of the nodes on itТs left subtree).
	 * 
	 * @return
	 */


	private int size(Node n) {
		if (n != null)
			return n.count;
		return 0;
	}

	public static void main(String[] args) {
		BST<Integer, Integer> b = new BST<>();
		b.put(12, 1);
		b.put(5, 1);
		b.put(3, 1);
		b.put(7, 1);
		b.put(9, 1);
		b.put(15, 1);
		b.put(13, 1);
		b.put(17, 1);
		b.put(19, 1);
		b.delete(3);
		//System.out.println(b.isEmpty());
		System.out.println(b.min());

	}

}
