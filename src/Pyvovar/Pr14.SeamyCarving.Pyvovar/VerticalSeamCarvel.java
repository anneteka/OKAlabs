package SeamyCarving;

import java.awt.Color;
import java.util.Stack;

import princetonlib.Picture;

/**
 * Стиснення картинки по вертикалі
 * 
 * @author Пивовар Олена, 4 група, ІПЗ
 *
 */
public class VerticalSeamCarvel {

	private Picture picture; // поточна картинка
	private Color[][] matrixPicture; // масив з rgb кожного пікселя
	private double[][] weightMatrix; // вага кожного пікселя
	private int sizeDigraph; // розмір графа

	private Color[][] resMatrixPicture; // матриця кольорів після стиснення
	private EdgeWeightedDigraph digraph; // граф, створений з картики
	private int verticalCarvel[]; // масив з елементів, які є швом для стиснення
	private Picture pectureRes; // картинка, яка утворилася в результаті

	/**
	 * @param picture картинка для стиснення
	 * @param n       кількість пікселів по вертикалі, яку необхідно вирізати
	 */
	public VerticalSeamCarvel(Picture picture, int n) {
		this.picture = picture;
		makeCarvel(n);
	}

	/**
	 * @return зважений орієнтований граф, у який ми перетворюємо картинку
	 */
	public EdgeWeightedDigraph digraph() {
		return digraph;
	}

	/**
	 * @return розмір графа
	 */
	public int getSizeDigraph() {
		return sizeDigraph;
	}

	/**
	 * перетворення картинки на матрицю кольорів
	 */
	private void makeRGBmatrix() {
		matrixPicture = new Color[height()][width()];
		for (int i = 0; i < height(); i++) {
			for (int j = 0; j < width(); j++) {
				matrixPicture[i][j] = picture.get(j, i);
			}
		}
	}

	/**
	 * створення зваженої матриці
	 */
	private void makeWeightMatrix() {
		weightMatrix = new double[height()][width()];
		for (int i = 0; i < height(); i++) {
			for (int j = 0; j < width(); j++) {
				weightMatrix[i][j] = energy(i, j);
				// System.out.print(weightMatrix[i][j] + " ");
			}
			// System.out.println();
		}
	}

	/**
	 * @return поточна картинка
	 */
	public Picture picture() {
		return picture;
	}

	/**
	 * @return висота поточної картинки
	 */
	public int height() {
		return picture.height();
	}

	/**
	 * @return ширина поточної картинки
	 */
	public int width() {
		return picture.width();
	}

	/**
	 * обрахування енерхії заданого пікселя
	 * 
	 * @param x
	 * @param y
	 * @return енергія пікселя
	 */
	public double energy(int x, int y) {
		if (x == 0 || y == 0 || x == height() - 1 || y == width() - 1)
			return 1000;
		double dxR = matrixPicture[x - 1][y].getRed() - matrixPicture[x + 1][y].getRed();
		double dxG = matrixPicture[x - 1][y].getGreen() - matrixPicture[x + 1][y].getGreen();
		double dxB = matrixPicture[x - 1][y].getBlue() - matrixPicture[x + 1][y].getBlue();
		double resX = Math.pow(dxR, 2) + Math.pow(dxG, 2) + Math.pow(dxB, 2);

		double dyR = matrixPicture[x][y - 1].getRed() - matrixPicture[x][y + 1].getRed();
		double dyG = matrixPicture[x][y - 1].getGreen() - matrixPicture[x][y + 1].getGreen();
		double dyB = matrixPicture[x][y - 1].getBlue() - matrixPicture[x][y + 1].getBlue();
		double resY = Math.pow(dyR, 2) + Math.pow(dyG, 2) + Math.pow(dyB, 2);

		return Math.sqrt(resX + resY);
	}

