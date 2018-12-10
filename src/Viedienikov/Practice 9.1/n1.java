import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class n1 {
     public static void main(String[] args) throws NumberFormatException, IOException {
        n1 n1 = new n1();
        Scanner scan=new Scanner(System.in);
        int tests = scan.nextInt();

        while(tests>0) {
            int n =scan.nextInt();
            String[] num= new String[n];
            for(int k = 0; k < n; ++k) {
                num[k] = scan.next();
            }
            Arrays.sort(num);
            boolean c=true;
            for(int i = 1; i < n; ++i) {
                if (num[i].startsWith(num[i - 1])) {
                    c= false;
                    break;
                }
            }

            if (c){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }
        }

    }
}
