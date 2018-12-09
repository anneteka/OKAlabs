import java.util.Scanner;

public class Rabbits {
    public static void main(String[] args) {
        int n,k,num;
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        k = scan.nextInt();
        num = 1;
        for (int i=0; i<n; i++){
            if (num>k)
                num -= k;
            num+=num;
        }
        System.out.println(num);
    }
}