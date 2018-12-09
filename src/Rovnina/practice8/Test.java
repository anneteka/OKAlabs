package practice8;

import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		// new Test().runST();
		new Test().runBST();

	}

	/**
	 * тестування класу BST
	 */
	private void runBST() {
		BST<String, Integer> bst = new BST<String, Integer>();
		bst.put("africa", 0);
		bst.put("icecream", 8);
		bst.put("qwerty", 5);
		bst.put("rabbit", 11);
		bst.put("africa", 5);
		bst.put("elephant", 3);
		bst.put("dino", 10);
		bst.put("apple", 9);
		System.out.println(bst.toString());
		System.out.println("tree size = " + bst.size() + "\n");

		// повернути елементи за ключами
		System.out.println("dino - " + bst.get("dino"));
		System.out.println("student - " + bst.get("student") + "\n");

		// видалити елементи
		bst.delete("dino");
		bst.delete("qwerty");
		bst.delete("icecream");
		System.out.println(bst.toString());
		System.out.println("tree size = " + bst.size() + "\n");

		// найбільший ключ менший або рівний
		System.out.println("floor(apple) - " + bst.floor("apple"));
		System.out.println("floor(pizza) - " + bst.floor("pizza") + "\n");
		// найменший ключ більший або рівний
		System.out.println("ceiling(elephant) - " + bst.ceiling("elephant"));
		System.out.println("ceiling(pizza) - " + bst.ceiling("pizza") + "\n");

		// найбльший і найменший ключ
		System.out.println("min - " + bst.min());
		System.out.println("max - " + bst.max() + "\n");

	}

	/**
	 * тестування класу ST
	 */
	public void runST() {
		ST<String, Integer> st = new ST<String, Integer>();
		st.put("africa", 0);
		st.put("icecream", 8);
		st.put("qwerty", 5);
		st.put("rabbit", 11);
		st.put("africa", 5);
		st.put("elephant", 3);
		st.put("dino", 10);
		st.put("apple", 9);
		System.out.println(Arrays.toString(st.getMap()));
		System.out.println("size = " + st.size() + "\n");

		// повернути елементи за ключами
		System.out.println("dino - " + st.get("dino"));
		System.out.println("student - " + st.get("student") + "\n");

		// видалити елементи
		st.delete("dino");
		st.deleteMin();
		st.deleteMax();
		System.out.println(Arrays.toString(st.getMap()));
		System.out.println("size = " + st.size() + "\n");

		// найбільший ключ менший або рівний
		System.out.println("floor(apple) - " + st.floor("apple"));
		System.out.println("floor(pizza) - " + st.floor("pizza") + "\n");
		// найменший ключ більший або рівний
		System.out.println("ceiling(elephant) - " + st.ceiling("elephant"));
		System.out.println("ceiling(pizza) - " + st.ceiling("pizza") + "\n");

		// найбльший і найменший ключ
		System.out.println("min - " + st.min());
		System.out.println("max - " + st.max() + "\n");

		// ключ за індексом
		System.out.println("2 - " + st.select(2));
		System.out.println("10 - " + st.select(10));
	}

}
