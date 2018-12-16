import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;
    /**Class Node for saving key values and roots of subtrees*/
    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private int count;
        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }
    /** Adding element to BST*/
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }
    public Node put(Node root, Key key, Value value) {
        if (root == null) return new Node(key, value);
        int cmp = key.compareTo(root.key);
        if (cmp < 0)
            root.left = put(root.left, key, value);
        else if (cmp > 0)
            root.right = put(root.right, key, value);
        else root.value = value;
        root.count=1+size(root.left)+size(root.right);
        return root;
    }
    /** Get an element by key*/
    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.value;
        }
        return null;
    }
    public int rank(Key key){
        return rank(key,root);
    }
    /**Deleting key*/
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("calls delete() with a null key");
        root = delete(root, key);
    }
    private Node delete(Node x, Key key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = delete(x.left,  key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left  == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.count = size(x.left) + size(x.right) + 1;
        return x;
    }
    /**Iterator for keys*/
    public Iterable<Key> iterator() {
        if (isEmpty()) return new Queue<Key>();
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }

    /**Get key with min value*/
    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        else                return min(x.left);
    }
    /**Get key with max value*/
    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        else                 return max(x.right);
    }

    /**Max key that less than parameter key*/
    public Key floor(Key key){
        Node x = floor(root, key);
        if (x==null) return null;
        return x.key;
    }
    private Node floor(Node root, Key key ){
        if (root==null) return null;
        int cmp=key.compareTo(root.key);
        if(cmp==0) return root;
        if (cmp<0) return floor(root.left,key);
        Node t =floor(root.right,key);
        if (t!=null) return t;
        else return root;

    }
    /**Min key that greater than parameter key*/
    public Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
        if (isEmpty()) throw new NoSuchElementException("calls ceiling() with empty symbol table");
        Node x = ceiling(root, key);
        if (x == null) return null;
        else return x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) {
            Node t = ceiling(x.left, key);
            if (t != null) return t;
            else return x;
        }
        return ceiling(x.right, key);
    }
    /** Number of keys in the subtree less than key.*/
    public int rank(Key key,Node x){
        if(x==null) return 0;
        int cmp=key.compareTo(x.key);
        if (cmp<0) return rank(key,x.left);
        else if (cmp>0) return 1+size(x.left)+rank(key,x.right);
        else return size(x.left);
    }
    /**Size of BST*/
    public int size(){
        return size(root);
    }
    private int size(Node node){
        if (node==null) return 0;
        return node.count;
    }
    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.count = size(x.left) + size(x.right) + 1;
        return x;
    }
    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.count = size(x.left) + size(x.right) + 1;
        return x;
    }

    public static void main(String[] args) {
        BST<String,Integer> bst=new BST<>();
        bst.put("A",6);
        bst.put("L",89);
        bst.put("V",-4);
        bst.delete("A");
    }
}

