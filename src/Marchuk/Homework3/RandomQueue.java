package Homework3;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ua.princeton.lib.StdRandom;

public class RandomQueue<T> implements Iterable<T> {
	
	private int N;		
	private T[] arrayItems;
	
	public RandomQueue(){}
	
	public boolean isEmpty(){
		return N==0;
	}
	
	public int size() {
		return N;
	}
	
	public void enqueue(T item){
		if(N == arrayItems.length) {
			T[] temp = (T[])new Object[2*arrayItems.length];
        
			for (int i = 0; i < N; i++) 
			{
	            temp[i] = arrayItems[i];
	        }
			
	        arrayItems = temp;
		}
		
		arrayItems[N] = item;
		
		N++;
	}
	
	public T sample() {
		if(isEmpty())
			throw new RuntimeException("Stack underflow error");
		
		return arrayItems[StdRandom.uniform(N)];
	}
	
	public T dequeue(){
		if(isEmpty())
			throw new RuntimeException("Stack underflow error");
		
		int rand = StdRandom.uniform(N);
		T item = arrayItems[rand];
		arrayItems[rand] = arrayItems[N-1];
		arrayItems[N-1] = null;
		
		N--;
		
		if (N > 0 && N == arrayItems.length/4) { 
			T[] temp = (T[])new Object[N/2];
        
			for (int i = 0; i < N; i++) 
			{
	            temp[i] = arrayItems[i];
	        }
			
	        arrayItems = temp;
		}
		
		return item;
	}

	@Override
	public Iterator<T> iterator() {
		return new RandomQueueIterator();
	}
	
	private class RandomQueueIterator implements Iterator<T>{
		private int i;
		private T[] iteratorArray;
		
		public RandomQueueIterator()
		{
			iteratorArray = arrayItems.clone();
			i = N;
		}
		
		public boolean hasNext()
		{
			return i > 0;
		}
		
		public void remove() {
            throw new UnsupportedOperationException();
        }
		
		public T next()
		{
			if(!hasNext())
				throw new NoSuchElementException();
		
			int rand = StdRandom.uniform(i);
			T item = iteratorArray[rand];
			iteratorArray[rand] = iteratorArray[i-1];
			iteratorArray[i-1] = null;
			
			i--;
			
			return item;
		}
	}
}
