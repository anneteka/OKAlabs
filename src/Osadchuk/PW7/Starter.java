package PW7;

public class Starter {

	public static void main(String[] args) {
		new CollisionSystem(createParticles());
	}
	
	private static Particle[] createParticles() {
		Particle[] pr = {

				new Particle(), new Particle(), new Particle(),new Particle(),new Particle(),
				new Particle(), new Particle(), new Particle(),new Particle(),new Particle(),
				new Particle(), new Particle(), new Particle(),new Particle(),new Particle(),
				new Particle(), new Particle(), new Particle(),new Particle(),new Particle(),
				new Particle(), new Particle(), new Particle(),new Particle(),new Particle(),
				new Particle(), new Particle(), new Particle(),new Particle(),new Particle(),
				new Particle(), new Particle(), new Particle(),new Particle(),new Particle(),
				new Particle(), new Particle(), new Particle(),new Particle(),new Particle(),
				
		};
		return pr;
	}

}