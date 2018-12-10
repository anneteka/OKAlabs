

public class BouncingBalls {
	public static void main(String[] args) {
		int N = 20;
		Ball[] balls = new Ball[N];
		for (int i = 0; i < N; i++) {
			balls[i] = new Ball(Math.random(), Math.random(), Math.random() * 0.015, Math.random() * 0.015, 0.015);
		}
		while (true) {
			StdDraw.clear();
			for (int i = 0; i < N; i++) {
				balls[i].move(1);
				balls[i].draw();
			}
			StdDraw.show(50);
		}
	}
	
}