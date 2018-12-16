import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public Main(PriorityQueue<Mission> missions, int neededScore) {
        long totalScore = 0;
        long totalTime = 0;
        System.out.println(missions);
        while(totalScore < neededScore && !missions.isEmpty()) {
            Mission m = missions.poll();
            totalScore += m.score;
            totalTime += m.time;
        }
        result = totalScore < neededScore ? -1 : totalTime;
    }
    public long result = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int stages = scanner.nextInt();
        int neededScore = scanner.nextInt();
//        int[] allScores = new int[stages];
//        int[] allDurations = new int[stages];
        PriorityQueue<Mission> missions = new PriorityQueue<Mission>(stages);
        for(int i=0;i<stages;i++) {
//            allScores[i] = scanner.nextInt();
//            allDurations[i] = scanner.nextInt();
//            int result = new Main(allScores, allDurations, neededScore).getResult();
//            System.out.println(result);
            missions.offer(new Mission(scanner.nextInt(), scanner.nextInt()));
        }
        long result = new Main(missions, neededScore).result;
        System.out.println(result);
    }
}
class Mission implements Comparable {
    int score;
    int time;

    public Mission(int score, int time) {
        this.score = score;
        this.time = time;
    }
    @Override
    public int compareTo(Object o) {
        Mission that = (Mission) o;
        if(this.time / (double) this.score < that.time / (double) that.score) {
            return -1;
        }
        if(this.time / (double) this.score > that.time / (double) that.score) {
            return 1;
        }
        if(this.score < that.score) return -1;
        if(this.score > that.score) return 1;
        return 0;
    }
    @Override
    public String toString() {
        return "(score: " + score + " , time: " + time + ", quality: " + score/(double)time + " )";
    }
}
