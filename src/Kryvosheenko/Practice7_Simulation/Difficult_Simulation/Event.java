package Difficult_Simulation;

class Event implements Comparable<Event> {
	private double time; // time of event
	private Particle a, b; // particles involved in event
	private int countA, countB; // collision counts for a and b

	public Event(double t, Particle a, Particle b) {
		setTime(t);
		setA(a);
		setB(b);
		if (a == null)
			countA = -1;
		else
			countA = a.getCount();
		if (b == null)
			countB = -1;
		else
			countB = b.getCount();
	}

	public int compareTo(Event that) {
		return (int) (this.getTime() - that.getTime());
	}

	public boolean isValid() {
		if (a != null && a.getCount() != countA)
			return false;
		if (b != null && b.getCount() != countB)
			return false;
		return true;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public Particle getA() {
		return a;
	}

	public void setA(Particle a) {
		this.a = a;
	}

	public Particle getB() {
		return b;
	}

	public void setB(Particle b) {
		this.b = b;
	}

	public int getCountA() {
		return countA;
	}

	public void setCountA(int countA) {
		this.countA = countA;
	}

	public int getCountB() {
		return countB;
	}

	public void setCountB(int countB) {
		this.countB = countB;
	}
}
