package week8.eolympWormix;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Mission implements Comparable<Mission> {

    static int missionsAvaliable;
    static int pointsNeeded;
    int point;
    int time;
    double ratio;
    static Mission[] missions;
    static boolean bool = true;

    Mission(int point, int time, double ratio) {
        this.point = point;
        this.time = time;
        this.ratio = ratio;
    }

    public static void main(String[] args) throws NumberFormatException {
        readNumbers();
        if (bool) {
            if (missions != null)
                Arrays.sort(missions);
            int result = 0;
            int index = 0;
            int pointsGained = 0;
            if (pointsNeeded == 0 || missionsAvaliable == 0) {
                System.out.println(0);
                return;
            }
            while (pointsGained < pointsNeeded) {
                if (index < missions.length) {
                    pointsGained += missions[index].point;
                    result += missions[index].time;
                    index++;
                } else {
                    System.out.println(-1);
                    return;
                }
            }
            System.out.println(result);
        }
    }

    private static void readNumbers() {
        int number1;
        int number2;
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        StringTokenizer tokenizer = new StringTokenizer(s);
        if (tokenizer.hasMoreTokens()) {
            missionsAvaliable = Integer.parseInt(tokenizer.nextToken());
            missions = new Mission[missionsAvaliable];
            if (tokenizer.hasMoreTokens()) {
                pointsNeeded = Integer.parseInt(tokenizer.nextToken());
                for (int i = 0; i < missionsAvaliable; i++) {
                    s = scanner.nextLine();
                    tokenizer = new StringTokenizer(s);
                    if (tokenizer.hasMoreTokens()) {
                        number1 = Integer.parseInt(tokenizer.nextToken());
                        if (tokenizer.hasMoreTokens()) {
                            number2 = Integer.parseInt(tokenizer.nextToken());
                            missions[i] = new Mission(number1, number2, (double) number1 / number2);
                        }
                    }
                }
            }
        }
    }

    @Override
    public int compareTo(Mission that) {
        if (ratio > that.ratio) return -1;
        if (ratio < that.ratio) return 1;
        if (ratio == that.ratio) {
            if (time > that.time) return 1;
            if (time < that.time) return -1;
        }
        return 0;
    }
}