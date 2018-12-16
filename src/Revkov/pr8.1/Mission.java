package wormix;


public class Mission implements  Comparable<Mission>{
	private int points;
	private int time;
	
	public Mission(int points, int time){
		this.points = points;
		this.time = time;
	}

	public int getPoints() {
		return points;
	}

	
	public int getTime() {
		return time;
	}

	@Override
	public String toString() {
		return "" + time;
	}

	public int compareTo(Mission that) {
		if (this.getTime() > that.getTime())
			return -1;
		else if (this.getTime() < that.getTime())
			return 1;
		else
			return 0;
	}

}
