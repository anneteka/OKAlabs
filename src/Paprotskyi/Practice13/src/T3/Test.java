package Task3;
import java.util.Stack;

import Helping.Digraph;
import lib.In;

public class Test {

	private static String testFile = "Test01.txt";

	public static void main(String[] args) {
		In in = new In(testFile);
		Digraph DG = new Digraph(in);
		Degrees d = new Degrees(DG);
		System.out.println("outdegree " + d.outdegree(2));
		System.out.println("indegree " + d.indegree(2));
		Stack<Integer> s = (Stack<Integer>) d.sources();
		System.out.println("sources: ");
		while (s.iterator().hasNext()) {
			System.out.print(s.pop() + " ");
		}
		System.out.println("\nsinks: ");
		Stack<Integer> sinks = (Stack<Integer>) d.sinks();
		while (sinks.iterator().hasNext()) {
			System.out.print(sinks.pop() + " ");
		}
		System.out.println("\nis Map? : "+d.isMap());
	}
}
