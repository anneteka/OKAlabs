package eolymp;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class Eolymp5MergeSort {
    static int[] mains, additional, addaux;
    static String[] line;
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream("input.txt"), StandardCharsets.UTF_8));
        PrintWriter output = new PrintWriter(new File("output.txt"));
        String[] line;
        int n = Integer.parseInt(reader.readLine());
        mains = new int[n];
        additional = new int[n];
        addaux = new int[n];
        for (int i = 0; i < n; i++) {
                line = reader.readLine().split(" ");
                mains[i] = Integer.parseInt(line[0]);
                additional[i] = Integer.parseInt(line[1]);
        }
        sort(mains);
        print(output);
        reader.close();
        output.close();
    }


    private static void print(PrintWriter pw) {
        for (int i = 0; i < mains.length; i++) {
            pw.println(mains[i] + " " + additional[i]);
        }
    }

    private static void merge(int[] a, int[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
            addaux[k] = additional[k];
        }
        int i = lo, j = mid + 1;
        int x = i, y = j;
        for (int k = lo; k <= hi; k++) {
       //     System.out.println(Arrays.toString(mains));
            if (i > mid) {
                a[k] = aux[j++];
                additional[k]=addaux[y++];
            } else if (j > hi) {
                //   System.out.println(k+"-"+i);
                additional[k]=addaux[x++];
                a[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                // System.out.println(k+"-"+j);
                additional[k]=addaux[y++];
                a[k] = aux[j++];
            } else {
                // System.out.println(k+"-"+i);
                additional[k]=addaux[x++];
                a[k] = aux[i++];
            }
        }

    }

    private static void sort(int[] a, int[] aux, int lo, int hi) {
        if (hi <= lo) return;
            //  if (hi <= lo + CUTOFF - 1) InsertionSort.sort(a,lo,hi);
        else {
            int mid = lo + (hi - lo) / 2;
            sort(a, aux, lo, mid);
            sort(a, aux, mid + 1, hi);
            if (!less(a[mid + 1], a[mid])) return;
            merge(a, aux, lo, mid, hi);
        }
    }

    public static void sort(int[] a) {
        int[] aux = new int[a.length];

        sort(a, aux, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int l, int m) {
        for (int i = l; i <= m; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
}

