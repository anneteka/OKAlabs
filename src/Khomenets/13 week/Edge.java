public class Edge {
	private Point one;
	private Point two;

	Edge(Point one, Point two) {
		this.one = one;
		this.two = two;
	}

	Point getOne() {
		return this.one;
	}

	Point getTwo() {
		return this.two;
	}
}