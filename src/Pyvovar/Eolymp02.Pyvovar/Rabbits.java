import java.io.PrintWriter;
import java.util.Scanner;

public class Rabbits {

	public static void main(String[] args) {
		Rabbits m = new Rabbits();
		m.countRabbits();
	}
	
	private void countRabbits() {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out, true);
		
		while(in.hasNextInt()) {
			int n = in.nextInt();
			int k = in.nextInt();
			int r = 1;
			for (int i = 0; i < n; i++) {
				if(r > k)
					r -= k;
				r *= 2;
			}
			out.println(r);
		}
	}

}
