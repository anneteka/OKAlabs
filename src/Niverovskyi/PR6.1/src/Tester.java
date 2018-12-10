import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Tester {

	public static void main(String[] args) {
		int col;
		int row;
		FileInputStream fi;
		Scanner s;
		String filename = "dnk.txt";
		try {
			fi = new FileInputStream(filename);
			s = new Scanner(fi);
			col = s.nextInt();
			row = s.nextInt();
			String[] str = s.nextLine().split(" ");
			if (col < 1 && row < 1) {
				s.close();
				fi.close();
				throw new IllegalArgumentException();
			}

			List list = new ArrayList();

			for (int i = 0; i < row; i++) {

				DNK dnk = new DNK(s.nextLine());
				list.add(dnk);
			}

			Collections.sort(list, new DNKComparator());

			print(list);

			fi.close();
			s.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void print(List list) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
	}

}
