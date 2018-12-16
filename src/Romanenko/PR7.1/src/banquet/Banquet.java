package banquet;

import java.util.Arrays;
import java.util.Collections;

public class Banquet {
	private Integer[] guests;

	public Banquet(Integer[] guests) {
		this.guests = guests;
	}

	public String talk() {
		Arrays.sort(guests);
	Arrays.sort(guests, Collections.reverseOrder());

		while (guests[0] != 0) {
			for (int i = 1; i < guests[0] + 1; i++) {
				guests[i]--;
			}
			guests[0] = 0;
			Arrays.sort(guests, Collections.reverseOrder());
		}

		if (check(guests))
			return "ok";
		else
			return "fail";
	}

	public static boolean check(Integer[] guests) {
		for (Integer i : guests) {
			if (i != 0)
				return false;
		}
		return true;
	}
}
