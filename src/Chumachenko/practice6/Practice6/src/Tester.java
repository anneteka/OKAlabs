import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class Tester {

	private static File file0 =new File("src/Data/horizontal100.txt");
	private static File file1=new File("src/Data/input100.txt");
	private static File file2=new File("src/Data/input40.txt");
	private static File file3=new File("src/Data/input400.txt");
	private static File file4=new File("src/Data/input50.txt");
	private static File file5=new File("src/Data/input56.txt");
	private static File file6=new File("src/Data/input6.txt");
	private static File file7=new File("src/Data/input8.txt");
	private static File file8 = new File("src/Data/rs1423.txt");
	private static File file9=new File("src/Data/grid6x6.txt");
	
	public static void main(String [] args) throws FileNotFoundException {
		 StdDraw.setXscale(0, 32768);
		 StdDraw.setYscale(0, 32768);

		new ConvexHull(file0);
		
	
	}
	
}
