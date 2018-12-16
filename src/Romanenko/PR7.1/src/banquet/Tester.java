package banquet;

import java.util.ArrayList;
import java.util.Scanner;

public class Tester {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> list = new ArrayList<Integer>();
		String s[] = sc.nextLine().split(" ");
		for (int i = 0; i < s.length; i++) {
			if (Integer.valueOf(s[i]) < 1000 && Integer.valueOf(s[i]) >= 1)
				list.add(Integer.valueOf(s[i]));
		}
		Integer[] arr = new Integer[list.size()];
		arr = list.toArray(arr);
		Banquet r = new Banquet(arr);
		System.out.println(r.talk());
;
	}
	}


