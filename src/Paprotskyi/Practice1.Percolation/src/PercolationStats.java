import java.util.Random;
import java.util.Scanner;

public class PercolationStats {
    int N , T;
    double[] results;

    public PercolationStats(int N, int T){
    this.N = N;
    this.T = T;
    this.results = new double[T];

    }

    public double mean() {
        //рахує середнє
        double meanStat = 0;
        for (int i = 0; i<T; i++){
            meanStat += results[i];
        }
        meanStat /= T;
        return meanStat;
    }


    public double stddev() {
    // рахує відхилення
    double stddevStat = 0;
    double tempMean = mean();

        for (int i = 0; i<T; i++) {
            stddevStat += Math.pow((results[i] - tempMean),2);
        }
        stddevStat /= T-1;

        return Math.sqrt(stddevStat);
    }



    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = 0;
        int t = 0;
        System.out.println("Enter N (for matrix N x N) and T (amount of tests): ");
        while (s.hasNextLine()){
            System.out.println("Enter N (for matrix N x N) and T (amount of tests): ");
            n = s.nextInt();
            t = s.nextInt();


        PercolationStats ps = new PercolationStats(n,t);

        for (int i = 0; i<t; i++) {
            Percolation p = new Percolation(n);
            Random rand = new Random();
            int row;
            int column;

            while (p.percolates() == false) {
                do {
                    row = rand.nextInt(n);
                    column = rand.nextInt(n);
                } while (p.isOpened(row, column));
                p.open(row, column);
                //System.out.println("Opened count =" + p.getOpenedCount());
            }
            //System.out.println("Percolates!");
            ps.results[i] =  p.getOpenedCount() / (n * n);
            }
            double meanRes = ps.mean();
            double stdDevRes = ps.stddev();
            double confIntervResFirst = meanRes - (1.96 * stdDevRes/Math.sqrt(t));
            double confIntervResSecond = meanRes + (1.96 * stdDevRes/Math.sqrt(t));

            System.out.println("Mean = "+ meanRes);
            System.out.println("Stddev = "+  stdDevRes);
            System.out.println("95 % confidence interval = " + confIntervResFirst + ", " + confIntervResSecond);
        }

    }


}
