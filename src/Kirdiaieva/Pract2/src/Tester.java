public class Tester {
    public static void main(String[] args) {
        Stopwatch stopwatch = new Stopwatch();
        Timing.trial(10000,777280);
        double time = stopwatch.elapsedTime();
        System.out.println(time);
    }
}