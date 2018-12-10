package CollisionSystemSimple;

import princetonlib.StdDraw;

/**
 * реалізація простого методу - взаємодії лише зі стінками
 * 
 * @author Пивовар Олена, 4 група, ІПЗ
 *
 */
public class BouncingBalls {
	public static void main(String[] args) {
		int screenSize = 20;
		StdDraw.setXscale(0, screenSize);
		StdDraw.setYscale(0, screenSize);
		int N = 10; // Integer.parseInt(args[0]);
		Ball[] balls = new Ball[N];
		for (int i = 0; i < N; i++)
			balls[i] = Ball.randomBall(screenSize);
		while (true) {
			StdDraw.clear();
			for (int i = 0; i < N; i++) {
				balls[i].move(0.5, screenSize);
				balls[i].draw();
			}
			StdDraw.show(20);
		}

	}

}
