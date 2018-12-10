import java.io.File;
import java.io.FileNotFoundException;

/**
 * клас тестувальник
 * 
 * @author Rovnina Tetiana
 *
 */
public class Test {
	public static void main(String[] args) {
		StdDraw.setXscale(-1000, 32768);
		StdDraw.setYscale(-1000, 32768);

		try {
//			Brute b = new Brute(new File("files/input40.txt")); //повільний спосіб
//			b.bruteForse();

			Fast f = new Fast(new File("files/rs1423.txt")); // швидкий спосіб
			f.fastDraw();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
