package week14.practice;

import java.awt.*;
import edu.princeton.cs.algs4.Picture;

public class SeamCarver {
    public int[][] colorArray;
    public final double D_INFINITY = Double.POSITIVE_INFINITY;

    public static void main(String[] args) {
        Picture myPicture = new Picture("src/week14/practice/test2.png");
        SeamCarver seamC = new SeamCarver(myPicture);
        int[] vSeam = seamC.findVerticalSeam();
        StringBuilder stB = new StringBuilder();
        for (int i : vSeam) {
            stB.append(i).append(" ");
        }
        System.out.println(stB);
        System.out.println("Column: " + myPicture.width()+'\n'+ "Row:" + myPicture.height());
    }


    public SeamCarver(Picture picture) {
        int w = picture.width();
        int h = picture.height();
        colorArray = new int[w][h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                colorArray[i][j] = picture.get(i, j).getRGB();
            }
        }
    }

    public int width() {
        return this.colorArray.length;
    }

    public int height() {
        return this.colorArray[0].length;
    }

    public Picture picture() {
        Picture picture = new Picture(colorArray.length, colorArray[0].length);
        for (int i = 0; i < colorArray.length; i++) {
            for (int j = 0; j < colorArray[0].length; j++) {
                Color color = new Color(this.colorArray[i][j]);
                picture.set(i, j, color);
            }
        }
        return picture;
    }

    private int redColor(int rgb) {
        return (rgb >> 16) & 0xFF;
    }

    private int greenColor(int rgb) {
        return (rgb >> 8) & 0xFF;
    }

    private int blueColor(int rgb) {
        return (rgb >> 0) & 0xFF;
    }


    public double energy(int x, int y) {
        if (x < 0 || x > this.width() - 1 || y < 0 || y > this.height() - 1) {
            throw new IndexOutOfBoundsException();
        }
        if (x == 0 || x == this.width() - 1 || y == 0 || y == this.height() - 1) {
            return 1000.0;
        } else {
            int RedColorX = redColor(colorArray[x - 1][y]) - redColor(colorArray[x + 1][y]);
            int RedColorY = redColor(colorArray[x][y - 1]) - redColor(colorArray[x][y + 1]);

            int GreenColorX = greenColor(colorArray[x - 1][y]) - greenColor(colorArray[x + 1][y]);
            int GreenColorY = greenColor(colorArray[x][y - 1]) - greenColor(colorArray[x][y + 1]);

            int BlueColorX = blueColor(colorArray[x - 1][y]) - blueColor(colorArray[x + 1][y]);
            int BlueColorY = blueColor(colorArray[x][y - 1]) - blueColor(colorArray[x][y + 1]);
            double energyRes = Math.sqrt(Math.pow(RedColorX, 2) + Math.pow(BlueColorX, 2) + Math.pow(GreenColorX, 2)
                    + Math.pow(RedColorY, 2) + Math.pow(BlueColorY, 2) + Math.pow(GreenColorY, 2));
            return energyRes;
        }

    }
//Горизонтальний шов на зображенні - це шлях пікселів,з'єднаних зліва на право з одним пікселем в кожному рядку.
//знаходимо горизонтальний шов з мінімальною сумарною енергією
    public int[] findHorizontalSeam() {
        this.colorArray = transpose(this.colorArray);
        int[] seam = findVerticalSeam();
        this.colorArray = transpose(this.colorArray);
        return seam;
    }
////Вертикальний шов на зображенні - это шлях пікселів,з'єднаних зверху до низу з одним пікселем в кожному рядку.
    public int[] findVerticalSeam() {
        int n = this.width() * this.height();
        int[] seamAr = new int[this.height()];
        int[] nodeAr = new int[n];
        double[] distToAr = new double[n];
        for (int i = 0; i < n; i++) {
            if (i < width())
                distToAr[i] = 0;
            else
                distToAr[i] = D_INFINITY;
        }
        for (int i = 0; i < height(); i++) {
            for (int j = 0; j < width(); j++) {
                for (int m = -1; m <= 1; m++) {
                    if (j + m < 0 || j + m > this.width() - 1 || i + 1 < 0 || i + 1 > this.height() - 1) {
                        continue;
                    } else {
                        if (distToAr[index(j + m, i + 1)] > distToAr[index(j, i)] + energy(j, i)) {
                            distToAr[index(j + m, i + 1)] = distToAr[index(j, i)] + energy(j, i);
                            nodeAr[index(j + m, i + 1)] = index(j, i);
                        }
                    }
                }
            }
        }

        double min = D_INFINITY;
        int index = -1;
        for (int j = 0; j < width(); j++) {
            if (distToAr[j + width() * (height() - 1)] < min) {
                index = j + width() * (height() - 1);
                min = distToAr[j + width() * (height() - 1)];
            }
        }

        // find seam one by one
        for (int j = 0; j < height(); j++) {
            int y = height() - j - 1;
            int x = index - y * width();
            seamAr[height() - 1 - j] = x;
            index = nodeAr[index];
        }

        return seamAr;
    }

    public void removeHorizontalSeam(int[] seamAr) {
        if (height() <= 1)
            throw new IllegalArgumentException();
        if (seamAr == null)
            throw new NullPointerException();
        if (seamAr.length != width())
            throw new IllegalArgumentException();

        for (int i = 0; i < seamAr.length; i++) {
            if (seamAr[i] < 0 || seamAr[i] > height() - 1)
                throw new IllegalArgumentException();
            if (i < width() - 1 && Math.pow(seamAr[i] - seamAr[i + 1], 2) > 1)
                throw new IllegalArgumentException();
        }
        int w = width();
        int h = height();
        int[][] updatedColorArray = new int[w][h-1];
        for (int i = 0; i < seamAr.length; i++) {
            if (seamAr[i] == 0) {
                System.arraycopy(this.colorArray[i], seamAr[i] + 1, updatedColorArray[i], 0, h - 1);
            } else if (seamAr[i] == h - 1) {
                System.arraycopy(this.colorArray[i], 0, updatedColorArray[i], 0, h - 1);
            } else {
                System.arraycopy(this.colorArray[i], 0, updatedColorArray[i], 0, seamAr[i]);
                System.arraycopy(this.colorArray[i], seamAr[i] + 1, updatedColorArray[i], seamAr[i],
                        h - seamAr[i] - 1);
            }

        }
        this.colorArray = updatedColorArray;
    }

    private int[][] transpose(int[][] origin) {
        if (origin == null)
            throw new NullPointerException();
        if (origin.length < 1)
            throw new IllegalArgumentException();
        int[][] resultAr = new int[origin[0].length][origin.length];
        for (int i = 0; i < origin[0].length; i++) {
            for (int j = 0; j < origin.length; j++) {
                resultAr[i][j] = origin[j][i];
            }
        }
        return resultAr;
    }

    public void removeVerticalSeam(int[] seamAr) {
        this.colorArray = transpose(this.colorArray);
        removeHorizontalSeam(seamAr);
        this.colorArray = transpose(this.colorArray);
    }

    private int index(int x, int y) {
        return width() * y + x;
    }
}