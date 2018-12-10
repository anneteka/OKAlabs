import java.util.ArrayList;

public class PercolationStats {

	private static ArrayList<Double> times = new ArrayList<Double>();
	private static int n,t;
	public static void main(String[] args) {
		n = Read.getInt("Print n: ");
		t = Read.getInt("Print t: ");
		PercolationStats p = new PercolationStats(n, t);
		System.out.println("mean: "+p.mean());
		System.out.println("stddev: "+p.stddev());
		System.out.println("[ "+(p.mean()-1.96*p.stddev()/Math.sqrt(t))+";"+(p.mean()+1.96*p.stddev()/Math.sqrt(t))+" ]");
	}
	
	public PercolationStats(int n, int t) {
		Percolation p;
		for(int i=0; i<t; i++) {
			p = new Percolation(n);
		}
	}
	
	public double mean() {
		double summ = 0;
		for(int i=0; i<times.size(); i++) {
			summ+=times.get(i);
		}
		return summ/(t);
	}
	public double stddev() {
		double summ = 0; double mean = mean();
		for(int i=0; i<times.size(); i++) {
			summ+=(times.get(i)-mean)*(times.get(i)-mean);
		}
		summ = summ/(t-1);
		return Math.sqrt(summ);
	}
	
	public static void addTimes(double x) {
		double y = x/(n*n);
		System.out.println("add "+y);
		times.add(y);
	}
}
