import java.util.ArrayList;

public class ST <Key extends Comparable<Key>, Value>{
	
	
	
	private class Node<Key, Value>{
		private Key key;
		private Value val;

		public Node(Key key, Value val){
			this.key = key;
			this.val = val;
		}
		
	}
	private Node[] arr;
	private int size=0;
    ST(){	
    	arr= new Node[10];
    }
    
    private void resize(int capacity){
		Node[] copy = new Node[capacity];
		for (int i=0;i<size;i++)
			copy[i]=arr[i];
		arr = copy;
    }
    //перезаписує старе значення новим
    void put(Key key, Value val) {
    	if(val!=null && key!=null) {
    	if(get(key)==null) {
    		arr[size]=new Node<Key, Value>(key, val);
    		size++;
    		for( int i=size-1; i>0; i--) {
    			if(((Comparable<Key>) arr[i].key).compareTo((Key) arr[i-1].key)<0) {
    				Node n=arr[i];
    				arr[i]=arr[i-1];
    				arr[i-1]=n;
    			} else break;
    		}
    		if (size==arr.length) resize(2*arr.length);
    	} else {
    		getNode(key).val=val;
    	}
    	}
    	
    }
    //повертає null якщо ключ відсутній
    Value get(Key key) {
    	int index=getIndex(key);
    	if(index==-1) return null;
    	else return (Value) arr[index].val;
    	
    }
    
    Node getNode(Key key) {
    	int index=getIndex(key);
    	if(index==-1) return null;
    	else return arr[index];
    }
    
    void delete(Key key) {
    	boolean go=false;
    	for( int i=0; i<size; i++) {
    		if(arr[i].key==key && !go) {
    			arr[i]=null;
    			go=true;
    		} else if(go) {
    			arr[i-1]=arr[i];
    		}
    	}
    	size--;
    	if (size>0&&size==arr.length/4) resize(arr.length/2);
    }
    
    boolean contains(Key key) {
    	return get(key) != null;
    }
    
    boolean isEmpty() {
		return size==0;
    	
    }
    
    int size() {
		return size;
    	
    }
	//найменший ключ
    Key min() {
		return (Key) arr[0].key;
    	
    }
	//найбільший ключ
    Key max() {
		return (Key) arr[size-1].key;
    	
    }
	//найбільший ключ менший або рівний key
    Key floor(Key key) {
    	int index=rank(key);
    	if(index==-1) return null;
    	else return (Key) arr[index-1].key;
    	
    }
	// найменший ключ більший або рівний key
    Key celling(Key key) {
    	int index=rank(key);
    	if(index==-1) return null;
    	else if(arr[index].key==key) return (Key) arr[index].key;
    	else return (Key) arr[index].key;
    	
    }
	//кількість ключів менших за key
    int rank(Key key) {
    	int lo = 0, hi = size-1;
    	while (lo <= hi){
	    	int mid = lo + (hi - lo) / 2;
	    	int cmp = key.compareTo((Key) arr[mid].key);
	    	if (cmp < 0) hi = mid - 1;
	    	else if (cmp > 0) lo = mid + 1;
	    	else if (cmp == 0) return mid;
    	}
    	return lo;
    }
	//key k
    Key select(int k) {
		if(k<size && k>=0) return (Key) arr[k].key ;
		else return null;
    	
    }
    void deleteMin() {
    	for( int i=1; i<size; i++) {
    	 arr[i-1]=arr[i];
    	}
    	size--;
    	if (size>0&&size==arr.length/4) resize(arr.length/2);
    }
    void deleteMax() {
    	if(size>0) {
    	arr[size-1]=null;
    	size--;
    	if (size>0&&size==arr.length/4) resize(arr.length/2);
    	}
    }
	// кількість ключів в [lo..hi]
    int size(Key lo, Key hi) {
		return rank(hi)-rank(lo);
    	
    }
	 //повертає ітератор по ключам
    Iterable<Key> keys(){
    	ArrayList<Key> k= new ArrayList<Key>(size);
    	for(int i=0; i<size; i++) {
    		k.add((Key) arr[i].key);
    	}
		return k;
    	
    }
    
    int getIndex(Key key) {
    	int lo = 0, hi = size-1;
    	while (lo <= hi){
	    	int mid = lo + (hi - lo) / 2;
	    	int cmp = key.compareTo((Key) arr[mid].key);
	    	if (cmp < 0) hi = mid - 1;
	    	else if (cmp > 0) lo = mid + 1;
	    	else if (cmp == 0) return mid;
    	}
    	return -1;
    }
    
    Iterable<Key> keys(Key lo, Key hi){
    	int iLo=getIndex(celling(lo));
    	int iHi=getIndex(floor(hi));
    	if(iLo==-1 || iHi==-1) return null;
    	ArrayList<Key> k= new ArrayList<Key>(iHi-iLo+1);
    	for(int i=iLo; i<iHi+1; i++) {
    		k.add((Key) arr[i].key);
    	}
		return k;
    }
}
