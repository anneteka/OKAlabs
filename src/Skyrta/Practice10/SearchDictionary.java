import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * 
 * @author Maria Skyrta. Get all the words from file that correspond with user's
 *         query with *.
 *
 */
public class SearchDictionary {
	private TreeSet<String> sd;

	public SearchDictionary(File file) throws FileNotFoundException {
		sd = new TreeSet<>();
		BufferedReader br = new BufferedReader(new FileReader(file));
		String s = "";
		while (s != null) {
			try {
				s = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(s);
			if (s != null) {
				StringTokenizer st = new StringTokenizer(s);
				while (st.hasMoreTokens())
					sd.add(st.nextToken().toLowerCase());
			}
		}

	}

	public void addWord(String word) {
		sd.add(word);
	}

	public String delWord(String word) {
		if (sd.remove(word)) {
			return word;
		}
		return null;
	}

	public boolean hasWord(String word) {
		return sd.contains(word);
	}

	// Returns set of words that meet user request
	public Iterable<String> query(String query) {
		if (query == null || query == "")
			return null;
		String request = query;
		if (query.endsWith("*"))
			request = query.substring(0, query.indexOf('*'));
		System.out.println(request);
		String toElement = request.substring(0, request.length() - 1);
		char ch = request.charAt(request.length() - 1);
		ch += 1;
		toElement += ch;
		System.out.println(toElement);
		return sd.subSet(request, true, toElement, false);
	}

	public int countWords() {
		return sd.size();
	}
}