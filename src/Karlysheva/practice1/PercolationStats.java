package practice1;

import java.util.Random;

public class PercolationStats {
    private Random random;
    private int[] results;
    private int n, t;

    public PercolationStats(int n, int t) {   // проведемо T окремих експериментів в N на N матриці
        System.out.println("Percolation Stats "+n+" "+t);
        results=new int[t];
        this.n=n;
        this.t=t;
        random = new Random();
        Percolation percolation;
        for (int i = 0; i < t; i++) {
            percolation = new Percolation(n);
            while (!percolation.percolates()) {
                percolation.open(random.nextInt(n), random.nextInt(n));
            }
            results[i] = percolation.getOpenedCount();
        }

    }

    public double mean() {// рахує середнє
        int mean = 0;
        for (int i:results) mean+=i;
        mean=mean/t;
    //    System.out.println("mean = "+mean);
        return mean;

    }

    public double stddev() {// рахує відхилення
        double delta=0;
        double m =mean();

        for (int i:results)
            delta+=(i-m)*(i-m);

        delta=delta/(t-1);
        delta=Math.sqrt(delta);
   //     System.out.println("stddev = "+delta);
        return delta;
    }
    public void confidence(){
        System.out.println("95% confidence interval = "+(mean()-1.96*stddev()/Math.sqrt(t))+", "+(mean()+1.96*stddev()/Math.sqrt(t)));
    }
    public static void main(String[] args) {
        PercolationStats stats1 = new PercolationStats(20, 10);

        System.out.println("mean = "+stats1.mean());
        System.out.println("stddev = "+stats1.stddev());
        stats1.confidence();
    }

}
