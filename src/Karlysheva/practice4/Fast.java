package practice4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Fast {
    Point[] copy;
    Point[] points;
    int n;
    File input;

    Fast(String file) throws IOException {
        input = new File(file);
        points = readFile();
        copy = new Point[n];
        for (int i = 0; i < n; i++) {
            copy[i] = points[i];
        }
        findLines();
    }


    void findLines() {
        for (Point p : points) {
            sortCopy(p);
            int counter = 0;
            for (int i = 0; i < n ; i++) {

                double slope = p.slopeTo(copy[i]);
                 for (int j = i;j<n&& p.slopeTo(copy[j]) == slope; j++) {
                    counter++;
                    i = j;
                }
                if (counter > 2){
                    draw(p, i - counter + 1, i);

                }
                counter = 0;
            }
        }
    }

    void draw(Point p, int p1, int p2) {
        Point min = p, max = p;
        for (int i = p1; i <= p2; i++) {
            min = min.compareTo(copy[i]) < 0 ? min : copy[i];
            max = max.compareTo(copy[i]) > 0 ? max : copy[i];
        }
        min.drawTo(max);

    }

    void sortCopy(Point point) {
        Arrays.sort(copy, point.SLOPE_ORDER);
    }

    Point[] readFile() throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader(input));
        n = Integer.parseInt(bf.readLine());
        Point[] temp = new Point[n];
        for (int i = 0; i < n; i++) {
            String[] line = bf.readLine().trim().split(" ");
            int a = Integer.parseInt(line[0]);
            int b = Integer.parseInt(line[line.length - 1]);
            temp[i] = new Point(a, b);
        }
        return temp;
    }
}
