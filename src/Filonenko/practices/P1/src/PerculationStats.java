import java.time.temporal.ValueRange;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class PerculationStats {
	private double[] resultsArray;
	private int numberOfRows;
	public PerculationStats(int numberOfRows, int numberOfAttempts) {
		this.numberOfRows = numberOfRows;
		resultsArray = new double[numberOfAttempts];
		for(int index=0;index<numberOfAttempts;index++) {
			Perculation perculation = new Perculation(numberOfRows);
			while(!perculation.perculates()){
				int randomCell = new Random().nextInt(numberOfRows*numberOfRows);
				int[] position = getRowAndColumnOf(randomCell);
				perculation.openCell(position[0], position[1]);
			}
			resultsArray[index] = perculation.getOpenedCount() / (double) (numberOfRows*numberOfRows);
		}
	}
	private int[] getRowAndColumnOf(int index) {
		// table position of cell from its index
		return new int[] {index / numberOfRows, index % numberOfRows};
	}
	
	public double mean() {
		return Arrays.stream(resultsArray).reduce((x, y) -> x+y).orElse(-1) / (double) resultsArray.length;
	}
	public double stddev() {
		return  Math.sqrt(Arrays.stream(resultsArray)
		.map(x -> (x-mean())*(x-mean()))
		.reduce((x, y) -> x+y)
		.orElse(-1)/(resultsArray.length-1));
	}
	private double[] interval() {
		// 95% confidence interval
		return new double[] {mean() - 1.96*stddev()/Math.sqrt((double) resultsArray.length), 
		mean() + 1.96*stddev()/Math.sqrt((double) resultsArray.length)};
	}
	
	// start the program with two arguments - number of rows and number of attempts
	public static void main(String[] args) {
		int rows=-1, attempts=-1;
		if(args.length != 2) {
			System.out.print("Incorrect number of arguments");
		} else {
			try {
				rows = Integer.parseInt(args[0]);
				attempts = Integer.parseInt(args[1]);
			} catch(Exception e) {
				System.out.print("Incorrect arguments");
				System.exit(0);;
			}
			if(rows<1 || attempts<1) {
				System.out.println("The values must be >0");
				System.exit(0);
			}
			PerculationStats stats = new PerculationStats(rows,attempts);
			System.out.println("mean = "  +  stats.mean());
			System.out.println("stddev = "  +  stats.stddev());
			System.out.println("95% confidence interval = "   +  stats.interval()[0] + ", " + stats.interval()[1]);
		}
	}
}
