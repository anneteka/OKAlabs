package Practice7;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Equeue {

	public static void main(String[] args) {
		Equeue obj = new Equeue();
		obj.doTask();
	}

	private void doTask() {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextInt()) {
		int n = sc.nextInt();
		int que = sc.nextInt();
		long tmpmax = 0;
		PriorityQueue<Long> desk = new PriorityQueue<Long>();
		for(int i=0; i<que; i++) {
			if(i==n) break;
			long elem = sc.nextLong();
			desk.add(elem);
		}
		long tp;
		if(que<n) {
			int curr = que;
			while(curr!=n) {
				long tmp = desk.poll();
				tmp += sc.nextLong();
				desk.add(tmp);
				curr++;
			}
			while(desk.size()>1) tp = desk.poll();
			System.out.println(desk.poll());
		} else {
			while(desk.size()>1) tp = desk.poll();
			System.out.println(desk.poll());
		}
}
	}
}