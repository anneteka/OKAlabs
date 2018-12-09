
public class RandomizedQueueTest {

	public static void main(String[] args) {
		
		RandomizedQueue<Integer> q = new RandomizedQueue<>();
		
		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(3);
		q.enqueue(4);
		q.enqueue(5);
		
		System.out.println("sample " + q.sample());
		
		for(int n : q) {
			System.out.println(n);
		}
		
		System.out.println("dequeued " + q.dequeue());
		System.out.println("dequeued " + q.dequeue());
		System.out.println("dequeued " + q.dequeue());
		
		for(int n : q) {
			System.out.println(n);
		}
		
		System.out.println("sample " + q.sample());
	}

}
