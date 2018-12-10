import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);

        Random z = new Random();
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        Point[] p = new Point[n];
        for (int i = 0; i < n; i++) {
            p[i]=new Point(sc.nextInt(),sc.nextInt());
            p[i].draw();
        }



        Arrays.sort(p,Point::compareTo);

        for (int i = 0; i <n-3; i++) {
            for (int j = i + 1; j < n-2; j++) {
                for (int k = j + 1; k < n-1; k++) {
                    for (int l = k + 1; l < n; l++) {
                        if (p[i].slopeTo(p[j]) == p[i].slopeTo(p[k]) && p[i].slopeTo(p[k]) == p[i].slopeTo(p[l])) {
                            p[i].drawN();
                            p[j].drawN();
                            p[k].drawN();
                            p[l].drawN();
                            p[i].drawTo(p[j]);
                            p[j].drawTo(p[k]);
                            p[k].drawTo(p[l]);
                        }
                    }
                }
            }
        }
    }
}
