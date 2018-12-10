package library;
import java.util.Arrays;
import java.util.Iterator;

/**
 * клас-тестувальник
 * 
 * @author Пивовар Олена
 *
 */
public class Tester {

	public static void main(String[] args) {
		ArrayQueue<Integer> aq = new ArrayQueue<>();
		for (int i = 0; i < 10; i++) {
			aq.enqueue(i);
		}
		for (int i = 0; i < 9; i++) {
			aq.dequeue();
		}
		for (int i = 0; i < 15; i++) {
			aq.enqueue(i);
		}
		
		Iterator<Integer> itAq = aq.iterator();
		while(itAq.hasNext()) {
			System.out.print(itAq.next() + " ");
		}
		System.out.println();
		System.out.println("aq.size() = " + aq.size());
		
		Bag<Integer> b = new Bag<>();
		for (int i = 0; i < 20; i++) {
			b.add(i*2);
		}
		Iterator<Integer> itB = b.iterator();
		while(itB.hasNext()) {
			System.out.print(itB.next() + " ");
		}
		System.out.println();
		System.out.println("b.size() = " + b.size());
		
		LinkedStack<Integer> ls = new LinkedStack<>();
		for (int i = 0; i < 10; i++) {
			ls.push(i);
		}
		Iterator<Integer> itLs = ls.iterator();
		while(itLs.hasNext()) {
			System.out.print(itLs.next() + " ");
		}
	}
}
