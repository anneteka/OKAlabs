public class DequeTest {

	public static void main(String[] args) {
		
		Deque<Integer> d = new Deque<>();
		d.addLast(1);
		d.addFirst(2);
		d.addLast(3);
		d.addFirst(4);
		d.addLast(5);
		
		for(int n : d) {
			System.out.println(n);
		}
		
		System.out.println("removed " + d.removeFirst());
		System.out.println("removed " + d.removeLast());
		System.out.println("removed " + d.removeFirst());
		System.out.println("removed " + d.removeLast());
		System.out.println("removed " + d.removeFirst());
		
		for(int n : d) {
			System.out.println(n);
		}
	}
}
