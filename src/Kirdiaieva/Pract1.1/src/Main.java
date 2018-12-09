import java.io.IOException;
import java.util.*;

public class Main{
    private static final int MAX = 1000000;

    public static void main(String args[]) throws IOException{
        Main obj = new Main();
        obj.begin();
    }

    private void begin() throws IOException{
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()){
            int i = in.nextInt();
            int j = in.nextInt();

            int from = Math.min(i, j);
            int to = Math.max(i, j);

            if(from > 0 && from < MAX && to > 0 && to < MAX){
                int max = 0;

                for(int counter = from; counter <= to; counter++){
                    max = Math.max(max, generate(counter, 1));
                }

                System.out.println(i+" "+j+" "+max);
            }
        }
    }

    private int generate(int n, int length){
        if(n > 1){
            if(n%2 == 0){
                n = n/2;
            }
            else{
                n = (3 * n) + 1;
            }

            length++;

            return generate(n, length);
        }
        else{
            return length;
        }
    }
}