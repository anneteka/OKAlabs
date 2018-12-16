package Practice7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Scanner;

public class DoctorWho {
	
	public static void main(String[] args) throws IOException {
		DoctorWho obj = new DoctorWho();
		obj.doTask();
	}

	private void doTask() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(br.ready()) {
		String[] tmp = br.readLine().split(" ");
		MaxPQ<Integer> guests = new MaxPQ<Integer>(tmp.length);
		for(int i=0; i<tmp.length; i++) {
			guests.insert(Integer.parseInt(tmp[i]));
		}
		countGuests(guests);
		}
	}
	
	private void countGuests(MaxPQ<Integer> guests) {
		while(!guests.isEmpty()) {
			int cur = guests.delMax();
			ArrayList<Integer> heap = new ArrayList<Integer>();
			while((cur--)>=1) {
				if(guests.isEmpty()) {
					System.out.println("fail");
					System.out.println("");
					return;
				}
				if(guests.max() != 1) heap.add(guests.max() - 1);
				guests.delMax();
			}
			int cnt = 0;
			while(!heap.isEmpty()) {			
				guests.insert(heap.get(cnt));
				heap.remove(heap.get(cnt));
			}
		}
			System.out.println("ok");
			System.out.println("");
	}
	
}
class MaxPQ<Key extends Comparable<Key>> {

	private Key[] pq;
	private int n;
	
	public MaxPQ(int capacity){
		pq = (Key[]) new Comparable[capacity+1];
	}
	
	public int size() {
		return n;
	}
	
    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }
	
	public boolean isEmpty(){
		return n ==0;
	}
	
	public void insert(Key key){
		pq[++n] = key;
		swim(n);
	}
	
	public Key delMax(){
		Key max = pq[1];
		exch(1,n--);
		sink(1);
		pq[n+1]=null;
		return max;
	}
	
	private void swim(int k){
		while (k>1 && less(k/2,k)){
			exch(k,k/2);
			k=k/2;
		}
	}
	
	private void sink(int k){
		while(2*k<=n){
			int j = 2*k;
			if (j<n&&less(j,j+1)) j++;
			if (!less(k,j)) break;
			exch(k,j);
			k=j;
		}
	}
	
	private boolean less(int i, int j){
		return pq[i].compareTo(pq[j])<0;
	}
	
	private void exch(int i, int j){
		Key t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;
	}
	
}
