import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main{



    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out, true);
        while (in.ready()) {
            int N = Integer.parseInt(in.readLine());
            Integer[] heights = new Integer[N];
            String[] s = in.readLine().split(" ");
            int answer = 0;

            for (int i = 0; i < N; i++){
                heights[i] = Integer.parseInt(s[i]);
            }
            s = in.readLine().split(" ");
            int min = Integer.parseInt(s[0]);
            int max = Integer.parseInt(s[1]);
            if (min > max){
                int temp = min;
                min = max;
                max = temp;
            }
            for (int j = 0; j < N; j++){
                if (heights[j] >= min && heights[j]<=max){
                    answer++;
                }
            }
            out.printf("%d\n",answer);
        }
    }
}