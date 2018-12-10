package practice4;

import ua.princeton.lib.StdDraw;

import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
       try{
           Fast f = new Fast("rs1423.txt");

          //BruteForce bf = new BruteForce("horizontal100.txt");
           }
       catch (IOException e){
           e.printStackTrace();
       }

    }
}
