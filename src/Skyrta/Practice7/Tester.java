public class Tester {

	public static void main(String[] args) {

		int n = 20;
		Particle[] particles = new Particle[n];
		for (int i = 0; i < n; i++) {
			particles[i] = new Particle();
		}
		CollisionSystem cs = new CollisionSystem(particles);
		cs.simulate();

	}

}
