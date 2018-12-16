import java.awt.Color;

public class SCGraph {
	private int count = 0;
	private Picture picture;
	private double[][] colors;// return weigth matrix of our picture
	private Color[][] colorsPicture;// return rgb matrix of our picture
	String file;
	EDigraph edg;
	
	public SCGraph(Picture p) {
		this.picture = p;
		next();
	}
	public void next() {
		makeRGBMatrix();
		makeEdgeDiGraph();
		int[] x = findVerticalSeam();
		removeVerticalSeam(x);
	}
	private double[][] makeRGBMatrix() {
		colorsPicture = new Color[picture.height()][picture.width()];
		for (int i = 0; i < picture.height(); i++)
			for (int j = 0; j < picture.width(); j++)
				colorsPicture[i][j] = picture.get(j, i);
		
		colors = new double[picture.height()][picture.width()];
		for (int i = 0; i < picture.height(); i++) 
			for (int j = 0; j < picture.width(); j++) 
				colors[i][j] = findColor(i, j);
		return colors;
	}
	private void makeEdgeDiGraph() {
		edg = new EDigraph(picture.width() * picture.height() + 2);
		for (int i = 0; i < picture.height() - 1; i++) {
			for (int j = 0; j < picture.width(); j++) {
				int v = i * picture.width() + j + 1;
				int w = (i + 1) * picture.width() + j + 1;

				Edge edge = new Edge(v, (i + 1) * picture.width() + j + 1, colors[i + 1][j]);
				edg.addEdge(edge);
				if (j != 0) {

					edge = new Edge(v, (i + 1) * picture.width() + j, colors[i + 1][j - 1]);
					edg.addEdge(edge);
				}
				if (j != picture.width() - 1) {

					edge = new Edge(v, (i + 1) * picture.width() + j + 2, colors[i + 1][j + 1]);
					edg.addEdge(edge);

				}
			}
		}
		for (int i = 1; i <= picture.width(); i++) {
			Edge e = new Edge(0, i, colors[0][i - 1]);
			edg.addEdge(e);
		}
		for (int i = 1; i <= picture.width(); i++) {
			Edge e = new Edge(picture.width() * (picture.height() - 1) + i, picture.width() * picture.height() + 1, 0);
			edg.addEdge(e);
		}

	}

	public double findColor(int i, int j) {
		if (i == 0 || i == (picture.height() - 1) || j == 0 || j == (picture.width() - 1))
			return 1000.00;
		
		int rX = colorsPicture[i + 1][j].getRed() - colorsPicture[i - 1][j].getRed();
		int gX = colorsPicture[i + 1][j].getGreen() - colorsPicture[i - 1][j].getGreen();
		int bX = colorsPicture[i + 1][j].getBlue() - colorsPicture[i - 1][j].getBlue();

		int rY = colorsPicture[i][j + 1].getRed() - colorsPicture[i][j - 1].getRed();
		int gY = colorsPicture[i][j + 1].getGreen() - colorsPicture[i][j - 1].getGreen();
		int bY = colorsPicture[i][j + 1].getBlue() - colorsPicture[i][j - 1].getBlue();
		return Math.sqrt(rX * rX + bX * bX + gX * gX + rY * rY + bY * bY + gY * gY);
	}

	public int[] findVerticalSeam() {
		int[] seam=new int[picture.height()];
		Dist asp = new Dist(edg, 0);
		int i=picture.height()-1;
		int c=0;
		for (Edge f : asp.pathTo(picture.width() * picture.height() + 1)) {
			if(c==0) {
				c++;
				continue;}
			seam[i]=f.to()%picture.width();
			i--;
		}
		return seam;	
	}

	public void removeVerticalSeam(int[] seam) {
		Color[][] newRGBMatrix = new Color[picture.height()][picture.width()-1];
		int indexWidth=0;
		for(int i=0; i<picture.height();i++) {
			for(int j=0; j<picture.width();j++) {
				if(j==seam[i])
					continue;
				newRGBMatrix[i][indexWidth]=colorsPicture[i][j];
				indexWidth++;
			}
			indexWidth=0;
		}
		colorsPicture=newRGBMatrix;
	}
	public Picture createPicture() {
		picture=new Picture(picture.width()-1, picture.height());
		for(int i=0;i<picture.height();i++) 
			for(int j=0;j<picture.width()-1;j++) 
				picture.set(j, i, colorsPicture[i][j]);		
		return picture;
	}

}