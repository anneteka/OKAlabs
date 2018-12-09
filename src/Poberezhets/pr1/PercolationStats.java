import java.util.ArrayList;

import lib.StdIn;

public class PercolationStats {
	int n;
	int t;
	Percolation p;
	ArrayList<Double> array=new ArrayList<Double>();
	// проведемо T окремих експериментів в N на N матриці
	public PercolationStats(int N, int T) {
		this.n=N;
		this.t=T;
		for(int i=0; i<T;i++) 
		{
			p=new Percolation(N);
			array.add((double) (p.getCounter()/(N*N)));
			
		}
	System.out.println("Середній поріг протікання: "+mean());
	System.out.println("Різкість порогу: "+stddev());
	
	}

	// рахує середнє
	public double mean() 
	{
		double res=0;
		for(double k : array)
			res+=k;
		res=res/t;
		return res;
		
	}

	// рахує відхилення
	public double stddev() 
	{
		double q=0;
		for(double k : array)
			q+=((k-mean())*(k-mean()));
		q=q/(t-1);
		return Math.sqrt(q);

	}

	public static void main(String[] args) {
		System.out.println("Введіть розмірність матриці: ");
		int _N=StdIn.readInt();
		System.out.println("Введіть кількість ітерацій: ");
		int _T=StdIn.readInt();
		PercolationStats stat=new PercolationStats(_N, _T);
		

	}
	
}
