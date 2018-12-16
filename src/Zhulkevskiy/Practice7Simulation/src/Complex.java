import edu.princeton.cs.algs4.StdDraw;

import java.io.*;

//Developed by Zhulkevskiy Vladyslav
public class Complex {

    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File("wallbouncing3.txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StdDraw.setCanvasSize(600, 600);
        try {
            String s = br.readLine();
            int n = Integer.parseInt(s);
            Particle[] particles = new Particle[n];
            for (int i = 0; i < n; i++) {
                s = br.readLine();
                String s1[] = s.split(" ");
                particles[i] = new Particle(Double.parseDouble(s1[0]),
                        Double.parseDouble(s1[1]), Double.parseDouble(s1[2]),
                        Double.parseDouble(s1[3]),
                        Double.parseDouble(s1[4]),
                        Double.parseDouble(s1[5]),
                        Integer.parseInt(s1[6]), Integer.parseInt(s1[7]),
                        Integer.parseInt(s1[8]));
            }
            CollisionSystem system = new CollisionSystem(particles);
            system.simulate(10000);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
