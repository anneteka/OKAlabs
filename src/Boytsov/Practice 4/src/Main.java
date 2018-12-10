import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] argv) throws IOException {
		new Main().run();
	}

	PrintWriter pw;
	Scanner sc;

	public void run() throws IOException {
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		sc = new Scanner(new File("rs1423.txt"));
		int count = sc.nextInt();
		Point points[] = new Point[count];
		Point temp;
		for (int i = 0; i < count; i++) {
			 temp = new Point(sc.nextInt(), sc.nextInt());
			 temp.draw();
			 points[i] = temp;
		}
		//Brute.analyze(points);
		Fast.analyze(points);
	}
}