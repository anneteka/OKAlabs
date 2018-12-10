//import java.util.*;
//
//public class Main {
//	public static void main(String[] args) {
//		Scanner con = new Scanner(System.in);
//		int i, n = con.nextInt(), res = 0;
//		for (i = 1; i <= n; i++)
//			res ^= i;
//		for (i = 1; i < n; i++) {
//			int val = con.nextInt();
//			res ^= val;
//		}
//		System.out.println(res);
//		con.close();
//	}
//}



import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
  Scanner sc = new Scanner(System.in);
  static StringTokenizer st;

  public static void main(String[] args) {
    Scanner ss = new Scanner(System.in);
    Main lc = new Main();
    String s = ss.nextLine();
    st = new StringTokenizer(s);
    int n = Integer.parseInt(st.nextToken());
    System.out.println(lc.calculate(n));

  }

  public double calculate(int n) {
    double max = 1 + n;
    max = max / 2 * n;
    System.out.println(max);
    for (int i = 0; i < n - 1; i++) {
      max -= Integer.parseInt(st.nextToken());
    }
    return max;
  }
}