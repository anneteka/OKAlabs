import java.util.ArrayList;
import java.util.Collections;


public class Tester {

	
	public static void main(String[] args) {
		TV tv1 = new TV(16500, "Samsung");
		TV tv2 = new TV(15200, "Sony");
		TV tv3 = new TV(14200, "Panasonic");
		TV tv4 = new TV(14500, "Philips");
		TV tv5 = new TV(20100, "Samsung");
		
		System.out.println("Comparable:");
		
		if (tv2.compareTo(tv3) > 0) {
			System.out.println(tv2.getMark() + " is  more expensive.");
		} else {
			System.out.println(tv3.getMark() + " is more expensive.");
		}
		System.out.println("===========");
		System.out.println();
		System.out.println("Comparator sorting by price:");
		ArrayList<TV> as = new ArrayList<TV>();
		as.add(tv1);
		as.add(tv2);
		as.add(tv3);
		as.add(tv4);
		as.add(tv5);
		Collections.sort(as,  TV.Price);
		for (TV a : as) {
			System.out.println(a.getMark());
		}
		
		System.out.println("==========");
		System.out.println("Comparator sorting by mark:");
		Collections.sort(as,  TV.Name);
		for (TV a : as) {
			System.out.println(a.getMark());
		
	}
	}
}
