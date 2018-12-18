import java.awt.Color;
import java.util.PriorityQueue;

public class SeamCarver {
    private Picture givenPicture;

    private class SearchStatus implements Comparable<SearchStatus> {
        private int x;
        private int y;
        private SearchStatus prev;
        private double dist;
        public SearchStatus(int x, int y, SearchStatus prev, double dist) {
            this.x = x;
            this.y = y;
            this.prev = prev;
            this.dist = dist;
        }
        public int compareTo(SearchStatus that) {
            if (this.dist < that.dist) {
                return 1;
            } else if (that.dist < this.dist) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    public SeamCarver(Picture picture) {
        givenPicture = new Picture(picture);
    }
    public double energy(int x, int y) {
        if (!checkBounds(x, y)) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        if (x == 0 || x == width() - 1 || y == 0 || y == height() - 1) {
            return 255 * 255 * 3.0;
        }
        Color leftColor = givenPicture.get(x - 1, y);
        Color rightColor = givenPicture.get(x + 1, y);
        Color topColor = givenPicture.get(x, y - 1);
        Color bottomColor = givenPicture.get(x, y + 1);
        int leftRed = leftColor.getRed();
        int leftGreen = leftColor.getGreen();
        int leftBlue = leftColor.getBlue();
        int topRed = topColor.getRed();
        int topGreen = topColor.getGreen();
        int topBlue = topColor.getBlue();
        int rightRed = rightColor.getRed();
        int rightGreen = rightColor.getGreen();
        int rightBlue = rightColor.getBlue();
        int bottomRed = bottomColor.getRed();
        int bottomGreen = bottomColor.getGreen();
        int bottomBlue = bottomColor.getBlue();
        return (leftRed - rightRed) * (leftRed - rightRed) + (leftGreen - rightGreen) * (leftGreen - rightGreen)
                + (leftBlue - rightBlue) * (leftBlue - rightBlue) + (topRed - bottomRed) * (topRed - bottomRed)
                + (topGreen - bottomGreen) * (topGreen - bottomGreen) + (topBlue - bottomBlue) * (topBlue - bottomBlue);
    }
    public Picture picture() {
        return givenPicture;
    }
    public int width() {
        return givenPicture.width();
    }
    public int height() {
        return givenPicture.height();
    }
    private boolean checkBounds(int x, int y) {
        return 0 <= x && x < width() && 0 <= y && y < height();
    }
    public int[] findHorizontalSeam() {
        PriorityQueue<SearchStatus> pq = new PriorityQueue<SearchStatus>();
        double[][] dp = new double[width()][height()];
        for (int i = 0; i < width(); ++i) {
            for (int j = 0; j < height(); ++j) {
                dp[i][j] = Double.POSITIVE_INFINITY;
            }
        }
        for (int j = 0; j < height(); ++j) {
            dp[0][j] = energy(0, j);
            pq.add(new SearchStatus(0, j, null, dp[0][j]));
        }
        while (pq.size() != 0) {
            SearchStatus current = pq.peek();
            if (current.x == width() - 1) {
                break;
            }
            pq.poll();
            for (int s = -1; s <= 1; ++s) {
                int cx = current.x;
                int cy = current.y;
                int px = cx + 1;
                int py = cy + s;
                if (!checkBounds(px, py)) {
                    continue;
                }
                if (dp[cx][cy] + energy(px, py) < dp[px][py]) {
                    dp[px][py] = dp[cx][cy] + energy(px, py);
                    SearchStatus post = new SearchStatus(px, py, current, dp[px][py]);
                    pq.add(post);
                }
            }
        }
        int[] seams = new int[width()];
        SearchStatus current = pq.poll();
        while (current != null) {
            seams[current.x] = current.y;
            current = current.prev;
        }
        return seams;
    }

    public int[] findVerticalSeam() {
        PriorityQueue<SearchStatus> pq = new PriorityQueue<SearchStatus>();
        double[][] dp = new double[width()][height()];
        for (int i = 0; i < width(); ++i) {
            for (int j = 0; j < height(); ++j) {
                dp[i][j] = Double.POSITIVE_INFINITY;
            }
        }
        for (int i = 0; i < width(); ++i) {
            dp[i][0] = energy(i, 0);
            pq.add(new SearchStatus(i, 0, null, dp[i][0]));
        }
        while (pq.size() != 0) {
            SearchStatus current = pq.peek();
            if (current.y == height() - 1) {
                break;
            }
            pq.poll();
            for (int s = -1; s <= 1; ++s) {
                int cx = current.x;
                int cy = current.y;
                int px = cx + s;
                int py = cy + 1;
                if (!checkBounds(px, py)) {
                    continue;
                }
                if (dp[cx][cy] + energy(px, py) < dp[px][py]) {
                    dp[px][py] = dp[cx][cy] + energy(px, py);
                    SearchStatus post = new SearchStatus(px, py, current, dp[px][py]);
                    pq.add(post);
                }
            }
        }
        int[] seams = new int[height()];
        SearchStatus current = pq.poll();
        while (current != null) {
            seams[current.y] = current.x;
            current = current.prev;
        }
        return seams;
    }
    public void removeHorizontalSeam(int[] a) {
        int n = a.length;
        if (n <= 1 || n != width()) {
            throw new java.lang.IllegalArgumentException();
        }
        for (int i = 0; i < n; ++i) {
            if (a[i] < 0 || a[i] >= height()) {
                throw new java.lang.IllegalArgumentException();
            }
        }
        for (int i = 1; i < n; ++i) {
            if (Math.abs(a[i] - a[i - 1]) > 1) {
                throw new java.lang.IllegalArgumentException();
            }
        }
        Picture tmp = new Picture(width(), height() - 1);
        for (int x = 0; x < width(); ++x) {
            for (int y = 0; y < a[x]; ++y) {
                tmp.set(x, y, givenPicture.get(x, y));
            }
            for (int y = a[x] + 1; y < height(); ++y) {
                tmp.set(x, y - 1, givenPicture.get(x, y));
            }
        }
        givenPicture = tmp;
    }
    public void removeVerticalSeam(int[] a) {
        int m = a.length;
        if (m <= 1 || m != height()) {
            throw new java.lang.IllegalArgumentException();
        }
        for (int i = 0; i < m; ++i) {
            if (a[i] < 0 || a[i] >= width()) {
                throw new java.lang.IllegalArgumentException();
            }
        }
        for (int i = 1; i < m; ++i) {
            if (Math.abs(a[i] - a[i - 1]) > 1) {
                throw new java.lang.IllegalArgumentException();
            }
        }
        Picture t = new Picture(width() - 1, height());
        for (int y = 0; y < height(); ++y) {
            for (int x = 0; x < a[y]; ++x) {
                t.set(x, y, givenPicture.get(x, y));
            }
            for (int x = a[y] + 1; x < width(); ++x) {
                t.set(x - 1, y, givenPicture.get(x, y));
            }
        }
        givenPicture = t;
    }
    public static void main(String[] args) {
        Picture picture = new Picture("sun.jpg");

        SeamCarver seamCarver = new SeamCarver(picture);

        for (int pixel = 0; pixel < 1; pixel++) {
            if (pixel % 130 == 0) {
                int[] vseam = seamCarver.findVerticalSeam();
                seamCarver.removeVerticalSeam(vseam);
            } else {
                int[] hseam = seamCarver.findHorizontalSeam();
                seamCarver.removeHorizontalSeam(hseam);
            }
        }
        seamCarver.picture().show();
    }
}