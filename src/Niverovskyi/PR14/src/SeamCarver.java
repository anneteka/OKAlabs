import java.util.Arrays;
import java.util.PriorityQueue;

public class SeamCarver {
    private Picture picture;

    public SeamCarver(Picture picture) {
        if (picture == null) throw new IllegalArgumentException("Expected non-null picture");
        this.picture = new Picture(picture);
    }

    public Picture picture() {
        return new Picture(picture);
    }

    public int width() {
        return picture.width();
    }

    public int height() {
        return picture.height();
    }

    private double getEnergy(int x, int y, boolean transposed) {
        if (transposed) return getEnergy(y, x, false);
        if (x < 0 || x >= width() || y < 0 || y >= height())
            throw new IllegalArgumentException("Expected x in [0, width - 1] and y in [0, height - 1]");
        if (x == 0 || x == width() - 1 || y == 0 || y == height() - 1)
            return 1000;
        double dx = 0, dy = 0;
        for (int i = 16; i >= 0; i -= 8) {
            dx += Math.pow(((picture.getRGB(x - 1, y) >> i) & 0xFF) - ((picture.getRGB(x + 1, y) >> i) & 0xFF), 2);
            dy += Math.pow(((picture.getRGB(x, y - 1) >> i) & 0xFF) - ((picture.getRGB(x, y + 1) >> i) & 0xFF), 2);
        }
        return Math.sqrt(dx + dy);
    }

    public double energy(int x, int y) {
        return getEnergy(x, y, false);
    }

    private static class State implements Comparable<State> {
        int r, c;
        double cost;

        State(int r, int c, double cost) {

            this.r = r;
            this.c = c;
            this.cost = cost;
        }

        @Override
        public int compareTo(State s) {
            return Double.compare(cost, s.cost);
        }
    }

    private int[] getPath(double[][] energy) {
        int n = energy.length;
        int m = energy[0].length;

        double[][] dist = new double[n][m];
        int[][] prev = new int[n][m];
        PriorityQueue<State> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++)
            Arrays.fill(dist[i], 1 << 30);

        for (int i = 0; i < m; i++) {
            dist[0][i] = energy[0][i];
            prev[0][i] = i;
            pq.offer(new State(0, i, dist[0][i]));
        }

        while (!pq.isEmpty()) {
            State curr = pq.poll();

            if (curr.r == n - 1) {
                int[] ret = new int[n];
                int c = curr.c;
                for (int i = n - 1; i >= 0; i--) {
                    ret[i] = c;
                    c = prev[i][c];
                }
                return ret;
            }

            for (int dc = -1; dc <= 1; dc++) {
                int nc = curr.c + dc;
                if (nc < 0 || nc >= m) continue;

                double ncost = curr.cost + energy[curr.r + 1][nc];
                if (dist[curr.r + 1][nc] <= ncost) continue;

                dist[curr.r + 1][nc] = ncost;
                prev[curr.r + 1][nc] = curr.c;
                pq.offer(new State(curr.r + 1, nc, ncost));
            }
        }

        return null;
    }

    public int[] findHorizontalSeam() {
        double[][] energy = new double[width()][height()];
        for (int i = 0; i < width(); i++)
            for (int j = 0; j < height(); j++)
                energy[i][j] = getEnergy(i, j, false);
        return getPath(energy);
    }

    public int[] findVerticalSeam() {
        double[][] energy = new double[height()][width()];
        for (int i = 0; i < height(); i++)
            for (int j = 0; j < width(); j++)
                energy[i][j] = getEnergy(i, j, true);
        return getPath(energy);
    }

    public void removeHorizontalSeam(int[] seam) {
        if (seam == null) throw new IllegalArgumentException("null seam");

        Picture np = new Picture(width(), height() - 1);
        for (int i = 0; i < width(); i++) {
            for (int j = 0, k = 0; j < height(); j++) {
                if (j != seam[i]) {
                    np.setRGB(i, k++, picture.getRGB(i, j));
                }
            }
        }
        this.picture = np;
    }

    public void removeVerticalSeam(int[] seam) {
        if (seam == null) throw new IllegalArgumentException("null seam");

        Picture np = new Picture(width() - 1, height());
        for (int j = 0; j < height(); j++) {
            for (int i = 0, k = 0; i < width(); i++) {
                if (i != seam[j]) {
                    np.setRGB(k++, j, picture.getRGB(i, j));
                }
            }
        }
        this.picture = np;
    }
}