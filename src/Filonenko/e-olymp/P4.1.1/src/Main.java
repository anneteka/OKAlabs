import java.util.Scanner;

public class Main {

    public static int result(int[] db, int floor, int ceiling) {
        int counter = 0;
        for(int i: db) {
            if(i>=floor && i<=ceiling) {
                counter++;
            }
        }
        return counter;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextInt()) {
            int capacity = scanner.nextInt();
            int[] db = new int[capacity];
            for(int i=0, length = db.length;i<length;i++) {
                db[i]=scanner.nextInt();
            }
            System.out.println(result(db, scanner.nextInt(), scanner.nextInt()));
        }
    }
}
