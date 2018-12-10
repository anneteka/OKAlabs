import java.text.DecimalFormat;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {


        double a;
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        Stopwatch stopwatch = new Stopwatch();
        for (int i = 250; i <= 16000; i *= 2) {
            Timing.trial(i, 777280);
            a = stopwatch.elapsedTime();
            System.out.println("Test " + i + ": " + a + " lgT " + decimalFormat.format(Math.log10(a)) + " lgN " + decimalFormat.format(Math.log10(i)));
        }

    }
}
