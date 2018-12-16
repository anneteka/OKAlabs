
public class PercolationStats {

	int numberOfExperements;
	int matrixSize;
	double count;
	double resultPowTwo;
	public PercolationStats(int N, int T) {
	    
		numberOfExperements = T;
	    matrixSize = N;
	for(int i = 0; i< T; i++) {
		Percolation per = new Percolation(matrixSize);
		per.opening();
		System.out.println(i+") "+per.getOpenedCount());
		//System.out.println("Result: " );
	   //System.out.println((double)per.getOpenedCount()/(double)(per.system.length-2));
        double j = (double)per.getOpenedCount()/(double)(per.system.length-2);
        
		count+= j;
        resultPowTwo += j*j;
      //  System.out.println(count+"; "+j+"  "+ resultPowTwo+ "; "+j*j );
	}
	    
		
	}

	public double mean() {
		return count/numberOfExperements;
		
	}
	public double stddev() {
		return Math.sqrt((resultPowTwo-2*mean()*count+ numberOfExperements*mean()*mean())/(numberOfExperements-1));
		
	}
	public String interval() {
		double stInter = mean() - (1.9*stddev()/Math.sqrt(numberOfExperements));
		double fiInter = mean() + (1.9*stddev()/Math.sqrt(numberOfExperements));

		return "95% confidence interval ("+stInter+"; "+fiInter+")";
		
	}

	public static void main(String[] args) 
	{  Stopwatch c = new Stopwatch();
		PercolationStats stat = new PercolationStats(200, 100);
		
		System.out.println("Result: "+System.lineSeparator()+
				"mean: "+stat.mean()+System.lineSeparator()
		+"stddev: "+stat.stddev());
		System.out.println(stat.interval());
        System.out.println(	c.elapsedTime());
}}
