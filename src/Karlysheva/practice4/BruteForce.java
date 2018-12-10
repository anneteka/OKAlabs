package practice4;

import java.io.*;
import java.util.Arrays;

public class BruteForce {
    File input;
    Point[] points;

    int n;

    BruteForce(String file) throws IOException{
        input = new File(file);
        System.out.println("file");
        points=readFile();
        System.out.println("points");
        findLines();
    }

    Point[] readFile() throws IOException {
        BufferedReader bf= new BufferedReader(new InputStreamReader(
                new FileInputStream(input)));
        n = Integer.parseInt(bf.readLine().split(" ")[0]);
        Point[] temp = new Point[n];
        for (int i = 0; i < n; i++){
            String[] line = bf.readLine().trim().split(" ");
            int a = Integer.parseInt(line[0]);
            int b = Integer.parseInt(line[line.length-1]);
            temp[i]=new Point(a,b);
        }
        return temp;
    }


    void findLines(){
        double slope1,slope2,slope3;
        for (int i = 0; i < n-3; i++) {
            for (int j = i+1; j < n-2; j++) {

                slope1 = points[i].slopeTo(points[j]);

                for (int k = j+1; k < n-1; k++) {

                    slope2 = points[i].slopeTo(points[k]);
                    for (int l = k+1; l < n; l++) {
                        slope3 = points[i].slopeTo(points[l]);
                        if (slope1==slope3&&slope1==slope2){
                            drawLine(minPoint(points[i],points[j],points[k],points[l]),maxPoint(points[i],points[j],points[k],points[l]));
                        }
                    }
                }
            }
        }
    }

    private Point minPoint(Point p1, Point p2, Point p3, Point p4){
        Point min=p1.compareTo(p2)<0?p1:p2;
        min=min.compareTo(p3)<0?min:p3;
        min=min.compareTo(p4)<0?min:p4;
        return min;
    }

    private Point maxPoint(Point p1, Point p2, Point p3, Point p4){
        Point max=p1.compareTo(p2)>0?p1:p2;
        max=max.compareTo(p3)>0?max:p3;
        max=max.compareTo(p4)>0?max:p4;
        return max;
    }

    void drawLine(Point p1, Point p2){
        p1.drawTo(p2);
    }
}
