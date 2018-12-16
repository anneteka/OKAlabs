
public class TesterEuler {

	public static void main(String[] args) {
		Digraph e = new Digraph(new In("src/pr13/directedEulerYes.txt"));
		EulerianCycle ec = new EulerianCycle(e);
		if (ec.hasEulerianCycle()) {
			System.out.println("Has Eulerian cycle: " + ec.cycle());
		} else {
			System.out.println("No path found");
		}

	}

}