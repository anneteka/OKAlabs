
public class MinPQ<Key extends Comparable<Key>> {

	private Key[] pq;
	private int n;
	
	public MinPQ(){
		this(1);
	}
	
	
	public MinPQ(int capacity){
		pq = (Key[]) new Comparable[capacity+1];
	}
	
	private void resize(int capacity) {
	        Key[] temp = (Key[]) new Comparable[capacity];
	        for (int i = 1; i <= n; i++) {
	            temp[i] = pq[i];
	        }
	        pq = temp;
	    }

	
	public boolean isEmpty(){
		return n ==0;
	}
	
	public void insert(Key key){
		 if (n == pq.length - 1) resize(2 * pq.length);
		pq[++n] = key;
		swim(n);
	}
	
	public Key delMin(){
		Key min = pq[1];
		exch(1,n--);
		sink(1);
		pq[n+1]=null;
	 if ((n > 0) && (n == (pq.length - 1) / 4)) resize(pq.length / 2);
		return min;
	}
	
	private void swim(int k){
		while (k>1 && greater(k/2,k)){
			exch(k,k/2);
			k=k/2;
		}
	}
	
	private void sink(int k){
		while(2*k<=n){
			int j = 2*k;
			if (j<n&&greater(j,j+1)) j++;
			if (!greater(k,j)) break;
			exch(k,j);
			k=j;
		}
	}
	
	private boolean greater(int i, int j){
		return pq[i].compareTo(pq[j])>0;
	}
	
	private void exch(int i, int j){
		Key t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;
	}
	
}
