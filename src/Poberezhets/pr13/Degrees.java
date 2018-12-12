package Poberezhets.pr13;

/**
 *  ����������� ������ (indegree) ������� � ������� - �� ������� ���������� �����, �� ��������� � ���� �������. 

 *  ����������� ������ ������� (outdegree) - �� ������� ���������� �����, �� �������� � ���� �������.
 *   ����� ������� ��������� � ������� � ������������ ������ 0 - ���� ������� ���������� ���.
 *    ������� � ������������ ������ 0 �� ������� � ����-��� ���� ������� - ���� ������� ���������� ��������. ������, � ����� �������� ����,
 *     � ����������� ������ ����� ������� ������� 1, ���������� ������������ - �������� � ������� ����� ����� �� 0 �� V-1 � �� � �������. 
 *     �������� ���� Degrees.java, �� ������ ��������� API:
 */
import java.util.Collection;
import java.util.Vector;

import princetonlib.In;

public class Degrees {
	Digraph G, R;

	Degrees(Digraph G) {
		this.G = G;
		R = new Digraph(G.V());
		// �������� ����
		for (int i = 0; i < R.V(); i++) {
			for (int v : G.adj(i))
				R.addEdge(v, i);
		}
	}

	/**
	 * @return ������� ����� ����������� � ������� v
	 */
	int indegree(int v) {
		Collection<Integer> c = (Collection<Integer>) R.adj(v);
		return c.size();

	}

	/**
	 * @return  ������� ����� ����������� � ������� v
	 */
	int outdegree(int v) {
		Collection<Integer> c = (Collection<Integer>) G.adj(v);
		return c.size();

	}

	/**
	 *�������, � ���� �������� �����, ��� � ��� �� ����� �����
	 * �������
	 * 
	 * @return �������
	 */
	Iterable<Integer> sources() {
		Vector<Integer> bag = new Vector<>();
		for (int i = 0; i < G.V(); i++) {
			if (indegree(i) == 0)
				bag.add(i);
		}
		return bag;

	}

	/**
	 *������� � ��� �� ����� ��������
	 * 
	 * @return �����
	 */
	Iterable<Integer> sinks() {
		Vector<Integer> bag = new Vector<>();
		for (int i = 0; i < G.V(); i++) {
			if (outdegree(i) == 0)
				bag.add(i);
		}
		return bag;

	}

	/**
	 *
	 * ���� �����, ��� ������� ����� �� ������ ���� ����
	 * 
	 * @return �� � G ������������
	 */
	boolean isMap() {
		for (int i = 0; i < G.V(); i++)
			for (int v : G.adj(i))
				if (outdegree(v) != 1)
					return false;

		return true;
	}

	// ������������ ����
	public Digraph getR() {
		return R;
	}
}
