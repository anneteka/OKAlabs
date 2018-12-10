	import java.io.IOException;

	public class testArray {

	public static void main(String[] args) throws IOException{ // Чтение строк из стандартного ввода, их сортировка и вывод.
		    		
	        FileArray farr = new FileArray();
	        String[] lines = farr.readLines("src/sortArray10K.txt");
	        for (String line : lines) {
	            System.out.println(line);
	        }
	    }
	}