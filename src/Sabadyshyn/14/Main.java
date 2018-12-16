public class Main {

    private Picture pic;
    private double[] energy;
    private int[] pathTo;

    public Main(Picture picture) {
        pic = new Picture(picture);
    }

    public Picture picture() {
        return new Picture(pic);
    }

    public     int width() {
        return pic.width();
    }

    public     int height() {
        return pic.height();
    }

    private double gradient(java.awt.Color x, java.awt.Color y) {
        double r = x.getRed() - y.getRed();
        double g = x.getGreen() - y.getGreen();
        double b = x.getBlue() - y.getBlue();
        return r*r + g*g + b*b;
    }

    public double energy(int x, int y) {
        if (x < 0 || x >= width() || y < 0 || y >= height())
            throw new IndexOutOfBoundsException();
        if (x == 0 || y == 0 || x == width()-1 || y == height()-1)
            return 195075;
        return gradient(pic.get(x-1, y), pic.get(x+1, y)) + gradient(pic.get(x, y-1), pic.get(x, y+1));
    }

    private double energy(int x, int y, int flag) {
        if (flag == 1)
            return energy(y, x);
        else
            return energy(x, y);
    }

    private void computeEnergy(int w, int h, int flag) {
        energy = new double[w*h];
        for (int r = 0; r < h; r++)
            for (int c = 0; c < w; c++) {
                energy[r*w + c] = energy(c, r, flag);
            }
    }

    private int[] computePath(int w, int h) {
        pathTo = new int[w*h];
        for (int i = 0; i < w; i++)
            pathTo[i] = -1;
        for (int r = 1, i = w; r < h; r++) {
            if (energy[i-w] <= energy[i-w+1]) pathTo[i] = i-w;
            else pathTo[i] = i-w+1;
            energy[i] += energy[pathTo[i]]; i++;
            for (int c = 1; c < w-1; c++, i++) {
                if (energy[i-w-1] <= energy[i-w]) {
                    if (energy[i-w-1] <= energy[i-w+1]) pathTo[i] = i-w-1;
                    else pathTo[i] = i-w+1;
                } else {
                    if (energy[i-w] <= energy[i-w+1]) pathTo[i] = i-w;
                    else pathTo[i] = i-w+1;
                }
                energy[i] += energy[pathTo[i]];
            }
            if (energy[i-w-1] <= energy[i-w]) pathTo[i] = i-w-1;
            else pathTo[i] = i-w;
            energy[i] += energy[pathTo[i]]; i++;
        }

        int pathEnd = w*(h-1);
        double minE = energy[w*(h-1)];
        for (int i = w*(h-1); i < w*h; i++) {
            if (minE > energy[i]) {
                minE = energy[i];
                pathEnd = i;
            }
        }

        int[] path = new int[h];
        for (int p = pathEnd; p >= 0; p = pathTo[p])
            path[p/w] = p % w;
        return path;
    }

    public   int[] findHorizontalSeam() {
        int w = height(), h = width();
        computeEnergy(w, h, 1);
        return computePath(w, h);
    }
    public   int[] findVerticalSeam() {
        int w = width(), h = height();
        computeEnergy(w, h, 0);
        return computePath(w, h);
    }
    public    void removeHorizontalSeam(int[] a) {
        if (height() <= 1)
            throw new IllegalArgumentException();
        if (a.length != width())
            throw new IllegalArgumentException("Not correct l");

        Picture p = new Picture(width(), height()-1);
        int prerow = a[0];
        for (int c = 0; c < width(); c++) {
            if (Math.abs(a[c] - prerow) > 1)
                throw new IllegalArgumentException("not correct seam");
            if (a[c] < 0 || a[c] >= height())
                throw new IndexOutOfBoundsException();
            prerow = a[c];
            for (int r = 0; r < height()-1; r++)
                if (r < a[c])
                    p.set(c, r, pic.get(c, r));
                else
                    p.set(c, r, pic.get(c, r+1));
        }
        pic = p;
        energy = null;
        pathTo = null;
    }
    public    void removeVerticalSeam(int[] a) {
        if (width() <= 1)
            throw new IllegalArgumentException();
        if (a.length != height())
            throw new IllegalArgumentException("Not correct length");

        Picture p = new Picture(width()-1, height());
        int precol = a[0];
        for (int r = 0; r < height(); r++) {
            if (Math.abs(a[r] - precol) > 1)
                throw new IllegalArgumentException("Not correct seam");
            if (a[r] < 0 || a[r] >= width())
                throw new IndexOutOfBoundsException();
            precol = a[r];
            for (int c = 0; c < width()-1; c++)
                if (c < a[r])
                    p.set(c, r, pic.get(c, r));
                else
                    p.set(c, r, pic.get(c+1, r));
        }
        pic = p;
        energy = null;
        pathTo = null;
    }
    public static void main(String[] args) {
        Picture picture = new Picture("test.jpg");
        picture.show();
        Main seamCarver = new Main(picture);
        long start, end;
        start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++)
            seamCarver.findVerticalSeam();
        end = System.currentTimeMillis();
        System.out.println("Час: "+(end-start)+"мс");

        start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++)
            seamCarver.findHorizontalSeam();
        end = System.currentTimeMillis();
        System.out.println("Час: "+(end-start)+"мс");

        start = System.currentTimeMillis();
        for (int i = 0; i < 1; i++)
            seamCarver.removeVerticalSeam(seamCarver.findVerticalSeam());
        end = System.currentTimeMillis();
        System.out.println("Час: "+(end-start)+"мс");

        start = System.currentTimeMillis();
        for (int i = 0; i < 1; i++)
            seamCarver.removeHorizontalSeam(seamCarver.findHorizontalSeam());
        end = System.currentTimeMillis();
        System.out.println("Час: "+(end-start)+"мс");
    }
}