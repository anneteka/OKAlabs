import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Digraph {
    static class OneRow {
        private int count;
        private int[] ver;

        OneRow(int count, int[] ver) {
            this.count = count;
            this.ver = ver;
        }
    }

    public static void main(String[] args) {
        BufferedReader bf;
        PrintWriter out = new PrintWriter(System.out);
        try {
            bf = new BufferedReader(new InputStreamReader(System.in));
            int vertexes = Integer.parseInt(bf.readLine());
            OneRow[] ar = new OneRow[vertexes];
            for (int j = 0; j < ar.length; j++) {
                ar[j] = new OneRow(0, new int[1]);
            }
            String line;

            for (int i = 0; i < vertexes; i++) {
                if ((line = bf.readLine()).equals("")) {
                    continue;
                }
                StringTokenizer str = new StringTokenizer(line, " ");
                int k;
                int length;
                while (str.hasMoreTokens()) {
                    k = (Integer.parseInt(str.nextToken()) - 1);
                    length = ar[k].ver.length;
                    //resize array
                    if (ar[k].count == length) {
                        int[] temp = new int[length * 2];
                        for (int j = 0; j < length; j++) {
                            temp[j] = ar[k].ver[j];
                        }
                        ar[k].ver = temp;
                    }

                    ar[k].ver[ar[k].count++] = i + 1;

                }
            }

            out.println(vertexes);
            for (int i = 0; i < ar.length; i++) {
                for (int j = 0; j < ar[i].count; j++) {
                    if (ar[i].count != 0) {

                        out.print(ar[i].ver[j] + " ");

                    }
                }
                out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.flush();
    }
}