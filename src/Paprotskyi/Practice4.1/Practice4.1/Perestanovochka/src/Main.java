import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Scanner sc= new Scanner(System.in);
        int a, n=sc.nextInt(), i, m[]=new int[10001];
        Arrays.fill(m,0);
        for (i = 0; i < n; i++)
        {
            a=sc.nextInt();
            if (a <= n) m[a]++;
        }


        for (i = 1; i <= n; i++)
            if (m[i] == 0)
                break;


        if (i <= n) System.out.println(i);
        else System.out.println(0);
    }
}