import java.util.ArrayList;
import java.util.Arrays;

public class DrawClient {

	public static int NUMBER_OF_SETS_PER_TEST = 5;
	static ArrayList<Point2D> generatedPoints = new ArrayList<>();
	static ShortestPoints shortestPointDistance;

	public static void generateRandomPoints(int numberOfPoints) {

		for (int x = 0; x < numberOfPoints; x++) {
			generatedPoints.add(new Point2D(StdRandom.uniform(0.0, 1.0), StdRandom.uniform(0, 1.0)));
		}
	}

	public static void drawLines() {
		for (Point2D pt : generatedPoints) {
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.setPenRadius(0.02);
			pt.draw();

		}
	}

	public static ShortestPoints findShortestDistance() {
		double shortestDistance = Double.MAX_VALUE;
		ShortestPoints shortestPoints = null;
		for (int x = 0; x < generatedPoints.size(); x++) {
			for (int y = 0; y < generatedPoints.size(); y++) {
				if (x == y) {
					continue;
				}
				double distanceBetweenToPoints = generatedPoints.get(x).distanceTo(generatedPoints.get(y));
		
				if (distanceBetweenToPoints<shortestDistance) {
					shortestDistance = distanceBetweenToPoints;
					shortestPoints = new ShortestPoints(generatedPoints.get(x), generatedPoints.get(y));
					//
				}
			}
		}
		// draw
		System.out.println("Shortest = " + shortestPoints.getX().toString());
		// shortestPoints.draw();
		return shortestPoints;
	}

	public static void main(String[] args) {
		int x0 = Integer.parseInt("1");
		int y0 = Integer.parseInt("1");
		int n = Integer.parseInt("10");

		generateRandomPoints(15);
		drawLines();
		shortestPointDistance = findShortestDistance();
		StdDraw.setPenRadius(0.01);
		// StdDraw.point(0.1, 0.2);

		// StdDraw.circle(5, 5, 5);
		StdDraw.line(shortestPointDistance.getX().x(), shortestPointDistance.getX().y(),
				shortestPointDistance.getY().x(), shortestPointDistance.getY().y());
	}

}