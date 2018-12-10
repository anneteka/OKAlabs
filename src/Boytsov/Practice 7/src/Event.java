class Event implements Comparable<Event> {
	double time; // time of event
	Particle a; // particles involved in event
	Particle b;
	private int countA, countB; // collision counts for a and b

	public Event(double t, Particle a, Particle b) {
		this.time = t;
		this.a = a;
		this.b = b;
		if (a != null)
			countA = a.count;
		else
			countA = -1;
		if (b != null)
			countB = b.count;
		else
			countB = -1;
	}

	public int compareTo(Event that) {
		if (this.time < that.time)
			return -1;
		if (this.time > that.time)
			return 1;
		return 0;
	}

	public boolean isValid() {
		if (a != null && a.count != countA)
			return false;
		if (b != null && b.count != countB)
			return false;
		return true;
	}
}
