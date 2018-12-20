package Stakhurskyi.Pr11;

import java.io.File;

import java.io.IOException;

import java.io.PrintWriter;

import java.util.Scanner;





public class Tester {



	public static void main(String[] args) throws IOException {

		new Tester().readFile();

	}



	/**

	 * ���������� � ����� �� ����������� �����

	 * 

	 * @throws IOException

	 */

	private void readFile() throws IOException {

		Scanner sc = new Scanner(new File("input1.txt"));

		PrintWriter pw = new PrintWriter(new File("result.txt"));

		int s = sc.nextInt();

		int v = sc.nextInt();

		int edg = sc.nextInt();



		Graph graph = new Graph(v);



		for (int i = 0; i < edg; i++) {

			int v1 = sc.nextInt();

			int v2 = sc.nextInt();

			graph.addEdge(v1, v2);

		}



		DepthFirstPaths dfp = new DepthFirstPaths(graph, s);

		pw.println("DepthFirstPaths: ");

		if (dfp.hasPathTo(0)) {

			int numberV = 0;

			pw.print("����: ");

			for (int step : dfp.pathTo(0)) {

				pw.print(step + " ");

				numberV++;

			}

			pw.println();

			pw.println("ʳ������ ��������� ������: " + numberV);

			int len = numberV-1;

			pw.println("������� �����: " + len);

		} else

			pw.println("����� ����");

		pw.println();



		BreadthFirstPaths bfp = new BreadthFirstPaths(graph, s);

		pw.println("BreadthFirstPaths: ");

		if (bfp.hasPathTo(0)) {

			int numberV = 0;

			pw.print("����: ");

			for (int step : bfp.pathTo(0)) {

				pw.print(step + " ");

				numberV++;

			}

			pw.println();

			pw.println("ʳ������ ��������� ������: " + numberV);

			int len = numberV-1;

			pw.println("������� �����: " + len);

		} else

			pw.println("����� ����");

		pw.close();

	}



}