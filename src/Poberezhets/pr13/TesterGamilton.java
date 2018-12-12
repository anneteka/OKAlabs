package Poberezhets.pr13;


import java.util.Stack;

import princetonlib.In;
import princetonlib.StdOut;

/**
 * �������� ��������� ���� ���� ���� ��� ���������� ������
 * �������� ������������� ���� ���� ����
 * �������� ����, �� ������ � ����� ��������� ������������ ���������� ����
 * �� �� ��� ������, �� ���������� ��������� ���� � ���� ��������� ��������,
 * ���� ���� �������
 */
public class TesterGamilton {


	//�������� ������������ ���� ����� �
	public void checkHamiltonCycle1(Digraph g) {
	    
	    Stack<Integer> cycleH = new Stack<Integer>();// hamiltonian cycle
	    
	    DepthFirstOrder dfp = new DepthFirstOrder(g);

	    Stack<Integer> q = (Stack<Integer>) dfp.reversePost();
	    
	    for (int i = 0; i < q.size() - 1; i++) {
	      // DepthFirstPaths dpp = new DepthFirstPaths(g, q.get(i + 1));
	      // System.out.println(q.get(i+1) + " " + q.get(i) + " " + g.oneEdje(q.get(i+1), q.get(i)));
	      if (!g.oneEdje(q.get(i+1), q.get(i))) {
	        System.out.println("No gamiltom cycle");
	        return;
	      }
	      cycleH.add(q.get(i));
	    }
	    if (!g.oneEdje(q.get(0), q.get(q.size() - 1))) {
	      System.out.println("No gamilton path");
	      return;
	    }
	    System.out.println("Gamilton cycle is :");
	    
	    for (Integer i : q) {
	      System.out.print(i + " ");
	    }
	  }

	public static void main(String[] args) {
		/**
		 * �������� �� � ������������ ����.
		 */
		Graph g = new Graph(new In("src/pr13/UndirectedHamiltonYes.txt"));
		//Graph g = new Graph(new In("src/pr13/UndirectedHamNo.txt"));
		HamiltonianCycle hm = new HamiltonianCycle(g);

		if (hm.hasHamiltonCycle(0)) {
			System.out.println("Your path is"+hm.cycle());
		} else {
			System.out.println("No hamilton cycle.");
		}
//		Digraph dg=new Digraph(new In("src/pr13/DirectedHamiltonNo.txt"));
		Digraph dg=new Digraph(new In("src/pr13/DirectedHamYes.txt"));
		new TesterGamilton().checkHamiltonCycle1(dg);
	}

}

