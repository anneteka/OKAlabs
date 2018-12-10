//Simple simulation without collisions 
public class BouncingBalls {
	public static void main(String[] args) {
		// int N = Integer.parseInt(args[0]);
		int n = 10;
		Ball[] balls = new Ball[n];
		for (int i = 0; i < n; i++)
			balls[i] = new Ball(0.03);
		while (true) {
			StdDraw.clear();
			for (int i = 0; i < n; i++) {
				balls[i].move(0.5);
				balls[i].draw();
			}
			StdDraw.show(50);
		}
	}
}