	/**
	 * створення графа з картинки
	 */
	private void makeGraph() {
		sizeDigraph = width() * height() + 2;
		digraph = new EdgeWeightedDigraph(sizeDigraph);

		for (int i = 1; i <= width(); i++) {
			DirectedEdge edge = new DirectedEdge(0, i, weightMatrix[0][i - 1]);
			digraph.addEdge(edge);
		}

		for (int i = 0; i < height() - 1; i++) {
			for (int j = 0; j < width(); j++) {
				int v = i * width() + j + 1;
				int w = v + width();
				DirectedEdge edge = new DirectedEdge(v, w, weightMatrix[i + 1][j]);
				digraph.addEdge(edge);

				if (j != 0) {
					edge = new DirectedEdge(v, w - 1, weightMatrix[i + 1][j - 1]);
					digraph.addEdge(edge);
				}

				if (j != width() - 1) {
					edge = new DirectedEdge(v, w + 1, weightMatrix[i + 1][j + 1]);
					digraph.addEdge(edge);
				}
			}
		}

		for (int i = 1; i <= width(); i++) {
			int n = width() * (height() - 1) + i;
			DirectedEdge edge = new DirectedEdge(n, sizeDigraph - 1, 0);
			digraph.addEdge(edge);
		}
	}

	/**
	 * виведення графа картинки в консоль
	 */
	public void printGraph() {
		for (int i = 0; i < getSizeDigraph(); i++) {
			for (DirectedEdge edg : digraph.adj(i)) {
				// System.out.println(edg.toString());
			}
		}
	}

	/**
	 * знаходження вертикального шву
	 * 
	 * @return вертикальний шов
	 */
	public int[] findVerticalSeam() {
		AcyclicSP acyclic = new AcyclicSP(digraph, 0);
		verticalCarvel = new int[height()];
		// System.out.println(acyclic.pathTo(getSizeDigraph() - 1));
		Stack<DirectedEdge> edges = (Stack<DirectedEdge>) acyclic.pathTo(getSizeDigraph() - 1);
		int j = 0;
		for (int i = edges.size() - 1; i > 0; i--) {
			int n = edges.get(i).to();
			n %= width();
			verticalCarvel[j++] = n - 1;
		}
//		for (int i = 0; i < verticalCarvel.length; i++) {
//			System.out.print(verticalCarvel[i] + " ");
//		}
		return verticalCarvel;
	}

	/**
	 * видалення вертикального шву із картинки
	 */
	public void removeVerticalSeam() {
		resMatrixPicture = new Color[height()][width() - 1];
		for (int i = 0; i < height(); i++) {
			int n = 0;
			for (int j = 0; j < width() - 1; j++) {
				if (j == verticalCarvel[i])
					n++;
				resMatrixPicture[i][j] = matrixPicture[i][j + n];
			}
		}

		picture = makeNewImage();
	}

	/**
	 * виведення матриці кольорів в консоль
	 */
	private void printColorMatrix() {
		System.out.println();
		for (int i = 0; i < height(); i++) {
			for (int j = 0; j < width(); j++) {
				System.out.print(matrixPicture[i][j] + "   ");
			}
			System.out.println();
		}

		System.out.println();
		for (int i = 0; i < height(); i++) {
			for (int j = 0; j < width() - 1; j++) {
				System.out.print(resMatrixPicture[i][j] + "   ");
			}
			System.out.println();
		}
	}

	/**
	 * @return нова обрізана картинка
	 */
	public Picture makeNewImage() {
		pectureRes = new Picture(width() - 1, height());
		for (int i = 0; i < height(); i++) {
			for (int j = 0; j < width() - 1; j++) {
				pectureRes.set(j, i, resMatrixPicture[i][j]);
			}
		}
		// System.out.println("picture made");
		// pictureCarvel.save("src/images/3x4Carvel.png");
		// pectureRes.show();
		return pectureRes;
	}

	/**
	 * стиснення картинки
	 * 
	 * @param n кількість пікселів, на яку потрібно стиснути картинку по вертикалі
	 */
	private void makeCarvel(int n) {
		if (n >= width()) {
			System.err.println("n must be less than picture width");
			return;
		}
		picture.show();
		for (int i = 0; i < n; i++) {
			makeRGBmatrix();
			makeWeightMatrix();
			makeGraph();
			findVerticalSeam();
			removeVerticalSeam();
		}
		pectureRes.show();
	}

	public static void main(String[] args) {
		Picture picture = new Picture("src/images/picture1.jpg");
		VerticalSeamCarvel seamCarver = new VerticalSeamCarvel(picture, 100);
	}

}
