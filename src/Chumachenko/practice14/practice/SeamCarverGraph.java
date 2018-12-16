package practice;

import java.awt.Color;
import lection14.AcyclicSP;
import lection14.DirectedEdge;
import lection14.EdgeWeightedDigraph;
import prinston.Picture;

public class SeamCarverGraph {
	private EdgeWeightedDigraph graph;
	private Picture picture;
	private double[][] weightOfPixels;
	private Color[][] rgbOfPixels;
	int pW;
	int pH;

	/** create a seam carver object based on the given picture */
	public SeamCarverGraph(Picture picture, int n) {
		this.picture = picture;
		pW = picture.width();
		pH = picture.height();
		weightOfPixels = new double[pH][pW];
		graph = new EdgeWeightedDigraph(picture.width() * picture.height() + 2);
		convertToMagtrix();
		computingWeightOfPixel();
		createGraph();
		for (int i = 0; i < n; i++) {
			removeVerticalSeam(findVerticalSeam());
			pictureResult();
		}
	}

	/** Create graph */
	private void createGraph() {
		for (int i = 0; i < pH - 1; i++) {
			for (int j = 0; j < pW; j++) {
				DirectedEdge edge = new DirectedEdge(i * pW + j + 1, (i + 1) * pW + j + 1, weightOfPixels[i + 1][j]);
				graph.addEdge(edge);
				if (j != 0) {
					edge = new DirectedEdge(i * pW + j + 1, (i + 1) * pW + j, weightOfPixels[i + 1][j - 1]);
					graph.addEdge(edge);
				}
				if (j != pW - 1) {
					edge = new DirectedEdge(i * pW + j + 1, (i + 1) * pW + j + 2, weightOfPixels[i + 1][j + 1]);
					graph.addEdge(edge);
				}
			}
		}
		for (int j = 1; j <= pW; j++) {
			DirectedEdge edge0 = new DirectedEdge(0, j, 1000);
			DirectedEdge edge1 = new DirectedEdge(j + (pW * (pH - 1)), pW * pH + 1, 0);
			graph.addEdge(edge0);
			graph.addEdge(edge1);
		}

	}

	public EdgeWeightedDigraph getGraph() {
		return graph;
	}

	/** Computing weight of each pixel */
	private void computingWeightOfPixel() {
		weightOfPixels = new double[pH][pW];
		for (int i = 0; i < pH; i++) {
			for (int j = 0; j < pW; j++) {
				weightOfPixels[i][j] = energy(i, j);
			}
		}
	}

	/** Create picture in matrix form */
	private void convertToMagtrix() {
		rgbOfPixels = new Color[pH][pW];
		for (int i = 0; i < pH; i++)
			for (int j = 0; j < pW; j++) {
				rgbOfPixels[i][j] = picture.get(j, i);
			}
	}

	/** current picture */
	public Picture picture() {

		return picture;

	}

	public void pictureResult() {
		Picture result = new Picture(pW - 1, pH);
		for (int i = 0; i < pH; i++)
			for (int j = 0; j < pW - 1; j++)
				result.set(j, i, rgbOfPixels[i][j]);
		convertToMagtrix();
		computingWeightOfPixel();
		createGraph();
		picture = result;
		pH = picture.height();
		pW = picture.width();
	}

	public int width() {
		return 0;
	} // width of current picture

	public int height() {
		return 0;
	} // height of current picture

	public double energy(int x, int y) {
		if (x == 0 || x == picture.height() - 1 || y == 0 || y == picture.width() - 1)
			return 1000;
		double deltaX = Math.pow((rgbOfPixels[x - 1][y].getRed() - rgbOfPixels[x + 1][y].getRed()), 2)
				+ Math.pow((rgbOfPixels[x - 1][y].getBlue() - rgbOfPixels[x + 1][y].getBlue()), 2)
				+ Math.pow((rgbOfPixels[x - 1][y].getGreen() - rgbOfPixels[x + 1][y].getGreen()), 2);
		double deltaY = Math.pow((rgbOfPixels[x][y - 1].getRed() - rgbOfPixels[x][y + 1].getRed()), 2)
				+ Math.pow((rgbOfPixels[x][y - 1].getBlue() - rgbOfPixels[x][y + 1].getBlue()), 2)
				+ Math.pow((rgbOfPixels[x][y - 1].getGreen() - rgbOfPixels[x][y + 1].getGreen()), 2);
		return Math.sqrt(deltaY + deltaX);
	} // energy of pixel at column x and row y

	public int[] findVerticalSeam() {
		AcyclicSP asp = new AcyclicSP(getGraph(), 0);
		int[] path = new int[pH];
		int count = 0;
		for (DirectedEdge g : asp.pathTo(pW * pH + 1)) {
			if (count == 0) {
				count++;
				continue;
			}
			path[pH - count] = g.to() % pW;
			count++;
		}
		return path;
	} // sequence of indices for vertical seam

	public void removeVerticalSeam(int[] seam) {
		Color[][] newColor = new Color[pH][pW - 1];
		int widthIndex = 0;
		for (int i = 0; i < pH; i++) {
			for (int j = 0; j < pW; j++) {
				if (j == seam[i])
					continue;
				newColor[i][widthIndex] = rgbOfPixels[i][j];
				widthIndex++;
			}
			widthIndex = 0;
		}
		rgbOfPixels = newColor;
	}// remove vertical seam from current picture

	public static void main(String[] args) {
		Picture pic = new Picture("C:\\Users\\I work 10\\Downloads\\timon.jpg");
		pic.show();
		SeamCarverGraph sc = new SeamCarverGraph(pic, 10);
		System.out.println("created");
		sc.picture().show();
	}
}
