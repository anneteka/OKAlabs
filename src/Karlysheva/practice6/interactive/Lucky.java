package practice6.interactive;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;

public class Lucky {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream("1.txt"), StandardCharsets.UTF_8));
         String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(lucky(Integer.parseInt(line)));
        }
    }

    static boolean lucky(int n) {
        String[] current;
        HashSet<Integer> cycle= new HashSet<>();
        int cur=0;
        int sn;
        while (n != 1) {
            if (!cycle.add(n)) return false;
            cur = 0;
            current = (n + "").split("");
            for (String s : current) {
                sn = Integer.parseInt(s);
                cur += sn * sn;
            }
            n = cur;

        }
        return true;
    }
}
