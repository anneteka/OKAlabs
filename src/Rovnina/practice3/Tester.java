import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * тестер класів Deque та RandomizedQueue
 * 
 * @author Rovnina Tetiana
 *
 */
public class Tester {

	public static void main(String[] args) {
		Tester t = new Tester();
		t.testDeque();
		System.out.println();
		t.testQueue();
	}

	/**
	 * метод, який тестує роботу черги
	 */
	private void testQueue() {
		RandomizedQueue<Integer> rq = new RandomizedQueue<>();
		System.out.println("1. Спроба очистити пусту чергу:");

		try {
			rq.dequeue();
		} catch (NoSuchElementException e) {
			System.out.println("Черга пуста");
		}

		System.out.println("\n2. Спроба додати пустий елемент у чергу:");
		try {
			rq.enqueue(null);
		} catch (Exception e) {
			System.out.println("Не можна додати пустий елемент");
		}

		System.out.println("\n3. Додаємо елементи у чергу:");
		for (int i = 0; i < 10; i++)
			rq.enqueue(i);

		for (Integer i : rq)// роздрукувати чергу
			System.out.println(i);

		System.out.println("\n4. Перевірка роботи ітератора, видалити 4 елемент:");
		Iterator<Integer> itr = rq.iterator();
		while (itr.hasNext()) {
			Integer x = (Integer) itr.next();
			if (x == 4)
				itr.remove();
		}
		for (Integer i : rq)// роздрукувати чергу
			System.out.println(i);

		System.out.println("\n5. Видалити існуючі елементи:");
		System.out.println("Видалити: " + rq.dequeue());
		System.out.println("Видалити: " + rq.dequeue());
		System.out.println("Видалити: " + rq.dequeue());
		for (Integer i : rq)// роздрукувати чергу
			System.out.println(i);
		
		System.out.println("\n6. Повернути випадковий елемент:");
		System.out.println("item = " + rq.sample());
	}

	/**
	 * метод, який тестує роботу деки
	 */
	private void testDeque() {
		Deque<Integer> d = new Deque<>();

		System.out.println("1. Спроба очистити пусту деку:");

		try {
			d.removeFirst();
			d.removeLast();
		} catch (NoSuchElementException e) {
			System.out.println("Дека пуста");
		}

		System.out.println("\n2. Спроба додати пустий елемент у деку:");
		d.addFirst(null);
		d.addLast(null);

		System.out.println("\n3. Додаємо елементи у деку:");
		for (int i = 0; i < 10; i++) {
			if (i % 2 == 0)
				d.addFirst(i);
			else
				d.addLast(i);
		}
		for (Integer i : d) // роздрукувати деку
			System.out.println(i);

		System.out.println("\n4. Перевірка роботи ітератора, видалити 0:");
		Iterator<Integer> itr = d.iterator();
		while (itr.hasNext()) {
			Integer x = (Integer) itr.next();
			if (x == 0)
				itr.remove();
		}
		for (Integer i : d) // роздрукувати деку
			System.out.println(i);

		System.out.println("\n5. Видалити існуючі елементи:");
		try {
			System.out.println("видалити останній: " + d.removeLast());
			System.out.println("видалити перший: " + d.removeFirst());
		} catch (NoSuchElementException e) {
			System.out.println("Дека пуста");
		}
		for (Integer i : d)// роздрукувати деку
			System.out.println(i);
	}

}
