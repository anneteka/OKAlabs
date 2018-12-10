package Chernova.week10.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StreamTokenizer;

public class DictionaryReader {

    public static void addWordsFromFile(SearchDictionary dictionary, String fileName) throws IOException {
        StreamTokenizer tokenizer = new StreamTokenizer(new FileInputStream(fileName));
        while (tokenizer.nextToken() != StreamTokenizer.TT_EOF) {
            dictionary.addWord(tokenizer.sval);
        }
    }
}
