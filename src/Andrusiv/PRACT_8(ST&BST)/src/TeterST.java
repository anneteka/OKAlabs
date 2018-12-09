
import java.awt.RenderingHints.Key;
import java.util.Iterator;

import edu.princeton.cs.algs4.*;

public class TeterST {

	public static void main(String[] args) throws ClassNotFoundException {
	
		
		System.out.println("-------------ST-------------");
		ST<String, Integer> st = new ST<String, Integer>();
		st.put("y", 17);
		st.put("t", 16);
		st.put("n", 14);
		st.put("o", 15);
		st.put("d", 12);
		st.put("h", 13);
		st.put("b", 11);
		for (String s : st.keys())
			System.out.println(s + " " + st.get(s));
		System.out.println("delete max");
		st.deleteMax();
		Iterator it = (Iterator) st.keys();
		while (it.hasNext())
			System.out.print(" " + it.next() + " ");
		System.out.println();
		Iterator it2 = (Iterator) st.keys();
		while (it2.hasNext())
			System.out.print(st.get((String) it2.next()) + " ");

		System.out.println("\nmin:" + st.min() + " max:" + st.max());
		
		System.out.println("floor -> "+st.floor("a")+" |a| "+st.ceiling("a")+" <- ceiling");
		System.out.println("floor -> "+st.floor("o")+" |o| "+st.ceiling("o")+" <- ceiling");
	System.out.println("floor -> "+st.floor("z")+" |z| "+st.ceiling("z")+" <- ceiling");
	
	
	System.out.println("\n----------BST----------");
	BST<String, Integer> bst = new BST<String, Integer>();
	bst.put("X", 34);
	bst.put("T", 100);
	bst.put("B", 67);
	bst.put("D", 75);
	bst.put("E", 23);
	Queue q = (Queue) bst.iterator();
	do {
		System.out.print(q.dequeue() + " ");
	} while (!q.isEmpty());
	System.out.println("\nmin:" + bst.min() + "\nmax:" + bst.max());

	System.out.println("floor -> "+bst.floor("A")+" |A| "+bst.ceiling("A")+" <- ceiling");
	System.out.println("floor -> "+bst.floor("T")+" |T| "+bst.ceiling("T")+" <- ceiling");
System.out.println("floor -> "+bst.floor("Z")+" |Z| "+bst.ceiling("Z")+" <- ceiling");

	
	
	}

}
