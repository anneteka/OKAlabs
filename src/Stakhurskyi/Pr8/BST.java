package Stakhurskyi.Pr8;

import java.util.Queue;

public class BST<Key extends Comparable<Key>, Value> {

    private class Node {
        private Key key;
        private Value val;
        private int count;
        private Node left, right;

        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }
    }

    Node root;

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node root, Key key, Value value) {
        if (root == null)
            return new Node(key, value);
        int cmp = key.compareTo(root.key);
        if (cmp < 0)
            root.left = put(root, key, value);
        else if (cmp > 0)
            root.right = put(root, key, value);
        else if (cmp == 0)
            root.val = value;
        root.count = size(root.left) + size(root.right) + 1;
        return root;
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        return node == null ? null : node.count;
    }

    public Value get(Key key) {
        Node cur = root;
        int cmp;
        while (cur != null) {
            cmp = key.compareTo(cur.key);
            if (cmp < 0)
                cur = cur.left;
            else if (cmp > 0)
                cur = cur.right;
            else
                return cur.val;
        }
        return null;
    }

    public Key min() {
        if (root == null)
            return null;
        return min(root).key;
    }

    private Node min(Node root){
        return root.left == null ? root : min(root.left);
    }

    public Key max() {
        if (root == null)
            return null;
        Node cur = root;
        while (cur.right != null) {
            cur = cur.right;
        }
        return cur.key;
    }

    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node floor(Node root, Key key) {
        if (root == null)
            return null;

        int cmp = key.compareTo(root.key);
        if (cmp < 0)
            return floor(root.left, key);
        if (cmp > 0) {
            Node tmp = floor(root.right, key);
            return (tmp == null) ? root : tmp;
        }

        return root;
    }

    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node ceiling(Node root, Key key) {
        if (root == null)
            return null;

        int cmp = key.compareTo(root.key);
        if (cmp > 0)
            return ceiling(root.right, key);
        if (cmp < 0) {
            Node tmp = floor(root.left, key);
            return (tmp == null) ? root : tmp;
        }

        return root;
    }

    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return rank(key, x.left);
        else if (cmp > 0)
            return 1 + size(x.left) + rank(key, x.right);
        else
            return size(x.left);
    }
//
//    public Iterable<Key> keys() {
//        Queue<Key> q = new Queue<Key>();
//        inorder(root, q);
//        return q;
//    }
//
//    private void inorder(Node x, Queue<Key> q) {
//        if (x == null) return;
//        inorder(x.left, q);
//        q.enqueue(x.key);
//        inorder(x.right, q);
//    }

    public void delete(Key key){
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
        x.count = size(x.left) + size(x.right) + 1;
        return x;
    }
    public void deleteMin(){
        root = deleteMin(root);
    }
    private Node deleteMin(Node x){
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

}
