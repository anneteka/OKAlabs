public class SearchDictionary {

	ST<Integer, String> st;
	
	public SearchDictionary(String source) {
		st = new ST<Integer, String>();
		
		// read in the data from csv file
		In in = new In(source);
		while (in.hasNextLine()) {
			String line = in.readLine();
			int key = line.hashCode();
			st.put(key, line);
		}
	}

	public void addWord(String word) {
		int key = word.hashCode();
		st.put(key, word);
	}

	public void delWord(String word) {
		int key = word.hashCode();
		st.delete(key);
	}

	public boolean hasWord(String word) {
		int key = word.hashCode();
		if (st.contains(key))
			return true;
		else
			return false;
	}

	public Iterable<String> query(String query) {
		Stack<String> words = new Stack<String>();
		int queryLength = query.length();
		if (query.charAt(queryLength-1) == '*') {
			String queryRaw = query.substring(0, queryLength-1);
			for(Integer key : st.keys() ){
				if (st.get(key).startsWith(queryRaw))
					words.push(st.get(key));
			}
		}
		else {
			int queryHash = query.hashCode();
			if (st.contains(queryHash))
				words.push(query);
		}
		
		return words;
	}

	public int countWords() {
		return st.size();
	}

	public static void main(String[] args) {

	}
}