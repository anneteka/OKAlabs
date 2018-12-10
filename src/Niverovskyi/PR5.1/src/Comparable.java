
public class Comparable {

	private final int numb1;
	private final int numb2;

	public Comparable(int numb1, int numb2) {
		this.numb1 = numb1;
		this.numb2 = numb2;
	}

	public int getNumb1() {
		return numb1;
	}

	@Override
	public String toString() {
		return numb1 + "  " + numb2;
	}

	public int compareTo(Comparable that) {
		if (this.getNumb1() > that.getNumb1())
			return -1;
		else if (this.getNumb1() < that.getNumb1())
			return 1;
		else
			return 0;
	}
}
