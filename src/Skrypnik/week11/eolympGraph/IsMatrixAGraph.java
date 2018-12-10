package week11.eolympGraph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class IsMatrixAGraph {

    public static void main(String[] args) throws NumberFormatException {
        try {
            int[][] array;
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/week11/eolympGraph/input.txt"));
            PrintWriter printWriter = new PrintWriter(new FileWriter("src/week11/eolympGraph/output.txt"));
            int N = Integer.parseInt(bufferedReader.readLine());
            if (N == 0) {
                printWriter.print("YES");
                printWriter.close();
                return;
            }
            if (N > 100 || N < 1) return;
            array = new int[N][N];
            StringTokenizer tokenizer;
            String token;
            for (int i = 0; i < N; i++) {
                tokenizer = new StringTokenizer(bufferedReader.readLine());
                for (int j = 0; j < N; j++) {
                    token = tokenizer.nextToken();
                    if (!token.equals("0") && !token.equals("1")) return;
                    array[i][j] = Integer.parseInt(token);
                }
            }
            for (int i = 1; i < N; i++) {
                for (int j = 0; j < i; j++) {
                    if (array[i][j] != array[j][i]) {
                        printWriter.print("NO");
                        printWriter.close();
                        return;
                    }
                }
            }
            for (int i = 0; i < N; i++) {
                if (array[i][i] == 1) {
                    printWriter.print("NO");
                    printWriter.close();
                    return;
                }
            }
            printWriter.print("YES");
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
