public class UnorderedArrayMinPQ<Key extends Comparable<Key>> {
	
	private Key[] pq;
	private int n;
	
	public UnorderedArrayMinPQ(){
		this(1);
	}
	
	public UnorderedArrayMinPQ(int capacity){
		pq = (Key[]) new Comparable[capacity];
	}
	
	public boolean isEmpty(){
		return n==0;
	}
	
	public void insert(Key x){
		if (n==pq.length) resize(2*pq.length);
		pq[n++] = x;
	}
	
	public Key delMin(){
		int min = 0;
		for (int i = 1; i<n;i++)
			if (less(i,min)) min = i;
		exch(min,n-1);
		Key item = pq[--n];
		pq[n]=null;
		if (n>0&&n==pq.length/4) resize(pq.length/2);
		return item;
	}
	
	private void exch(int i, int j){
		Key swap = pq[i];
		pq[i] = pq[j];
		pq[j] = swap;
	}

	private static boolean less(Comparable v, Comparable w){
		return v.compareTo(w)<0;
	}
	
	private void resize(int capacity){
		Key[] copy = (Key[])new Comparable[capacity];
		for (int i=0;i<n;i++)
			copy[i]=pq[i];
		pq = copy;
	}
	
}