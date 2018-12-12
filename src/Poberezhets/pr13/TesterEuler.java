package Poberezhets.pr13;

import princetonlib.In;

/**
 * 1. �������� ����, �� ������ � ����� ��������� ������������ ������������
 * ���� �� �� ��� ������, �� ���������� ��������� ���� � ���� ���������
 * ��������, ���� ���� �������.
 * 
 * 4. �������� ����, �� ������ � ����� ��������� ������������ ����������
 * ���� �� �� ��� ������, �� ���������� ��������� ���� � ���� ���������
 * ��������, ���� ���� �������. ������: �������, �� ������ G ������
 * ��������� ���� ��� � ����� ��� ���� G � ��'����� � ����������� ������
 * ����� ������� ������� �� ����������� ������. ��� ������ ����� ��������� -
 * ��������� ���������� ���������� � ��������, �� ���� ����� �� ������
 * ���������� ����� ������ � ������������ �������.
 * 
 * @author �������
 *
 */

public class TesterEuler {

	public static void main(String[] args) {
	
		/**
		 * 
		 * �������� �� � ��������� ���� �� ������������� �� ������������� ������
		 */
		//Graph e = new Graph(new In("src/pr13/eulerNo.txt"));
		Digraph e = new Digraph(new In("src/pr13/directedEulerYes.txt"));
		EulerianCycle ec = new EulerianCycle(e);
		if (ec.hasEulerianCycle()) {
			System.out.println("Has Eulerian cycle: " + ec.cycle());
		} else {
			System.out.println("No path found");
		}

	}
	

}
