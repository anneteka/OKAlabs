
public class MinPQ<Key extends Comparable<Key>> {

	private Key[] pq;
	int n;
	
	public MinPQ(int capacity){
		pq = (Key[]) new Comparable[capacity+1];
		
	}
	
	public boolean isEmpty(){
		return n ==0;
	}
	
	public void insert(Key key){
		if(n==pq.length-1)resize(2*pq.length);
		pq[++n] = key;
		swim(n);
		
	}
	private void resize(int capacity){
		Key[] copy = (Key[])new Comparable[capacity];
		for (int i=0;i<=n;i++)
			copy[i]=pq[i];
		pq = copy;
	}
	

	public Key delMin(){
		Key min = pq[1];
		exch(1,n--);
		sink(1);
		pq[n+1]=null;
		//if (n>0&&n==pq.length/4) resize(pq.length/2);
		return min;
	}
	
	private void swim(int k){
		while (k>1 && bigger(k/2,k)){
			exch(k,k/2);
			k=k/2;
		}
	}
	
	private void sink(int k){
		while(2*k<=n){
			int j = 2*k;
			if (j<n&&bigger(j,j+1)) j++;
			if (!bigger(k,j)) break;
			exch(k,j);
			k=j;
		}
	}
	
	private boolean bigger(int i, int j){
		return pq[i].compareTo(pq[j])>0;
	}
	
	private void exch(int i, int j){
		Key t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;
	}
	
}

