
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

public class SearchDictionaryTree {

TreeSet<String> allWords;
		public SearchDictionaryTree(){
			allWords= new TreeSet<String>();
		}

		public void addWord(String word){
			allWords.add(word);
		}

		public String delWord(String word){
			allWords.remove(word);
			return word;
			}

		public boolean hasWord(String word){
			return allWords.contains(word);
			}


		public Iterable<String> query(String query){
			if(query=="*") return allWords;
			else if(query.charAt(query.length()-1)=='*'){
			String from=query.substring(0,query.length()-1);
			String to=from.substring(0,from.length()-1);
			char ch=from.charAt(from.length()-1);
			to+=++ch;
			return allWords.subSet(from, true, to, false);
			} else if(hasWord(query)) {
				Set<String> toReturn= new HashSet<String>();
				toReturn.add(query);
				return toReturn;
			} else return null;
		}

		public int countWords(){
			return allWords.size();
			
		} 
}
