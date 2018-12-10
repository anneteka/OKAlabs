
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;

public class SearchDictionary {

	Set<String> set;

	public SearchDictionary() {
		this.set = new TreeSet<>();
	}

	public void addWord(String word) {
		if (!hasWord(word))
			this.set.add(word);
	}

	public String delWord(String word) {
		if (this.set.remove(word))
			return word;
		return "There is no such a word in this dictionary";
	}

	public boolean hasWord(String word) {
		if (this.set.contains(word))
			return true;
		return false;
	}

	public Iterable<String> query(String query) {
		String squery = query.replaceAll("\\*", "\\\\w*");
		Pattern p = Pattern.compile(squery);
		ArrayList<String> list = new ArrayList<String>();
		for (String str : this.set) {
			if (p.matcher(str).matches())
				list.add(str);
		}

//		List<String> result = this.set.parallelStream().filter(s -> p.matcher(s).matches())
//				.collect(Collectors.toList());

		if (list.isEmpty())
			return null;
		else
			return list;
	}

	public int countWords() {
		return this.set.size();
	}
}
