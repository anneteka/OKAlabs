import java.util.Comparator;

public class TV implements Comparable<TV> {

	private int price;
	private String mark;

	public TV(int price, String mark) {
		this.price = price;
		this.mark = mark;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	@Override
	public int compareTo(TV tv) {
		if (this.getPrice() > tv.getPrice())
			return 1;
		else if (this.getPrice() < tv.getPrice())
			return -1;
		else
			return 0;
	}

public 	static Comparator<TV> Price = new Comparator<TV>() {

		@Override
		public int compare(TV tv1, TV tv2) {
			int tv1Price = tv1.getPrice();
			int tv2Price = tv2.getPrice();

			if (tv1Price > tv2Price) {
				return 1;
			} else if (tv1Price < tv2Price) {
				return -1;
			} else {
				return 0;
			}

		}

	};

public static Comparator<TV> Name = new Comparator<TV>(){
	@Override
    public int compare(TV tv1, TV tv2) {
        return tv1.getMark().compareTo(tv2.getMark());
    }
	
};
}
