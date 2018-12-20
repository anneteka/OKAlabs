package Stakhurskyi.Pr6;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class ConvexHull {

    private static Point2D[] pArray;
    private static int N;

    private static LinkedStack<Integer> pStack;

    private static void drawPoints(String filePath) {

        Scanner scr;

        try {
            scr = new Scanner(new File(filePath));

            N = scr.nextInt();
            System.out.println(N);
            pArray = new Point2D[N];

            for (int i = 0; i < N; i++) {
                pArray[i] = new Point2D(scr.nextInt(), scr.nextInt());
                pArray[i].draw();
            }

            Arrays.sort(pArray);

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }

    }

    private static void drawLines() {

        if (N < 3) {
            if (N == 2)
                pArray[0].drawTo(pArray[1]);
            return;
        }

        Arrays.sort(pArray, pArray[0].POLAR_ORDER);

        pStack = new LinkedStack<Integer>();



        int i = 0, k = 1, j = 2;
        pStack.push(0);
        pStack.push(1);


        while (j < N) {


            if (Point2D.ccw(pArray[i], pArray[k], pArray[j]) <= 0) {
                pStack.push(j);
                i = k;
                k = j;
                j++;
            } else {
                pStack.pop();
                Iterator<Integer> it = pStack.iterator();
                k = it.next();
                i = it.next();
            }
        }


        int last = -1;
        for (Integer index : pStack) {
            if (last == -1) {
                pArray[0].drawTo(pArray[index]);
                last = index;
            } else {
                pArray[last].drawTo(pArray[index]);
                last = index;
            }
        }
    }

    public static void main(String[] args) {

        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);

        drawPoints("src/input400.txt");
        drawLines();

    }

}