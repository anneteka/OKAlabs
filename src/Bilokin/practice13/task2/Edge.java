package Task2;

public class Edge {
	private Point first;
	private Point second;

	Edge(Point one, Point two) {
		this.first = one;
		this.second = two;
	}

	Point getOne() {
		return this.first;
	}

	Point getSecond() {
		return this.second;
	}
}