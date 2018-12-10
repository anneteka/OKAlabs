import java.util.Random;

public class BouncingBalls {
	
	public static final int WIDTH = 30000;
	public static final int HEIGHT = 30000;
	public static final int N = 100;
	public static final int RADIUS = 500;
	public static final int MIN_SPEED = 1;
	public static final int MAX_SPEED = 200;
	public static final int MIN_MASS = 100;
	public static final int MAX_MASS = 100;
	
	public static void main(String[] args) {
		Random rand = new Random();
		Ball[] balls = new Ball[N];
		for (int i = 0; i < N; i++)
			balls[i] = new Ball(rand.nextInt((int)(WIDTH - 2*RADIUS)) + RADIUS, 
								rand.nextInt((int)(HEIGHT - 2*RADIUS)) + RADIUS, 
								Math.signum(rand.nextDouble() - 0.5) * rand.nextInt(MAX_SPEED), 
								Math.signum(rand.nextDouble() - 0.5) * rand.nextInt(MAX_SPEED), 
								RADIUS);
		StdDraw.setCanvasSize(720, 720);
		StdDraw.setXscale(0, WIDTH);
		StdDraw.setYscale(0, HEIGHT);
		
		
		while (true) {
			StdDraw.clear();
			for (int i = 0; i < N; i++) {
				balls[i].move(0.5);
				balls[i].draw();
			}
			StdDraw.show(1);
		}
	}
}
