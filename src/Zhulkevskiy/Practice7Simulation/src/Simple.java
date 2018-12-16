import edu.princeton.cs.algs4.StdDraw;

import java.io.*;

//only wall bouncing
public class Simple {
    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File("particles.txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            String s = br.readLine();
            int n = Integer.parseInt(s);
            Ball[] balls = new Ball[n];
            for (int i = 0; i < n; i++) {
                s = br.readLine();
                String s1[] = s.split(" ");
                balls[i] = new Ball(Double.parseDouble(s1[0]),
                        Double.parseDouble(s1[1]), Double.parseDouble(s1[2]),
                        Double.parseDouble(s1[3]),
                        Double.parseDouble(s1[4]),
                        Double.parseDouble(s1[5]),
                        Integer.parseInt(s1[6]), Integer.parseInt(s1[7]),
                        Integer.parseInt(s1[8]));
            }
            while (true) {
                StdDraw.clear();
                for (int i = 0; i < n; i++) {
                    balls[i].move(0.5);
                    balls[i].draw();
                }
                StdDraw.show(5);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
