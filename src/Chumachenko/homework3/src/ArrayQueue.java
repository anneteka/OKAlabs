import java.util.Iterator;

public class ArrayQueue <Item> implements Iterable<Item> {
       
	Item[] array;
	int head = 0;
	int tail = 0;
	int sizeOfQueue=0;
	int capacity=10;
	
	public ArrayQueue() {
	    array = (Item[]) new Object[capacity];	
	}
	
	public boolean isEmpty() {
		return sizeOfQueue==0;
	}
	
	public void enqueue(Item i) {
	
		
		array[tail++] = i;
		
		if(tail>=capacity) 
			if(head!=0) 
				tail = 0;
			else resize();
		
        if(!isEmpty()&& tail == head) resize();		
      
        sizeOfQueue++;
	}
	
	public Item dequeue() {
		Item item = array[head];
		array[head] = null;
		sizeOfQueue--;
		head++;
		if(sizeOfQueue<(capacity/4)) {
			resizeSmall();
		}
		return item;
	}
	
	
	
	private void resizeSmall() {
	
		Item[] newArray = (Item[]) new Object[capacity/2];
		if(tail>head) {
			System.arraycopy(array, head, newArray, 0, sizeOfQueue);
		}
		else {
			int j = 0;
		for(int i = head; i<capacity; i++) {
			newArray[j++] = array[i];
		}
		for(int i = 0; i<tail; i++) {
			newArray[j++] = array[i];
		
		}
		}
	}

	private void resize() {
	
	
		Item[] newArray = (Item[]) new Object[capacity*2];
		if(tail>head) {
		System.arraycopy(array, head, newArray, 0, tail);}
		else {
			int j = 0;
			for(int i = head; i<capacity; i++) {
				newArray[j++] = array[i];
			}
			for(int i = 0; i<tail; i++) {
				newArray[j++] = array[i];
			}
			head = 0;
			tail = j-1;
		}
		capacity*=2;
		array = newArray;
		
	}

	@Override
	public Iterator<Item> iterator() {
		return new ArrayQueueIterator();
	}

	private class ArrayQueueIterator implements Iterator<Item> {

		private int i= head;
		private int j = 0;
		@Override
		public boolean hasNext() {
			if(head<tail) return i<tail;
			if(head>=tail)return i>capacity||j<tail;
			return i>0;
		}

		@Override
		public Item next() {
			if(head < tail) 
				return array[i++];
			else 
				if(i>capacity)
					return array[i++];
				else 
					return array[j++];
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}
	
}
