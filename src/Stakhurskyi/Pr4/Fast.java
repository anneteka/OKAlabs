package Stakhurskyi.Pr4;



import java.io.BufferedReader;

import java.io.File;

import java.io.FileReader;

import java.io.IOException;

import java.util.Arrays;

import java.util.StringTokenizer;



import princetonlib.StdDraw;

public class Fast {



	private Point[] points;

	private BufferedReader reader;

	private int n;

	private StringTokenizer token;

	private String res = "";

	private double[] pointsSlope;

	private Point[] pointsCopy;

	private Point item;



	/**

	 * �����������

	 * 

	 * @param file ����, ��� ���������� �����

	 * @throws IOException

	 */

	public Fast(File file) throws IOException {

		reader = new BufferedReader(new FileReader(file));

		String str = reader.readLine();

		token = new StringTokenizer(str);

		n = Integer.parseInt(token.nextToken());

		points = new Point[n];

		StdDraw.setXscale(0, 32768);

		StdDraw.setYscale(0, 32768);

		fillArray();

	}



	/**

	 * ���������� ����� � �����

	 * 

	 * @throws IOException

	 */

	private void fillArray() throws IOException {

		for (int i = 0; i < points.length; i++) {

			if (!token.hasMoreTokens()) {

				String str = reader.readLine();

				token = new StringTokenizer(str);

			}

			points[i] = new Point(Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken()));

			points[i].draw();

		}

	}



	/**

	 * ����� ����� ������� � ������ �� ���� ���䳺��

	 */

	public void findLine() {

		for (int i = points.length - 1; i > 2; i--) {

			pointsSlope = new double[i];

			pointsCopy = Arrays.copyOf(points, i);

			item = points[i];

			for (int j = i - 1; j >= 0; j--) {

				pointsSlope[j] = item.slopeTo(points[j]);

			}

			sortPoints(pointsSlope, pointsCopy);

			findEqualSlone();

		}

	}



	/**

	 * ��������� � ������������� ����� ������� ���䳺��� ������� ��������

	 */

	private void findEqualSlone() {

		int i = 0; // ��� �� �����

		while (i < pointsSlope.length - 2) {

			if (pointsSlope[i] == pointsSlope[i + 2])

				sortFourPoint(i, i + 1, i + 2);

			i++;

		}

	}



	/**

	 * �������� 4 ����� �� 1 ��

	 * 

	 * @param linePoints

	 */

	private void printAndDraw(Point[] linePoints) {

		for (int l = 0; l < linePoints.length - 1; l++) {

			res += linePoints[l] + " -> ";

			linePoints[0].drawTo(linePoints[l + 1]);

		}

		res += linePoints[linePoints.length - 1] + "\n";

	}



	/**

	 * ����� ������ ����� �� ����� �� �����

	 * 

	 * @param i

	 * @param j

	 * @param k

	 */

	private void sortFourPoint(int i, int j, int k) {

		Point[] fourPoints = { pointsCopy[i], pointsCopy[j], pointsCopy[k], item };

		int n = fourPoints.length;

		for (int m = 0; m < n; m++) {

			for (int l = m; l > 0; l--)

				if (fourPoints[l].compareTo(fourPoints[l - 1]) == -1) {

					Point temp = fourPoints[l];

					fourPoints[l] = fourPoints[l - 1];

					fourPoints[l - 1] = temp;

				} else

					break;

		}

		printAndDraw(fourPoints);

	}



	/**

	 * ����� �� ���䳺����

	 * 

	 * @param pointsSlope

	 * @param pointsCopy

	 */

	private void sortPoints(double[] pointsSlope, Point[] pointsCopy) {

		int h = 1;

		while (h < pointsSlope.length / 3)

			h = 3 * h + 1;



		while (h >= 1) {

			for (int i = h; i < pointsSlope.length; i++) {

				for (int j = i; j >= h; j -= h)

					if (pointsSlope[j] < pointsSlope[j - h]) {

						double k = pointsSlope[j];

						pointsSlope[j] = pointsSlope[j - h];

						pointsSlope[j - h] = k;



						Point pointTep = pointsCopy[j];

						pointsCopy[j] = pointsCopy[j - h];

						pointsCopy[j - h] = pointTep;

					} else

						break;

			}

			h = h / 3;

		}

	}



	// ����� �������

	public String toString() {

		return res;

	}



}