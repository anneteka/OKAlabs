import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int n,T;
        Scanner sc = new Scanner(System.in);
        n=sc.nextInt();
        T=sc.nextInt();
        PercolationStats ps = new PercolationStats(n,T);
    }
}
