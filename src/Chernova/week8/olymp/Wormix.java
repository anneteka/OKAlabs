package week8.olymp;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Wormix implements Comparable<Wormix> {

    static int missionsAvailable;
    static long pointsNeeded;
    long point;
    int time;
    double ratio;
    static Wormix[] missions;
//    static boolean bool = true;

    Wormix(long point, int time, double ratio) {
        this.point = point;
        this.time = time;
        this.ratio = ratio;
    }

    public static void main(String[] args) throws NumberFormatException {
        long number1;
        int number2;
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        StringTokenizer tokenizer = new StringTokenizer(s);
//        if (tokenizer.hasMoreTokens()) {
        missionsAvailable = Integer.parseInt(tokenizer.nextToken());
//            if (missionsAvailable < 0 || missionsAvailable > 100) {
//                System.out.println("Wrong format of the number!");
//                bool = false;
//                return;
//            }
        missions = new Wormix[missionsAvailable];
//            if (tokenizer.hasMoreTokens()) {
        pointsNeeded = Long.parseLong(tokenizer.nextToken());
//                if (pointsNeeded < 0 || pointsNeeded > 10000) {
//                    System.out.println("Wrong format of the number!");
//                    bool = false;
//                    return;
//                }
        for (int i = 0; i < missionsAvailable; i++) {
            s = scanner.nextLine();
            tokenizer = new StringTokenizer(s);
//                    if (tokenizer.hasMoreTokens()) {
            number1 = Long.parseLong(tokenizer.nextToken());
//                        if (number1 < 0 || number1 > 100) {
//                            System.out.println("Wrong format of the number!");
//                            bool = false;
//                            return;
//                        }
//                        if (tokenizer.hasMoreTokens()) {
            number2 = Integer.parseInt(tokenizer.nextToken());
//                            if (number2 < 0 || number2 > 10000) {
//                                System.out.println("Wrong format of the number!");
//                                bool = false;
//                                return;
//                            }
            missions[i] = new Wormix(number1, number2, (double) number1 / number2);
//                        }
//                    }
//        }
//            }
        }
        //        if (bool) {
        if (missions != null)
            Arrays.sort(missions);
        int result = 0;
        int index = 0;
        long pointsGained = 0;
//        if (pointsNeeded == 0 || missionsAvailable == 0) {
//            System.out.println(0);
//            return;
//        }
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
        index=0;
        while (pointsGained - missions[index].point >= pointsNeeded && index<missions.length) {
            pointsGained -=  missions[index].point;
            result -=  missions[index].time;
            index++;
        }
        System.out.println(result);
//        }
    }

    @Override
    public int compareTo(Wormix that) {
        if (ratio > that.ratio) return -1;
        if (ratio < that.ratio) return 1;
        if (ratio == that.ratio) {
            if (time > that.time) return 1;
            if (time < that.time) return -1;
        }
        return 0;
    }
}