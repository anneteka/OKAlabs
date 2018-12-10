import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class n1 {


    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));

        int tests  = Integer.parseInt(br.readLine());
        while(tests!=0) {
            tests--;
            if(br.readLine().isEmpty());
            String[] str = br.readLine().split(" ");
            int k = Integer.parseInt(str[0]);
            int n = Integer.parseInt(str[1]);

            Dnk[] arr = new Dnk[n];

            int i=0;
            while(i<n){
                char[] s = br.readLine().toCharArray();
                int inversions = value(s,k);
                arr[i++] = new Dnk(s, inversions);
            }

            Arrays.sort(arr);

            for(Dnk l: arr)
                System.out.println(l.arr);
            System.out.println();

        }
        br.close();
    }


    private static int value(char[] ch,int k){
        int i, j, res = 0;
        for(i = 0; i < k - 1; i++)
            for(j = i + 1; j < k; j++)
                if (ch[i] > ch[j]) res++;
        return res;
    }
    private static class Dnk implements Comparable<Dnk> {

        private int count;
        private char[] arr;

        Dnk(char[] arr,int count){
            this.arr = arr;
            this.count= count;
        }

        @Override
        public int compareTo(Dnk that) {
            if(this.count>that.count) return 1;
            else if(this.count<that.count) return -1;
            else return 0;
        }
    }


}

