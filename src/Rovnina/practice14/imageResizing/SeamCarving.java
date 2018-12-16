package imageResizing;

import java.awt.Color;
import graphs.AcyclicSP;
import graphs.DirectedEdge;
import graphs.EdgeWeightedDigraph;
import prinston.Picture;

/**
 * @author Rovnina Tetiana IPZ, 5 group 
 *
 */
public class SeamCarving {
	private Picture picture;
	private static double[][] energyMatrix;
	private static Color[][] colorMatrix;
	private EdgeWeightedDigraph graph;

	public SeamCarving(Picture picture) {
		this.picture = picture;
		createColorMatrix();
		createEnergyMatrix();
		createGraph();

	}

	/**
	 * створити граф з картинки
	 */
	private void createGraph() {
		int size = width() * height() + 2;
		graph = new EdgeWeightedDigraph(size);

		for (int row = 0; row < height() - 1; row++) {
			for (int col = 0; col < width(); col++) {
				int v = getIndex(row, col);
				// 2 вершина
				int w = getIndex(row + 1, col);
				double weight = energyMatrix[row + 1][col];
				graph.addEdge(new DirectedEdge(v, w, weight));

				// 1 вершина
				if (col != 0) {
					w = getIndex(row + 1, col - 1);
					weight = energyMatrix[row + 1][col - 1];
					graph.addEdge(new DirectedEdge(v, w, weight));
				}

				// 3 вершина
				if (col != width() - 1) {
					w = getIndex(row + 1, col + 1);
					weight = energyMatrix[row + 1][col + 1];
					graph.addEdge(new DirectedEdge(v, w, weight));
				}
			}
		}
		// з'єдати з уявною верхньою коміркою
		int v = 0;
		int w = 0;
		int weight = 1000;
		for (int col = 0; col < width(); col++) {
			w = getIndex(0, col);
			graph.addEdge(new DirectedEdge(v, w, weight));
		}

		// з'єдати з уявною нижньою коміркою
		w = size - 1;
		weight = 0;
		for (int col = 0; col < width(); col++) {
			v = getIndex(height() - 1, col);
			graph.addEdge(new DirectedEdge(v, w, weight));
		}

	}

	/**
	 * @return індекс елемента у матриці
	 */
	private int getIndex(int row, int col) {
		return row * width() + col + 1;
	}

	/**
	 * @return картинка
	 */
	public Picture picture() {
		return picture;
	}

	/**
	 * @return ширина картинки
	 */
	public int width() {
		return picture.width();
	}

	/**
	 * @return висота картинки
	 */
	public int height() {
		return picture.height();
	}

	/**
	 * @return енергія пікселя в рядку x колонці y
	 */
	public double energy(int x, int y) {
		if (x == 0 || x == (height() - 1) || y == 0 || y == (width() - 1))
			return 1000;

		int redX = colorMatrix[x + 1][y].getRed() - colorMatrix[x - 1][y].getRed();
		int greenx = colorMatrix[x + 1][y].getGreen() - colorMatrix[x - 1][y].getGreen();
		int blueX = colorMatrix[x + 1][y].getBlue() - colorMatrix[x - 1][y].getBlue();
		double dx = redX * redX + greenx * greenx + blueX * blueX;

		int redY = colorMatrix[x][y + 1].getRed() - colorMatrix[x][y - 1].getRed();
		int greenY = colorMatrix[x][y + 1].getGreen() - colorMatrix[x][y - 1].getGreen();
		int blueY = colorMatrix[x][y + 1].getBlue() - colorMatrix[x][y - 1].getBlue();
		double dy = redY * redY + greenY * greenY + blueY * blueY;

		return Math.sqrt(dx + dy);
	}

	/**
	 * створюємо матрицю енергії пікселів
	 */
	public void createEnergyMatrix() {
		energyMatrix = new double[height()][width()];
		for (int row = 0; row < height(); row++)
			for (int col = 0; col < width(); col++)
				energyMatrix[row][col] = energy(row, col);
	}

	/**
	 * створюємо матрицю кольорів
	 */
	public void createColorMatrix() {
		colorMatrix = new Color[height()][width()];
		for (int row = 0; row < height(); row++)
			for (int col = 0; col < width(); col++)
				colorMatrix[row][col] = picture.get(col, row);
	}

	/**
	 * @return масив індексів які можна видалити
	 */
	private int[] findVerticalSeam() {
		AcyclicSP acyclic = new AcyclicSP(graph, 0);

		int[] path = new int[height()];
		int i = height();
		for (int p : acyclic.pathTo(graph.V() - 1)) {
			if (i != height())
				path[i] = getCol(p, i);
			i--;
		}

		return path;
	}
	
	
	/**
	 * @return номер колонки
	 */
	private int getCol(int indx, int row) {
		return indx - 1 - (row * width());
	}

	

	/**
	 * видаляє масив індексів з картинки
	 */
	private void removeVerticalSeam(int[] seam) {
		if (seam == null || seam.length == 0)
			return;

		Color[][] tempColor = new Color[height()][width() - 1];

		for (int row = 0; row < height(); row++) {
			for (int col = 0, i = 0; col < width(); col++) {
				if (col != seam[row]) {
					tempColor[row][i] = colorMatrix[row][col];
					i++;
				}
			}
		}
		colorMatrix = tempColor;
	}

	/**
	 * створює нову картинку, а для неї новий граф, та нову матрицю енергії
	 */
	private void createPicture(int w, int h) {
		Picture pic = new Picture(w, h);
		for (int row = 0; row < h; row++)
			for (int col = 0; col < w; col++)
				pic.set(col, row, colorMatrix[row][col]);

		picture = pic;
		createEnergyMatrix();
		createGraph();
	}

	
	/**
	 * @return ребра графа
	 */
	public String graphToString() {
		String str = "";
		for (int i = 0; i < graph.V(); i++) {
			for (DirectedEdge edg : graph.adj(i))
				str += edg.toString();

			str += "\n";
		}
		return str;
	}


	/**
	 * @return енергетичну матрицю
	 */
	public String energyToString() {
		String str = "";
		for (int i = 0; i < height(); i++) {
			for (int j = 0; j < width(); j++) {
				str += ((int) energyMatrix[i][j] + " ");
			}
			str += "\n";
		}
		return str;
	}

	/**
	 * @return матрицю кольорів
	 */
	public String pictureToString() {
		String str = "";
		for (int i = 0; i < height(); i++) {
			for (int j = 0; j < width(); j++) {
				str += (colorMatrix[i][j] + " ");
			}
			str += "\n";
		}
		return str;
	}

	/**
	 * обрізати картинку по ширині на w
	 */
	public void cutPicture(int w) {
		if (w == width())
			throw new IllegalArgumentException("Висота картинка не може бути меншена ніж 1 піксель");

		for (int i = 0; i < w; i++) {
			int[] seam = findVerticalSeam();
			removeVerticalSeam(seam);
			createPicture(width() - 1, height());
		}
	}

	public static void main(String[] args) {
		//SeamCarving sc = new SeamCarving(new Picture("img/12x10.png"));
		SeamCarving sc = new SeamCarving(new Picture("img/tom&jery.jpg"));
		// SeamCarving sc = new SeamCarving(new Picture("img/mcorp.jpg"));
		//SeamCarving sc = new SeamCarving(new Picture("img/timon.jpg"));
		sc.picture().show();
		sc.cutPicture(300);
		sc.picture().show();
	}

}