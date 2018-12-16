import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;

public class Fast {
    //    private static final String testFile = "grid6x6.txt";
    //private static final String testFile = "horizontal100.txt";
//    private static final String testFile = "input6.txt";
//    private static final String testFile = "input8.txt";
    //private static final String testFile = "text.txt";
//    private static final String testFile = "input50.txt";
//    private static final String testFile = "input56.txt";
//    private static final String testFile = "input100.txt";
//    private static final String testFile = "input400.txt";
    private static final String testFile = "rs1423.txt";

    public static void main(String[] args) throws FileNotFoundException{

        StdDraw.setXscale(0,32768);
        StdDraw.setYscale(0,32768);
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(testFile));
        System.setIn(in);
        int n = StdIn.readInt();
        Point[] points = new Point[n];

        int i=0;
        while (!StdIn.isEmpty()) {
            int x = StdIn.readInt();
            int y = StdIn.readInt();
            points[i++] = new Point(x, y);
        }
        double[][] slopes;
        for (int j = 0; j < n; j++){
                slopes = new double[n][2]; // [i from points[]][slope to this point]
                for (int k = 0; k < n; k++){
                        slopes[k][0] = points[j].slopeTo(points[k]);
                        slopes[k][1] = k;
                }
                Arrays.sort(slopes, Comparator.comparingDouble(arr -> arr[0]));

                for (int p = 0; p<n-2;  p++){
                    if ((slopes[p][0] == slopes[p+1][0]) && (slopes[p+1][0] == slopes [p+2][0])){
                        points [j].draw();
                        points[(int)slopes[p][1]].draw();
                        points[(int)slopes[p+1][1]].draw();
                        points[(int)slopes[p+2][1]].draw();
                        points [j].drawTo(points[(int)slopes[p][1]]);
                        points [(int)slopes[p][1]].drawTo(points[(int)slopes[p+1][1]]);
                        points [(int)slopes[p+1][1]].drawTo(points[(int)slopes[p+2][1]]);
                        System.out.println(points[j] + "->" + points[(int)slopes[p][1]] + "->" + points[(int)slopes[p+1][1]] + "->" +points[(int)slopes[p+2][1]]);
                    }
                }
        }
    }

}




