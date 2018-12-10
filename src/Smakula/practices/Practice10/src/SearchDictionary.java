import java.io.IOException;
import java.sql.Time;
import java.util.*;

public class SearchDictionary {


    private TreeSet<String> dictionary;

    public SearchDictionary() {
        dictionary = new TreeSet<>();
    }

    public void addWord(String word) {
        if (word == null) return;
        dictionary.add(word);
    }

    public String delWord(String word) {
        return dictionary.remove(word) ? word : null;
    }

    public boolean hasWord(String word) {
        return dictionary.contains(word);
    }

    public Iterable<String> query(String query) {
        String downLimit = dictionary.floor(query);
        String uppLimit = getUppLimit(query, downLimit);
        return dictionary.subSet(downLimit, uppLimit);
    }

    private String getUppLimit(String prefix, String currentWord) {
        if (currentWord.startsWith(prefix)) return getUppLimit(prefix, dictionary.higher(currentWord));
        return currentWord;
    }

    public int countWords() {
        return dictionary.size();
    }


    public static void main(String[] args) throws IOException {
        String fileName = "dictionary.txt";
        SearchDictionary searchDictionary = new SearchDictionary();
        DictionaryReader.addWordsFromFile(searchDictionary, fileName);
        System.out.println(searchDictionary.countWords());
//        Iterable<String> iterable = searchDictionary.query("auto");
//        iterable.forEach(System.out::println);
    }


}
