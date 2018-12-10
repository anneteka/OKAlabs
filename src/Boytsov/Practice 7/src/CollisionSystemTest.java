
public class CollisionSystemTest {
	public static void main(String[] args) throws InterruptedException {
		int l = 2;
		//int l = Integer.parseInt(args[0]);
		Particle particles[] = new Particle[l+1];
	/*	for (int i = 0; i < l; i++)
			particles[i] = new Particle();*/
		particles[0] = new Particle(0.2, 10, 0, 0, 0.5, 0.5);
		particles[1] = new Particle(0.2, 10, 0, 0, 0.5, 0.5);
		CollisionSystem cs = new CollisionSystem(particles);
		StdDraw.enableDoubleBuffering();
		cs.simulate();

	}
}
