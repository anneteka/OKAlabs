
public class Tester {

	public static void main(String[] args) {
		System.out.println("\n ST");
		ST<Integer, Integer> s= new ST<Integer, Integer>();
		s.put(5, 7);
		s.put(67, 77);
		s.put(1, 45);
		s.put(6778, 771);
		s.put(2, 55);
		s.put(22, 111);
		s.put(2677, 22);
		s.put(78, 78);
		s.put(627, 878);
		s.put(27, 878);
		s.put(8, 878);
		s.put(299, 878);
		System.out.println("All elements: ");
		for(int n: s.keys()) {
			System.out.println(" "+n+" --> "+s.get(n));
		}
		System.out.println("put (1,55) and delete(2)");
		s.put(1, 55);
		s.delete(2);
		System.out.println("All elements: ");
		for(int n: s.keys()) {
			System.out.println(" "+n+" --> "+s.get(n));
		}
		System.out.println("Key of min = " +s.min());
		System.out.println("Key of max = " +s.max());
		System.out.println("floor(23) = " +s.floor(23));
		System.out.println("celling(77) = " +s.celling(77));
		System.out.println("select(5) = " +s.select(5));
		System.out.println("Delete min");
		s.deleteMin();
		System.out.println("All elements: ");
		for(int n: s.keys()) {
			System.out.println(" "+n+" --> "+s.get(n));
		}
		System.out.println("Delete max");
		s.deleteMax();
		System.out.println("All elements: ");
		for(int n: s.keys()) {
			System.out.println(" "+n+" --> "+s.get(n));
		}
		System.out.println("size(21, 90) = "+s.size(21, 90));
		System.out.println("All elements from 21 to 90: ");
		for(int n: s.keys(21, 90)) {
			System.out.println(" "+n+" --> "+s.get(n));
		}
		System.out.println("\n BST");
		BST<Integer, Integer> bst= new BST<Integer, Integer>();
		bst.put(5, 7);
		bst.put(67, 77);
		bst.put(1, 45);
		bst.put(6778, 771);
		bst.put(2, 55);
		bst.put(22, 111);
		bst.put(2677, 22);
		bst.put(78, 78);
		bst.put(627, 878);
		bst.put(27, 878);
		bst.put(8, 878);
		bst.put(299, 878);
		System.out.println("All elements: ");
		for(int n: bst.keys()) {
			System.out.println(" "+n+" --> "+bst.get(n));
		}
		System.out.println("put (1,55) and delete(2)");
		bst.put(1, 55);
		bst.delete(2);
		System.out.println("All elements: ");
		for(int n: bst.keys()) {
			System.out.println(" "+n+" --> "+bst.get(n));
		}
		System.out.println("Key of min = " +bst.min());
		System.out.println("Key of max = " +bst.max());
		System.out.println("floor(23) = " +bst.floor(23));
		System.out.println("celling(77) = " +bst.celling(77));
		System.out.println("Delete min");
	}

}
