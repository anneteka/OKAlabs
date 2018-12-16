import java.awt.Color;

public class SeamCarver {
	private int count = 0;
	private Picture picture;
	private double[][] rgbColor;// return weigth matrix of our picture
	private Color[][] colorsPicture;// return rgb matrix of our picture
	String file;

	// create a seam carver object based on the given picture
	public SeamCarver(String str) {
		this.file = str;
		this.picture = picture();
		System.out.println(picture.width());
		System.out.println(picture.height());
		makeRGBMatrix();

	}

	private double[][] makeRGBMatrix() {
		colorsPicture = new Color[picture.width()][picture.height()];
		for (int i = 0; i < picture.width(); i++)
			for (int j = 0; j < picture.height(); j++)
				colorsPicture[i][j] = picture.get(i, j);
		return makeWeightMatrix();
	}

	// √( Δ2x(x,y)+Δ2y(x,y)), Δ2x(x,y)=Rx(x,y)2+Gx(x,y)2+Bx(x,y
	public double[][] makeWeightMatrix() {
		rgbColor = new double[picture.width()][picture.height()];
		for (int i = 0; i < picture.width(); i++) {
			for (int j = 0; j < picture.height(); j++) {
				rgbColor[i][j] = energy(i, j);
			}
		}
		return rgbColor;

	}

	// current picture
	public Picture picture() {
		return new Picture(file);

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
		if (i == 0 || i == picture.width() - 1 || j == 0 || j == picture.height() - 1)
			return 1000.00;
		int rX = colorsPicture[i - 1][j].getRed() - colorsPicture[i + 1][j].getRed();
		int gX = colorsPicture[i - 1][j].getGreen() - colorsPicture[i + 1][j].getGreen();
		int bX = colorsPicture[i - 1][j].getBlue() - colorsPicture[i + 1][j].getBlue();
		int rY = colorsPicture[i][j - 1].getRed() - colorsPicture[i][j + 1].getRed();
		int gY = colorsPicture[i][j - 1].getGreen() - colorsPicture[i][j + 1].getGreen();
		int bY = colorsPicture[i][j - 1].getBlue() - colorsPicture[i][j + 1].getBlue();
		double deltaColorX = (rX * rX + bX * bX + gX * gX);
		double deltaColorY = (rY * rY + bY * bY + gY * gY);
		double z = Math.sqrt(deltaColorX + deltaColorY);
		return z;

	}

	// retunrn indexes of least element
	public int[] findVerticalSeam() {
		int v = 0;
		double minMin = Double.MAX_VALUE;
		int[] minWeg = new int[picture.height()];
		for (int i = 1; i < picture.width() - 1; i++) {
			// v=i;
			if (count == 6) {
				count++;
			}
			count++;
			int[] temp = new int[picture.height()];
			temp[0] = i;
			double sumWeight = 0;
			for (int j = 0; j < picture.height(); j++) {
				double min = Math.min(rgbColor[i][j], (Math.min(rgbColor[i + 1][j], rgbColor[i - 1][j])));
				if (min == rgbColor[i + 1][j])
					v++;
				if (min == rgbColor[i - 1][j])
					v--;
				if (min == rgbColor[i][j])
					v = i;
				temp[j] = v;
				sumWeight += min;
			}
			if (sumWeight <= minMin) {
				minMin = sumWeight;
				minWeg = temp;
			}
			v = i;

		}
		for (int i = 0; i < minWeg.length; i++)
			System.out.print(minWeg[i] + " ");
		return minWeg;

	}

	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {
	}

	public static void main(String[] args) {
		SeamCarver c = new SeamCarver("./src/test.jpg");
		for (int i = 0; i < c.width(); i++) {
			for (int j = 0; j < c.height(); j++) {
				System.out.print(c.rgbColor[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("------------");
		c.findVerticalSeam();

	}
}