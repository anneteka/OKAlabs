import java.util.LinkedList;
import java.util.Queue;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node node, Key key, Value val) {
        if (node == null) return new Node(key, val);
        int cmp = key.compareTo(node.key);
        if (cmp > 0) {
            node.right = put(node.right, key, val);
        } else if (cmp < 0) {
            node.left = put(node.left, key, val);
        } else {
            node.value = val;
        }
        node.count = 1 + size(node.left) + size(node.right);
        return node;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp > 0) {
            return get(node.right, key);
        } else if (cmp < 0) {
            return get(node.left, key);
        } else {
            return node.value;
        }
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    public void delete(Key key1, Key key2) {
        if (key1.compareTo(key2) > 0) throw new IllegalArgumentException("Key1 > Key2");
        delete(root, key1, key2);
    }

    private Node delete(Node node, Key key1, Key key2) {
        if (node == null) return null;
        int cmpLo = key1.compareTo(node.key);
        int cmpHi = key2.compareTo(node.key);
        if (cmpLo < 0) node.left = delete(node.left, key1, key2);
        if (cmpLo <= 0 && cmpHi >= 0) return deleteNode(node);
        if (cmpHi > 0) node.right = delete(node.right, key1, key2);
        return node;
    }

    private Node deleteNode(Node node) {
        if (node.left == null) return node.right;
        if (node.right == null) return node.left;
        Node toDelete = node;
        node = deleteMin(toDelete.right);
        node.left = toDelete.left;
        node.right = toDelete.right;
        return node;
    }

    public Node deleteMin(Node node) {
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        node.count = 1 + size(node.left) + size(node.right);
        return node;
    }


    private Node delete(Node node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp > 0) {
            node.right = delete(node.right, key);
        } else if (cmp < 0) {
            node.left = delete(node.left, key);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node toDelete = node;
            node = deleteMin(toDelete.right);
            node.left = toDelete.left;
            node.right = toDelete.right;
            return node;
        }
        node.count = 1 + size(node.left) + size(node.right);
        return node;
    }

    public Iterable<Key> iterator() {
        return keys();
    }

    public Queue<Key> keys() {
        Queue<Key> queue = new LinkedList<>();
        keys(root, queue);
        return queue;
    }

    private void keys(Node node, Queue<Key> keys) {
        if (node == null) return;
        keys(node.left, keys);
        keys.add(node.key);
        keys(node.right, keys);
    }

    public Key min() {
        return min(root);
    }

    private Key min(Node node) {
        if (node.left == null) return node.key;
        return min(node.left);
    }

    public Key max() {
        return max(root);
    }

    private Key max(Node node) {
        if (node.right == null) return node.key;
        return max(node.right);
    }

    public Key floor(Key key) {
        Node floor = floor(root, key);
        if (floor == null) return null;
        return floor.key;
    }

    private Node floor(Node node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp == 0) return node;
        if (cmp < 0) return floor(node.left, key);
        Node temp = floor(node.right, key);
        return temp == null ? node : temp;
    }

    public Key ceiling(Key key) {
        Node ceiling = ceiling(root, key);
        if (ceiling == null) return null;
        return ceiling.key;
    }

    private Node ceiling(Node node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp == 0) return node;
        if (cmp > 0) return ceiling(node.right, key);
        Node temp = ceiling(node.left, key);
        return temp == null ? node : temp;
    }

    public int rank(Key key) {
        return rank(root, key);
    }

    private int rank(Node node, Key key) {
        if (node == null) return 0;
        int cmp = key.compareTo(node.key);
        if (cmp > 0) {
            return 1 + size(node.left) + rank(node.right, key);
        } else if (cmp < 0) {
            return rank(node.left, key);
        } else {
            return size(node.left);
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) return 0;
        return node.count;
    }

    private class Node {
        Node left, right;
        Key key;
        Value value;
        int count;

        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

}
