
public class Tester {

	public static void main(String[] args) {
		System.out.println("Bag");
		Bag<Integer> bag = new Bag<Integer>();
		bag.add(3); bag.add(4); bag.add(null); bag.add(5);
		for(Integer d: bag) {
			System.out.print(d+" ");
		}
		System.out.println();
		System.out.println("End of Bag.");
		
		System.out.println();
		System.out.println("ArrayQueue");
		ArrayQueue<Integer> arrayQuene = new ArrayQueue<>();
		arrayQuene.enqueue(1); arrayQuene.enqueue(2);
		arrayQuene.enqueue(3); arrayQuene.enqueue(4);
		arrayQuene.enqueue(5);
		arrayQuene.enqueue(6); arrayQuene.enqueue(7);
		arrayQuene.enqueue(8); arrayQuene.enqueue(9);
		arrayQuene.enqueue(10);
		
		
		
		for(Integer d: arrayQuene) {
			System.out.print(d+" ");
		}
		System.out.println();
		
		arrayQuene.dequeue();arrayQuene.dequeue();arrayQuene.dequeue();arrayQuene.dequeue();arrayQuene.dequeue();
		
		for(Integer d: arrayQuene) {
			System.out.print(d+" ");
		}
		System.out.println();
		
		arrayQuene.enqueue(11); arrayQuene.enqueue(12);
		
		for(Integer d: arrayQuene) {
			System.out.print(d+" ");
		}
		System.out.println();
		
		arrayQuene.dequeue();arrayQuene.dequeue();arrayQuene.dequeue();arrayQuene.dequeue();
	
		for(Integer d: arrayQuene) {
			System.out.print(d+" ");
		}
		System.out.println();
			
	
		System.out.println("End of ArrayQueue.");
		System.out.println("Linked Queue");
		LinkedQueue<Integer> linkedQueue = new LinkedQueue<>();
		linkedQueue.enqueue(5);
		linkedQueue.enqueue(15);
		linkedQueue.enqueue(25);
		linkedQueue.enqueue(35);
		for(Integer d: linkedQueue) {
			System.out.print(d+" ");
		}
		System.out.println();
		
		linkedQueue.dequeue();
		linkedQueue.dequeue();
	  
		for(Integer d: linkedQueue) {
			System.out.print(d+" ");
		}
		System.out.println();
		System.out.println("End of LinkedQueue");
		
		
		System.out.println("ArrayStack");
		ArrayStack<Integer> arrayStack = new ArrayStack<Integer>();
		arrayStack.push(4);
		arrayStack.push(45);
		arrayStack.push(46);
		arrayStack.push(47);
	
		
		for(Integer d: arrayStack) {
			System.out.print(d+" ");
		}
		System.out.println();
		arrayStack.pop();
		for(Integer d: arrayStack) {
			System.out.print(d+" ");
		}
		System.out.println();
        System.out.println("End of ArrayStack");
        System.out.println("LinkedStack");
        LinkedStack<Integer> link = new LinkedStack<>();
        link.push(6);
        link.push(7);
        link.push(8);
        
        for(Integer d: link) {
        	System.out.print(d+" ");
		}
		System.out.println();
        link.pop();
        
        for(Integer d: link) {
        	System.out.print(d+" ");
		}
		System.out.println();
	}
}
