package practice6.interactive;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MySqrt {
    public static void main(String[] args) throws IOException {
       /* BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream("input.txt"), StandardCharsets.UTF_8));
        String line;
        while ((line = reader.readLine()) != null) {
           // System.out.println(lucky(Integer.parseInt(line)));
        }*/
       System.out.println(sqrt(8));
        System.out.println(sqrt(9));
        System.out.println(sqrt(40));
        System.out.println(sqrt(1));
    }

    static int pow(int n){
        int answer=0;
        for (int i = 0; i < n; i++)
            answer+=n;
        return answer;
    }

    static int nextPow(int current, int curN){
        return current+curN+curN+1;
    }
    static int sqrt(int n){
        int currentSqrt=1;
        int nextPow=4;
        while (!(n<nextPow)){
            currentSqrt++;
            nextPow=nextPow(nextPow, currentSqrt);
          }
        return currentSqrt;
    }
}
