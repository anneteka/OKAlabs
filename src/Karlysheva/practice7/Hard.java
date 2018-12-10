package practice7;

import ua.princeton.lib.StdDraw;

public class Hard {

    public static void main(String[] args) {
        StdDraw.square(0.5,0.5,0.5);
        Particle[] particles = new Particle[50];
        for (int i = 0; i < particles.length; i++) {
            particles[i]=new Particle();
        }
        CollisionSystem collisionSystem = new CollisionSystem(particles);
        collisionSystem.simulate();
    }
}
