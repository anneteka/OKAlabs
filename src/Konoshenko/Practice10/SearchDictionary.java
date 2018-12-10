import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SearchDictionary {
	private Map<String,String> dictionary;
	private ArrayList<String> iterator;
	
	public SearchDictionary() {
		dictionary = new HashMap<String,String>();
		iterator = new ArrayList<String>();
	}
	
	public void addWord(String word, String value) {
		dictionary.put(word, value);
		iterator.add(word);
	}
	
	public String delWord(String word) {
		if(hasWord(word)) {
			dictionary.remove(word);
			return word;
		}
		else {
			return null;
		}
		
	}
	public boolean hasWord(String word) {
		return dictionary.containsKey(word);
	}
	
	public int countWords() {
		return dictionary.size();
	}
	
	public List<String> searchJocker(String wordStart){
		ArrayList<String> res = new ArrayList<>();
		for(int i = 0;i<dictionary.size(); i++) {
			if(iterator.get(i).startsWith(wordStart))
				res.add(iterator.get(i)+" - "+dictionary.get(iterator.get(i)));
		}
		return res;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		SearchDictionary sd = new SearchDictionary();
		sd.addWord("Peremoga", "Перемога");
		sd.addWord("Zrada", "Зрада");
		sd.addWord("Kyiv", "Київ");
		sd.addWord("Kaniv", "Канів");
		sd.addWord("KMA", "КМА");
		
		String s = sc.nextLine();
		if(s.contains("*"))
			s.replace("*", "");
		List<String> res = sd.searchJocker(s);
		for(int i = 0; i<res.size(); i++)
			System.out.println(res.get(i));
			
	}

}
