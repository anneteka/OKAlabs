import java.awt.Color;


public class SeamCarverGraph {
	private int count = 0;
	private Picture picture;
	private double[][] rgbColor;// return weigth matrix of our picture
	private Color[][] colorsPicture;// return rgb matrix of our picture
	String file;
	EdgeWeightedDigraph edg;
	// create a seam carver object based on the given picture
	public SeamCarverGraph(Picture p) {
		this.picture = p;
		makeRGBMatrix();
		makeEdgeDiGraph();
		removeVerticalSeam(findVerticalSeam());
		//makePicture();
	}
//make an energy interpretation of picture
	private double[][] makeRGBMatrix() {
		colorsPicture = new Color[height()][width()];
		for (int i = 0; i < picture.height(); i++)
			for (int j = 0; j < picture.width(); j++)
				colorsPicture[i][j] = picture.get(j, i);
		return makeWeightMatrix();
	}

	// √( Δ2x(x,y)+Δ2y(x,y)), Δ2x(x,y)=Rx(x,y)2+Gx(x,y)2+Bx(x,y
	public double[][] makeWeightMatrix() {
		rgbColor = new double[picture.height()][picture.width()];
		for (int i = 0; i < picture.height(); i++) {
			for (int j = 0; j < picture.width(); j++) {
				rgbColor[i][j] = energy(i, j);
			}
		}
		return rgbColor;

	}
	//make an EdgeGraph from picture
	private void makeEdgeDiGraph() {
		int V = width() * height() + 2;
		edg = new EdgeWeightedDigraph(V);
		for (int i = 0; i < height() - 1; i++) {
			for (int j = 0; j < width(); j++) {
				int v = i * width() + j + 1;
				int w = (i + 1) * width() + j + 1;

				DirectedEdge edge = new DirectedEdge(v, (i + 1) * width() + j + 1, rgbColor[i + 1][j]);
				edg.addEdge(edge);
				if (j != 0) {

					edge = new DirectedEdge(v, (i + 1) * width() + j, rgbColor[i + 1][j - 1]);
					edg.addEdge(edge);
				}
				if (j != width() - 1) {

					edge = new DirectedEdge(v, (i + 1) * width() + j + 2, rgbColor[i + 1][j + 1]);
					edg.addEdge(edge);

				}
			}
		}
		for (int i = 1; i <= width(); i++) {
			DirectedEdge e = new DirectedEdge(0, i, rgbColor[0][i - 1]);
			edg.addEdge(e);
		}
		for (int i = 1; i <= width(); i++) {
			DirectedEdge e = new DirectedEdge(width() * (height() - 1) + i, width() * height() + 1, 0);
			edg.addEdge(e);
		}

	}



	// width of current picture
	public int width() {
		return picture.width();

	}

	// height of current picture
	public int height() {
		return picture.height();

	}

	// fill an array with weight of each pixel
	public double energy(int i, int j) {
		if (i == 0 || i == (height() - 1) || j == 0 || j == (width() - 1))
			return 1000.00;
		int rX = colorsPicture[i + 1][j].getRed() - colorsPicture[i - 1][j].getRed();
		int gX = colorsPicture[i + 1][j].getGreen() - colorsPicture[i - 1][j].getGreen();
		int bX = colorsPicture[i + 1][j].getBlue() - colorsPicture[i - 1][j].getBlue();

		int rY = colorsPicture[i][j + 1].getRed() - colorsPicture[i][j - 1].getRed();
		int gY = colorsPicture[i][j + 1].getGreen() - colorsPicture[i][j - 1].getGreen();
		int bY = colorsPicture[i][j + 1].getBlue() - colorsPicture[i][j - 1].getBlue();
		double deltaColorX = (rX * rX + bX * bX + gX * gX);
		double deltaColorY = (rY * rY + bY * bY + gY * gY);
		double z = Math.sqrt(deltaColorX + deltaColorY);
		return z;

	}

	// retunrn indexes of least element
	public int[] findVerticalSeam() {
		int[] seam=new int[height()];
		AcyclicSP asp = new AcyclicSP(edg, 0);
		int i=height()-1;
		int counnt=0;
		for (DirectedEdge f : asp.pathTo(width() * height() + 1)) {
			if(counnt==0) {
				counnt++;
				continue;
			}
			seam[i]=f.to()%width();
			i--;
		}
		return seam;	
	}

	public EdgeWeightedDigraph getEdg() {
		return edg;
	}

	public void setEdg(EdgeWeightedDigraph edg) {
		this.edg = edg;
	}

	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {
		Color[][] newRGBMatrix = new Color[height()][width()-1];
		int indexWidth=0;
		for(int i=0; i<height();i++) {
			for(int j=0; j<width();j++) {
				if(j==seam[i])
					continue;
				newRGBMatrix[i][indexWidth]=colorsPicture[i][j];
				indexWidth++;
			}
			indexWidth=0;
			
		}
		colorsPicture=newRGBMatrix;
	}
	//make a new scaled picture
	public Picture makePicture() {
		picture=new Picture(width()-1, height());
		for(int i=0;i<height();i++) {
			for(int j=0;j<width()-1;j++) {
				picture.set(j, i, colorsPicture[i][j]);		
			}
		}
		
		//picture.show();
		return picture;
	}

	public static void main(String[] args) {
		Picture p=new Picture("./src/test.jpg");
		SeamCarverGraph c = new SeamCarverGraph(p);
		Picture pp=p;
		pp.show();
		for(int i=0; i<100;i++) {
			c=new SeamCarverGraph(pp);
			pp=c.makePicture();
			
		}
		pp.show();
	}
}