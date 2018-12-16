import java.util.Scanner;

public class Main {

    static boolean enough(int minDistance, int iceCreamMen, int[] coords){
        int numberOfStalls = 1;
        int distance=0;
        for(int i=1;i<coords.length;i++) {
            distance += coords[i] - coords[i-1];
            if(distance >= minDistance) {
                distance = 0;
                numberOfStalls++;
            }
        }
        return numberOfStalls  >= iceCreamMen;
    }

    static int getMinDistance(int iceCreamMen, int[] stalls) {
        int left = 0, right = 1000000000, middle;
        while(left <= right) {
            middle = (left + right) /2;
            if(enough(middle, iceCreamMen, stalls)) left = middle + 1;
            else right = middle - 1;
        }
        return left - 1;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfStalls = scanner.nextInt();
        int numberOfIceCreamMan = scanner.nextInt();
        int[] stalls = new int[numberOfStalls];
        for(int i=0;i<numberOfStalls;i++) {
            stalls[i] = scanner.nextInt();
        }
        System.out.println(getMinDistance(numberOfIceCreamMan, stalls));
    }
}
