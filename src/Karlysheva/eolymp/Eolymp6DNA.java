package eolymp;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class Eolymp6DNA {
    static BufferedReader reader;
    static int tests;
    static PrintWriter output;
    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(
                new FileInputStream("input.txt"), StandardCharsets.UTF_8));
        output = new PrintWriter(new File("output.txt"));
        String[] line;
        tests=Integer.parseInt(reader.readLine());
        for (int i = 0; i < tests; i++) {
            test();
            if (i<tests-1) output.println();
        }
    }

    public static void test() throws IOException{
        String line = reader.readLine();
        if (line.equals(""));
        line = reader.readLine();
        String[] data = line.split(" ");
        int length=Integer.parseInt(data[0]);
        int dnas = Integer.parseInt(data[1]);
        DNA[] sorted = new DNA[dnas];
        for (int i = 0; i < dnas; i++) {
            sorted[i]=new DNA(reader.readLine());
        }
        Arrays.sort(sorted);
        print(sorted);
    }
    private static void print(DNA[] dnas){
        for (DNA dna:dnas) {
            output.println(dna);
        }
    }


}
class DNA implements Comparable<DNA>{
    String dnaS;
    char[] dna;
    int sorted;

    public DNA(String dna) {
        dnaS=dna;
        this.dna = dna.toCharArray();
        sorted = countSorted();
    }

    private int countSorted(){
        int sort=0;
        for (int i = 0; i < dna.length; i++) {
            for (int j = i; j < dna.length; j++) {
                if (dna[i]>dna[j])sort++;
            }
        }
        return sort;
    }

    @Override
    public int compareTo(DNA o) {
        return sorted-o.sorted;
    }

    @Override
    public String toString() {
        return dnaS;
    }
}
