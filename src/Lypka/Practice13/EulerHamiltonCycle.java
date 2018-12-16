public class EulerHamiltonCycle {
	Graph grph;

	public EulerHamiltonCycle(Graph graph) {// first
		grph = graph;
	}

	public EulerHamiltonCycle(Digraph digraph) {// fourth
		grph = digraph.DigraphToGraph();
	}

	public EulerCycle getEuler() {
		EulerCycle euler = new EulerCycle(grph);
		if (euler.hasEulerianCycle())
			return euler;
		else
			return null;
	}

	public Cycle getHamilton() {
		Cycle hamilton = new Cycle(grph);
		if (hamilton.hasCycle())
			return hamilton;
		else
			return null;
	}
}