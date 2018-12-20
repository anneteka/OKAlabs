package Stakhurskyi.Pr1;

/**
 * Created by Dmytro on 06.12.2017.
 */
public class PercolationStats {
    public class PercolationStats {
        public PercolationStats(int N, int T)    // perform T independent computational experiments on an N-by-N grid
        public double mean();                // sample mean of percolation threshold
        public double stddev();                   // sample standard deviation of percolation threshold
        public double confidenceLo();             // returns lower bound of the 95% confidence interval
        public double confidenceHi() ;            // returns upper bound of the 95% confidence interval
        public static void main(String[] args)  {} // test client, described below
    }
}
