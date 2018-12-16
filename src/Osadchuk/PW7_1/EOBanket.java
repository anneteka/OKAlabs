package PW7_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class EOBanket {

	public static void main(String[] args) throws IOException {
		
//		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
//		if 1 element => true
		
		while(sc.hasNextLine()){
			boolean bl = true;
			
			String str = sc.nextLine();
			String[] strg = str.split(" ");
			
			MaxPQ<Short> guests = new MaxPQ<Short>(strg.length);
			for (short i = 0; i < strg.length; ++i)
				guests.insert(Short.parseShort(strg[i]));
			
			for (short i=0; i<strg.length-1; ++i){
				short len = guests.delMax();
				
				short[] arr = new short[len];
				for(short j=0; j<len; ++j)
					if(!guests.isEmty())
						arr[j] = (short) (guests.delMax()-1);
					else
						bl = false;
				
				boolean bbl = false;
				for(short j=0; j<len; ++j)
					if(arr[j]!=0){
						guests.insert(arr[j]);
						bbl = true;
					}
				if(!bbl && guests.isEmty())
					break;
			}
			
			if(bl)
				pw.println("ok\n");
			else
				pw.println("fail\n");
		}
		
		pw.flush();
	}
}

class MaxPQ<Key extends Comparable<Key>> {

	private Key[] pq;
	private int n;
	
	public MaxPQ(int capacity){
		pq = (Key[]) new Comparable[capacity+1];
	}
	
	public boolean isEmty(){
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