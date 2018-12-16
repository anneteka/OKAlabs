package icecream;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Tester {
	public static void main(String[] args) throws FileNotFoundException {
		String filename = "ice-cream.txt";
		Scanner s = new Scanner(new FileInputStream(filename));
		int kiosks = s.nextInt();
		int vendors = s.nextInt();
		ArrayList<Integer> coordinates = new ArrayList<Integer>();

		for (int i = 0; i < kiosks; i++) {
			coordinates.add(s.nextInt());
		}

		ArrayList<Integer> distance = new ArrayList<Integer>();
		for (int i = 0; i < kiosks - 1; i++)
			distance.add(coordinates.get(i + 1) - coordinates.get(i));

		while (distance.size() > vendors - 1) {
			Collections.sort(distance);
			distance.set(1, distance.get(0) + distance.get(1));
			distance.remove(0);
		}
		Collections.sort(distance);

		System.out.println(distance.get(0));
	}
}
