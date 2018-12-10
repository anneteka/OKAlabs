import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BST<Key extends Comparable<Key>, Value> implements Iterable<Key> {
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int size;

        public Node(Key key, Value val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException();
        if (value == null) {
            delete(key);
            return;
        }
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) return new Node(key, value, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, value);
        else if (cmp > 0) x.right = put(x.right, key, value);
        else x.val = value;
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (key == null) throw new IllegalArgumentException();
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException();
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    public Iterator<Key> iterator() {
        return new IteratorBST();
    }

    private class IteratorBST implements Iterator<Key> {
        private Stack<Node> stack = new Stack<Node>();

        IteratorBST() {
            Node x = root;
            while (x != null) {
                stack.push(x);
                x = x.left;
            }
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public void remove() {
        }

        public Key next() {
            if (!hasNext()) throw new NoSuchElementException();
            Node x = stack.pop();
            Key key = x.key;
            x = x.right;
            while (x != null) {
                stack.push(x);
                x = x.left;
            }
            return key;
        }
    }

    public Key min() {
        if (size(root) == 0) throw new NoSuchElementException();
        return min(root).key;
    }

    private Node min(Node node) {
        if (node.left == null) return node;
        else return min(node.left);
    }

    public Key max() {
        if (size(root) == 0) throw new NoSuchElementException();
        return max(root).key;
    }

    private Node max(Node node) {
        if (node.right == null) return node;
        else return max(node.right);
    }

    public Key floor(Key key) {
        if (key == null) throw new IllegalArgumentException();
        if (size(root) == 0) throw new NoSuchElementException();
        Node x = floor(root, key);
        if (x == null) return null;
        else return x.key;
    }

    private Node floor(Node node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp == 0) return node;
        if (cmp < 0) return floor(node.left, key);
        Node t = floor(node.right, key);
        if (t != null) return t;
        else return node;
    }

    public Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException();
        if (size(root) == 0) throw new NoSuchElementException();
        Node x = ceiling(root, key);
        if (x == null) return null;
        else return x.key;
    }

    private Node ceiling(Node node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp == 0) return node;
        if (cmp < 0) {
            Node t = ceiling(node.left, key);
            if (t != null) return t;
            else return node;
        }
        return ceiling(node.right, key);
    }

    public int rank(Key key) {
        if (key == null) throw new IllegalArgumentException();
        return rank(key, root);
    }

    private int rank(Key key, Node node) {
        if (node == null) return 0;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) return rank(key, node.left);
        else if (cmp > 0) return node.size = 1 + size(node.left)
                + rank(key, node.right);
        else return size(node.left);
    }

    private int size(Node node) {
        if (node == null) return 0;
        else return node.size;
    }
}
