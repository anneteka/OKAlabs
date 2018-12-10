import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

/**
 * 
 * @author Maria Skyrta. Read words from test file and print the results of
 *         user's queries.
 *
 */
public class Tester {
	public static void main(String[] args) throws IOException {
		SearchDictionary sd = null;
		try {
			sd = new SearchDictionary(new File("C:/test1.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		Iterable<String> st = sd.query(s);
		Iterator<String> it = st.iterator();
		while (it.hasNext())
			System.out.println(it.next());
	}

}
