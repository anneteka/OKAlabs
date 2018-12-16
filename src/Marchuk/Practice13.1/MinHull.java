package Practice13;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MinHull {

	public static void main(String[] args) {
		MinHull obj = new MinHull();
		obj.doTask();
	}

	private void doTask() {
		Scanner sc = new Scanner(System.in);
			int n = sc.nextInt();
			int m = sc.nextInt();
			EdgeWeightedGraph ewg = new EdgeWeightedGraph(n);
			for(int i=0; i<m; i++) {
				ewg.addEdge(new Edge(sc.nextInt()-1,sc.nextInt()-1, sc.nextInt()));
			}
			LazyPrimMST lp = new LazyPrimMST(ewg);
			int res = 0;
			for(Edge e: lp.mst())
				res += e.getWeight();
			System.out.println(res);
	}
	
}

class Edge implements Comparable{

	private final int v, w;
	private final double weight;
	
	public Edge(int v, int w, double weight){
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	
	public int either(){ 
		return v; 
	}
	
	public double getWeight() {
		return weight;
	}
	
	public int other(int vertex){
		if (vertex == v) return w;
		else return v;
	}
	@Override
	public int compareTo(Object o) {
        Edge that = (Edge) o;
        if(this.weight > that.weight) return 1;
        if(this.weight < that.weight) return -1;
        return 0;
    }
}

class EdgeWeightedGraph {
	
	private final int V;
	private final Bag<Edge>[] adj;
	public EdgeWeightedGraph(int V)	{
		this.V = V;
		adj = (Bag<Edge>[]) new Bag[V];
		for (int v = 0; v < V; v++)
			adj[v] = new Bag<Edge>();
	}
	public void addEdge(Edge e){
		int v = e.either(), w = e.other(v);
		adj[v].add(e);
		adj[w].add(e);
	}
	
	public int V() {
		return V;
	}
	
	public Iterable<Edge> adj(int v){ 
		return adj[v]; 
	}

}

class Bag<Item> implements Iterable<Item> {
    private Node<Item> first;   
    private int n;               

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public void add(Item item) {
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    public Iterator<Item> iterator()  {
        return new BagIterator<Item>(first);  
    }

    private class BagIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public BagIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext(){
        	return current != null;
        }
        public void remove(){
        	throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
}

class LazyPrimMST {

	private boolean[] marked;
	private Queue<Edge> mst;
	private MinPQ<Edge> pq; 
	
	public LazyPrimMST(EdgeWeightedGraph G)	{
		pq = new MinPQ<Edge>();
		mst = new Queue<Edge>();
		marked = new boolean[G.V()];
		visit(G, 0);
		while (!pq.isEmpty() && mst.size() < G.V() - 1)	{
			Edge e = pq.delMin();
			int v = e.either(), w = e.other(v);
			if (marked[v] && marked[w]) continue;
			mst.enqueue(e);
			if (!marked[v]) 
				visit(G, v);
			if (!marked[w]) 
				visit(G, w);
		}
	}
	
	private void visit(EdgeWeightedGraph G, int v)	{
		marked[v] = true;
		for (Edge e : G.adj(v))
			if (!marked[e.other(v)])
				pq.insert(e);
	}
	
	public Iterable<Edge> mst()	{ 
		return mst; 
	}
}

class Queue<Item> implements Iterable<Item> {
    private Node<Item> first;    // beginning of queue
    private Node<Item> last;     // end of queue
    private int n;               // number of elements on queue

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    public Queue() {
        first = null;
        last  = null;
        n = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return first.item;
    }

    public void enqueue(Item item) {
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else           oldlast.next = last;
        n++;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) last = null;   // to avoid loitering
        return item;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    } 

    public Iterator<Item> iterator()  {
        return new ListIterator<Item>(first);  
    }

    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
}

class MinPQ<Key> implements Iterable<Key> {
    private Key[] pq;
    private int n;
    private Comparator<Key> comparator;

    public MinPQ(int initCapacity) {
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
    }

    public MinPQ() {
        this(1);
    }

    public MinPQ(int initCapacity, Comparator<Key> comparator) {
        this.comparator = comparator;
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
    }

    public MinPQ(Comparator<Key> comparator) {
        this(1, comparator);
    }

    public MinPQ(Key[] keys) {
        n = keys.length;
        pq = (Key[]) new Object[keys.length + 1];
        for (int i = 0; i < n; i++)
            pq[i+1] = keys[i];
        for (int k = n/2; k >= 1; k--)
            sink(k);
        assert isMinHeap();
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }

    private void resize(int capacity) {
        assert capacity > n;
        Key[] temp = (Key[]) new Object[capacity];
        for (int i = 1; i <= n; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    public void insert(Key x) {
        if (n == pq.length - 1) resize(2 * pq.length);
        pq[++n] = x;
        swim(n);
        assert isMinHeap();
    }

    public Key delMin() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        Key min = pq[1];
        exch(1, n--);
        sink(1);
        pq[n+1] = null;
        if ((n > 0) && (n == (pq.length - 1) / 4)) resize(pq.length / 2);
        assert isMinHeap();
        return min;
    }

    private void swim(int k) {
        while (k > 1 && greater(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && greater(j, j+1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private boolean greater(int i, int j) {
        if (comparator == null) {
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) > 0;
        }
        else {
            return comparator.compare(pq[i], pq[j]) > 0;
        }
    }

    private void exch(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    private boolean isMinHeap() {
        return isMinHeap(1);
    }

    private boolean isMinHeap(int k) {
        if (k > n) return true;
        int left = 2*k;
        int right = 2*k + 1;
        if (left  <= n && greater(k, left))  return false;
        if (right <= n && greater(k, right)) return false;
        return isMinHeap(left) && isMinHeap(right);
    }
    public Iterator<Key> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Key> {
        private MinPQ<Key> copy;
        public HeapIterator() {
            if (comparator == null) copy = new MinPQ<Key>(size());
            else                    copy = new MinPQ<Key>(size(), comparator);
            for (int i = 1; i <= n; i++)
                copy.insert(pq[i]);
        }

        public boolean hasNext()  { return !copy.isEmpty();                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Key next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.delMin();
        }
    }
}
