package Homework3;

import java.util.Iterator;

public class ArrayQueue<T> implements Iterable<T> {
	    T[] data;
	    int first = -1;
	    int last = -1;
	    int size; 
	    public boolean isEmpty() {
	        return (first == -1 && last == -1);
	    }

	    public boolean isFull() {
	        return (last + 1)%size == first;
	    }
	    
	    private int size() {
	    	return size;
	    }

	    public void enqueue(T el) throws IllegalStateException {
	        if(isFull()) {
	            throw new IllegalStateException("Queue is full.");
	        }
	        if(isEmpty()){
	            first = last = 0;
	        } else {
	            last = (last + 1)%size;
	        }
	        data[last] = el;
	    }

	    public T dequeue() throws IllegalStateException {
	        T tmp;
	        if(isEmpty()) {
	            throw new IllegalStateException("Queue is full.");
	        }
	        if(first == last) {
	            tmp = data[first];
	            first = last = -1;
	        } else {
	            tmp = data[first];
	            first = (first +1)%size;
	        }
	        return tmp;
	    }
	    
		@Override
		public Iterator<T> iterator() {
			return new ArrayQueueIterator();
		}
		
		private class ArrayQueueIterator implements Iterator<T>{

			private int i=size;
			@Override
			public boolean hasNext() {
				return i>0;
			}

			@Override
			public T next() {
				return data[--i];
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
			
		}

}
