import java.util.Arrays;
import java.util.Scanner;

public class Wormix {
    static class Mission implements Comparable<Mission> {
        private double points;
        private double time;
        private double pt;

        Mission(double points, double time) {
            this.points = points;
            this.time = time;
            this.pt = points / time;
        }

        @Override
        public int compareTo(Mission mission) {
            if (mission.pt < this.pt) {
                return -1;
            }
            if (mission.pt > this.pt) {
                return 1;
            }
            if (this.time < mission.time) {
                return -1;
            }
            if (this.time > mission.time) {
                return 1;
            }
            return 0;
        }

        @Override
        public String toString() {
            return pt + " (" + points + " " + time + ")";
        }

    }

    public static int minTime(Mission[] missions, int points){
        double pointSum = 0;
        double timeSum = 0;

        for (Mission mission : missions) {
            if (pointSum >= points) return (int) timeSum;
            pointSum += mission.points;
            timeSum += mission.time;
        }

        return -1;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numberOfMissions = in.nextInt();
        int points = in.nextInt();
        Mission missions[] = new Mission[numberOfMissions];

        for (int i = 0; i < numberOfMissions; i++) {
            missions[i] = new Mission(in.nextInt(), in.nextInt());
        }

        Arrays.sort(missions);

        System.out.println(minTime(missions, points));
    }
}