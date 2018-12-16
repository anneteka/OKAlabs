import java.util.Scanner;

public class Main {
    private static int result(int[] cards) {
        ShellSort.sort(cards);
        int current = cards[0];
        if(current != 1) return 1;
        for(int i=1,length=cards.length;i<length;i++) {
            if(cards[i]-current != 1) return cards[i]-1;
            else current = cards[i];
        }
        return cards.length+1;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextInt()) {
            int capacity = scanner.nextInt();
            if(capacity == 1)  {
                System.out.println(1);
                continue;
            }
            int[] cards = new int[capacity-1];
            for(int i=0, length = cards.length;i<length;i++) {
                cards[i] = scanner.nextInt();
            }
            System.out.println(result(cards));
        }
    }
}

class ShellSort {
    private static void exch(int[] a, int i, int j){
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    public static void sort(int[] a){
        int n = a.length;

        int h = 1;
        while (h<n/3) h = 3*h+1; // 1, 4, 13, 40, 121, 364, ...

        while(h>=1){
            for (int i = h; i<n;i++){
                for (int j=i; j>=h;j-=h) {
                    if(a[j]<a[j-h]) {
                        exch(a,j,j-h);
                    } else break;
                }
            }
            h=h/3;
        }
    }
}