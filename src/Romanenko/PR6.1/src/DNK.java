import java.util.*;

class DNK {
	private String str = null;
	private int sortNum = 0;

	public DNK(String input) {
		str = input;
		int num = 0;
		for (int i = 0; i < str.length() - 1; i++) {
			for (int j = i + 1; j < str.length(); j++)
				if (str.charAt(i) > str.charAt(j))
					num++;
		}
		sortNum = num;
	}

	public int getSortNum() {
		return sortNum;
	}

	public String toString() {
		return str;
	}
}

class DNKComparator implements Comparator {
	public int compare(Object o1, Object o2) {
		DNK d1 = (DNK) o1;
		DNK d2 = (DNK) o2;

		if (d1.getSortNum() > d2.getSortNum())
			return 1;
		else if (d1.getSortNum() == d2.getSortNum())
			return 0;
		else
			return -1;
	}
}