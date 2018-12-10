import ua.princeton.lib.In;


public class Tester {

	
		public static void main(String[] args) {
			In in = new In("brush.txt");
			Comparable[] numbers = new Comparable[in.readInt()];

			int number1, number2, robots = 0;
			while (!in.isEmpty()) {
				number1 = in.readInt();
				number2 = in.readInt();
				numbers[robots++] = new Comparable(number1, number2);
			}
			MergeSort ms = new MergeSort();
			ms.sort(numbers);
			System.out.println("Sorted:");
			for(Comparable t: numbers)
				System.out.println(t.toString());
			
	}

}
