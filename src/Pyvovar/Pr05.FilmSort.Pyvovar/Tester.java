import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * клас-тестувальник
 * 
 * @author Пивовар Олена, 4 група, ІПЗ
 *
 */
public class Tester {

	public static void main(String[] args) throws IOException {
		Film[] arrays;
		
		BufferedReader br = new BufferedReader(new FileReader(new File("src\\film1.txt")));
		PrintWriter pw = new PrintWriter(new File("src\\sortfilm.txt"));
		String str = br.readLine();
		String name;
		int year;
		int length;
		int n = Integer.parseInt(str);
		StringTokenizer token;
		arrays = new Film[n];
		
		for (int i = 0; i < n; i++) {
			name = br.readLine();
			str = br.readLine();
			token = new StringTokenizer(str);
			year = Integer.parseInt(token.nextToken());
			str = br.readLine();
			token = new StringTokenizer(str);
			length = Integer.parseInt(token.nextToken());
			arrays[i] = new Film(name, year, length);
		}
		
		InsertionSort.sort(arrays);
		for (Film f : arrays) {
			pw.println(f);
		}
		pw.println();
		
		InsertionSort.sort(arrays, Film.BY_NAME);
		for (Film f : arrays) {
			pw.println(f);
		}
		pw.println();
		
		MergeSort.sort(arrays, Film.BY_LENGTH);
		for (Film f : arrays) {
			pw.println(f);
		}
		
		pw.close();
		br.close();
		
	}

}
