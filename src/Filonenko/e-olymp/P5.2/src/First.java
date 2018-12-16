import java.util.Scanner;

public class First {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        for(int i=0;i<10;i++) {
            int number = scanner.nextInt();
            char[] s = Integer.toString(number).toCharArray();
            boolean p = true;
            for(int j=0;j<=s.length/2-1;j++) {
                if(s[j] != s[s.length-j-1]) {
                    p = false;
                }
            }
            System.out.println(p);
        }
    }
}
