import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SearchDictionary {
	private Map<String, Integer> dictionary = null;

	public SearchDictionary() {
		dictionary = new HashMap<String, Integer>();
	}

	public void addWord(String word) {
		if (!dictionary.containsKey(word))
			dictionary.put(word, 1);
		else {
			int val = dictionary.get(word);
			dictionary.put(word, val + 1);
		}
	}

	public String delWord(String word) {
		String s = word;
		int temp = 1;
		if (dictionary.containsKey(word)) {
			temp = dictionary.get(word);
			for (Iterator<Map.Entry<String, Integer>> it = dictionary.entrySet().iterator(); it.hasNext();) {
				Map.Entry<String, Integer> entry = it.next();
				if (entry.getKey().equals(word)) {
					it.remove();
					break;
				}
			}
		} else
			return "Dictionary doesn't have such word";

		return (s + " - " + temp);
	}

	public boolean hasWord(String word) {
		if (dictionary.containsKey(word))
			return true;
		return false;
	}

	public String getWord(String word) { // return 1 word
		if (dictionary.containsKey(word))
			return word + " - " + dictionary.get(word);
		return "Dictionary doesn't have such word";
	}

	public Iterable<String> query(String part) { // return all words which begin with String
		List<String> list = new LinkedList<String>();
		Iterator it = dictionary.entrySet().iterator();
		String temp = "";
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			temp = (String) pair.getKey();
			if(temp.length()<part.length())
				continue;
			temp = temp.substring(0, part.length());
			if (temp.equals(part))
				list.add(pair.getKey() + " - " + pair.getValue());
		}
		return list;
	}

	public Iterable<String> query() { // return collection
		return (Iterable<String>) dictionary;
	}

	public int countWords() {
		return dictionary.size();
	}

	public void readFromFile(String file) throws FileNotFoundException {
		Scanner reader = new Scanner(new FileReader(file));

		String inputLine = null;


		
			while (reader.hasNextLine()) {
				inputLine = reader.nextLine();
				String[] words = inputLine.split("\\s+");

				if (inputLine.equals(""))

					continue;

				for (String word : words) {

					word = word.replace(".", "");
					word = word.replace(",", "");

					if (dictionary.containsKey(word)) {
						int val = dictionary.get(word);
						dictionary.put(word, val + 1);
					} else
						dictionary.put(word, 1);
				}
			}
			reader.close();
			
		

	}

	public void printAllWords() {
		for (String key : dictionary.keySet())

			System.out.println(key + ": " + dictionary.get(key));

	}

}
