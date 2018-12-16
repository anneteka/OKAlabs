package Practice_5_1;

import java.io.*;
import java.util.Arrays;

public class Main {
    private Robot[] robots;
    private int n;

    public Main(int size) {
        robots = new Robot[size];
    }

    private void addRobot(int firstValue, int secondValue) {
        robots[n++] = new Robot(firstValue, secondValue);
    }

    private void sort() {
        Arrays.sort(robots);
    }

    private class Robot implements Comparable<Robot> {
        int key;
        int value;

        Robot(int key, int value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(Robot o) {
            return Integer.compare(key, o.key);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] line = reader.readLine().split(" ");
        int size = Integer.parseInt(line[0]);
        Main main = new Main(size);
        for (int i = 0; i < size; i++) {
            line = reader.readLine().split(" ");
            main.addRobot(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
        }
        main.sort();
        main.printAll(main);
    }

    private static void printAll(Main main) {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out));
        writer.println();
        for (Robot robot : main.robots) {
            writer.println(robot.key + " " + robot.value);
        }
        writer.close();
    }
}