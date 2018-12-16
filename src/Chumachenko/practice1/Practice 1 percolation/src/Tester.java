
public class Tester {
 
	public static void main (String[] args) {
		Percolation n = new Percolation(3);
	    n.opening();
		
	    System.out.println();
	    System.out.println();
	    System.out.println(n.percolates());	
        System.out.println();
        System.out.println(n.getOpenedCount());
        System.out.println(n.system.length-2);

		System.out.println(n.percolates());

	}
}
