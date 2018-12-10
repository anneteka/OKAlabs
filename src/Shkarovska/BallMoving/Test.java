import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		StdDraw.setCanvasSize(600, 600);

		StdDraw.enableDoubleBuffering();

		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		Particle[] particles = new Particle[x];
		for (int i = 0; i < x; i++) {
			particles[i] = new Particle();
		}
		CollisionSystem system = new CollisionSystem(particles);
		while(true)
		system.simulate();
	}
}
