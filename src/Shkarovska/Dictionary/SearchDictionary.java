import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

public class SearchDictionary {
	private int count;
	private TreeMap<Character, ArrayList<String>> st;

	public SearchDictionary() {
		st = new TreeMap<Character, ArrayList<String>>();
		count = 0;
	}

	public void addWord(String word) {
		count++;
		char key = word.charAt(0);
		if(!st.containsKey(key)) {
			ArrayList<String> str = new ArrayList<>();
			str.add(word);
			st.put(key, str);
		} else {
			st.get(key).add(word);
		}
	}
	public ArrayList<String> getArray(Character a){
		ArrayList<String> res;
		try {
			res = st.get(a);
			return res;
		} catch(Exception e) {
			System.out.println("dictionary dan't know such words");
		}
		return null;
	}

	public String delWord(String word) {
		String res = word;
		count-- ;
		if(st.containsKey(word.charAt(0)));
		st.get(word.charAt(0)).remove(word);
		return word;
	}

	public boolean hasWord(String word) {
		return count!=0;
	}

	public int countWords() {
		return count;
	}
}