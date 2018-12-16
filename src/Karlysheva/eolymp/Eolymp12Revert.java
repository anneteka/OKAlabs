package Karlysheva.eolymp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Eolymp12Revert {
    static StringBuilder[] answer;
    static BufferedReader reader;

    public static void main(String[] args) throws IOException {
     //   System.out.println("2\n2\n1");
        reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        answer = new StringBuilder[n + 1];
        for (int i = 0; i < n+1; i++) {
            answer[i]=new StringBuilder();
        }
        for (int i = 0; i < n; i++) {
            String[] line = reader.readLine().split(" ");
            for (int j = 0; j < line.length; j++) {
                try {
                    answer[Integer.parseInt(line[j])]
                            .append(i+1)
                            .append(" ");
                }
                catch (NumberFormatException e){

                }
            }
        }
        System.out.println(n);
        for (int i = 1; i < n+1; i++) {
            System.out.println(answer[i].toString().trim());
        }
    }
}
