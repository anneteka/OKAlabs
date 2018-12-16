//import java.io.BufferedInputStream;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//
//public class Main {
//
////    private static final String testFile = "grid6x6.txt";
////    private static final String testFile = "horizontal100.txt";
////    private static final String testFile = "input6.txt";
////    private static final String testFile = "input8.txt";
//    private static final String testFile = "input40.txt";
////    private static final String testFile = "input50.txt";
////    private static final String testFile = "input56.txt";
////    private static final String testFile = "input100.txt";
////    private static final String testFile = "input400.txt";
////    private static final String testFile = "rs1423.txt";
//
//    public static void main(String[] args) throws FileNotFoundException {
////        StdDraw.setXscale(0,32768);
////        StdDraw.setYscale(0,32768);
//        BufferedInputStream in = new BufferedInputStream(new FileInputStream(testFile));
//        System.setIn(in);
//        int n = StdIn.readInt();
//        Point[] points = new Point[n];
//        int i=0;
//        while (!StdIn.isEmpty()){
//            int x = StdIn.readInt();
//            int y = StdIn.readInt();
//            points[i++] = new Point(x,y);
//            Brute br = new Brute(points,n);
//
//        }
//    }
//}
//