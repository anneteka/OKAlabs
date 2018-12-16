package week11.practice;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class GraphGenerator {

    static class Pair {

        int value1;
        int value2;

        Pair(int value1, int value2) {
            this.value1 = Math.min(value1, value2);
            this.value2 = Math.max(value1, value2);
        }

        @Override
        public String toString() {
            return value1 + " " + value2;
        }

        public boolean equals(Pair pair) {
            return this.value1==pair.value1 && this.value2==pair.value2;
        }
    }

    public static void main(String[] args) {
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter("graphs//output.txt"));
            Random random = new Random();
            int vertexes = random.nextInt(10) + 10;
            int edges = random.nextInt(10) + 20;
            printWriter.println(vertexes);
            printWriter.println(edges);
            ArrayList<Pair> arrayList = new ArrayList();
            boolean bool;
            for (int i = 0; i < edges; i++) {
                bool = true;
                int value1 = random.nextInt(vertexes);
                int value2 = random.nextInt(vertexes);
                if (value1 == value2) i--;
                else {
                    Pair pair = new Pair(value1, value2);
                    for (Pair pairs : arrayList)
                        if (pair.equals(pairs)) {
                            i--;
                            bool=false;
                            break;
                        }
                    if (bool) {
                        arrayList.add(pair);
                        printWriter.println(pair.toString());
                    }
                }
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
