import java.util.HashSet;
import java.util.Set;

public class SearchDictionary {
    private Set<String> dictionary;
    public SearchDictionary() {
        dictionary = new HashSet<String>();
    }
    public void addWord(String word) {
        dictionary.add(word);
    }
    public void delWord(String word) {
        dictionary.remove(word);
    }
    public boolean hasWord(String word) {
        return dictionary.contains(word);
    }
    private boolean containsPattern(String word, String pattern) {
        if(word.length() < pattern.length()) return false;
        String subString = word.substring(0,pattern.length());
        if(subString.hashCode() == pattern.hashCode() && subString.equals(pattern)) {
            return true;
        }
        return false;
    }
    public Iterable<String> query(String query) {
        Set<String> result = new HashSet<String>();
        if(query.charAt(query.length()-1) != '*') {
            if(hasWord(query)) {
                result.add(query);
            }
        } else {
            String pattern = query.substring(0,query.length()-1);
            for(String word: dictionary) {
                if(containsPattern(word,pattern)) result.add(word);
            }
        }
        return result;
    }
}
