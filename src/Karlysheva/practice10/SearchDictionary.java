package practice10;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class SearchDictionary {


    private TreeSet<String> dictionary;

    private Comparator<String> ignoreCase = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            return o1.toLowerCase().compareTo(o2.toLowerCase());
        }
    };


    public SearchDictionary() {
        dictionary = new TreeSet<>(ignoreCase);
    }

    public SearchDictionary(File file) throws IOException {
        dictionary = new TreeSet<>(ignoreCase);
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream("input.txt"), StandardCharsets.UTF_8));
        reader.lines().forEach(s -> addWords(s.split(" ")));
    }

    public void addWord(String word) {
        dictionary.add(word);
    }

    public void addWords(String[] words) {
        for (String word : words)
            addWord(word);
    }

    public String delWord(String word) {
        dictionary.remove(word);
        return word;
    }

    public boolean hasWord(String word) {
        return dictionary.contains(word);
    }

    public Iterable<String> query(String query) {
        query = query.substring(0, query.length() - 1);
        char lastchar = query.charAt(query.length() - 1);
        String queryTo = query.substring(0, query.length() - 1) + (char) (lastchar + 1);
        return dictionary.subSet(query, queryTo);
    }

    public int countWords() {
        return dictionary.size();
    }
}