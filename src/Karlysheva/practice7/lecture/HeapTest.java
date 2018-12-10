package practice7.lecture;

public class HeapTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] mas = {"s","o","r","t","e","x","a","m","p","l","e"};
		Heap.sort(mas);
		for (String s:mas)
			System.out.print(s+" ");
	}

}
