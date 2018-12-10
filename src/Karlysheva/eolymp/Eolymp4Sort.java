package eolymp;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Eolymp4Sort {
    public static void main(String[] args) throws IOException {
     //   Scanner sc = new Scanner(System.in);
        int n;
        int a;
        int b;
        int answer;
        int x;
        String ans;
        String[] ab;
        String[] ints;
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream("input.txt"), StandardCharsets.UTF_8));
     PrintWriter output = new PrintWriter(new File("output.txt"));
           String line;
            while ((line = reader.readLine()) != null) {
            n = Integer.parseInt(line.split(" ")[0]);
            ints = reader.readLine().split(" ");
           ab=reader.readLine().split(" ");
           a= Integer.parseInt(ab[0]);
           b=Integer.parseInt(ab[1]);
            answer = 0;

            for (int i = 0; i < n; i++) {
                x = Integer.parseInt(ints[i]);
                if (x <= b && x >= a) answer++;
            }
                ans=""+answer+"\n";
output.println(ans);

        }
        output.close();
            reader.close();
    }
}