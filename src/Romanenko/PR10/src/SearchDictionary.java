

public class SearchDictionary {
	private int counter = 0;
	private LinearProbingHashST<String,String> lphst;

	public SearchDictionary() {
		lphst = new LinearProbingHashST<String,String>();
	}

	public void addWord(String word) {
		lphst.put(word,word);
		counter++;
	}

	public String delWord(String word) {
		String del = lphst.delete(word);
		if (del == null)
			return null;
		counter--;
		return del;
	}

	public boolean hasWord(String word) {
		return lphst.contains(word);
	}

	public Iterable<String> query(String query) {
		return lphst.query(query);
	}

	public int countWords() {
		return counter;
	}
}