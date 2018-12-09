import java.util.ArrayList;
import edu.princeton.cs.algs4.*; 

public class PercolationStats {

	private static ArrayList<Double> times = new ArrayList<Double>();
	private static int n,t;
	public static void main(String[] args) {
		System.out.print("Size of matrix: ");
		n = StdIn.readInt();
		
		
		System.out.print("Number of experiments: ");
		t = StdIn.readInt();
		while(t<=1) {
			System.out.print("must be bigger than one: ");
			t = StdIn.readInt();
		}
			
		
		PercolationStats p = new PercolationStats(n, t);
		System.out.println("average: "+p.ave());
		System.out.println("stddev: "+p.stddev());
		System.out.println("95% confidence interval [ "+(p.ave()-1.96*p.stddev()/Math.sqrt(t))+";"+(p.ave()+1.96*p.stddev()/Math.sqrt(t))+" ]");
	}
	
	public PercolationStats(int n, int t) {
		Percolation p;
		for(int i=0; i<t; i++) {
			p = new Percolation(n);
		}
	}
	
	public double ave() {
		double summ = 0;
		for(int i=0; i<times.size(); i++) {
			summ+=times.get(i);
		}
		return summ/(t);
	}
	public double stddev() {
		double summ = 0; double mean = ave();
		for(int i=0; i<times.size(); i++) {
			summ+=(times.get(i)-mean)*(times.get(i)-mean);
		}
		if(t!=1)summ = summ/(t-1);
		
		return Math.sqrt(summ);
	}
	
	public static void addTimes(double x) {
		double y = x/(n*n);
		times.add(y);
	}
}
